package org.ui;

import org.main.Board;
import org.main.BoardDisplay;

public class TuiBoard implements BoardDisplay {
	@Override
	public void display(Board board) {
		System.out.println("\ta b c");
		for (int y = 0; y < 3; y++) {
			System.out.print((y + 1) + "\t");
			for (int x = 0; x < 3; x++) {
				System.out.print(board.get(x,y).playerToChar());
				if (x != 2)
					System.out.print("|");
			}
			System.out.println();
			if (y != 2)
				System.out.println("   --+-+--");
		}
	}
}
