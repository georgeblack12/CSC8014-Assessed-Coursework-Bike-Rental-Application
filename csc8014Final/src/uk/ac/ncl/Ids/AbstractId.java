package uk.ac.ncl.Ids;

import java.util.ArrayList;

import java.util.Random;

/**
 * The abstract class of Id to be used CustomerId and AbstractId. The abstract class defines all the methods from the
 * interface, Id, except for createIdNumberPart. The class contains two constructors and two createIdStringPart methods.
 * The reason for this is to get the proper String needed for an Id based on the type of Id it is (either
 * CustomerId or BikeId). To be more specific, the two constructors and createIdStringPart methods are needed in order
 * to allow us to use the Factory method correctly in the two subclasses. One could argue that the factory
 * should have been created in AbstractId, but I believe that made this class and the factory method *way* too cluttered.
 * The code may now have some extra repetition, but I now find it so much easier to use and read. Additionally, one could
 * say that there should not be two constructors and createIdStringPart methods, but it is necessary for us
 * to use the super constructors correctly in our subclasses. Additionally, one could say that there should not be
 * an Abstract class. Still, then the other methods defined here (toString,getId,getList, and locationInList) would need to
 * be defined multiple times. I believe this was the most logical place to define the methods that are listed in the
 * previous sentence. In conclusion, I made this approach to complete the course requirement of using a factory in the
 * most uncomplicated and easy-to-read way.
 */
abstract class AbstractId implements uk.ac.ncl.Ids.Id {
    private final String completeIdString;


    /**
     * The constructor to be used when we are creating a bikeId. Uses the createIdStringPart method needed to make
     * the proper String part of a BikeId. This is a package-private constructor, so it cannot be directly instantiated
     * by clients outside of this class.
     */
    AbstractId() {
        this.completeIdString = createIdStringPart() + createIdNumberPart();
    }

    /**
     * The constructor to be used when we are creating a customerId. Uses the createIdStringPart method needed to make
     * the proper String part of a CustomerId.This is a package-private constructor, so it cannot be directly instantiated
     * by clients outside of this class.
     *
     * @param initials The Initials of the Customer.
     * @param year     The year Customer began having a record.
     */
    AbstractId(String initials, int year) {
        this.completeIdString = createIdStringPart(initials, year)
                + createIdNumberPart();
    }


    /**
     * @return The entire Id of the object.
     */
    public String getId() {
        return completeIdString;
    }


    /**
     * Returns a copy of an ArrayList (made up of strings) given in as the method's parameter. The method will be used in the factory
     * in CustomerId and BikeId.
     *
     * @param theList The ArrayList (containing Strings) that will have a copy of itself created and have its copy returned.
     * @return A copy of the ArrayList(made up of Strings) given as the method's parameter.
     */
    public static ArrayList<String> getList(ArrayList<String> theList) {
        return new ArrayList<>(theList);
    }


    /**
     * Returns the index of a String inside of an ArrayList of Strings (listToCheck). If the String is not in the
     * ArrayList -1 is returned.
     *
     * @param listToCheck The ArrayList of Strings that we will check to see if it contains potentialId
     * @param potentialId The String we will be searching for in the listToCheck.
     * @return The index of a String inside of an ArrayList of Strings. If the String is not in the ArrayList -1 is
     * returned.
     */
    public static int locationInList(ArrayList<String> listToCheck, String potentialId) {
        if (listToCheck.contains(potentialId)) {
            return listToCheck.indexOf(potentialId);
        } else {
            return -1;
        }
    }


    /**
     * Returns a String representation of the Id. The String Representation is simply the completeIdString attribute.
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getId();
    }

    /**
     * @return The part of the Id that is two random letters.
     * @author dogbane
     * @see <a href=https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java>
     * Origanl Code</a>
     *
     * Creates the part of the Id that is two random letters. This method is only to be used by a BikeId object.
     * it used in the constructor of AbstractId that does not contain any parameters.
     */
    public String createIdStringPart() {
        Random rnd = new Random();
        char c1 = (char) ('a' + rnd.nextInt(26));
        char c2 = (char) ('a' + rnd.nextInt(26));

        return "" + c1 + c2;
    }

    /**
     * Creates the part of the Id that is made up of the initials and date the membership was issued. This method is
     * only to be used by a CustomerId object. It is used in the constructor of AbstractId that contains the parameters,
     * initials, and year.
     *
     * @return A String made up of the Id that is made of the customer's initials (in all caps) and the year that
     * the customer began having a record. The String separates the two values listed above with a "-" and follows the
     * year with a "-".
     */
    public String createIdStringPart(String initials, int year) {
        return initials.toUpperCase() + "-" + year + "-";
    }


}








