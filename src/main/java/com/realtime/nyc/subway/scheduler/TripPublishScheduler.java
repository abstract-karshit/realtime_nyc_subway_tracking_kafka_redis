package com.realtime.nyc.subway.scheduler;

import com.realtime.nyc.subway.service.SubwayDataPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripPublishScheduler {

    private final SubwayDataPublisherService subwayDataPublisherService;

    @Scheduled(fixedDelay = 30000)
    public void fetchDataAndPublish() throws Exception {
        subwayDataPublisherService.publishTripUpdates();
    }
}
