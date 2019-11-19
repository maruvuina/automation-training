package com.github.cleancode.aircompany.builder;

import com.github.cleancode.aircompany.entity.Plane;

public interface PlaneBuilder<T extends Plane> {
    T getPlane();
    void buildModel(String model);
    void buildMaxSpeed(int maxSpeed);
    void buildMaxFlightDistance(int maxFlightDistance);
    void buildMaxLoadCapacity(int maxLoadCapacity);
}
