package org.main;

public enum Symbols {
	empty,
	circle,
	cross;
	public char playerToChar(){
		if(this == Symbols.circle)
			return 'O';
		if(this == Symbols.cross)
			return 'X';
		return ' ';
	}
}