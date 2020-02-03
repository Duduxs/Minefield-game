package application;

import models.Board;

public class Program {

	public static void main(String[] args) {
		
		Board game = new Board(6, 6, 6);
		
		game.open(3, 3);
		game.mark(4, 4);
		game.mark(4, 5);
		
		System.out.println(game);
	}

}
