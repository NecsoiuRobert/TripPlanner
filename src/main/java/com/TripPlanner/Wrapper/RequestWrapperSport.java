package com.TripPlanner.Wrapper;

import com.TripPlanner.Model.Sport;
import com.TripPlanner.Model.Spot;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestWrapperSport {
    @NotBlank
    Spot spot;
    @NotBlank
    Sport sport;

    public RequestWrapperSport(@JsonProperty("spot") Spot spot,@JsonProperty("sport") Sport sport) {
        this.spot = spot;
        this.sport = sport;
    }

    public Spot getSpot() {
        return spot;
    }

    public Sport getSport() {
        return sport;
    }
}
