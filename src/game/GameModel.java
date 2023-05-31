package game;

import model.*;
import model.weapons.Weapon_Knife;

import javax.swing.*;

public class GameModel {
    UI ui;
    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});
    double difficultRate;
    Player player;
    Weapon knife, longSword, trident, demonSword;
    Armor[] armors;
    Spell selectedSpell, fireStorm, lightningBolt, waterSurge, poisonBreeze;
    Effect paralyzedEffect, poisonousEffect;

    Monster goblin, evilWitch, riverMonster, shadowSerpent, demonKing;

    String position, lastPosition;
    boolean isAngryGuard, isRestAtTent, isTakenArmor, isTakenLongSword, isTakenPower,
            witchQuestActive, isTakenGoblinEar, isTakenApple,
            isALiveRiverMonster, isALiveGoblin, isDefeatedEvilWitch, isAliveShadowSerpent, isAliveDemonKing;
    int appleOnTree;

    public GameModel(UI ui) {
        this.ui = ui;
    }

    public void setup() {
        difficultRate = ((Difficulty) difficulties.getElementAt(ui.difficultyComboBox.getSelectedIndex())).getValue();

        player = new Player(25);
        ui.hpLabel.setText("HP: " + player.getPlayerHP() + "/" + player.getPlayerMaxHP());

        if (knife == null)
            knife = new Weapon_Knife();
        player.addWeapon(knife);

        ui.weaponComboBox.removeAllItems();
        ui.spellComboBox.removeAllItems();

        ui.weaponComboBox.addItem(knife.getName());

        ui.armorLabel.setVisible(false);
        ui.mapButton.setVisible(false);
        ui.spellComboBox.setVisible(false);

        isAngryGuard = isRestAtTent = isTakenArmor = isTakenLongSword = isTakenPower = false;
        witchQuestActive = isTakenGoblinEar = isTakenApple = isDefeatedEvilWitch = false;
        isALiveRiverMonster = isALiveGoblin = isAliveShadowSerpent = isAliveDemonKing = true;
        appleOnTree = 3;
    }
}
