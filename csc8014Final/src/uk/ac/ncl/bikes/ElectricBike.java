package uk.ac.ncl.bikes;

/**
 * A bike that is an electric type has three added methods (not counting the constructor) getCharge, reCharge, and
 * lostCharge. Moreimportantly it has a new attribute, currentCharge.
 */
public final class ElectricBike extends Bike {
    private boolean currentCharge;

    /**
     * Constructor for the Electric Bike. Sets the current Charge to true assuming that when the company creates the
     * Electric Bike ,the Bike would be charged by them. This is a package-private constructor, and the factory method
     * from Bike should be used to instantiate an electric bike.
     */
    ElectricBike() {
        super();
        currentCharge = true;

        //for testing
//        System.out.println(isCharged());
//        reCharge();
//        System.out.println(isCharged());
    }

    /**
     * Returns a boolean telling if the Bike is charged or not.
     *
     * @return a boolean that returns true if the Bike is charged and false if the Bike is not charged.
     */
    public boolean getCharge() {
        return currentCharge;
    }

    /**
     * Changes the Bikes currrentCharge to True, implying the Bike is now charged.
     */
    public void reCharge() {
        currentCharge = true;
    }


    /**
     * A boolean that changes the Bikes currentCharge to false. This method is to be used when the Bike is rented.
     * Assuming the customer will use the Bike (causing it to lose battery), we can automatically assume that its
     * currentCharge will need to be recharged when it is returned. Therefore, we can use this method when the Bike is
     * rented, knowing the Bike will need to be recharged when it is returned.
     */
    public void lostCharge() {
        currentCharge = false;
    }


}
