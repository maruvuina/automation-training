package com.github.cleancode.aircompany.builder;

import com.github.cleancode.aircompany.entity.MilitaryPlane;
import com.github.cleancode.aircompany.entity.MilitaryPlaneType;

public class MilitaryPlaneBuilder implements PlaneBuilder<MilitaryPlane> {
    private MilitaryPlane militaryPlane = new MilitaryPlane();

    @Override
    public MilitaryPlane getPlane() {
        return militaryPlane;
    }

    @Override
    public void buildModel(String model) {
        militaryPlane.setModel(model);
    }

    @Override
    public void buildMaxSpeed(int maxSpeed) {
        militaryPlane.setMaxSpeed(maxSpeed);
    }

    @Override
    public void buildMaxFlightDistance(int maxFlightDistance) {
        militaryPlane.setMaxFlightDistance(maxFlightDistance);
    }

    @Override
    public void buildMaxLoadCapacity(int maxLoadCapacity) {
        militaryPlane.setMaxLoadCapacity(maxLoadCapacity);
    }

    public void buildMilitaryPlaneType(MilitaryPlaneType militaryPlaneType) {
        militaryPlane.setMilitaryPlaneType(militaryPlaneType);
    }
}
