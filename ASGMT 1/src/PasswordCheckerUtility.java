import java.lang.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class: CMSC 204
 * Instructor: Monshi
 * Description: A program with a GUI that checks the validity of a password amongst set requirements.
 * Due: 9/13/2022
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: B. Leigh Vining
 */

/**
 * @author B. Leigh Vining
 */

public class PasswordCheckerUtility {

	public PasswordCheckerUtility() {
	}

	/**
	 * @param password        String to be checked for
	 * @param passwordConfirm String to be checked against password for
	 * @throws UnmatchedException Thrown if not the same, case sensitive
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}
	}

	/**
	 * @param password        String to be checked for
	 * @param passwordConfirm string to be checked against password for
	 * @return True if both are the same, case sensitive. False otherwise
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		return password.equals(passwordConfirm);
	}

	/**
	 * @param passwords List of passwords
	 * @return ArrayList of invalid passwords in the correct format
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalidPasswords = new ArrayList<String>();

		for (int i = 0; i < passwords.size(); i++) {
			try {
				isValidPassword(passwords.get(i));
			} catch (Exception e) {
				invalidPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			}
		}
		return invalidPasswords;
	}

	/**
	 * @param password String to be checked for
	 * @return True if password contains 6 to 9 characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() >= 6 && password.length() <= 9) {
			return true;
		} else
			return false;
	}

	/**
	 * @param password String to be checked for
	 * @return True if password meets digit requirement
	 * @throws NoDigitException Thrown if password does not meet digit requirement
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		boolean hasDigit = false;

		for (char c : password.toCharArray()) {
			if (Character.isDigit(c)) {
				hasDigit = true;
			}
		}

		if (!hasDigit) {
			throw new NoDigitException();
		} else
			return true;

	}

	/**
	 * @param password String to be checked for
	 * @return True if it meets the lowercase requirement
	 * @throws NoLowerAlphaException Thrown if it does not meet the lowercase
	 *                               requirement
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		boolean hasLower = false;

		for (char c : password.toCharArray()) {
			if (Character.isLowerCase(c)) {
				hasLower = true;
			}
		}

		if (hasLower) {
			return true;
		} else
			throw new NoLowerAlphaException();
	}

	/**
	 * @param password String to be checked for
	 * @return True if password meets the special character requirement
	 * @throws NoSpecialCharacterException Thrown if it does not meet the special
	 *                                     character requirement
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			return true;
		} else
			throw new NoSpecialCharacterException();
	}

	/**
	 * @param password String to be checked for
	 * @return True if password meets the alpha character requirement
	 * @throws NoUpperAlphaException Thrown if it does not meet the alpha character
	 *                               requirement
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		boolean hasUpper = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUpper = true;
			}
		}

		if (hasUpper) {
			return true;
		} else
			throw new NoUpperAlphaException();
	}

	/**
	 * @param password String to be checked for
	 * @return True if it meets the minimum length requirement
	 * @throws LengthException Thrown if it does not meet the minimum length
	 *                         requirement
	 */
	public static boolean isValidLength(String password) throws LengthException {
		if (password.length() >= 6) {
			return true;
		} else
			throw new LengthException();
	}

	/**
	 * @param password String to be checked for
	 * @return True if the password is valid/follows all the requirements. false if
	 *         invalid
	 * @throws LengthException             Thrown if it does not meet the minimum
	 *                                     length requirement
	 * @throws NoUpperAlphaException       Thrown if it does not meet the alpha
	 *                                     character requirement
	 * @throws NoLowerAlphaException       Thrown if it does not meet the lowercase
	 *                                     requirement
	 * @throws NoDigitException            Thrown if password does not meet digit
	 *                                     requirement
	 * @throws NoSpecialCharacterException Thrown if it does not meet the special
	 *                                     character requirement
	 * @throws InvalidSequenceException    Thrown if it DOES meet the sequence
	 *                                     requirement
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
			NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		if (isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password)
				&& hasSpecialChar(password) && NoSameCharInsequence(password))
			return true;
		else
			return false;

	}

	/**
	 * @param password String to be checked for
	 * @return False if the password is valid and the length of the password is NOT
	 *         between 6 and 9 (inclusive)
	 * @throws WeakPasswordException Thrown if length of password is between 6 and 9
	 *                               (inclusive), ALTHOUGH the password may be VALID
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {

		if (isValidPassword(password) && !hasBetweenSixAndNineChars(password)) {
			return false;
		} else
			throw new WeakPasswordException();
	}

	/**
	 * @param password String to be checked for
	 * @return False if the password does NOT meet the sequence requirement
	 * @throws InvalidSequenceException Thrown if it DOES meet the sequence
	 *                                  requirement
	 */
	public static boolean NoSameCharInsequence(String password) throws InvalidSequenceException {
		boolean noSeq = true;

		for (int i = 1; i < password.length(); i++) {
			if (password.charAt(i) == password.charAt(i - 1)) {
				noSeq = false;
				throw new InvalidSequenceException();
			}
		}
		return noSeq;
	}

}