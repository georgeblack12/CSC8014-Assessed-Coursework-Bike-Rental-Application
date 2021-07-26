package uk.ac.ncl.people;

import uk.ac.ncl.utilitiesAndMain.Utilities;

/**
 * A name made up of a first, last name, and the name's initials. The class has Getters and some overridden methods.
 */
final class Name {
    private final String firstName;
    private final String lastName;
    private final String initials;

    /**
     * Construct a Name given its first and last name. The first name will be lowercase except for the first letter.
     * The same is true with the last name. The Initials of the Name will be set and will be capital. Empty names are not
     * permitted.
     *
     * @param fName The first name of the Name.
     * @param lName The last name of the Name.
     * @throws IllegalArgumentException if <code>fName</code> or <code>lName</code> is empty;
     */
    Name(String fName, String lName) {
        if (fName.length() == 0 || lName.length() == 0) {
            throw new IllegalArgumentException("first " +
                    "and/or last name cannot be empty");
        }
        this.firstName = Utilities.capitalizeString(fName);
        this.lastName = Utilities.capitalizeString(lName);
        this.initials = createInitials(getFirstName(), getLastName());
    }


    /**
     * Takes the first name and the last name entered in and returns the initials in all caps.
     *
     * @param firstName The firstName of the full Name.
     * @param lastName  The lastName of the full Name.
     * @return The Initials of the Name in all caps.
     */
    private String createInitials(String firstName, String lastName) {
        final String firstInitial = Character.toString(firstName.charAt(0));
        final String lastInitial = Character.toString(lastName.charAt(0));
        final String initials = firstInitial.toUpperCase() + lastInitial.toUpperCase();
        return initials;
    }

    /**
     * @return The firstName of this Name.
     */
    String getFirstName() {
        return firstName;
    }

    /**
     * @return The lastName of this Name.
     */
    String getLastName() {
        return lastName;
    }

    String getInitials() {
        return initials;
    }


    /**
     * @author Steve Riddle
     * @see <a href=https://nucode.ncl.ac.uk/scomp/student-portfolios/c0063213-portfolio/csc8014-practicals/blob/solutions/csc8014-p2-mutables/src/uk/ac/ncl/teach/ex/immut/ImmutablePerson.java>Original Work</a>
     *
     * Returns a String representation of a name. The String Representation is the first name and the last name of
     * the full name separated by a space character
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getFirstName() + " " + getLastName();
    }


}
