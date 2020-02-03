package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.minefieldException;

public class Field {

	private final int rows;
	private final int columns;

	private boolean open;
	private boolean mine ;
	private boolean marked;

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

	public boolean addNeighbor(Field neighbor) {

		// var which return true if the neighbor are in the diagonal, false if aren't.
		boolean diagonal = rows != neighbor.rows && columns != neighbor.columns ? true : false;

		// Distance between rows and columns and sum.
		int distancerow = Math.abs(rows - neighbor.rows);
		int distancecolumn = Math.abs(columns - neighbor.columns);
		int distancesum = distancerow + distancecolumn;

		// Neighbor are in the diagonal
		if (distancesum == 2 && diagonal) {
			neighbor.addNeighbor(neighbor);
			return true;

			// Neighbor are in the cross
		} else if (distancesum == 1 && !diagonal) {
			neighbor.addNeighbor(neighbor);
			return true;
		}
		// Not exists neighbor
		else
			return false;

	}

	public void changeMark() {
		// Logic for input the flag, the field must be closed (false)
		if (open == false)
			marked = true;
	}

	public boolean open() {
		// Logic for open a field
		if (open == false && marked == false) {
			open = true;

			if (mine == true)
				throw new minefieldException("You Lose!");

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
	// This method represents ->  Being able to do an action without losing
	public boolean goalAchieved() {
		boolean fieldFound = mine == false && open == true;
		boolean fieldProtected = mine == true && marked == true;
		return fieldFound || fieldProtected;
	}
	//Count the mines in the neighbor
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
			return "x";
		} else if (open == true && mine == true) {
			return "*";
		} else if (open == true && mineInNeighbor() > 0) {
			return Long.toString(mineInNeighbor());
		} else if (open == true) {
			return " ";
		} else {
			return "?";
		}
	}
}
