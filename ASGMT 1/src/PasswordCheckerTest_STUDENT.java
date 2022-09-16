
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * 
 * @author B. Leigh Vining
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> studentPasswords;

	@Before
	public void setUp() throws Exception {
		studentPasswords = new ArrayList<String>();
		String[] passwords = { "AbC#246", "strong#PWD1", "Go#12", "AAc$134", "abcd@123", "QYZ?456", "Bobcat1993",
				"@@Comma" };
		/*
		 * 0 is weak, 1 is valid, 2 is length, 3 is sequence, 4 is upper, 5 is lower, 6
		 * is special character, 7 is digit
		 */

		studentPasswords.addAll(Arrays.asList(passwords));

	}

	@After
	public void tearDown() throws Exception {
		studentPasswords = null;
	}

	/**
	 * Test if the password is less than 6 characters long. This test should throw a
	 * LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(studentPasswords.get(2)));
			assertTrue("Did not throw LengthException", false);
		}

		catch (LengthException e) {
			assertTrue("Successfully threw a LengthException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exceptions besides LengthException", false);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character This test
	 * should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(studentPasswords.get(4)));
			assertTrue("Did not throw a NoUpperAlphaException", false);
		}

		catch (NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exceptions besides NoUpperAlphaException", false);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character This test
	 * should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(studentPasswords.get(5)));
			assertTrue("Did not throw a NoLowerAlphaException", false);
		}

		catch (NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exceptions besides NoLowerAlphaException", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword(studentPasswords.get(0)));
			assertTrue("Did not throw WeakPasswordException", false);
		}

		catch (WeakPasswordException e) {
			assertTrue("Successfully threw a WeakPasswordException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exceptions besides WeakPasswordException", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(studentPasswords.get(3)));
			assertTrue("Did not throw InvalidSequenceException", false);
		}

		catch (InvalidSequenceException e) {
			assertTrue("Successfully threw a InvalidSequenceException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exceptions besides InvalidSequenceException", false);
		}
	}

	/**
	 * Test if the password has at least one digit One test should throw a
	 * NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(studentPasswords.get(7)));
			assertTrue("Did not throw NoDigitException", false);
		}

		catch (NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException", true);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			assertTrue("Threw some other exceptions besides NoDigitException", false);
		}
	}

	/**
	 * Test correct passwords This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(studentPasswords.get(1)));
			assertTrue("Did not throw any exceptions", true);
		}

		catch (Exception e) {
			assertTrue("Threw some exceptions", false);
		}
	}

	/**
	 * Test the invalidPasswords method Check the results of the ArrayList of
	 * Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(studentPasswords);

		assertEquals(results.get(0), "Go#12 -> The password must be at least 6 characters long");
		assertEquals(results.get(1),
				"AAc$134 -> The password cannot contain more than two of the same character in sequence");
		assertEquals(results.get(2),
				"abcd@123 -> The password must contain at least one uppercase alphabetic character");
		assertEquals(results.get(3),
				"QYZ?456 -> The password must contain at least one lowercase alphabetic character");
		assertEquals(results.get(4), "Bobcat1993 -> The password must contain at least one special character");
		assertEquals(results.get(5), "@@Comma -> The password must contain at least one digit");
	}

}
