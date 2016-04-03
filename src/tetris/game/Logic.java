package tetris.game;

/*
todo:
line piece at bottom crash fix
 */
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import tetris.gui.GUI;

public class Logic extends Timer implements ActionListener {

    private Board board;
    private Scanner reader;
    private Random random;
    private boolean pieceActive;
    private Piece piece;
    private int tickCounter;
    private GUI gui;
    private boolean panic;

    public Logic() {
        super(200, null);
        this.board = new Board(20, 10);
        this.reader = new Scanner(System.in);
        this.random = new Random();
        this.pieceActive = false;
        this.tickCounter = 0;
        this.panic = false;
        addActionListener(this);
        setInitialDelay(2000);
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void startGame() {
        board.initialize();
        if (!this.pieceActive) {
            this.piece = newPiece();
            board.addPiece(piece);
            this.pieceActive = true;
        }
    }

    public void executeCommand(String command) {
        if (command.equals("Right")) {
            moveRight();
        } else if (command.equals("Left")) {
            moveLeft();
        } else if (command.equals("Rotate")) {
            rotate();
        } else if (command.equals("Down")) {
            moveDown();
        } else if (command.equals("Drop")) {
            drop();
        }
        gui.reDraw();
    }

    /*
    public void gameLoop() {
        while (true) {
            if (!this.pieceActive) {
                this.piece = newPiece();
                board.addPiece(piece);
                this.pieceActive = true;
            }
            board.drawBoard();
            System.out.print("1: advance tick\n"
                    + "2: right\n"
                    + "3: left\n"
                    + "4: rotate\n"
                    + "5: down\n"
                    + "6: drop\n"
                    + "command: ");
            String command = reader.nextLine();
            if (command.equals("1")) {
                tick();
            } else if (command.equals("2")) {
                moveRight();
            } else if (command.equals("3")) {
                moveLeft();
            } else if (command.equals("4")) {
                rotate();
            } else if (command.equals("5")) {
                moveDown();
            } else if (command.equals("6")) {
                drop();
                tick();
            }
        }
    }
     */
    public void tick() {
        this.tickCounter++;
        board.removePiece(piece);
        if (isValidMove("Down")) {
            moveDown();
        } else {
            board.addPiece(piece);
            board.checkLines();            
            piece.deletePiece();
            this.piece = newPiece();
            board.addPiece(piece);
            gui.reDraw();
        }
    }

    public void rotate() {
        board.removePiece(piece);
        String direction = "";
        int steps = 0;

        //Rotating line piece is completely messed up. It is a gross hack that probably doesn't crash the game.
        //For example if you rotate line piece that is 2 tiles from a wall it will rotate twice and then can't be rotated.
        //Rotate kick not implemented if the piece is next to a block, should work with a wall though.
        //Returning to the original position if rotation was invalid is ugly..
        //Turns out rotating stuff is hard when you don't plan properly!
        
        if (piece.getCenterY() == board.getHeight() - 1) {
            board.addPiece(piece);
            return;
        }

        if (piece.getCenterX() == 9 && isValidMove("Left")) {
            direction = "Left";
            steps++;
            piece.moveLeft();
            if (piece.getType() == 1 && isValidMove("Left")) {
                piece.moveLeft();
                steps++;
            }
        } else if (piece.getCenterX() == 9 && !isValidMove("Left")) {
            board.addPiece(piece);
            return;
        }

        if (piece.getCenterX() == 0 && isValidMove("Right")) {
            piece.moveRight();
            direction = "Right";
            steps++;
            if (piece.getType() == 1 && isValidMove("Right")) {
                piece.moveRight();
                steps++;
            }
        } else if (piece.getCenterX() == 0 && !isValidMove("Right")) {
            board.addPiece(piece);
            return;
        }
        if (piece.rotate(this, board)) {
            List<Block> pieces = piece.getBlocks();
            for (Block block : pieces) {
                if (board.getCell(block.getY(), block.getX()) != 0) {
                    if (direction.equals("Left")) {
                        for (int i = 0; i < steps; i++) {
                            piece.moveLeft();
                        }
                    }
                    if (direction.equals("Right")) {
                        for (int i = 0; i < steps; i++) {
                            piece.moveRight();
                        }
                    }
                    piece.rotate(this, board);
                    piece.rotate(this, board);
                    piece.rotate(this, board);
                }
            }
        } else if (isValidMove("Left") || piece.getCenterY() < 17) {
            if (panic = false) {
                panic = true;
                piece.moveLeft();
                rotate();
            }
        } else if (isValidMove("Right") || piece.getCenterY() < 17) {
            if (panic = false) {
                panic = true;
                piece.moveRight();
                rotate();
            }
        }
        panic = false;
        board.addPiece(piece);
    }

    public Piece newPiece() {
        //Piece newPiece = new Piece(1);
        Piece newPiece = new Piece(random.nextInt(7) + 1);
        List<Block> pieces = newPiece.getBlocks();
        for (Block block : pieces) {
            if (board.getCell(block.getY(), block.getX()) != 0) {
                gameOver();
            }
        }
        return newPiece;
    }

    public void gameOver() {
        System.out.println("GAME OVER");
        System.exit(-1);
    }

    public void drop() {
        while (true) {
            board.removePiece(piece);
            if (isValidMove("Down")) {
                piece.lower();
                board.addPiece(piece);
            } else {
                board.addPiece(piece);                
                tick();
                break;
            }
        }
    }

    public void moveRight() {
        board.removePiece(piece);
        if (isValidMove("Right")) {
            piece.moveRight();
            board.addPiece(piece);
        } else {
            board.addPiece(piece);
        }
    }

    public void moveLeft() {
        board.removePiece(piece);
        if (isValidMove("Left")) {
            piece.moveLeft();
            board.addPiece(piece);
        } else {
            board.addPiece(piece);
        }
    }

    public void moveDown() {
        board.removePiece(piece);
        if (isValidMove("Down")) {
            piece.lower();
            board.addPiece(piece);
        } else {
            board.addPiece(piece);
        }
    }

    public boolean isValidMove(String direction) {
        List<Block> blocks = piece.getBlocks();
        if (direction.equals("Right")) {
            for (Block block : blocks) {
                if (block.getX() + 1 >= board.getWidth()) {
                    return false;
                }
                if (board.getCell(block.getY(), block.getX() + 1) != 0) {
                    return false;
                }
            }
        } else if (direction.equals("Left")) {
            for (Block block : blocks) {
                if (block.getX() == 0) {
                    return false;
                }
                if (board.getCell(block.getY(), block.getX() - 1) != 0) {
                    return false;
                }
            }
        } else if (direction.equals("Down")) {
            for (Block block : blocks) {
                if (block.getY() >= board.getHeight() - 1) {
                    return false;
                }
                if (board.getCell(block.getY() + 1, block.getX()) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getHeight() {
        return board.getHeight();
    }

    public int getWidth() {
        return board.getWidth();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (gui.getJframe() != null) {
            tick();
            gui.reDraw();
            setDelay(500);
        }
    }
}
