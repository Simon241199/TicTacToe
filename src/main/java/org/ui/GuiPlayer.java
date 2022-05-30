package org.ui;

import org.main.Board;
import org.main.MoveAble;

import java.util.concurrent.Semaphore;

public class GuiPlayer implements MoveAble {
	int x = -1;
	int y = -1;
	Semaphore sem = new Semaphore(0);

	@Override
	public Board move(Board board) {
		int x = 0;
		int y = 0;

		sem.drainPermits();
		try {
			do {
				sem.acquire();
				x = this.x;
				y = this.y;
			} while (!board.moveAllowed(x, y));
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		return board.move(x, y);
	}

	void setLastClickLocation(int x, int y) {
		this.x = x;
		this.y = y;
		sem.release();
	}
}
