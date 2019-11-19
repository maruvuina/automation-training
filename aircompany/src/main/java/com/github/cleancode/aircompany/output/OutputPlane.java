package com.github.cleancode.aircompany.output;

import com.github.cleancode.aircompany.entity.Plane;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class OutputPlane {
    private static final Logger logger = LogManager.getLogger(OutputPlane.class);

    public void showPlanes(List<Plane> planes) {
        for (Plane plane : planes) {
            logger.log(Level.INFO, plane);
        }
    }
}
