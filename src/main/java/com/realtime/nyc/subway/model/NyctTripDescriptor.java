package com.realtime.nyc.subway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NyctTripDescriptor {

    @JsonProperty("is_assigned")
    private String isAssigned;

    @JsonProperty("train_id")
    private String trainId;

    @JsonProperty("direction")
    private String direction;

}
