package com.github.cleancode.aircompany.entity;

import java.util.Objects;

public class MilitaryPlane extends Plane {
    private MilitaryPlaneType militaryPlaneType;

    public MilitaryPlane() {}

    public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, MilitaryPlaneType militaryPlaneType) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.militaryPlaneType = militaryPlaneType;
    }

    public void setMilitaryPlaneType(MilitaryPlaneType militaryPlaneType) {
        this.militaryPlaneType = militaryPlaneType;
    }

    public MilitaryPlaneType getMilitaryPlaneType() {
        return militaryPlaneType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MilitaryPlane{");
        sb.append("militaryPlaneType=").append(militaryPlaneType);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MilitaryPlane that = (MilitaryPlane) o;
        return getMilitaryPlaneType() == that.getMilitaryPlaneType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMilitaryPlaneType());
    }
}
