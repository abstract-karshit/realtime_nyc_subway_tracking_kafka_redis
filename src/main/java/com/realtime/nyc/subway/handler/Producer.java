package com.realtime.nyc.subway.handler;

public interface Producer {

    /**
     * Publish message to Queue
     * @param message String message
     */
    void send(String message);
}
