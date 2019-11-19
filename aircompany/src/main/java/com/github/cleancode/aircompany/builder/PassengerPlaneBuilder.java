package com.github.cleancode.aircompany.builder;

import com.github.cleancode.aircompany.entity.PassengerPlane;

public class PassengerPlaneBuilder implements PlaneBuilder<PassengerPlane> {
    private PassengerPlane passengerPlane = new PassengerPlane();

    @Override
    public PassengerPlane getPlane() {
        return passengerPlane;
    }

    @Override
    public void buildModel(String model) {
        passengerPlane.setModel(model);
    }

    @Override
    public void buildMaxSpeed(int maxSpeed) {
        passengerPlane.setMaxSpeed(maxSpeed);
    }

    @Override
    public void buildMaxFlightDistance(int maxFlightDistance) {
        passengerPlane.setMaxFlightDistance(maxFlightDistance);
    }

    @Override
    public void buildMaxLoadCapacity(int maxLoadCapacity) {
        passengerPlane.setMaxLoadCapacity(maxLoadCapacity);
    }

    public void buildPassengersCapacity(int passengersCapacity) {
        passengerPlane.setPassengersCapacity(passengersCapacity);
    }
}
