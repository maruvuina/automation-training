package com.github.cleancode.aircompany.entity;

import java.util.Comparator;

public class Plane {
    private String model;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    public Plane() {}

    public Plane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxFlightDistance(int maxFlightDistance) {
        this.maxFlightDistance = maxFlightDistance;
    }

    public void setMaxLoadCapacity(int maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractPlane{");
        sb.append("model='").append(model).append('\'');
        sb.append(", maxSpeed=").append(maxSpeed);
        sb.append(", maxFlightDistance=").append(maxFlightDistance);
        sb.append(", maxLoadCapacity=").append(maxLoadCapacity);
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
        Plane abstractPlane = (Plane) o;
        if (getMaxSpeed() != abstractPlane.getMaxSpeed()) {
            return false;
        }
        if (getMaxFlightDistance() != abstractPlane.getMaxFlightDistance()) {
            return false;
        }
        if (getMaxLoadCapacity() != abstractPlane.getMaxLoadCapacity()) {
            return false;
        }
        return getModel() != null ? getModel().equals(abstractPlane.getModel()) : abstractPlane.getModel() == null;
    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + getMaxSpeed();
        result = 31 * result + getMaxFlightDistance();
        result = 31 * result + getMaxLoadCapacity();
        return result;
    }

    public static class MaxFlightDistanceComparator<T extends Plane> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
        }
    }

    public static class MaxSpeedComparator<T extends Plane> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.getMaxSpeed() - o2.getMaxSpeed();
        }
    }

    public static class MaxLoadCapacityComparator<T extends Plane> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity();
        }
    }
}
