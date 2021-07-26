package uk.ac.ncl.utilitiesAndMain;

import uk.ac.ncl.bikes.Bike;
import uk.ac.ncl.bikes.ElectricBike;
import uk.ac.ncl.people.CustomerRecord;

import java.util.*;

/**
 * The RentalManager is the class made up of the "most important" methods for this project. Those methods are:
 * availableBikes(typeOfBike), getRentedBikes(), getBike(customerRecord), issueBike(customerRecord, typeOfBike),
 * and terminateRental(customerRecord). The class contains a Hashmap to store all of the rented bike's information.
 * An ArrayList containing the roadBikes (normal bikes) that can be rented, as well as an ArrayList containing
 * containing the elecricBikes that can be rented. These two ArrayLists are created as soon an instance of this
 * class is created. Only one instance of this class is created due to the factory method. All of the testing for these listed methods
 * methods are done in this class.
 * <p>
 * READ THIS!!: This class (and most others) throw a lot of exceptions. Dr. Riddle said since my errors are informative
 * I do not have to handle them, and it is acceptable that the program is terminated when these exceptions are thrown.
 * Additionally, since all possible people are a CustomerRecord, it is unnecessary and does not make sense to check
 * if a CustomerRecord has a CustomerRecord. Therefore, I do not do this in the methods of this class.
 */
public class RentalManager implements RentalManagerInterface{

    //Stores rentedBikes information
    private static final Map<Bike, CustomerRecord> rentedBikes = new HashMap<Bike, CustomerRecord>();

    //The available roadBikes for rent.
    private final ArrayList<Bike> roadBikesForRent = createMultipleBikes("Road");

    //The available electricBikes for rent.
    private final ArrayList<Bike> electricBikesForRent = createMultipleBikes("Electric");

    //I could have used the ArrayLists lengths instead of this for some methods in the class, but I decided not to.
    private int availableRoadBikes = Bike.POSSIBLE_ROAD_BIKES; //50
    private int availableElectricBikes = Bike.POSSIBLE_ELECTRIC_BIKES; //10
    private static final RentalManager INSTANCE = new RentalManager(); //

    /**
     * Private constructor that creates an instance of this class. Since it is private, it cannot be accessed outside
     * this class.
     */
    private RentalManager() {
    }

    /**
     * Returns the single instance of a rentalManager, INSTANCE.
     *
     * @return INSTANCE, the single instance of rentalManager.
     */
    public static RentalManager getInstance() {
        return INSTANCE;
    }

    /**
     * This method returns the number of bikes of the specified type that are
     * available to rent.
     *
     * @param typeOfBike The bike type of which we are wanting to see how much of them are available for rent.
     * @return The number of bikes of the specified type that are
     * available to rent.
     */
    public int availableBikes(String typeOfBike) {

        //Throws an error if a proper bike type is not entered in the parameter.
        final String officialType = Utilities.checkBikeType(typeOfBike);

        if (officialType.equals(Bike.ROAD_BIKE)) return availableRoadBikes;
        else return availableElectricBikes;
    }

    /**
     * @return The set of Bikes that are currently being rented.
     */
    public Set<Bike> getRentedBikes() {
        Set<Bike> copyOfRented = new HashSet<>(rentedBikes.keySet());
        return copyOfRented;
    }

//    /**
//     * Prints out the Bikes that are available for rent. Right now, it is set to print out the electricBikesForRent.
//     * THIS WAS ENTERED PURELY FOR TESTING METHODS. Therefore, it is commented out.
//     */
//    public void printAvailableBikes(){
//        System.out.println(electricBikesForRent);
//    }


    /**
     * Given a person's customer record, this method returns a copy of the Bike they are currently renting (if any).
     * If the person is not renting a Bike null is returned.
     *
     * @param customer The CustomerRecord of the person we want to see what Bike they are renting (if any).
     * @return A copy of the Bike <code>customer</code> is currently renting.
     * @throws CloneNotSupportedException if the class Bike does not implement the Cloneable interface.
     */
    public Bike getBike(CustomerRecord customer) throws CloneNotSupportedException {
        Bike bikeToGet = getExactBike(customer); //
        Bike bikeCopy;
        if (bikeToGet != null) {
            bikeCopy = bikeToGet.clone(); //avoiding possible mutability issues.
        } else {
            bikeCopy = null;
        }
        return bikeCopy;
    }

