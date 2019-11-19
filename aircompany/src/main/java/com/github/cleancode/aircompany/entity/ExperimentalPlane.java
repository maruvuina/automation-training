package com.github.cleancode.aircompany.entity;

import java.util.Objects;

public class ExperimentalPlane extends Plane {
    private ExperimentalPlaneType experimentalPlaneType;
    private ClassificationPlaneLevel classificationPlaneLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalPlaneType experimentalPlaneType, ClassificationPlaneLevel classificationPlaneLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalPlaneType = experimentalPlaneType;
        this.classificationPlaneLevel = classificationPlaneLevel;
    }

    public ClassificationPlaneLevel getClassificationPlaneLevel() {
        return classificationPlaneLevel;
    }

    public void setClassificationPlaneLevel(ClassificationPlaneLevel classificationPlaneLevel) {
        this.classificationPlaneLevel = classificationPlaneLevel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExperimentalPlane{");
        sb.append("experimentalPlaneType=").append(experimentalPlaneType);
        sb.append(", classificationPlaneLevel=").append(classificationPlaneLevel);
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
        ExperimentalPlane that = (ExperimentalPlane) o;
        return experimentalPlaneType == that.experimentalPlaneType &&
                getClassificationPlaneLevel() == that.getClassificationPlaneLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalPlaneType, getClassificationPlaneLevel());
    }
}
