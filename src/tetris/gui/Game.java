package tetris.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.game.Board;
import tetris.game.Logic;

public class Game extends JPanel {
    private Logic logic;
    private Board board;
    private int blockSize;
    private Graphics game;
    
    public Game(Logic logic, Board board) {
        this.logic = logic;
        this.board = board;
        this.blockSize = 30;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        board = logic.getBoard();
        super.paintComponent(g);
        setBackground(Color.lightGray);
        this.game = g.create();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCell(i, j) == 0) {
                    game.setColor(Color.black);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 1) {
                    game.setColor(Color.cyan);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 2) {
                    game.setColor(Color.yellow);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 3) {
                    game.setColor(Color.magenta);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 4) {
                    game.setColor(Color.lightGray);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 5) {
                    game.setColor(Color.blue);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 6) {
                    game.setColor(Color.red);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } else if (board.getCell(i, j) == 7) {
                    game.setColor(Color.green);
                    game.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
                } 
            }
        }
    }
    
}
