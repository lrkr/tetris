package tetris.game;

import tetris.gui.GUI;

public class Main {

    public static void main(String[] args) {
        Logic logic = new Logic();
        GUI gui = new GUI(logic, logic.getBoard());
        logic.setGui(gui);
        logic.startGame();
        logic.start();
        gui.run();
    }
}
