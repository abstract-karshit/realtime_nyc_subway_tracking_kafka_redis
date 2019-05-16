package com.realtime.nyc.subway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StopTimeUpdate {

    private Arrival arrival;

    @JsonProperty("nyct_stop_time_update")
    private NyctStopTimeUpdate nyctStopTimeUpdate;

    @JsonProperty("stop_id")
    private String stopId;
}
