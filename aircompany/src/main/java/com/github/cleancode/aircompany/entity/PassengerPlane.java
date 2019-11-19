package com.github.cleancode.aircompany.entity;

import java.util.Objects;

public class PassengerPlane extends Plane {
    private int passengersCapacity;

    public PassengerPlane() {}

    public PassengerPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, int passengersCapacity) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.passengersCapacity = passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PassengerPlane{");
        sb.append("passengersCapacity=").append(passengersCapacity);
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
        PassengerPlane that = (PassengerPlane) o;
        return getPassengersCapacity() == that.getPassengersCapacity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPassengersCapacity());
    }
}
