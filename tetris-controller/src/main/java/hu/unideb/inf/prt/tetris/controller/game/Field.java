package hu.unideb.inf.prt.tetris.controller.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javafx.scene.traversal.Direction;

public class Field {

	private static Logger logger = LoggerFactory.getLogger(Field.class);

	private int[][] map;

	public Field() {
		map = new int[20][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 0;
			}
		}
	}

	public boolean isBadSpawn(Piece piece) {
		int[][] pieceFigure = piece.getFigure();
		for (int i = 0; i < pieceFigure.length; i++) {
			for (int j = 0; j < pieceFigure[i].length; j++) {
				if (pieceFigure[i][j] != 0 && map[piece.getY() + i][piece.getX() + j] != 0) {
					logger.info("GAME OVER! New piece cannot spawn");
					return true;
				}
			}
		}
		return false;
	}

	public boolean isPieceCollide(Piece piece, PieceAction action) {
		int xx = 0;
		int yy = 0;
		switch (action) {
		case DOWN: {
			yy = 1;
			break;
		}
		case LEFT: {
			xx = -1;
			break;
		}
		case RIGHT: {
			xx = 1;
			break;
		}
		default: {
		}
		}
		int[][] figure = piece.getFigure();
		for (int i = 0; i < figure.length; i++) {
			for (int j = 0; j < figure[i].length; j++) {
				if (figure[i][j] != 0) {
					try {
						if (map[piece.getY() + i + yy][piece.getX() + j + xx] != 0) {
							logger.info("Piece Collides with an already settled piece");
							return true;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						logger.info("Piece Collides with the border");
						return true;
					}
				}
			}
		}
		return false;
	}

	public void settlePiece(Piece piece) {
		int[][] figure = piece.getFigure();
		for (int i = 0; i < figure.length; i++) {
			for (int j = 0; j < figure[i].length; j++) {
				if (figure[i][j] != 0) {
					map[piece.getY() + i][piece.getX() + j] = figure[i][j];
				}
			}
		}
		logger.info("Piece settled");
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
}
