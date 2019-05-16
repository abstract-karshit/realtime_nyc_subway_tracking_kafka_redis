package com.realtime.nyc.subway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trip {

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("route_id")
    private String routeId;

    @JsonProperty("nyct_trip_descriptor")
    private NyctTripDescriptor nyctTripDescriptor;

    @JsonProperty("start_date")
    private String startDate;
}
