package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.minefieldException;

public class Field {

	private final int rows;
	private final int columns;

	private boolean open;
	private boolean mine;
	private boolean marked;

	public static final String YELLOW = "\033[0;33m";
	public static final String RED = "\033[0;31m";  
	  public static final String GREEN_BRIGHT = "\033[0;92m";
	public static final String RESET = "\033[0m"; 

	private List<Field> neighbor = new ArrayList<>();

	public Field(int rows, int colums) {
		this.rows = rows;
		this.columns = colums;

	}

	public int getRow() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public boolean getOpen() {
		return open;
	}

	public boolean getMine() {
		return mine;
	}

	public void setMine(boolean b) {
		mine = b;
	}

	public boolean getMarked() {
		return marked;
	}

	void setOpen(boolean open) {
		this.open = open;
	}

	public boolean addNeighbor(Field neighbors) {

		// var which return true if the neighbor are in the diagonal, false if aren't.
		boolean diagonal = rows != neighbors.rows && columns != neighbors.columns ? true : false;

		// Distance between rows and columns and sum.
		int distancerow = Math.abs(rows - neighbors.rows);
		int distancecolumn = Math.abs(columns - neighbors.columns);
		int distancesum = distancerow + distancecolumn;

		// Neighbor are in the cross
		if (distancesum == 1 && !diagonal) {
			neighbor.add(neighbors);
			return true;

			// Neighbor are in the diagonal
		} else if (distancesum == 2 && diagonal) {
			neighbor.add(neighbors);
			return true;
		}
		// Not exists neighbor
		else
			return false;

	}

	public void changeMark() {
		// Logic for input the flag, the field must be closed (false)
		if (open == false) 
			marked = !marked;
	}
	

	public boolean open() {
		// Logic for open a field
		if (open == false && marked == false) {
			open = true;

			if (mine == true)
				throw new minefieldException("You found a mine! You Lose!");

			/*
			 * Check if the neighbors are security Checking the list and not finding a mine.
			 * So, for each neighboar safe i expand the field.
			 */
			if (neighbor.stream().noneMatch(x -> x.mine))
				neighbor.forEach(x -> x.open());

			return true;
		} else
			return false;
	}

	// This method represents -> Being able to do an action without losing
	public boolean goalAchieved() {
		boolean fieldFound = mine == false && open == true;
		boolean fieldProtected = mine == true && marked == true;
		return fieldFound || fieldProtected;
	}

	// Count the mines in the neighbor
	public long mineInNeighbor() {
		return neighbor.stream().filter(x -> x.mine).count();
	}

	public void reload() {
		open = false;
		mine = false;
		marked = false;
	}

	@Override
	public String toString() {

		if (marked == true) {
			return YELLOW + "x" + RESET;
		} else if (open == true && mine == true) {
			return RED + "*" + RESET;
		} else if (open == true && mineInNeighbor() > 0) {
			return GREEN_BRIGHT + Long.toString(mineInNeighbor()) + RESET;
		} else if (open == true) {
			return " "  ;
		} else {
			return "?";
		}
	}
}
