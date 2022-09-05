import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {

	private GradeBook g1;
	private GradeBook g2;
	private GradeBook g3;
	private GradeBook g4;

	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g1.addScore(50);
		g1.addScore(75);
		
		g2 = new GradeBook(5);
		g2.addScore(25);
		g2.addScore(100);
		g2.addScore(50);
		
		g3 = new GradeBook(5);
		g3.addScore(93);
		g3.addScore(81);
		g3.addScore(100);
		g3.addScore(70);
		g3.addScore(98);
		
		g4 = new GradeBook(5);
	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
		g3 = null;
		g4 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(g1.toString().equals("50.0 75.0 "));
		assertEquals(2, g1.getScoreSize(), .0001);
		
		assertTrue(g2.toString().equals("25.0 100.0 50.0 "));
		assertEquals(3, g2.getScoreSize(), .0001);
		
		assertTrue(g3.toString().equals("93.0 81.0 100.0 70.0 98.0 "));
		assertEquals(5, g3.getScoreSize(), .0001);
	}

	@Test
	void testSum() {
		assertEquals(125, g1.sum(), .0001);
		assertEquals(175, g2.sum(), .0001);
		assertEquals(442, g3.sum(), .0001);
	}

	@Test
	void testMinimum() {
		assertEquals(50, g1.minimum(), .0001);
		assertEquals(25, g2.minimum(), .0001);
		assertEquals(70, g3.minimum(), .0001);
	}

	@Test
	void testFinalScore() {
		assertEquals(75, g1.finalScore(), .0001);
		assertEquals(150, g2.finalScore(), .0001);
		assertEquals(372, g3.finalScore(), .0001);
		assertEquals(0, g4.finalScore(), .0001);
	}
}
