package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private final GUI gui;
    
    
    public KeyboardListener(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            gui.executeCommand("Left");
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            gui.executeCommand("Right");
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            gui.executeCommand("Rotate");
        }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            gui.executeCommand("Down");
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            gui.executeCommand("Drop");
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {               
    }
}
