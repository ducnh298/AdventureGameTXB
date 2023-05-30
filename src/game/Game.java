package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    UI ui = new UI();
    VisibilityManager vm = new VisibilityManager(ui);
    ChoiceHandler choiceHandler = new ChoiceHandler();
    Story story = new Story(this, ui, vm);

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        ui.createTitleScreen(choiceHandler);
        ui.createGameScreen(choiceHandler);
        vm.showTitleScreen();
        ui.loadImg();
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String yourChoice = e.getActionCommand();
            switch (yourChoice) {
                case "start":
                    story.gameSetup();
                    break;
                case "restart":
                    vm.showTitleScreen();
                    break;
                case "quit":
                    System.exit(0);
                    break;
                case "c1":
                    story.selectedPosition(story.nextPosition1);
                    break;
                case "c2":
                    story.selectedPosition(story.nextPosition2);
                    break;
                case "c3":
                    story.selectedPosition(story.nextPosition3);
                    break;
                case "c4":
                    story.selectedPosition(story.nextPosition4);
                    break;
                case "map":
                    story.selectedPosition("map");
                    break;
            }

        }
    }
}
