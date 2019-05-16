package com.realtime.nyc.subway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripUpdate {

    private Trip trip;

    @JsonProperty("stop_time_update")
    private StopTimeUpdate[] stopTimeUpdates;
}