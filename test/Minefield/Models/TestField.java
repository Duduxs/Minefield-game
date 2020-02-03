package Minefield.Models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Field;

public class TestField {

	/*
	 * Left (3, 2) Right (3,4) Up (2,3) Down (4,3) Diagonal left-down (2,2) Diagonal
	 * left-up (4,2) Diagonal right-down (2,4) Diagonal right-up (4,4)
	 */

	private Field f1, f2;

	@BeforeEach
	void startField() {
		f1 = new Field(3, 3);
		f2 = new Field(3, 4);
	}

	@Test
	void testMyDistancecross() {
		assertEquals(true, f1.addNeighbor(f2));

	}

	@Test
	void testMyDistancediagonal() {
		assertEquals(true, f1.addNeighbor(f2));

	}

	@Test
	void testIsNotNeighbor() {
		assertEquals(false, f1.addNeighbor(f2));

	}

	@Test
	void testOpenTheField() {
		assertTrue(f1.open());
	}

	@Test
	void toStringMethodMark() {

		f1.changeMark();
		assertEquals("x", f1.toString());
	}

	@Test
	void toStringMethodOpen() {
		f1.open();
		assertEquals(" ", f1.toString());
	}

	@Test
	void toStringMethodElse() {
		assertEquals("?", f1.toString());
	}

	@Test
	void toStringMethodOpenAndMine() {
		f1.open();
		f1.setMine(true);
		assertEquals("*", f1.toString());
	}

	@Test
	void goalArchivedFielProtected() {
		f1.changeMark();
		f1.setMine(true);
		assertEquals(true, f1.goalAchieved());

	}

	@Test
	void goalArchivedFieldFound() {
		f1.setMine(false);
		f1.open();
		assertEquals(false, f1.goalAchieved());
	}

}
