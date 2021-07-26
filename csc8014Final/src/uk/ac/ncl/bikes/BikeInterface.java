package uk.ac.ncl.bikes;

import uk.ac.ncl.utilitiesAndMain.Utilities;

/**
 * An interface for the Bike Class. This is mostly here to check that I implement the required methods.
 *
 */
public interface BikeInterface {

     String ROAD_BIKE = Utilities.capitalizeString("Road");
     String ELECTRIC_BIKE = Utilities.capitalizeString("Electric");
     int POSSIBLE_ROAD_BIKES = 50; //changed to 3 for testing
     int POSSIBLE_ELECTRIC_BIKES = 10; //changed to 1 for testing

    /**
     * Returns the BikeSerialNumber of this Bike
     *
     * @return the BikeSerialNumber of this Bike.
     */
    public String getSerialNumber();

    /**
     * Returns the rentalStatus of this Bike.
     *
     * @return A boolean that represents if the bike is currently being rented.
     */
    public boolean getRentalStatus();

    /**
     * Changes the renting status of the Bike to true, saying the Bike is now being rented.
     */
    public void isRenting();


    /**
     * Changes the renting status of the Bike to false, saying the Bike is not being rented.
     */
    public void isNoLongerRenting();





}
