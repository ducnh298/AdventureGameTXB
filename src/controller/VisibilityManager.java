package controller;

import view.UI;

public class VisibilityManager {
    UI ui;

    public VisibilityManager(UI ui) {
        this.ui = ui;
    }

    public void showGameScreen() {
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);
        ui.difficultySelectPanel.setVisible(false);
        ui.startImagePanel.setVisible(false);

        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerStatPanel.setVisible(true);
        ui.gameImagePanel.setVisible(true);
    }

    public void showTitleScreen() {
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);
        ui.difficultySelectPanel.setVisible(true);
        ui.startImagePanel.setVisible(true);

        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerStatPanel.setVisible(false);
        ui.gameImagePanel.setVisible(false);
    }
}
