package org.main;

public class COM implements MoveAble {

	@Override
	public Board move(Board board) {
		int bestScore = -2;
		Board bestBoard = new Board(board);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!board.moveAllowed(i, j))
					continue;
				int score = -findScore(board.move(i, j), 11);
				if (score > bestScore) {
					bestBoard = board.move(i, j);
					bestScore = score;
				}
			}
		}
		return bestBoard;
	}

	private int findScore(Board board, int height) {
		if (board.gameOver()) {
			if (board.gameWon(board.onTurn()))
				return height;
			if (board.gameWon(board.notOnTurn()))
				return -height;
			return 0;
		}
		int bestScore = -2;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!board.moveAllowed(i, j))
					continue;
				int score = -findScore(board.move(i, j), height - 1);
				if (score > bestScore) {
					bestScore = score;
				}
			}
		}
		return bestScore;
	}
}