    /**
     * @param customer The CustomerRecord of the person we want to see what Bike they are renting (if any).
     * @return The Bike <code>customer</code> is currently renting.
     * @author baeldung
     * @see <a href=https://www.baeldung.com/java-map-key-from-value>Original Work</a>
     *
     * Given a person's customer record, this method returns the Bike they are currently renting (if any).
     * If the person is not renting a Bike, null is returned. The difference between this method and getBike is that
     * this method does not return a Bike copy. This method is private, so it cannot be used outside of this class.
     * This method can cause some issues with mutability if and only if it is used in this class. This method is used
     * with other methods inside this class, but it does not cause any mutability issues.
     */
    private Bike getExactBike(CustomerRecord customer) {
        Bike bikeToGet = null;
        for (Map.Entry<Bike, CustomerRecord> entry : rentedBikes.entrySet()) {
            if (entry.getValue() == customer) {
                bikeToGet = entry.getKey();
            }
        }
        return bikeToGet;
    }

    /**
     * Given a person's customerRecord and type of bike required (road or electric), this method determines whether the
     * person is eligible to rent a bike of the specified type and, if there is a bike available, issues a bike of the
     * specified type. The method associates the bike with the person's customer number (so that the
     * company has a record of bikes out for rent and the people renting them). If a bike cannot be issued, the method
     * returns an appropriate indication of the failure to issue a bike.
     * @param customer The CustomerRecord (customer) who will try to rent a bike.
     * @param typeOfBike The type of Bike the <code>customer</code> will try to rent.
     */
    public void issueBike(CustomerRecord customer, String typeOfBike) {
        if(!checkHasBike(customer)) {
            //Throws an error if a proper bike type is not entered in for typeOfBike.
            final String officialType = Utilities.checkBikeType(typeOfBike);

            //sees if Bike is available for tent
            if (!(officialType.equals(Bike.ELECTRIC_BIKE))) {
                if(checkRoadCheckout(customer)) {
                    checkOutRoadBike(customer); //checks out bike
                }
            } else {
                // sees if customer can check out an electric Bike
                if(checkElectricCheckout(customer)) {
                    checkOutElectricBike(customer); //checks out electric Bike.
                }
            }
        }
    }

    /**
     * Checks to see this customer is renting a Bike. If they are true is returned (returns false if the customer
     * is not renting a bike). If customer is already renting a Bike, a message is displayed.
     * @param customer The CustomerRecord to be checked if it is renting a Bike.
     * @return True if <code>customer</code>is currently renting a bike (returns false if
     * <code>customer</code>is not renting a bike).
     */
    private boolean checkHasBike(CustomerRecord customer) {
        boolean hasBike=false;
        Bike custHasBike = getExactBike(customer);
        if (custHasBike != null) {
            System.out.println("The customer "+customer.getName()+" is currently renting a bike, they are unable to checkout a bike");
            hasBike=true;
        }
        return hasBike;
    }

    /**
     * The act of a customer checking out a RoadBike. This method is used in issueBike().
     * @param customer The CustomerRecord checking out the RoadBike.
     */
    private void checkOutRoadBike(CustomerRecord customer) {

        //put the bike and customer in the map of rentedBikes.
        rentedBikes.put(roadBikesForRent.get(availableRoadBikes - 1), customer);

        roadBikesForRent.get(availableRoadBikes - 1).isRenting(); //known the Bike is now being rented.

        //removes the Bike from the ArrayList of Bikes that can be Rented
        roadBikesForRent.remove(availableRoadBikes - 1);

        //The amount of available roadBikes is decreased.
        availableRoadBikes--;
    }

    /**
     * A method to make sure a RoadBike can be rented. This method is used in the method issueBike.
     * @param customer The CustomerRecord wanting to check out a road bike.
     * @return a boolean that is true if <code>customer</code> can check out a road bike (returns false if
     * <code>customer</code> cannot check out a road Bike.
     */
    private boolean checkRoadCheckout(CustomerRecord customer) {
        boolean canCheckOut = true;
        if (roadBikesForRent.size() == 0) {
            System.out.println("No road bikes are currently available for rent. "+customer.getName()+
            " cannot rent a roadBike.");
            canCheckOut = false;
        }

        //This first if should NEVER occur, but is left as a safety net.
        if(canCheckOut) {
            if (roadBikesForRent.get(availableRoadBikes - 1).getRentalStatus()) {
                System.out.println("The bike you are trying to give out is currently being rented.");
                canCheckOut = false;
            }
        }
        return canCheckOut;
    }

