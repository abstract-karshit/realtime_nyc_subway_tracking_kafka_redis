package com.realtime.nyc.subway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NyctStopTimeUpdate {

    @JsonProperty("actual_track")
    private String actualTrack;

    @JsonProperty("scheduled_track")
    private String scheduledTrack;
}