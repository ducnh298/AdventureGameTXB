package view;

import controller.Game;
import controller.VisibilityManager;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    public String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});
    double difficultRate = 1;
    Player player;
    Map<String, Weapon> weaponMap = new HashMap() {
        {
            put("Knife", new Weapon("Knife", 3, 5));
            put("Long sword", new Weapon("Long sword", 4, 6));
            put("Bow", new Weapon("Bow", 3, 7));
            put("Trident", new Weapon("Trident", 5, 7));
            put("Holy sword", new Weapon("Holy sword", 999, 999));
        }
    };
    Armor[] armors = {new Armor("Wooden armor", 1), new Armor("Silver armor", 2), new Armor("Golden armor", 3)};

    Monster goblin;
    Monster riverMonster;

    String position;
    boolean isAngryGuard, isRestAtTent, isDefeatedRiverMonster, isDefeatedGoblin, isTakenArmor;
    int appleOnTree;
    Random rand = new Random();

    public Story(Game game, UI ui, VisibilityManager vm) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
    }

    public void gameSetup() {
        difficultRate = ((Difficulty) difficulties.getElementAt(ui.difficultyComboBox.getSelectedIndex())).getValue();

        player = new Player(20);
        ui.hpNumberLabel.setText(player.getPlayerHP() + "/20");
        player.addWeapon(weaponMap.get("Knife"));
        player.getWeaponList().forEach(weapon -> ui.weaponComboBox.addItem(weapon.getName()));

        isAngryGuard = isRestAtTent = isDefeatedRiverMonster = isDefeatedGoblin = isTakenArmor = false;
        appleOnTree = 3;

        goblin = new Monster("Goblin", (int) Math.ceil(6 * difficultRate), (int) Math.ceil(2 * difficultRate));
        riverMonster = new Monster("River monster", (int) Math.ceil(10 * difficultRate), (int) Math.ceil(4 * difficultRate));
        vm.showGameScreen();
        townGate();
    }

    public void selectedPosition(String nextPosition) {
        switch (nextPosition) {
            case "townGate":
                townGate();
                break;
            case "talkGuard":
                talkGuard();
                break;
            case "attackGuard":
                attackGuard();
                break;
            case "crossRoad":
                crossRoad();
                break;
            case "blackSmithHouse":
                blackSmithHouse();
                break;
            case "talkBlackSmith":
                talkBlackSmith();
                break;
            case "takeArmor":
                takeArmor();
                break;
            case "goblinCave":
                goblinCave();
                break;
            case "encounterGoblin":
                encounterGoblin();
                break;
            case "attackGoblin":
                attackGoblin();
                break;
            case "riverSide":
                riverSide();
                break;
            case "northRiver":
                northRiver();
                break;
            case "takeARest":
                takeARest();
                break;
            case "southRiver":
                southRiver();
                break;
            case "encounterRiverMonster":
                encounterRiverMonster();
                break;
            case "attackRiverMonster":
                attackRiverMonster();
                break;
            case "jungle":
                jungle();
                break;
            case "hitTheAppleTree":
                hitTheAppleTree();
                break;
        }
    }

    public void townGate() {
        position = "townGate";
        ui.mainTextArea.setText("You are now at the gate of the town. \nA guard is standing in front of you.\nWhat would you do?");
        if (isAngryGuard) {
            ui.choice1.setText("Talk to the angry guard");
            ui.choice2.setText("Attack the guard again");
        } else {
            ui.choice1.setText("Talk to the guard");
            ui.choice2.setText("Attack the guard");
        }
        ui.choice3.setText("Enter town");
        ui.choice4.setText("Leave");

        nextPosition1 = "talkGuard";
        nextPosition2 = "attackGuard";
        nextPosition3 = "talkGuard";
        nextPosition4 = "crossRoad";
    }

    public void talkGuard() {
        position = "talkGuard";
        if (isAngryGuard)
            ui.mainTextArea.setText("Guard:\"You again?!\nGet away.\"");
        else
            ui.mainTextArea.setText("Guard:\"Hello stranger!\nI have never seen your face before.\nI cannot let stranger in our town.\"");
        ui.choice1.setText("Leave");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        nextPosition1 = "townGate";
        nextPosition2 = "";
        nextPosition3 = "";
        nextPosition4 = "";
    }

    public void attackGuard() {
        position = "attackGuard";
        isAngryGuard = true;

        int hpLost = (int) Math.ceil(3 * difficultRate);
        player.loseHP(hpLost);
        updatePlayerHealth();
        if (player.getPlayerHP() > 0) {
            ui.mainTextArea.setText("Guard:\"Hey!!! Don't be so stupid!\"\n\nThe guard fought back and hit you hard.\n(You received " + hpLost + " damage)");
            ui.choice1.setText("\"Sorry\"");
            ui.choice2.setText("Leave");
            ui.choice3.setText("");
            ui.choice4.setText("");

            nextPosition1 = "crossRoad";
            nextPosition2 = "crossRoad";
            nextPosition3 = "";
            nextPosition4 = "";
        }
    }

    public void crossRoad() {
        position = "crossRoad";
        ui.mainTextArea.setText("You now at a cross road.\nWhere would you go?");
        ui.choice1.setText("Go North");
        ui.choice2.setText("Go East");
        ui.choice3.setText("Go South");
        ui.choice4.setText("Go West (the town)");
        nextPosition1 = "blackSmithHouse";
        nextPosition2 = "riverSide";
        nextPosition3 = "goblinCave";
        nextPosition4 = "townGate";
    }

    public void blackSmithHouse() {
        position = "blackSmithHouse";
        ui.mainTextArea.setText("There is a blacksmith shop.\nSomeone is forging, look like the owner of this shop");
        ui.choice1.setText("Talk to the blacksmith");
        ui.choice2.setText("Go East");
        ui.choice3.setText("Go South");
        ui.choice4.setText("");
        nextPosition1 = "talkBlackSmith";
        nextPosition2 = "northRiver";
        nextPosition3 = "crossRoad";
        nextPosition4 = "";
    }

    public void talkBlackSmith() {
        position = "talkBlackSmith";
        if (!isDefeatedGoblin) {
            ui.mainTextArea.setText("The BlackSmith: \"Hi there! You are not a familiar face around here, right?" +
                    "\nBefore asking me anything, can you do me a favor?" +
                    "\nThere is a goblin which have caused us so much trouble recently." +
                    "\nLast time i saw it was in the cave south from here." +
                    "\nComeback whenever you've killed that little bitch.\"");
            ui.choice1.setText("\"Yeah! I'm on it.\"");
        } else {
            ui.mainTextArea.setText("The BlackSmith: \"Look like you've kill that goblin. Thank you\"");
            if (!isTakenArmor) {
                ui.choice1.setText("Take reward");
                nextPosition1 = "takeArmor";
            } else {
                ui.choice1.setText("");
                nextPosition1 = "";
            }
        }
        ui.choice2.setText("Leave");
        ui.choice3.setText("");
        ui.choice4.setText("");

        nextPosition2 = "blackSmithHouse";
        nextPosition3 = "";
        nextPosition4 = "";
    }

    public void takeArmor() {
        Armor armor = armors[rand.nextInt(3)];
        player.setArmor(armor);
        if (player.getArmor().getName().equals("Wooden armor"))
            ui.armorLabel.setForeground(Color.decode("#544335"));
        else if (player.getArmor().getName().equals("Silver armor"))
            ui.armorLabel.setForeground(Color.decode("#ff964f"));
        else if (player.getArmor().getName().equals("Golden armor"))
            ui.armorLabel.setForeground(Color.decode("#EACE09"));
        isTakenArmor = true;
        ui.mainTextArea.setText("The BlackSmith: \"Take this " + armor.getName() + ".\nMaybe it could help you.\"");
        ui.choice1.setText("\"Thank you\"");
        ui.choice2.setText("Leave");
        ui.choice3.setText("");
        ui.choice4.setText("");
        nextPosition1 = "blackSmithHouse";
        nextPosition2 = "blackSmithHouse";
        nextPosition3 = "";
        nextPosition4 = "";
    }

    public void goblinCave() {
        position = "goblinCave";
        ui.mainTextArea.setText("There is a small cave in the mountainside,\nWhat would you do?");
        if (!isDefeatedGoblin) {
            ui.choice1.setText("Throw a wooden stick into it");
        } else ui.choice1.setText("");
        ui.choice2.setText("Go North");
        ui.choice3.setText("Go East");
        ui.choice4.setText("");
        nextPosition1 = "encounterGoblin";
        nextPosition2 = "crossRoad";
        nextPosition3 = "southRiver";
        nextPosition4 = "";
    }

    public void encounterGoblin() {
        position = "encounterGoblin";
        if (rand.nextBoolean()) {
            JOptionPane.showMessageDialog(ui.window, "You encounter a goblin!!!");
            encounterMonster(goblin);
        } else {
            JOptionPane.showMessageDialog(ui.window, "Nothing happen.");
            goblinCave();
        }
    }

    public void riverSide() {
        position = "riverSide";
        ui.mainTextArea.setText("You approach a river.\nWhat would you do?");
        ui.choice1.setText("Go North");
        ui.choice2.setText("Go East (cross the river)");
        ui.choice3.setText("Go South");
        ui.choice4.setText("Go West");
        nextPosition1 = "northRiver";
        nextPosition2 = "jungle";
        nextPosition3 = "southRiver";
        nextPosition4 = "crossRoad";
    }

    public void northRiver() {
        position = "northRiver";
        if (!isRestAtTent) {
            ui.mainTextArea.setText("North of the river.\n\nThere is a old tent on the river side (You can rest to recover 5HP).A wooden bridge cross the river. \nNorthern path lead to a waterfall, we couldn't go further.\nWhat would you do?");
            ui.choice1.setText("Take a rest");
            nextPosition1 = "takeARest";
        } else {
            ui.mainTextArea.setText("North of the river.\n\nThere is a old tent on the river side.A wooden bridge cross the river.\nNorthern path lead to a waterfall, we couldn't go further.\nWhat would you do?");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
        ui.choice2.setText("Go East(cross the river)");
        ui.choice3.setText("Go South");
        ui.choice4.setText("Go West");
        nextPosition2 = "mountainside";
        nextPosition3 = "riverSide";
        nextPosition4 = "blackSmithHouse";
    }

    public void takeARest() {
        player.restoreHP(5);
        updatePlayerHealth();
        isRestAtTent = true;
        northRiver();
    }

    public void southRiver() {
        position = "southRiver";
        if (!isDefeatedRiverMonster) {
            ui.mainTextArea.setText("South of the river.\n\nThere is a strange vortex in the middle of the river.\nSouthern path has been blocked by crashed tree, we couldn't go further.\nWhat would you do?");
            ui.choice1.setText("Throw a rock into it");
            nextPosition1 = "encounterRiverMonster";
        } else {
            ui.mainTextArea.setText("South of the river.\n\nSouthern path has been blocked by crashed tree, we couldn't go further.\nWhat would you do?");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
        ui.choice2.setText("Go North");
        ui.choice3.setText("Go East(cross the river)");
        ui.choice4.setText("Go West");
        nextPosition2 = "riverSide";
        nextPosition3 = "";
        nextPosition4 = "goblinCave";
    }

    public void encounterRiverMonster() {
        position = "encounterRiverMonster";
        JOptionPane.showMessageDialog(ui.window, "You encounter a powerful river monster!!!");
        encounterMonster(riverMonster);
    }

    public void jungle() {
        if (!isDefeatedRiverMonster && position.equalsIgnoreCase("riverSide") && crossTheRiver()
                || isDefeatedRiverMonster || !position.equalsIgnoreCase("riverSide")) {
            position = "jungle";
            ui.mainTextArea.setText("You go into the jungle." +
                    "\nYou see an apple tree." +
                    "\nWhat would you do?");
            ui.choice1.setText("Hit the tree");
            ui.choice2.setText("Go North");
            ui.choice3.setText("Go South");
            ui.choice4.setText("Go West(cross the river)");
            nextPosition1 = "hitTheAppleTree";
            nextPosition2 = "mountainside";
            nextPosition3 = "";
            nextPosition4 = "riverSide";
        }
    }

    public void hitTheAppleTree() {
        position = "hitTheAppleTree";
        int c1 = rand.nextInt(4);
        if (appleOnTree > 0 && c1 % 2 == 0) {
            int c2 = JOptionPane.showConfirmDialog(ui.window, "An apple fell down. Do you want to eat this apple (+2HP)?");
            if (c2 == 0) {
                player.restoreHP(2);
                updatePlayerHealth();
                appleOnTree--;
            }
        } else if (c1 == 1) {
            JOptionPane.showMessageDialog(ui.window, "\"Hoohoohaha\" *angry noises. A monkey on the tree throw a stick at you (-1HP).");
            player.loseHP(1);
            updatePlayerHealth();
        } else
            JOptionPane.showMessageDialog(ui.window, "Nothing happen.");
        jungle();
    }

    public boolean crossTheRiver() {
        if (rand.nextInt(3) == 0) {
            encounterRiverMonster();
            return false;
        } else return true;
    }

    public void encounterMonster(Monster monster) {
        ui.mainTextArea.setText(monster.getName() + "'s HP: " + monster.getMonsterCurrentHP() + "/" + monster.getMonsterMaxHP() + "\nWhat would you do?");
        ui.choice1.setText("Fight");
        ui.choice2.setText("Try to run");
        ui.choice3.setText("");
        ui.choice4.setText("");
        if (monster.getName().equalsIgnoreCase("Goblin")) {
            nextPosition1 = "attackGoblin";
            nextPosition2 = "tryToRunGoblin";
        } else if (monster.getName().equalsIgnoreCase("River monster")) {
            nextPosition1 = "attackRiverMonster";
            nextPosition2 = "tryToRunRiverMonster";
        }
        nextPosition3 = "";
        nextPosition4 = "";
    }

    public void attackGoblin() {
        position = "attackGoblin";
        attackMonster(goblin);
        updatePlayerHealth();
        if (player.getPlayerHP() == 0)
            deadScreen();
        else if (goblin.getMonsterCurrentHP() == 0) {
            isDefeatedGoblin = true;
            JOptionPane.showMessageDialog(ui.window, "You have defeated the goblin!");
            goblinCave();
        } else encounterMonster(goblin);
    }

    public void tryToRunGoblin() {
        position = "tryToRunGoblin";
        if (tryToRun(goblin))
            crossRoad();
        else encounterMonster(goblin);
    }

    public void attackRiverMonster() {
        position = "attackRiverMonster";
        attackMonster(riverMonster);
        updatePlayerHealth();
        if (player.getPlayerHP() == 0)
            deadScreen();
        else if (riverMonster.getMonsterCurrentHP() == 0) {
            isDefeatedRiverMonster = true;
            JOptionPane.showMessageDialog(ui.window, "You have defeated the river monster!\nYou obtained a trident");
            player.addWeapon(weaponMap.get("Trident"));
            ui.weaponComboBox.addItem(weaponMap.get("Trident").getName());
            southRiver();
        } else encounterMonster(riverMonster);
    }

    public void tryToRunRiverMonster() {
        position = "tryToRunRiverMonster";
        if (tryToRun(riverMonster))
            riverSide();
        else encounterMonster(riverMonster);
    }

    public void attackMonster(Monster monster) {
        Weapon currentWeapon = weaponMap.get(ui.weaponComboBox.getSelectedItem());
        int playerDamage = rand.nextInt(currentWeapon.getCriticalAttackDamage() - currentWeapon.getAttackDamage()) + currentWeapon.getAttackDamage();
        JOptionPane.showMessageDialog(ui.window, "You attacked the monster with " + currentWeapon.getName() + " and gave " + playerDamage + " damage!");
        monster.loseHP(playerDamage);

        int monsterDamage = rand.nextInt(monster.getAttackDamage()) + 1;
        if (player.getArmor() != null) {
            monsterDamage -= player.getArmor().getDamageReduced();
            if (monsterDamage < 0) monsterDamage = 0;
            JOptionPane.showMessageDialog(ui.window, "The monster attacked you. With " + player.getArmor().getName() + ", you took " + monsterDamage + " damage!");
        } else
            JOptionPane.showMessageDialog(ui.window, "The monster attacked you and gave " + monsterDamage + " damage!");
        player.loseHP(monsterDamage);
    }

    public boolean tryToRun(Monster monster) {
        if (rand.nextBoolean()) {
            JOptionPane.showMessageDialog(ui.window, "You dodged the monster's attack and run away!");
            return true;
        } else {
            int damageTaken = (int) Math.ceil(1 * difficultRate);
            JOptionPane.showMessageDialog(ui.window, "You've failed to escape and been taken " + damageTaken + " damage from the monster!");
            player.loseHP(damageTaken);
            ui.hpNumberLabel.setText(player.getPlayerHP() + "/20");
            return false;
        }
    }

    public void updatePlayerHealth() {
        ui.hpNumberLabel.setText(player.getPlayerHP() + "/20");
    }

    public void deadScreen() {
        position = "deadScreen";
        ui.mainTextArea.setText("YOU'RE DEAD!!!!");
        ui.choice1.setText("Try again");
        ui.choice2.setText("Quit game");
        ui.choice3.setText("");
        ui.choice4.setText("");
        nextPosition1 = "start";
        nextPosition2 = "quit";
        nextPosition3 = "";
        nextPosition4 = "";
    }

    public void toTitleScreen() {
        vm.showTitleScreen();
    }
}
