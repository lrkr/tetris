package tetris.game;

public class Block {

    private int color;
    private int x;
    private int y;
        
    public Block(int color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void lower() {
        this.y++;
    }
    
    public void moveRight() {
        this.x++;
    }
    
    public void moveLeft() {
        this.x--;
    }

    public int getColor() {
        return this.color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