    /**
     * The act of a customer checking out an ElectricBike. This method is used in the method issueBike.
     * @param customer The CustomerRecord checking out the ElectricBike.
     */
    private void checkOutElectricBike(CustomerRecord customer) {
        //put the bike and customer in the map of rentedBikes.
        rentedBikes.put(electricBikesForRent.get(availableElectricBikes - 1), customer);


        electricBikesForRent.get(availableElectricBikes - 1).isRenting(); //known the Bike is now being rented.

        //removes the Bike from the ArrayList of Bikes that can be Rented
        ElectricBike eBike = (ElectricBike) electricBikesForRent.get(availableElectricBikes - 1);
//        System.out.println(eBike.isCharged()); //for testing
        eBike.lostCharge(); //Makes it known the Bike will need charge when it is returned.

        //removes the Bike from the ArrayList of Bikes that can be Rented
        electricBikesForRent.remove(availableElectricBikes - 1);

//        System.out.println(eBike.isCharged()); //for testing

        //The amount of available electricBikes is decreased.
        availableElectricBikes--;
    }



    /**
     * A method to make sure the customer can rent an ElectricBike. This method is used in the method issueBike.
     * This checks every reason why customer would not be able to check out an ElectricBike except if the person is
     * already renting a bike.
     * @param customer The CustomerRecord wanting to rent an ElectricBike. If the customer cannot check out a bike,
     *                 the reason they cannot check out a Bike is displayed.
     * @return a boolean that is true if <code>customer</code> can check out an ElectricBike (returns false if
     * <code>customer</code> cannot check out an ElectricBike.
     */
    private boolean checkElectricCheckout(CustomerRecord customer) {
        boolean canCheckOut = true;
        if (customer.getAge() < 21) {
            System.out.println("The customer "+customer.getName()+" is not old enough to rent an electric bike");
            canCheckOut=false;
        }
        if (!customer.isGold()) {
            System.out.println("The customer is not a Gold Class member, cannot issue Electric Bike to "+customer.getName());
            canCheckOut=false;
        }
        if (electricBikesForRent.size() == 0) {
            System.out.println("There are currently no electric bikes that can be rented "+customer.getName()+" " +
                    "cannot rent an electric bike");
            canCheckOut=false;
        }
        //This should nto occur and is only here as a safety net.
        if(canCheckOut) {
            if (electricBikesForRent.get(availableElectricBikes - 1).getRentalStatus()) {
                System.out.println("The bike you are trying to give out is currently being rented");
                canCheckOut = false;
            }
        }
        return canCheckOut;
    }

    //needs to be made public and static in order for it to be tested in otherTesting class

    /**
     * Creates an ArrayList of the Bike Type given as the parameter. The number of Bikes in the ArrayList is the max amount of
     * Bikes (for the given bike type) that are allowed to be made. This number is provided in the Bike Class.
     * @param bikeType The type of Bikes we want the ArrayList to Hold.
     * @return An ArrayList of the Bike Type given as the parameter. The number of Bikes in the ArrayList is the max amount of
     * Bikes (for the given bike type) that are allowed to be made. This number is listed in the Bike Class.
     */
    private ArrayList<Bike> createMultipleBikes(String bikeType) {
        final int amount;

        //Throws an error if a proper bike type is not entered in the parameter.
        final String officialType = Utilities.checkBikeType(bikeType);

        if (officialType.equals(Bike.ROAD_BIKE)) amount = Bike.POSSIBLE_ROAD_BIKES;
        else {
            amount = Bike.POSSIBLE_ELECTRIC_BIKES;
        }

        Bike[] bikes = new Bike[amount];
        for (int i = 0; i < amount; i++) {
            bikes[i] = Bike.getInstance(officialType);
        }
        //for testing
//        for(int i=0;i<amount;i++){
//            System.out.println(bikes[i]);
//        }
        ArrayList<Bike> bikeList = new ArrayList<>();
        bikeList.addAll(Arrays.asList(bikes));
        return bikeList;
    }


    /**
     * This method terminates the rental contract associated with the given customer record. In effect, the renter is
     * returning the bike. The bike is then available for rent by someone else. The method removes the
     * record of the rental from the company's records. This method changes the status of the returned bike to not
     * rented. If the bike is an electric bike, then it is necessary to recharge the battery. There is no charge to the
     * customer for this. Terminating a non-existent contract has no effect.
     * @param customer The customer who wishes to return a Bike.
     */
    public void terminateRental(CustomerRecord customer) {
        Bike bikeToReturn = getExactBike(customer);
        if (!(bikeToReturn == null)){
            bikeToReturn.isNoLongerRenting();
            rentedBikes.remove(bikeToReturn);
            if (bikeToReturn instanceof ElectricBike) {
                ElectricBike eBike = (ElectricBike) bikeToReturn;
                eBike.reCharge();
                availableElectricBikes++;
                electricBikesForRent.add(eBike);
            } else {
                availableRoadBikes++;
                roadBikesForRent.add(bikeToReturn);
            }
        }
    }


