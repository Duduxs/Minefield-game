package application;

import models.Board;

public class Program {

	public static void main(String[] args) {
		
		Board game = new Board(8, 10, 10);
		
		new UI(game);
	}

}
