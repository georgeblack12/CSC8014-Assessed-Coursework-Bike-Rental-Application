package uk.ac.ncl.utilitiesAndMain;

import uk.ac.ncl.bikes.Bike;

/**
 * A utilities class with only methods that should be free to use by all classes.
 */
public final class Utilities {
    /**
     * A method that takes a String and capitalizes the first letter of all the words in the String and sets all the
     * other letters in the String to their lowercase values. The String that was given in the method parameter with the
     * capitalization changes stated above is returned.
     *
     * @param: String StringUsed - The String that is to have the capitalization of all the words in the string changed.
     * @return: String, The String that was entered given in the method parameter with the first letter of all the words
     * in the String capitalized, and all the other letters in the String set to their lowercase values.
     * <p>
     * NOTE: For this method, define a word as part of the String that does not contain any space.
     */
    public static String capitalizeString(String stringUsed) {
        final String[] tmp = stringUsed.split(" ");
        String capitalString = "";

        for (String word : tmp) {
            if (word.equals(tmp[tmp.length - 1]))
                capitalString = capitalString + capitalizeWord(word);
            else
                capitalString = capitalString + capitalizeWord(word) + " ";
        }
        return capitalString;
    }

    /**
     * The purpose of this method is to take the single word given in the parameter and alter it so the first letter of
     * the given parameter is capitalized, and all the letters in the parameter are set to lowercase.
     *
     * @author: Jens Piegsa
     * @author: George Black
     * @param: word, The word to have the capitalization of its characters to be changed.
     * @Retur: String, The word given in the parameter of this method with the first letter being capitalized and all
     * the other letters of the word set to lowercase.
     * @see <a href=https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java>Orginal Code</a>
     */
    private static String capitalizeWord(String word) {
        final String firstLetter = String.valueOf(word.charAt(0)).toUpperCase();
        final String restOfLetters = word.substring(1).toLowerCase();
        return firstLetter + restOfLetters;
    }

    /**
     * Method to check that the entered String is either "Gold Class" or "Normal Class"
     * (capitalization does not matter). If the String in the parameter is either "Gold Class" or "Normal Class",
     * the String is returned with the needed capitalzation. If the String is not one of the two options an
     * exception is thrown.
     *
     * @param typeToCheck The String to be checked if it is either "Gold Class" or "Normal Class"
     *                    (capitalization does not matter).
     * @return <code>typeToCheck</code> with the needed capitalization.
     * @throws IllegalArgumentException if <code>typeToCheck</code> is not "Gold Class" or "Normal Class"
     * (capitalization does not matter).
     */
    public static String checkBikeType(String typeToCheck) {
        final String officialType = capitalizeString(typeToCheck);
        if (officialType.equals(Bike.ROAD_BIKE)) {
            return Bike.ROAD_BIKE;
        } else if (officialType.equals(Bike.ELECTRIC_BIKE)) {
            return Bike.ELECTRIC_BIKE;
        } else {
            throw new IllegalArgumentException("Invalid Bike Type: " + officialType);
        }
    }
}
