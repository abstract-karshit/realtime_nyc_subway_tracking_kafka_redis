package com.realtime.nyc.subway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realtime.nyc.subway.model.Arrival;
import com.realtime.nyc.subway.model.StopTimeUpdate;
import com.realtime.nyc.subway.model.Trip;
import com.realtime.nyc.subway.model.TripUpdate;
import com.realtime.nyc.subway.utils.FileUtils;
import java.time.Duration;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubwayDataProcessingServiceImpl implements SubwayDataProcessingService {

    private final ObjectMapper objectMapper;

    private final CacheService cacheService;

    private static HashMap<String, String> stopIdNameMap = new HashMap<>();

    @PostConstruct
    void init() throws Exception {
        Iterable<CSVRecord> records = FileUtils.readFileAsCsv("static/mta_stations.csv");
        for (CSVRecord record : records) {
            stopIdNameMap.put(record.get("GTFS Stop ID"), record.get("Stop Name"));
        }
    }

    @Override
    @KafkaListener(topics = "${kafka.events.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void processStream(String message) throws Exception {
        log.debug("Message Consumed -> " + message);
        TripUpdate tripUpdate = objectMapper.readValue(message, TripUpdate.class);
        Trip trip = tripUpdate.getTrip();

        if (ObjectUtils.isEmpty(trip)) {
            return;
        }
        String routeId = trip.getRouteId();
        StopTimeUpdate[] stopTimeUpdates = tripUpdate.getStopTimeUpdates();

        if (ObjectUtils.isEmpty(stopTimeUpdates)) {
            return;
        }

        for (StopTimeUpdate update : stopTimeUpdates) {
            String stopIdString = update.getStopId();
            Arrival arrival = update.getArrival();
            if (ObjectUtils.isEmpty(arrival) || StringUtils.isEmpty(stopIdString)) {
                return;
            }

            String stopId = stopIdString.substring(0, 3);
            String direction = stopIdString.substring(3);
            Long newArrivalTime = Long.valueOf(arrival.getTime());
            String cacheKey = generateKey(routeId, stopId, direction);
            String nextArrivalTime = cacheService.getValue(cacheKey);
            Long currentTime = System.currentTimeMillis() / 1000;

            if ((newArrivalTime > currentTime) && (StringUtils.isEmpty(nextArrivalTime)
                || newArrivalTime < Long.valueOf(nextArrivalTime))) {
                cacheService.setValue(cacheKey, String.valueOf(newArrivalTime));
                long minutes = Duration.ofSeconds(newArrivalTime - currentTime).toMinutes();
                log.info("Next {} bound {} train will arrive at station {} in {} minutes",
                    direction, routeId, stopIdNameMap.get(stopId), minutes);
            }
        }
    }

    private String generateKey(String routeId, String stopId, String direction) {
        return String.join("|", routeId, stopId, direction);
    }
}
