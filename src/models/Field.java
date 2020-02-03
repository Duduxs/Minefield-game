package models;

import java.util.ArrayList;
import java.util.List;

public class Field {

	private final int rows;
	private final int columns;

	private boolean open;
	private boolean mine;
	private boolean marked;

	private List<Field> neighbor = new ArrayList<>();

	public Field(int rows, int colums) {
		this.rows = rows;
		this.columns = colums;

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

	@Override
	public String toString() {
		return rows + ", " + columns;
	}

}
