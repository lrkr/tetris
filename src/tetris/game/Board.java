package tetris.game;

import java.util.List;

public class Board {

    private int[][] board;
    private int height;
    private int width;
    private int linesCleared;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new int[height][width];
        this.linesCleared = 0;
    }

    public void initialize() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void checkLines() {
        for (int i = height - 1; i > 0; i--) {
            if (lineComplete(i)) {
                linesCleared++;
                for (int j = 0; j < width; j++) {
                    board[i][j] = 0;
                }
                for (int startHeight = i; startHeight > 0; startHeight--) {
                    for (int j = 0; j < width; j++) {
                        board[startHeight][j] = board[startHeight - 1][j];
                    }
                }
                for (int j = 0; j < width; j++) {
                    board[0][j] = 0;
                }
                i++;
            }
        }
    }

    public boolean lineComplete(int line) {
        for (int i = 0; i < width; i++) {
            if (board[line][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void removePiece(Piece piece) {
        List<Block> pieceParts = piece.getBlocks();
        for (Block part : pieceParts) {
            board[part.getY()][part.getX()] = 0;
        }
    }

    public void addPiece(Piece piece) {
        List<Block> pieceParts = piece.getBlocks();
        for (Block part : pieceParts) {
            board[part.getY()][part.getX()] = part.getColor();
        }
    }

    public void drawBoard() {
        for (int i = 2; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
        System.out.println("-------------");
    }

    public int[][] getBoard() {
        return this.board;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getCell(int y, int x) {
        return this.board[y][x];
    }
}
