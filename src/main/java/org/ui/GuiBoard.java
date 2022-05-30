package org.ui;

import org.main.Board;
import org.main.BoardDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class GuiBoard implements BoardDisplay, MouseListener {
	JFrame frame;
	JPanel panel;
	Font font = new Font("SansSerif", Font.BOLD, 200);
	JLabel[][] field = new JLabel[3][3];
	boolean initialized = false;

	GuiPlayer player;
	public void init(GuiPlayer player) {
		initialized = true;

		this.player = player;

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		panel = new JPanel();
		GridLayout layout = new GridLayout(3, 3);
		panel.setLayout(layout);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				field[j][i] = new JLabel();
				field[j][i].setHorizontalAlignment(JLabel.CENTER);
				field[j][i].setVerticalAlignment(JLabel.CENTER);
				field[j][i].setFont(font);
				panel.add(field[j][i]);
				if (player != null)
					field[j][i].addMouseListener(this);
			}
		}

		frame.add(panel);
		frame.setVisible(true);
	}

	@Override
	public void display(Board board) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		if (!initialized)
			init(null);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				field[j][i].setText(board.get(j, i).playerToChar() + "");
			}
		}
	}

	public void clean() {
		initialized = false;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if(field[x][y].equals(e.getComponent())){
					player.setLastClickLocation(x,y);
					return;
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
