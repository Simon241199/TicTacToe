package org.main;

import java.util.Arrays;

import static java.lang.String.format;

public class Board {
	private final Symbols[][] board = new Symbols[3][3];
	private Symbols playerOnTurn = Symbols.cross;

	Board() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				board[x][y] = Symbols.empty;
			}
		}
	}

	Board(Board other) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				board[x][y] = other.board[x][y];
			}
		}
		playerOnTurn = other.playerOnTurn;
	}

	private Board(Board other, int moveX, int moveY) {
		playerOnTurn = other.playerOnTurn == Symbols.cross ? Symbols.circle : Symbols.cross;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				board[x][y] = other.board[x][y];
				if (x == moveX && y == moveY)
					board[x][y] = other.playerOnTurn;
			}
		}
	}

	Symbols onTurn() {
		return playerOnTurn;
	}

	Symbols notOnTurn() {
		return playerOnTurn == Symbols.cross ? Symbols.circle : Symbols.cross;
	}


	public Board move(int x, int y) {
		if (!moveAllowed(x, y)) {
			throw new IllegalArgumentException(format("Move %d, %d is not allowed\n", x, y));
		}
		return new Board(this, x, y);
	}

	public boolean moveAllowed(int x, int y) {
		if (x < 0 || x >= 3 || y < 0 || y >= 3)
			return false;
		return board[x][y] == Symbols.empty;
	}

	public Symbols get(int x, int y) {
		return board[x][y];
	}

	boolean gameOver() {
		long emptyCount = Arrays.stream(board).flatMap(Arrays::stream).filter(x -> x == Symbols.empty).count();
		if (emptyCount == 0) {
			return true;
		}

		return gameWon(Symbols.circle) || gameWon(Symbols.cross);
	}

	boolean gameWon(Symbols p) {
		if (p == Symbols.empty)
			return false;

		for (int i = 0; i < 3; i++) {
			boolean wonVertical = true;
			boolean wonHorizontal = true;
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != p)
					wonVertical = false;
				if (board[j][i] != p)
					wonHorizontal = false;
			}
			if (wonVertical || wonHorizontal)
				return true;
		}
		// Check Diagonals:
		return board[1][1] == p && (board[2][0] == p && board[0][2] == p || board[0][0] == p && board[2][2] == p);
	}
}
