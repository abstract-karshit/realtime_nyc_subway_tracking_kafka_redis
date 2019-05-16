package com.realtime.nyc.subway.client;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.realtime.nyc.subway.model.TripUpdate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class MTAWebClient {

    @Value("${mta.base.api.uri}")
    private String mtaBaseUri;

    @Value("${mta.nyc.subway.feed.id}")
    private String subwayFeedId;

    @Value("${mta.api.key}")
    private String mtaApiKey;

    private final RestTemplate restTemplate;

    /**
     * Get data in GTFS format from MTA website
     * @return protobuf FeedMessage
     * @throws Exception
     */
    public FeedMessage getTripUpdatesFromMTA() throws Exception {
        List<TripUpdate> tripUpdates = new ArrayList<>();
        URI uri = UriComponentsBuilder.fromUriString(mtaBaseUri)
            .queryParam("key", mtaApiKey)
            .queryParam("feed_id", subwayFeedId)
            .build().toUri();

        ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);
        return FeedMessage.parseFrom(response.getBody());
    }
}
