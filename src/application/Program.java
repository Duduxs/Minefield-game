package application;

import models.Board;

public class Program {

	public static void main(String[] args) {
		
		Board game = new Board(6, 6, 6);
		
		new UI(game);
	}

}
