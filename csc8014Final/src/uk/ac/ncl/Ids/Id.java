package uk.ac.ncl.Ids;

/**
 * Interface for the types of Id Classes that will be created (AbstractId, CustomerId, and BikeId). The purpose of these
 * methods are to be used to create the Id properly for a customer and a bike.
 */
interface Id {
    /**
     * Creates the random number part of the Id. These numbers are randomly generated. The method is implemented in CustomerId
     * and BikeId.
     *
     * @return The random integer part of an Id.
     */
    int createIdNumberPart();

    /**
     * Creates the String part of the Id. The method is overloaded in AbstractId to have it used adequately for
     * each type of Id (CustomerId and BikeId).
     *
     * @return The String part of an Id.
     */
    String createIdStringPart();

    /**
     * @return the entire Id of the object.
     */
    public String getId();
}
