package uk.ac.ncl.Ids;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Id for a bike. "A bicycle serial number has two components - two letters followed
 * by a three-digit number(...)" The class uses a factory to make sure each bikeId is unique. This class is a subclass of
 * AbstractId.
 */
public final class BikeSerialNumber extends AbstractId {
    private final static ArrayList<String> bikeIds = new ArrayList<>();

    /**
     * The constructor to be used when we are creating a bikeId. Uses the createIdStringPart method needed to make
     * the proper String part of a BikeId. This is a private constructor, so it cannot be directly instantiated
     * by clients outside of this class.
     */
    private BikeSerialNumber() {
        super();

        //for testing
        //System.out.println(bikeIds);
    }

    /**
     * Returns a BikeId that is unique with the proper format.
     *
     * @return a BikeId that is unique with the proper format.
     */
    public static BikeSerialNumber getInstance() {
        int listLocation = 0;
        Id idToCheck;
        do {
            idToCheck = new BikeSerialNumber();
            //System.out.println(idToCheck.getId()); for debugging and testing

            listLocation = locationInList(getList(bikeIds), idToCheck.getId());
        } while (listLocation != -1); //Keeps recreating a completeIdString until the one created is unique.

        //once the completeIdString is unique add it to the ArrayList of unique bikeIds and return the bikeId.
        bikeIds.add(idToCheck.getId());
        return (BikeSerialNumber) idToCheck;

    }


    /**
     * Creates a random three-digit number to potentially be added to the number part of the BikeId attribute
     * completeStringID.
     *
     * @return a random three digit number to potentially be added to the number part of the BikeId attribute
     * completeStringID.
     */
    public int createIdNumberPart() {
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(900) + 100; //can be changed if more Id's are somehow needed.
        return randomNumber;
    }

    /**
     * Method that returns the two-letter part of a BikeSerialNumber. This method is never used and is only here to do
     * the specifications saying, "You must provide access to each component".
     *
     * @return the two-letter part of a BikeSerialNumber.
     */
    public String returnLettersPart() {
        return getId().substring(0, 2);
    }

    /**
     * Method that returns the three number part of a BikeSerialNumber. This method is never used and is only here to do
     * the specifications saying, "You must provide access to each component".
     *
     * @return The three-number part of a BikeSerialNumber.
     */
    public String returnNumberPart() {
        return getId().substring(2, getId().length());
    }
}
