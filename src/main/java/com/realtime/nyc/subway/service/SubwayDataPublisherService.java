package com.realtime.nyc.subway.service;

public interface SubwayDataPublisherService {

    /**
     * Method to fetch data from realtime feed and publish the same to Kafka
     */
    void publishTripUpdates() throws Exception;
}