    //The testing of the "main" methods from this project all other testing is done in the class OtherTesting
    public static void main(String[] args) throws CloneNotSupportedException {
        //Check getInstance();
  final RentalManager theManager = RentalManager.getInstance();
//  // Bike bike1=Bike.getInstance("Road"); throws error max amount of bikes already created
//      //final RentalManager newManager = RentalManager.getInstance();
//      theManager.printAvailableBikes();
//      System.out.println();
//      //newManager.printAvailableBikes();

//// Checking the available bikes method
//                System.out.println(theManager.availableBikes("electric"));
//        System.out.println(theManager.availableBikes("road"));
//
        //Create Proper objects needed to do testing
//        final Calendar myDob = Calendar.getInstance();
//        myDob.set(1997, 11, 27);
//        Date dDob = myDob.getTime();
//        final Calendar myRecord = Calendar.getInstance();
//        myRecord.set(2000, 1, 1);
//        Date dMyRecord = myRecord.getTime();
//
//        CustomerRecord cust1 = new CustomerRecord("George", "Black", dDob,
//                "Gold class", dMyRecord);
//
//        CustomerRecord cust2 = new CustomerRecord("Steve","O",dDob,"goLD CLasS",dMyRecord);
//
//        CustomerRecord custBlank = new CustomerRecord("Johnny", "Malibu", dMyRecord, "Gold cLass",
//                dDob);
//
//        CustomerRecord cust3 = new CustomerRecord("Johnny", "Malibu", dMyRecord, "Gold cLass",
//                dDob);


        //Check issueBike and terminateRental with a single person.
//        theManager.printAvailableBikes();
//        System.out.println();
//        theManager.issueBike(cust1,"electric");
//        System.out.println();
//        theManager.printAvailableBikes();
//        System.out.println();
//        theManager.terminateRental(cust1);
//        theManager.printAvailableBikes();


//        //checks terminateRental()
//        theManager.printAvailableBikes();
//        theManager.terminateRental(cust1);
//        System.out.println();
//        theManager.printAvailableBikes();
//        System.out.println();;
//        theManager.issueBike(cust1,"electric");
//        theManager.printAvailableBikes();





              //Making sure the renting and returning is working with multiple bikes
//        theManager.printAvailableBikes();
//        System.out.println();
//        theManager.issueBike(cust1, "electric");
//       // theManager.issueBike(cust1,"electric"); //causes error
//        theManager.printAvailableBikes();
//        System.out.println();
//        //System.out.println(theManager.getRentedBikes());
//         theManager.issueBike(cust2,"electric");
//        theManager.printAvailableBikes();
//        //System.out.println(theManager.getRentedBikes());
//        System.out.println();
//        theManager.issueBike(cust3,"electric");
//        theManager.printAvailableBikes();
//        //System.out.println(theManager.getRentedBikes());
//        theManager.terminateRental(cust1);
//        theManager.printAvailableBikes();
//        //System.out.println(theManager.getRentedBikes());
//        System.out.println();
//        theManager.terminateRental(cust3);
//        theManager.printAvailableBikes();
//        System.out.println();
//        theManager.issueBike(cust1,"electric");
//        theManager.printAvailableBikes();
//        System.out.println();
//        System.out.println(theManager.getBike(cust1));
//        System.out.println(theManager.getRentedBikes());


        //Check getBike Method
//        System.out.println(theManager.getBike(cust1));
//        System.out.println(theManager.getBike(cust2));
//        System.out.println(theManager.getBike(custBlank)); // returns null

//        //Testing if copying works Correctly
//        Bike possibleIssue = theManager.getBike(cust1);
//        System.out.println(theManager.getBike(cust1));
//        possibleIssue.isNoLongerRenting();
//        System.out.println(theManager.getBike(cust1));
//        System.out.println(possibleIssue);

//        Bike possibleIssue = theManager.getBike(cust2);
//        System.out.println(theManager.getBike(cust2));
//        possibleIssue.isNoLongerRenting();
//        System.out.println(theManager.getBike(cust2));
//        System.out.println(possibleIssue);
//        ElectricBike eBike = (ElectricBike) theManager.getBike(cust2);
//        System.out.println(eBike.getCharge());
//        eBike.reCharge();
//        System.out.println(eBike.getCharge());
//        System.out.println(((ElectricBike) theManager.getBike(cust2)).getCharge());


    }
}
