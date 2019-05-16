package com.realtime.nyc.subway.service;

public interface CacheService {

    /**
     * Get value from cache provider for the key or empty if no key found
     *
     * @param key String key
     * @return String value
     */
    String getValue(String key);

    /**
     * Set value in cache provider for the corresponding key
     *
     * @param key String key
     * @param value Value
     */
    void setValue(String key, String value);
}
