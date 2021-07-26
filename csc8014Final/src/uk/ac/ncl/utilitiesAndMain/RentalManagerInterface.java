package uk.ac.ncl.utilitiesAndMain;

import uk.ac.ncl.bikes.Bike;
import uk.ac.ncl.people.CustomerRecord;

import java.util.Set;

/**
 * An interface for the RentalManager. This is mostly here to check that I implement the required methods.
 *
 */
 interface RentalManagerInterface {

    /**
     * This method returns the number of bikes of the specified type that are
     * available to rent.
     *
     * @param typeOfBike The bike type of which we are wanting to see how much of them are available for rent.
     * @return The number of bikes of the specified type that are
     * available to rent.
     */
    public int availableBikes(String typeOfBike);

    /**
     * @return The set of Bikes that are currently being rented.
     */
    public Set<Bike> getRentedBikes();

    /**
     * Given a person's customer record, this method returns a copy of the Bike they are currently renting (if any).
     * If the person is not renting a Bike null is returned.
     *
     * @param customer The CustomerRecord of the person we want to see what Bike they are renting (if any).
     * @return A copy of the Bike <code>customer</code> is currently renting.
     * @throws CloneNotSupportedException if the class Bike does not implement the Cloneable interface.
     */
    public Bike getBike(CustomerRecord customer) throws CloneNotSupportedException;


    /**
     * Given a person's customerRecord and type of bike required (road or electric), this method determines whether the
     * person is eligible to rent a bike of the specified type and, if there is a bike available, issues a bike of the
     * specified type. The method associates the bike with the person's customer number (so that the
     * company has a record of bikes out for rent and the people renting them). If a bike cannot be issued, the method
     * returns an appropriate indication of the failure to issue a bike.
     * @param customer The CustomerRecord (customer) who will try to rent a bike.
     * @param typeOfBike The type of Bike the <code>customer</code> will try to rent.
     */
    public void issueBike(CustomerRecord customer, String typeOfBike);

    /**
     * This method terminates the rental contract associated with the given customer record. In effect, the renter is
     * returning the bike. The bike is then available for rent by someone else. The method removes the
     * record of the rental from the company's records. This method changes the status of the returned bike to not
     * rented. If the bike is an electric bike, then it is necessary to recharge the battery. There is no charge to the
     * customer for this. Terminating a non-existent contract has no effect.
     * @param customer The customer who wishes to return a Bike.
     */
    public void terminateRental(CustomerRecord customer);
}
