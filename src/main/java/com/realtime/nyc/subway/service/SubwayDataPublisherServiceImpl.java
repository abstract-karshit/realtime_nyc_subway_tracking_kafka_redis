package com.realtime.nyc.subway.service;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.googlecode.protobuf.format.JsonFormat;
import com.realtime.nyc.subway.client.MTAWebClient;
import com.realtime.nyc.subway.handler.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubwayDataPublisherServiceImpl implements SubwayDataPublisherService {

    private final MTAWebClient mtaWebClient;

    private final Producer producer;

    private final JsonFormat jsonFormat;

    @Override
    public void publishTripUpdates() throws Exception {
        log.debug("Initiating feed from MTA Api");
        FeedMessage feed = mtaWebClient.getTripUpdatesFromMTA();
        log.debug("Feed from MTA received");

        for (FeedEntity entity : feed.getEntityList()) {
            if (entity.hasTripUpdate()) {
                producer.send(jsonFormat.printToString(entity.getTripUpdate()));
            }
        }
    }
}
