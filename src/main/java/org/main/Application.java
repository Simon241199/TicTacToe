package org.main;

import org.ui.GuiBoard;
import org.ui.GuiPlayer;

public class Application {

	public static void main(String[] args) {
		GuiPlayer player = new GuiPlayer();
		GuiBoard display = new GuiBoard();
		display.init(player);
		GameManager game = new GameManager(new COM(), player, display);
		game.run();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		display.clean();
	}
}
