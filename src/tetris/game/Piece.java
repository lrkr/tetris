package tetris.game;

import java.util.ArrayList;
import java.util.List;

//oikeet: 
//1: line piece 
//2: cube 
//3: T 
//4: L 
//5: reverse L 
//6: squiggly 
//7: reverse squiggly
public class Piece {

    private List<Block> pieces;
    private int type;
    private int centerX;
    private int centerY;

    public Piece(int type) {
        this.type = type;
        this.pieces = new ArrayList<>();

        if (type == 1) {
            pieces.add(new Block(type, 3, 2));
            pieces.add(new Block(type, 4, 2));
            pieces.add(new Block(type, 5, 2));
            pieces.add(new Block(type, 6, 2));
            this.centerX = 4;
            this.centerY = 2;
        } else if (type == 2) {
            pieces.add(new Block(type, 4, 2));
            pieces.add(new Block(type, 5, 2));
            pieces.add(new Block(type, 4, 3));
            pieces.add(new Block(type, 5, 3));
        } else if (type == 3) {
            pieces.add(new Block(type, 4, 2));
            pieces.add(new Block(type, 3, 3));
            pieces.add(new Block(type, 4, 3));
            pieces.add(new Block(type, 5, 3));
            this.centerX = 4;
            this.centerY = 3;
        } else if (type == 4) {
            pieces.add(new Block(type, 3, 3));
            pieces.add(new Block(type, 4, 3));
            pieces.add(new Block(type, 5, 3));
            pieces.add(new Block(type, 3, 2));
            this.centerX = 4;
            this.centerY = 3;
        } else if (type == 5) {
            pieces.add(new Block(type, 3, 3));
            pieces.add(new Block(type, 4, 3));
            pieces.add(new Block(type, 5, 3));
            pieces.add(new Block(type, 5, 2));
            this.centerX = 4;
            this.centerY = 3;
        } else if (type == 6) {
            pieces.add(new Block(type, 3, 2));
            pieces.add(new Block(type, 4, 2));
            pieces.add(new Block(type, 4, 3));
            pieces.add(new Block(type, 5, 3));
            this.centerX = 4;
            this.centerY = 3;
        } else if (type == 7) {
            pieces.add(new Block(type, 3, 3));
            pieces.add(new Block(type, 4, 3));
            pieces.add(new Block(type, 4, 2));
            pieces.add(new Block(type, 5, 2));
            this.centerX = 4;
            this.centerY = 3;

        }
    }

    public List<Block> getBlocks() {
        return this.pieces;
    }

    public void deletePiece() {
        this.pieces = new ArrayList<>();
    }

    public boolean rotate(Logic logic, Board board) {
        if (this.type == 2) {
            return true;
        }

        for (Block block : pieces) {
            rotateBlock(block);
        }
        for (Block piece : pieces) {
            //Yeah... This is completely screwed up. But probably wont crash!
            if (piece.getX() > board.getWidth() - 1 || piece.getX() < 0 || piece.getY() > board.getHeight() - 1) {
                for (int i = 0; i < 3; i++) {
                    for (Block block : pieces) {
                        rotateBlock(block);
                    }
                }
                return false;
            }
        }
        return true;
    }

    public void rotateBlock(Block block) {
        block.setX(block.getX() - this.centerX);
        block.setY(block.getY() - this.centerY);
        int rotatedY = block.getY();
        int rotatedX = rotatedY * -1;
        rotatedY = block.getX();
        rotatedX = rotatedX + this.centerX;
        rotatedY = rotatedY + this.centerY;
        block.setX(rotatedX);
        block.setY(rotatedY);
    }

    public void moveRight() {
        this.centerX++;
        for (Block block : pieces) {
            block.moveRight();
        }
    }

    public void moveLeft() {
        this.centerX--;
        for (Block block : pieces) {
            block.moveLeft();
        }
    }

    public void lower() {
        this.centerY++;
        for (Block block : pieces) {
            block.lower();
        }
    }

    public int getCenterX() {
        return this.centerX;
    }

    public int getCenterY() {
        return this.centerY;
    }

    public int getType() {
        return this.type;
    }
}
