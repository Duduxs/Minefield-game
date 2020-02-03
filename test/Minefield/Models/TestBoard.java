package Minefield.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import models.Board;

class TestBoard {

	Board b1 = new Board(3,3,3);
	
	@Test
	void testMyGoal() {
		assertEquals(false, b1.goalAchieved());
	}

}
