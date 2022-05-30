package org.main;


public class GameManager {

	Board board = new Board();
	MoveAble[] player;
	BoardDisplay display;

	public GameManager(MoveAble p1, MoveAble p2, BoardDisplay disp) {
		player = new MoveAble[]{p1, p2};
		display = disp;
	}

	void run() {
		while (!board.gameOver()) {
			display.display(board);
			int playerIndex = board.onTurn() == Symbols.cross ? 0 : 1;
			board = player[playerIndex].move(board);
		}
		display.display(board);
		System.out.println("End");
	}
}