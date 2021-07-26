package uk.ac.ncl.people;

import uk.ac.ncl.Ids.CustomerNumber;
import uk.ac.ncl.utilitiesAndMain.Utilities;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * The Customer record of a customer of the bike company. "A customer record has the customer name (comprising a
 * first and lastname), the date of birth of the customer, a unique customer number, the year in which the customer
 * record was issued, and an indication of whether or not the customer is a "Gold Class" customer." The class provide
 * methods to access the customer's full name, date of birth, the customer number, the date of issue of
 * the record and whether the customer is a Gold Class customer or not. Also, the Person must be at least a year old in
 * order to create a membership.
 */
public class CustomerRecord {

    private final Name custName;
    private final Date dob;
    private final int age;
    private final CustomerNumber custId;
    private final String membershipType;
    private final Date recordDate;

    //#Austin Powers #YYYAAA BBBAABBYY
    private static final String GOLD_CLASS_MEMBERSHIP = Utilities.capitalizeString("Gold Class");

    private static final String NORMAL_MEMBER = Utilities.capitalizeString("Normal Class");


    /**
     * The Constructor to create a proper CustomerRecord. Each Id for a customer will be unique. The name is created
     * from the firstName and LastName String. The membership on the Customer record must either be "Gold Class" or
     * "normal class."
     * Note: Dr. Riddle said we do not need to create people that do not have a customerRecord.
     *
     * @param firstName  The first name of the Customer.
     * @param lastName   The last name of the Customer.
     * @param dob        The Date of birth of the Customer.
     * @param membership The type of membership the Customer has (either "Gold Class" or "Normal Class").
     * @param recordDate The date the person became a Customer. Note: Dr. Riddle said this could be an exact date and not
     *                   a year.
     */
    public CustomerRecord(String firstName, String lastName, Date dob, String membership, Date recordDate) {
        this.custName = new Name(firstName, lastName);
        this.dob = new Date(dob.getTime());
        this.age = calcAge();
        this.membershipType = checkMembership(membership);
        this.recordDate = checkRecordDate(recordDate);
        this.custId = CustomerNumber.getInstance(custName.getInitials(), getRecordYear());
    }

    /**
     * Converts a date to a LocalDate. The conversion is done because the spec says the dateOfBirth and recordDate on the Customer
     * Record must be a Date, but I a more comfortable using LocalDate to extract further information. This method is used
     * in other methods in this class.
     *
     * @param date The date that will be used to create the LocalDate.
     * @return A LocalDate of the parameter <code>date</code>.
     */
    private LocalDate convertDateToLocal(Date date) {
        final LocalDate dateAsLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return dateAsLocal;
    }

    /**
     * Calculates this Person's Age (in years)  based on the entered dob.
     *
     * @return The age of this Person (in years)
     * @throws IllegalArgumentException if this person's age is not at least one year old.
     */
    private int calcAge() {
        final LocalDate dobAsLocal = convertDateToLocal(this.dob);
        final int age = Period.between(dobAsLocal, LocalDate.now()).getYears();
        //System.out.println(age); for testing
        if (age <= 0) { //must be at least a year old
            throw new IllegalArgumentException("Invalid Birthday: " + dob);
        }
        return Period.between(dobAsLocal, LocalDate.now()).getYears();
    }


    /**
     * Checks to see that the String to represent this membershipType is either "Gold Class" or "Normal Class." Note:
     * capitalization does not matter.
     *
     * @param membership The String to be checked if it is either "Gold Class" or "Normal Class" where
     *                   capitalization does not matter.
     * @return either the String "Gold Class" or "Normal Class" based on what <code>membership</code> is.
     * @throws IllegalArgumentException if <code>membership</code> is not "Gold Class" or "Normal Class" where
     *                                  capitalization does not matter.
     */
    private String checkMembership(String membership) {
        final String membershipCap = Utilities.capitalizeString(membership); //gets proper capitalization

        if (!(membershipCap.equals(GOLD_CLASS_MEMBERSHIP) || membershipCap.equals(NORMAL_MEMBER))) {
            throw new IllegalArgumentException("Not a valid membership type: " + membershipCap);
        }
        return membershipCap;
    }

    /**
     * Makes sure <code>d</code> is a date that has occured. This will be used when creating this dateRecord in the
     * constructor.
     *
     * @param d The date to be checked if it is date that has occurred.
     * @return The Date <code>d</code> if it is a date that has occurred.
     * @throws IllegalArgumentException if Date <code>d</code> has not occured yet.
     */
    private Date checkRecordDate(Date d) {
        Date currentDate = new Date();
        if (currentDate.before(d)) {
            throw new IllegalArgumentException("Invalid record date: " + d);
        }
        return new Date(d.getTime());
    }

    /**
     * Gets the year of this recordDate.
     *
     * @return The year of this recordDate.
     */
    private int getRecordYear() {
        LocalDate localRecordDate = convertDateToLocal(this.recordDate); //convert date to local date.
        return localRecordDate.getYear();
    }

    /**
     * @return A copy of this custName.
     */
    public Name getName() {
        return new Name(custName.getFirstName(), custName.getLastName());
    }


    /**
     * @return the age of this CustomerRecord.
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the String of the Id of this CustomerRecord.
     */
    public String getCustNumber() {
        return custId.getId();
    }

    /**
     * @return the recordDate of this CustomerRecord.
     */
    public Date getRecordDate() {
        return new Date(recordDate.getTime());
    }

    /**
     * Method that returns if this CustomerRecord is a "Gold Class" member or not.
     * (Returns true if this is a "Gold Class" member).
     *
     * @return if this CustomerRecord is a "Gold Class" member or not.
     */
    public boolean isGold() {
        if (membershipType.equals(GOLD_CLASS_MEMBERSHIP)) {
            return true;
        } else return false;
    }

    /**
     * Returns a String representation of this CustomerRecord. The String Representation is used for testing purposes.
     * The String is made up of this CustomerRecord's attribute.
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return String.format("Name: %s\n" +
                "DOB: %s\n" +
                "Age: %s\n" +
                "Customer Number: %s\n" +
                "Membership: %s\n" +
                "Record Date: %s\n", getName(), dob, getAge(), getCustNumber(), membershipType, getRecordDate());
    }
}
