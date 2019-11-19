package com.github.cleancode.aircompany.builder;

import com.github.cleancode.aircompany.entity.MilitaryPlane;
import com.github.cleancode.aircompany.entity.MilitaryPlaneType;
import com.github.cleancode.aircompany.entity.PassengerPlane;

public class DirectorPlane {
    public static PassengerPlane constructPassengerPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, int passengersCapacity) {
        PassengerPlaneBuilder builder = new PassengerPlaneBuilder();
        builder.buildModel(model);
        builder.buildMaxSpeed(maxSpeed);
        builder.buildMaxFlightDistance(maxFlightDistance);
        builder.buildMaxLoadCapacity(maxLoadCapacity);
        builder.buildPassengersCapacity(passengersCapacity);
        return builder.getPlane();
    }

    public static MilitaryPlane constructMilitaryPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, MilitaryPlaneType militaryPlaneType) {
        MilitaryPlaneBuilder builder = new MilitaryPlaneBuilder();
        builder.buildModel(model);
        builder.buildMaxSpeed(maxSpeed);
        builder.buildMaxFlightDistance(maxFlightDistance);
        builder.buildMaxLoadCapacity(maxLoadCapacity);
        builder.buildMilitaryPlaneType(militaryPlaneType);
        return builder.getPlane();
    }
}
