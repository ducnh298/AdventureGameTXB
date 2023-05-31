package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    UI ui = new UI(1440,960);
//    UI ui = new UI();
    VisibilityManager visibilityManager = new VisibilityManager(ui);
    ChoiceHandler choiceHandler = new ChoiceHandler();
    GameModel gameModel = new GameModel(ui);
    StoryManager storyManager = new StoryManager(this);

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        ui.createWindow(choiceHandler);
        ui.createTitleScreen(choiceHandler);
        ui.createGameScreen(choiceHandler);
        visibilityManager.showTitleScreen();
        ui.loadImg();
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String yourChoice = e.getActionCommand();
            switch (yourChoice) {
                case "start":
                    gameModel.setup();
                    visibilityManager.showGameScreen();
                    storyManager.townGate();
                    break;
                case "restart":
                    visibilityManager.showTitleScreen();
                    break;
                case "quit":
                    System.exit(0);
                    break;
                case "c1":
                    storyManager.selectedPosition(storyManager.nextPosition1);
                    break;
                case "c2":
                    storyManager.selectedPosition(storyManager.nextPosition2);
                    break;
                case "c3":
                    storyManager.selectedPosition(storyManager.nextPosition3);
                    break;
                case "c4":
                    storyManager.selectedPosition(storyManager.nextPosition4);
                    break;
                case "map":
                    storyManager.selectedPosition("map");
                    break;
            }

        }
    }
}
