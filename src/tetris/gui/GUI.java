package tetris.gui;

import tetris.game.Logic;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.game.Board;

public class GUI implements Runnable {

    private JFrame frame;
    private Logic logic;
    private Game game;
    private Board board;

    public GUI(Logic logic, Board board) {
        this.logic = logic;
        this.board = board;
        this.game = new Game(logic, board);
    }
    
    public void executeCommand(String command) {
        logic.executeCommand(command);
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        //205 429
        frame.setPreferredSize(new Dimension(315,639));
        frame.setSize(new Dimension(315, 639));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    
    
    private void createComponents(Container container) {
        game = new Game(logic, board);
        container.setLayout(new BorderLayout());
        container.add(game);

        KeyboardListener keyboardListener = new KeyboardListener(this);
        frame.setFocusable(true);

        frame.addKeyListener(keyboardListener);
    }
    
    public void reDraw() {
        frame.getContentPane().repaint();
    }
    
    public JFrame getJframe() {
        return frame;
    }
}
