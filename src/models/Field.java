package models;

import java.util.ArrayList;
import java.util.List;

public class Field {

	private final int rows;
	private final int colums;
	
	private boolean open;
	private boolean mine;
	private boolean marked;
	
	private List<Field> neighbor = new ArrayList<>();

	public Field(int rows, int colums) {
		this.rows = rows;
		this.colums = colums;
		
	}
	


	@Override
	public String toString() {
		return rows + ", " + colums;
	}

}
