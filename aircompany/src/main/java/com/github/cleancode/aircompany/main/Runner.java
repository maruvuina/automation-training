package com.github.cleancode.aircompany.main;

import com.github.cleancode.aircompany.airport.Airport;
import com.github.cleancode.aircompany.action.AirportAction;
import com.github.cleancode.aircompany.action.PlanesCreator;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        Airport airport = new Airport(new PlanesCreator().getPlanes());
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassengerPlanes());
        AirportAction militaryAirportAction = new AirportAction(militaryAirport);
        AirportAction passengerAirportAction = new AirportAction(passengerAirport);
        logger.log(Level.INFO, "Military airport sorted by max distance: " +
                militaryAirportAction.sortPlanesByMaxFightDistance()
                        .toString());
        logger.log(Level.INFO, "Passenger airport sorted by max speed: " +
                passengerAirportAction.sortPlanesByMaxSpeed()
                        .toString());
        logger.log(Level.INFO, "Plane with max passenger capacity: " +
                passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
    }
}
