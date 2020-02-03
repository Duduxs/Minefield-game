package models;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int rows;
	private int columns;
	private int mines;

	private final List<Field> fields = new ArrayList<>();

	public Board(int rows, int columns, int mines) {
		this.rows = rows;
		this.columns = columns;
		this.mines = mines;
		start();

	}

	/*
	 * Search for a row and a column in my list using the values ​​of the passed
	 * parameter. If the parameters are right open my field
	 */
	public void open(int row, int column) {
		fields.parallelStream().filter(x -> x.getRow() == row && x.getColumns() == column).findFirst()
				.ifPresent(x -> x.open());
	}

	/*
	 * Search for a row and a column in my list using the values ​​of the passed
	 * parameter. If the parameters are right mark my field
	 */
	public void mark(int row, int column) {
		fields.parallelStream().filter(x -> x.getRow() == row && x.getColumns() == column).findFirst()
				.ifPresent(x -> x.changeMark());
	}

	private void start() {
		// Generate the field
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				fields.add(new Field(i, j));
			}
		}
		// Add neighbor
		for (Field f1 : fields) {
			for (Field f2 : fields) {
				f1.addNeighbor(f2);
			}
		}

		// Add and shuflle mines for each match
		long Armedmines = 0;
		do {

			// Randomize mines in the field.
			int random = (int) (Math.random() * fields.size());
			fields.get(random).setMine(true);
			// Calculates the qtd of armedmines in the field.
			Armedmines = fields.stream().filter(x -> x.getMine()).count();
		} while (Armedmines < mines);

	}

	// Return if the player win or not.
	public boolean goalAchieved() {
		return fields.stream().allMatch(x -> x.goalAchieved());
	}

	// Reload the game
	public void reload() {
		fields.stream().forEach(x -> x.reload());
		start();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		// Print out the GUI colums of the game
		sb.append("  ");
		for (int c = 0; c < columns; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}

		sb.append("\n");

		// Print out the GUI rows and Board of the game.
		int l = 0;
		for (int i = 0; i < rows; i++) {
			sb.append(i);
			sb.append(" ");
			for (int j = 0; j < columns; j++) {
				sb.append(" ");
				sb.append(fields.get(l));
				sb.append(" ");
				l++;

			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
