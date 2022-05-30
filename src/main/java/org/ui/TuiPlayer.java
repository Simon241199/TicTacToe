package org.ui;

import org.main.Board;
import org.main.MoveAble;

import java.util.Scanner;

public class TuiPlayer implements MoveAble {

	@Override
	public Board move(Board board) {
		Scanner scan = new Scanner(System.in);
		int x;
		int y;
		while (true) {
			String in = scan.next();
			if (in.length() != 2) {
				continue;
			}
			x = in.charAt(0) - 'a';
			y = in.charAt(1) - '1';
			if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
				if (board.moveAllowed(x, y)) {
					break;
				}
			}
		}
		return board.move(x, y);
	}
}
