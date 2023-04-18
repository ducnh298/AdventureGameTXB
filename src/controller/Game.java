package controller;

import model.Difficulty;
import model.Monster;
import model.Player;
import model.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Game {
    JFrame window;
    JPanel titleNamePanel, difficultySelectPanel, startButtonPanel, startImagePanel, mainTextPanel, choiceButtonPanel, playerStatPanel;
    JLabel titleNameLabel, difficultyLabel, startImageLabel, hpLabel, hpNumberLabel, weaponLabel, weaponNameLabel;
    JComboBox difficultyComboBox;
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 85);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 38);

    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});
    double difficultRate;
    Player player;
    Weapon knife = new Weapon("Knife", 2, 4);
    Weapon longSword = new Weapon("Long sword", 4, 6);
    Weapon bow = new Weapon("Bow", 3, 6);
    Monster riverMonster = new Monster("River monster", 6, 3);
    String position;
    boolean isAngryGuard = false;
    boolean isRestAtTent = false;
    boolean isDefeatedRiverMonster = false;

    Random rand = new Random();

    public Game() {
        window = new JFrame();
        window.setTitle("TXAdventureGame");
        window.setSize(1000, 820);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.decode("#D4FAFA"));
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 50, 800, 100);
        titleNamePanel.setBackground(Color.GREEN);
        titleNameLabel = new JLabel("ADVENTURE GAME");
        titleNameLabel.setForeground(Color.RED);
        titleNameLabel.setFont(titleFont);

        difficultySelectPanel = new JPanel();
        difficultySelectPanel.setBounds(350, 600, 300, 80);
        difficultySelectPanel.setBackground(Color.decode("#D4FAFA"));
        difficultySelectPanel.setLayout(new GridLayout(2, 1));
        difficultyLabel = new JLabel("DIFFICULTY", SwingConstants.CENTER);
        difficultyLabel.setForeground(Color.MAGENTA);
        difficultyLabel.setFont(normalFont);
        difficultyComboBox = new JComboBox(difficulties);
        difficultyComboBox.setFont(normalFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(400, 700, 200, 100);
        startButtonPanel.setBackground(Color.decode("#D4FAFA"));
        startButton = new JButton("START");
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.addActionListener(titleScreenHandler);
        startButton.setFocusPainted(false);

        startImagePanel = new JPanel();
        startImagePanel.setBounds(300, 200, 400, 400);
        startImagePanel.setBackground(Color.WHITE);
        Icon imgIcon = new ImageIcon(this.getClass().getResource("/img/adventurer.gif"));
        startImageLabel = new JLabel(imgIcon);

        titleNamePanel.add(titleNameLabel);
        difficultySelectPanel.add(difficultyLabel);
        difficultySelectPanel.add(difficultyComboBox);
        startButtonPanel.add(startButton);
        startImagePanel.add(startImageLabel);

        window.add(titleNamePanel);
        window.add(difficultySelectPanel);
        window.add(startButtonPanel);
        window.add(startImagePanel);

        UIManager.put("OptionPane.messageFont", normalFont);
        UIManager.put("OptionPane.buttonFont", normalFont);

        window.setVisible(true);
    }

    public void createGameScreen() {
        difficultRate = ((Difficulty) difficulties.getElementAt(difficultyComboBox.getSelectedIndex())).getValue();

        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        difficultySelectPanel.setVisible(false);
        startImagePanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(150, 120, 700, 300);
        mainTextPanel.setBackground(Color.DARK_GRAY);
        mainTextArea = new JTextArea("This is main text area");
        mainTextArea.setBounds(150, 120, 700, 300);
        mainTextArea.setBackground(Color.DARK_GRAY);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 450, 500, 200);
        choiceButtonPanel.setBackground(Color.WHITE);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.decode("#6E78FF"));
        choice1.setForeground(Color.WHITE);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.decode("#6E78FF"));
        choice2.setForeground(Color.WHITE);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.decode("#6E78FF"));
        choice3.setForeground(Color.WHITE);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.decode("#6E78FF"));
        choice4.setForeground(Color.WHITE);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        playerStatPanel = new JPanel();
        playerStatPanel.setBounds(200, 0, 600, 100);
        playerStatPanel.setBackground(Color.decode("#D4FAFA"));
        playerStatPanel.setLayout(new GridLayout(1, 4));

        hpLabel = new JLabel("HP: ");
        hpLabel.setForeground(Color.RED);
        hpLabel.setFont(normalFont);
        hpNumberLabel = new JLabel();
        hpNumberLabel.setForeground(Color.RED);
        hpNumberLabel.setFont(normalFont);
        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setForeground(Color.decode("#03c03c"));
        weaponLabel.setFont(normalFont);
        weaponNameLabel = new JLabel();
        weaponNameLabel.setForeground(Color.decode("#03c03c"));
        weaponNameLabel.setFont(normalFont);

        playerStatPanel.add(hpLabel);
        playerStatPanel.add(hpNumberLabel);
        playerStatPanel.add(weaponLabel);
        playerStatPanel.add(weaponNameLabel);
        mainTextPanel.add(mainTextArea);
        window.add(mainTextPanel);
        window.add(choiceButtonPanel);
        window.add(playerStatPanel);

        playerSetup();
    }

    public void playerSetup() {
        player = new Player(10, knife);
        hpNumberLabel.setText(player.getPlayerHP() + "/10");
        weaponNameLabel.setText(player.getWeapon().getName());
        isAngryGuard = isRestAtTent = isDefeatedRiverMonster = false;
        townGate();
    }

    public void townGate() {
        position = "townGate";
        mainTextArea.setText("You are now at the gate of the town. \nA guard is standing in front of you.\nWhat would you do?");
        if (isAngryGuard) {
            choice1.setText("Talk to the angry guard");
            choice2.setText("Attack the guard again");
        } else {
            choice1.setText("Talk to the guard");
            choice2.setText("Attack the guard");
        }
        choice3.setText("Enter town");
        choice4.setText("Leave");
    }

    public void talkGuard() {
        position = "talkGuard";
        if (isAngryGuard)
            mainTextArea.setText("Guard: You again?!\nGet away.");
        else
            mainTextArea.setText("Guard: Hello stranger!\nI have never seen your face before.\nI cannot let stranger in our town.");
        choice1.setText("Leave");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void attackGuard() {
        position = "attackGuard";
        isAngryGuard = true;

        int hpLosed = (int) Math.round(3 * difficultRate);
        player.loseHP(hpLosed);
        hpNumberLabel.setText(player.getPlayerHP() + "/10");
        if (player.getPlayerHP() == 0)
            deadScreen();
        else {
            mainTextArea.setText("Guard: Hey!!! Don't be so stupid!\n\nThe guard fought back and hit you hard.\n(You received " + hpLosed + " damage)");
            choice1.setText("\"Sorry\"");
            choice2.setText("Leave");
            choice3.setText("");
            choice4.setText("");
        }
    }

    public void crossRoad() {
        position = "crossRoad";
        mainTextArea.setText("You now at a cross road.\nWhere would you go?");
        choice1.setText("Go North");
        choice2.setText("Go East");
        choice3.setText("Go South");
        choice4.setText("Go West (the town)");
    }

    public void riverSide() {
        position = "riverSide";
        mainTextArea.setText("You approach a river.\nWhat would you do?");
        choice1.setText("Go North (along the river)");
        choice2.setText("Go East (across the river)");
        choice3.setText("Go South (along the river)");
        choice4.setText("Go West");
    }

    public void northRiver() {
        position = "northRiver";
        if (!isRestAtTent) {
            mainTextArea.setText("North of the river.\nThere is a old tent on the river side (You can rest to recover 3HP).\nNorthern path lead to a waterfall, we couldn't go further.\nWhat would you do?");
            choice1.setText("Take a rest");
        } else {
            mainTextArea.setText("North of the river.\nThere is a old tent on the river side.\nNorthern path lead to a waterfall, we couldn't go further.\nWhat would you do?");
            choice1.setText("");
        }
        choice2.setText("Go East");
        choice3.setText("Go South");
        choice4.setText("Go West");
    }

    public void southRiver() {
        position = "southRiver";
        if (!isDefeatedRiverMonster) {
            mainTextArea.setText("South of the river.\n There is a strange vortex in the middle of the river.\nSouthern path has been blocked by crashed tree, we couldn't go further.\nWhat would you do?");
            choice1.setText("Throw a rock into it");
        } else {
            mainTextArea.setText("South of the river.\nSouthern path has been blocked by crashed tree, we couldn't go further.\nWhat would you do?");
            choice1.setText("");
        }
        choice2.setText("Go North");
        choice3.setText("Go East");
        choice4.setText("Go West");
    }

    public void encounterRiverMonster() {
        position = "encounterRiverMonster";

        mainTextArea.setText(riverMonster.getName() + "'s HP: " + riverMonster.getMonsterCurrentHP() + "/" + riverMonster.getMonsterMaxHP() + "\nWhat would you do?");
        choice1.setText("Fight");
        choice2.setText("Try to run");
        choice3.setText("");
        choice4.setText("");
    }

    public void deadScreen() {
        position = "deadScreen";
        mainTextArea.setText("YOU'RE DEAD!!!!");
        choice1.setText("Try again");
        choice2.setText("Quit game");
        choice3.setText("");
        choice4.setText("");
    }


    public class TitleScreenHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String yourChoice = e.getActionCommand();
            switch (position) {
                case "townGate":
                    switch (yourChoice) {
                        case "c1":
                        case "c3":
                            talkGuard();
                            break;
                        case "c2":
                            attackGuard();
                            break;
                        case "c4":
                            crossRoad();
                            break;
                    }
                    break;
                case "talkGuard":
                    switch (yourChoice) {
                        case "c1":
                            crossRoad();
                            break;
                    }
                    break;
                case "attackGuard":
                    switch (yourChoice) {
                        case "c1":
                        case "c2":
                            townGate();
                            break;
                    }
                    break;
                case "crossRoad":
                    switch (yourChoice) {
                        case "c2":
                            riverSide();
                            break;
                        case "c4":
                            townGate();
                            break;
                    }
                    break;
                case "riverSide":
                    switch (yourChoice) {
                        case "c1":
                            northRiver();
                            break;
                        case "c2":
                            break;
                        case "c3":
                            southRiver();
                            break;
                        case "c4":
                            crossRoad();
                            break;
                    }
                    break;
                case "northRiver":
                    switch (yourChoice) {
                        case "c1":
                            if (!isRestAtTent) {
                                player.restoreHP(3);
                                hpNumberLabel.setText(player.getPlayerHP() + "/10");
                                isRestAtTent = true;
                                northRiver();
                            }
                            break;
                        case "c2":
                            break;
                        case "c3":
                            riverSide();
                            break;
                    }
                    break;
                case "southRiver":
                    switch (yourChoice) {
                        case "c1":
                            if (!isDefeatedRiverMonster) {
                                JOptionPane.showMessageDialog(window, "You encounter a powerful river monster!!!");
                                encounterRiverMonster();
                            }
                            break;
                        case "c2":
                            riverSide();
                            break;
                        case "c3":

                            break;
                    }
                    break;
                case "encounterRiverMonster":
                    switch (yourChoice) {
                        case "c1":
                            int playerDamage = rand.nextInt(player.getWeapon().getCriticalAttackDamage() - player.getWeapon().getAttackDamage()) + player.getWeapon().getAttackDamage();
                            JOptionPane.showMessageDialog(window, "You attacked the monster and gave " + playerDamage + " damage!");
                            riverMonster.loseHP(playerDamage);

                            int monsterDamage = rand.nextInt(riverMonster.getAttackDamage()) + 1;
                            JOptionPane.showMessageDialog(window, "The monster attacked you and gave " + monsterDamage + " damage!");
                            player.loseHP(monsterDamage);
                            hpNumberLabel.setText(player.getPlayerHP() + "/10");

                            encounterRiverMonster();

                            if (player.getPlayerHP() == 0)
                                deadScreen();

                            else if (riverMonster.getMonsterCurrentHP() == 0) {
                                isDefeatedRiverMonster = true;
                                JOptionPane.showMessageDialog(window, "Congratulation!!! You have defeated the river monster!");
                                southRiver();
                            }
                            break;
                        case "c2":
                            if (rand.nextBoolean()) {
                                JOptionPane.showMessageDialog(window, "You dodged the monster's attack and run to the north!");
                                riverSide();
                                break;
                            }
                            JOptionPane.showMessageDialog(window, "You've failed to escape and been taken 1 damage from the monster!");
                            player.loseHP(1);
                            hpNumberLabel.setText(player.getPlayerHP() + "/10");
                            encounterRiverMonster();
                            break;
                        case "c3":

                            break;
                    }
                    break;
                case "deadScreen":
                    switch (yourChoice) {
                        case "c1":
                            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                            new Game();
                            break;
                        case "c2":
                            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                            break;
                    }
                    break;
            }
        }
    }

}
