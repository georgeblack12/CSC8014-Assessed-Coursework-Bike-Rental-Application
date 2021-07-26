package uk.ac.ncl.Ids;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Id for a customer. "The first component is made up of the initial of the first name of the customer followed by
 * the initial of the last name of the customer. The second component is the year the record was issued. The third
 * component is an arbitrary  serial number." The class uses a factory to make sure each CustomerId is unique. This
 * class is a subclass of AbstractId.
 */
public final class CustomerNumber extends AbstractId {


    //The ArrayList that contains all the unique CustomerIds. Used to make sure that an CustomerId created has not
    //already been created before.
    private final static ArrayList<String> customerIds = new ArrayList<>();


    /**
     * Uses the AbstractId Constructor that contains two parameters. This allows us to call the proper the
     * createIdStringPart method needed to create the appropriate String part of a CustomerId. This Constructor is private
     * , so it cannot be used outside of the class.
     *
     * @param Initials The Initials of the Customer.
     * @param year     The year Customer began having a record.
     */
    private CustomerNumber(String Initials, int year) {
        super(Initials, year);

        //used for testing.
        // System.out.println(customerIds);
    }


    /**
     * Returns a CustomerId that is unique with the proper format.
     *
     * @param initials The Initials of the Customer
     * @param year     The year the Customer began having a record.
     * @return A a CustomerId that is unique with the proper format.
     * @throws IllegalArgumentException if <code>initials</code> is not two letters.
     * @throws IllegalArgumentException if <code>year</code> is not a 4 digits.
     */
    public static CustomerNumber getInstance(String initials, int year) {

        //makes sure initials are valid. Not really necessary since this is checked with name,
        //but left here as a safety net.
        checkInitials(initials);

        int listLocation = 0;
        Id idToCheck;

        do {
            idToCheck = new CustomerNumber(initials, year);
            //System.out.println(idToCheck.getId()); Here for testing

            listLocation = locationInList(getList(customerIds), idToCheck.getId());
        } while (listLocation != -1); //Keeps recreating a completeIdString until the one created is unique.

        //once the completeIdString is unique add it to the ArrayList of unique Ids and return the Id.
        customerIds.add(idToCheck.getId());
        return (CustomerNumber) idToCheck;
    }


    /**
     * Checks to make sure that the value entered is only made up of alphabetical letters and is only two letters.
     * Note: We do not need to worry about it being uppercase as this was done in the proper createIdString method
     * located in AbstractId. This method will be necessary for the factory.
     *
     * @param initialsToCheck The String to be checked to see if it meets the requirements of only being
     *                        alphabetical letters and is only two letters.
     * @throws IllegalArgumentException if <code>initials</code> is not two letters.
     */
    private static void checkInitials(final String initialsToCheck) {
        final String initToCaps = initialsToCheck.toUpperCase();
        if (!(initToCaps.matches("[A-Z]+"))) {
            throw new IllegalArgumentException("Initials Must only be letters");
        }
        if (initToCaps.length() != 2) {
            throw new IllegalArgumentException("The initials must be two letters");
        }
    }


    /**
     * Creates a 1-2 digit random to potentially be added to the number part of the CustomerId attribute completeIdString.
     * Note: Dr.Riddle stated that this part of the Id would be fine if it only contained one digit for
     * some instances.
     *
     * @return a random 1-2 digit number to potentially be added to the number part of the completeIdString.
     */
    public int createIdNumberPart() {
        Random random = new Random();
        int rand = random.nextInt(100); //can be changed if numbers of Id's needed is increased.
        return rand;
    }

}
