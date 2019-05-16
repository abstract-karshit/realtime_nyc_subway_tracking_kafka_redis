package com.realtime.nyc.subway.service;

public interface SubwayDataProcessingService {

    /**
     * Process stream received from queue
     * @param message
     * @throws Exception
     */
    void processStream(String message) throws Exception;
}
