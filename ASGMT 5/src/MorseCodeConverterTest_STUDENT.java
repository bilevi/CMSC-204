import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import org.junit.jupiter.api.Test;

class MorseCodeConverterTest_STUDENT {

	@Test
	void testConvertToEnglishString() {
		String morseCode = MorseCodeConverter.convertToEnglish(".-- .... . -. / .--. .. --. ... / -.-. .- -. / ..-. .-.. -.-- / .- -. -.. / .... . .-.. .-.. / ..-. .-. . . --.. . ... / --- ...- . .-.");
		assertEquals("when pigs can fly and hell freezes over", morseCode);
	}

	@Test
	void testConvertToEnglishFile() {
		File file = new File("src/test.txt");
		
		try {
			assertEquals("i think therefore i am", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e)
		{
			assertTrue("An unwanted exception was caught", false);	
		}
	}

}
