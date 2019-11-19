package com.github.cleancode.aircompany;

import com.github.cleancode.aircompany.action.AirportAction;
import com.github.cleancode.aircompany.airport.Airport;
import com.github.cleancode.aircompany.builder.DirectorPlane;
import com.github.cleancode.aircompany.entity.*;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class AirportTest {
    private static final Logger logger = LogManager.getLogger(AirportTest.class);
    private static List<Plane> planes;

    private PassengerPlane planeWithMaxPassengerCapacity;

    private Airport airport;

    @BeforeMethod
    public void initParameneters() {
        planes = Arrays.asList(
                DirectorPlane.constructPassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                DirectorPlane.constructPassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
                DirectorPlane.constructPassengerPlane("Boeing-747", 980, 16100, 70500, 242),
                DirectorPlane.constructPassengerPlane("Airbus A320", 930, 11800, 65500, 188),
                DirectorPlane.constructPassengerPlane("Airbus A330", 990, 14800, 80500, 222),
                DirectorPlane.constructPassengerPlane("Embraer 190", 870, 8100, 30800, 64),
                DirectorPlane.constructPassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
                DirectorPlane.constructPassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
                DirectorPlane.constructMilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryPlaneType.BOMBER),
                DirectorPlane.constructMilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryPlaneType.BOMBER),
                DirectorPlane.constructMilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryPlaneType.BOMBER),
                DirectorPlane.constructMilitaryPlane("F-15", 1500, 12000, 10000, MilitaryPlaneType.FIGHTER),
                DirectorPlane.constructMilitaryPlane("F-22", 1550, 13000, 11000, MilitaryPlaneType.FIGHTER),
                DirectorPlane.constructMilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryPlaneType.TRANSPORT),
                new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalPlaneType.HIGH_ALTITUDE, ClassificationPlaneLevel.SECRET),
                new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalPlaneType.VTOL, ClassificationPlaneLevel.TOP_SECRET)
        );
        airport = new Airport(planes);
        planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
    }

    @Test
    public void atLeastOneTransportMilitaryPlaneAtAirport() {
        assertTrue(airport.getTransportMilitaryPlanes()
                .stream()
                .allMatch(
                        militaryPlane -> MilitaryPlaneType.TRANSPORT.equals(militaryPlane.getMilitaryPlaneType())));
    }

    @Test
    public void isAirportHasPassengerPlaneWithMaxCapacity() {
        logger.log(Level.INFO, "Test isAirportHasPassengerPlaneWithMaxCapacity started.");
        assertEquals(planeWithMaxPassengerCapacity, new Airport(planes).getPassengerPlaneWithMaxPassengersCapacity());
    }

    @Test
    public void isPlanesSortedByLoadCapacity() {
        airport.getPlanes().sort(new Plane.MaxLoadCapacityComparator<>());
        Assert.assertEquals(airport.getPlanes(), new AirportAction(airport).sortPlanesByMaxLoadCapacity().getPlanes());
    }

    @Test
    public void airportHasAtLeastOneBomberInMilitaryPlanes() {
        assertTrue(airport.getBomberMilitaryPlanes()
                .stream()
                .allMatch(
                        bomberPlane -> MilitaryPlaneType.BOMBER.equals(bomberPlane.getMilitaryPlaneType())));
    }

    @Test
    public void isExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        assertFalse(airport.getExperimentalPlanes()
                .stream()
                .allMatch(
                        experimentalPlane -> ClassificationPlaneLevel.UNCLASSIFIED.equals(experimentalPlane.getClassificationPlaneLevel())));
    }
}