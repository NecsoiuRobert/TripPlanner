package com.TripPlanner.Wrapper;

import com.TripPlanner.Model.Sport;
import com.TripPlanner.Model.Spot;
import lombok.Data;

import java.util.List;

@Data
public class WrapperBonus implements Comparable {
    private Spot spot;
    private List<Sport> sports;
    private Double cost;

    public WrapperBonus(Spot spot, List<Sport> sports, Double cost) {
        this.spot = spot;
        this.sports = sports;
        this.cost = cost;
    }

    @Override
    public int compareTo(Object o) {
        return this.cost.compareTo(((WrapperBonus)o).getCost());
    }
}
