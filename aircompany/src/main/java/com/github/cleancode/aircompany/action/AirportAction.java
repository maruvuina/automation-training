package com.github.cleancode.aircompany.action;

import com.github.cleancode.aircompany.airport.Airport;
import com.github.cleancode.aircompany.entity.Plane;

public class AirportAction {
    private Airport airport;

    public AirportAction(Airport airport) {
        this.airport = airport;
    }

    public Airport sortPlanesByMaxFightDistance() {
        airport.getPlanes().sort(new Plane.MaxFlightDistanceComparator<>());
        return airport;
    }

    public Airport sortPlanesByMaxSpeed() {
        airport.getPlanes().sort(new Plane.MaxSpeedComparator<>());
        return airport;
    }

    public Airport sortPlanesByMaxLoadCapacity() {
        airport.getPlanes().sort(new Plane.MaxLoadCapacityComparator<>());
        return airport;
    }
}
