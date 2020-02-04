package application;

import java.util.Scanner;

import exceptions.exitException;
import exceptions.minefieldException;
import models.Board;

public class UI {

	private Board board;
	Scanner keyboard = new Scanner(System.in);

	public UI(Board board) {
		this.board = board;
		cleanScreen();
		startGame();
	}

	private void cleanScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void startGame() {
		try {
			while (!board.goalAchieved()) {

				System.out.println(board);

				System.out.print("Enter [x,y]: ");
				String value = keyboard.next();
				// Split the String in the numbers and make a casting
				int x = Integer.parseInt(value.substring(0, 1));
				int y = Integer.parseInt(value.substring(2));

				System.out.print("\n[1] - Open \n[2] - Mark/MarkOff \n[3] - Exit \n\n");
				System.out.print("Value: ");
				value = keyboard.next();

				cleanScreen();

				if ("1".equalsIgnoreCase(value))
					board.open(x, y);
				else if ("2".equalsIgnoreCase(value))
					board.mark(x, y);

				else if ("3".equalsIgnoreCase(value))
					throw new exitException("Thank you for playing!");

			}

		} catch (minefieldException e) {

			System.out.println("\nYou found a mine! You Lose!");
			System.out.println(board);
			System.out.print("Do you want to play another match? (S/N): ");
			String choose = keyboard.next().toUpperCase();

			if (choose.contentEquals("S")) {

				cleanScreen();
				board.reload();
				startGame();

			} else { throw new exitException("Thank you for playing!"); }
			
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("\nSay two values for [x] and [y] in this format 0,0 !");
		} catch (NumberFormatException e) {
			System.out.println("\nSay two values for [x] and [y] in this format 0,0 !");
		}

		finally {
			keyboard.close();
		}
	}

}
