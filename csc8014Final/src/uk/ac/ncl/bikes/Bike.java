package uk.ac.ncl.bikes;

import uk.ac.ncl.Ids.BikeSerialNumber;
import uk.ac.ncl.utilitiesAndMain.Utilities;

/**
 * A regular Bike, aka a road bike. This class uses a getIntance method for there to be an instance of it.
 * Note: The reason I have a regular bike class instead of an abstract BikeFactory and then two subclasses (RoadBike
 * and ElectricBike) is because if I added a BikeFactory, the RoadBike class would only be a constructor. Additionally,
 * an electric bike "is-a" a regular road bike. Therefore, I found this approach of classes to be the most appropriate.
 */
public class Bike implements Cloneable, BikeInterface {
    private boolean rentalStatus;
    private BikeSerialNumber bikeSerialNumber;


    private static int roadBikeCount = 0;
    private static int electricBikeCount = 0;


    /**
     * Constructor for a regular road Bike. The rental status is already set to false, and a unique Bike Serial Number is
     * created. This constructor is package-private, so it cannot be used outside this class.
     */
    Bike() {
        this.bikeSerialNumber = BikeSerialNumber.getInstance();
        this.rentalStatus = false;
    }

    /**
     * @author Marcus Biel
     * @see <a href=https://www.youtube.com/watch?v=0_V-z6QcaWc>Original Work</a>
     *
     * Returns a shallow copy of this Bike.
     * Note: We only need to create a shallow copy of Bike because we do not need to worry about the possibility of
     * this.BikeSerialNumber being changed.
     * @return A shallow copy of this Bike.
     *  @throws CloneNotSupportedException if the class Bike does not implement the Cloneable interface.
     */
    public Bike clone() throws CloneNotSupportedException {
        Bike copyBike;
        try {
            if (this instanceof ElectricBike) {
                copyBike = (ElectricBike) super.clone();
            } else {
                copyBike = (Bike) super.clone();
            }
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
        return copyBike;
    }


    /**
     * Returns a Bike with the proper methods needed based on the type of bike we want to create.
     *
     * @param bikeType The type of bike we want (either electric or road).
     * @return a BikeInterface that has the proper methods needed. Based on the type of bike we want to create.
     * @throws IllegalArgumentException if <code>bikeType</code> is "Road" and the maximum amount of road bikes (Bikes)
     *                                  have been created.
     * @throws IllegalArgumentException if <code>bikeType</code> is "Electric" and the maximum amount of electric bikes
     *                                  have been created.
     * @throws NullPointerException     if<code>bikeType</code> is not "electric" or "road" (Note: capitalization does not
     *                                  matter
     */
    public static Bike getInstance(String bikeType) {
        Bike bikeToGet = null;

        //avoids Strings not being equal due to capitalization
        final String officialBikeType = Utilities.checkBikeType(bikeType);
        if (officialBikeType.equals(ROAD_BIKE)) {
            if (roadBikeCount < POSSIBLE_ROAD_BIKES) {
                bikeToGet = new Bike();
                roadBikeCount++;
            } else {
                throw new IllegalArgumentException("Maximum amount of road bikes have been created");
            }

        } else if (officialBikeType.equals(ELECTRIC_BIKE)) {
            if (electricBikeCount < POSSIBLE_ELECTRIC_BIKES) {
                bikeToGet = new ElectricBike();
                electricBikeCount++;
            } else {
                throw new IllegalArgumentException("Maximum amount of electric bikes have been created");
            }
        }
        return bikeToGet;
    }


    /**
     * Returns the BikeSerialNumber of this Bike
     *
     * @return the BikeSerialNumber of this Bike.
     */
    public String getSerialNumber() {
        final String serialNumber = this.bikeSerialNumber.getId();
        return serialNumber;
    }

    /**
     * Returns the rentalStatus of this Bike.
     *
     * @return A boolean that represents if the bike is currently being rented.
     */
    public boolean getRentalStatus() {
        return rentalStatus;
    }

    /**
     * Changes the renting status of the Bike to true, saying the Bike is now being rented.
     */
    public void isRenting() {
        rentalStatus = true;
    }

    /**
     * Changes the renting status of the Bike to false, saying the Bike is not being rented.
     */
    public void isNoLongerRenting() {
        rentalStatus = false;
    }

    /**
     * Returns a String representation of the Bike. The String Representation is used for testing purposes. It returns a
     * string with the BikeType, the Bikes serial number, and if the Bikes rental status.
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String type;
        if (this instanceof ElectricBike) {
            type = ELECTRIC_BIKE;
        } else {
            type = ROAD_BIKE;
        }
        return String.format("BikeType: %s\n" +
                "BikeSerialNumber: %s\n" +
                "RentalStatus: %s ", type, getSerialNumber(), getRentalStatus());
    }
}
