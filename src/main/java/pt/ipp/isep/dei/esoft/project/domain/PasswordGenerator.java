package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Password Generator.
 * <p>
 * This class represents a Password Generator.
 * </p>
 */
public class PasswordGenerator implements Serializable {

    private static final int PASSWORD_LENGTH = 7;
    private static final int NUM_CAPITAL_LETTERS = 3;
    private static final int NUM_DIGITS = 2;

    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private SecureRandom random;

    /**
     * Instantiates a new Password generator.
     */
    public PasswordGenerator() {
        random = new SecureRandom();
    }

    /**
     * @return password
     */
    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < NUM_CAPITAL_LETTERS; i++) {
            password.append(randomCharacter(CAPITAL_LETTERS));
        }

        for (int i = 0; i < NUM_DIGITS; i++) {
            password.append(randomCharacter(DIGITS));
        }

        for (int i = 0; i < PASSWORD_LENGTH - NUM_CAPITAL_LETTERS - NUM_DIGITS; i++) {
            password.append(randomCharacter(LOWERCASE_LETTERS));
        }

        return shuffleString(password.toString());
    }

    /**
     * @param characters
     * @return
     */
    private char randomCharacter(String characters) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    /**
     * @param input String
     * @return
     */
    private String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
