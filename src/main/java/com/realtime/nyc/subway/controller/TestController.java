package com.realtime.nyc.subway.controller;

import com.realtime.nyc.subway.client.MTAWebClient;
import com.realtime.nyc.subway.service.SubwayDataPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * THIS CLASS IS ONLY FOR TESTING THE LOGIC, NOT TO BE USED DIRECTLY IN PRODUCTION
 */
@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
public class TestController {

    private final MTAWebClient mtaWebClient;

    private final SubwayDataPublisherService subwayDataPublisherService;

    @GetMapping(value = "/client")
    void getMtaData() throws Exception {
        System.out.println(mtaWebClient.getTripUpdatesFromMTA());
    }

    @GetMapping(value = "/produce")
    void produceKafka() throws Exception {
        subwayDataPublisherService.publishTripUpdates();
    }
}
