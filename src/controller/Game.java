package controller;

import model.Difficulty;
import model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {
    int size = 3;
    double difficult = Difficulty.EASY.ordinal();
    int[][] map;
    int playerHP;
    String weapon;
    String position;
    Boolean angryGuard = false;
    JFrame window;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerStatPanel;
    JLabel titleNameLabel, hpLabel, hpNumberLabel, weaponLabel, weaponNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    TitleScreenHandler titleScreenHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();

    public Game() {
        window = new JFrame();
        window.setTitle("TXAdventureGame");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 50, 600, 100);
        titleNamePanel.setBackground(Color.BLACK);
        titleNameLabel = new JLabel("ADVENTURE");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);
        startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.addActionListener(titleScreenHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
        window.add(startButtonPanel);
        window.setVisible(true);
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 120, 600, 200);
        mainTextPanel.setBackground(Color.DARK_GRAY);
        mainTextArea = new JTextArea("This is main text area");
        mainTextArea.setBounds(100, 120, 600, 200);
        mainTextArea.setBackground(Color.DARK_GRAY);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(200, 350, 400, 200);
        choiceButtonPanel.setBackground(Color.WHITE);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.WHITE);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.BLACK);
        choice4.setForeground(Color.WHITE);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        playerStatPanel = new JPanel();
        playerStatPanel.setBounds(100, 0, 600, 100);
        playerStatPanel.setBackground(Color.BLACK);
        playerStatPanel.setLayout(new GridLayout(1, 4));

        hpLabel = new JLabel("HP: ");
        hpLabel.setForeground(Color.RED);
        hpLabel.setFont(normalFont);
        hpNumberLabel = new JLabel();
        hpNumberLabel.setForeground(Color.RED);
        hpNumberLabel.setFont(normalFont);
        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setForeground(Color.GREEN);
        weaponLabel.setFont(normalFont);
        weaponNameLabel = new JLabel();
        weaponNameLabel.setForeground(Color.GREEN);
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
        playerHP = 10;
        weapon = "Knife";
        hpNumberLabel.setText(playerHP + "/10");
        weaponNameLabel.setText(weapon);

        townGate();
    }

    public void townGate() {
        position = "townGate";
        mainTextArea.setText("You are now at the gate of the town. \nA guard is stading in front of you.\nWhat would you do?");
        if (angryGuard) {
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
        if(angryGuard)
            mainTextArea.setText("Guard: You again?!\nGet out.");
        else
            mainTextArea.setText("Guard: Hello stranger!\nI have never seen your face before.\nI cannot let stranger in our town.");
        choice1.setText("Leave");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void attackGuard() {
        position = "attackGuard";
        angryGuard = true;
        playerHP -= 3;
        hpNumberLabel.setText(playerHP + "/10");
        mainTextArea.setText("Guard: Hey!!! Don't be so stupid!\n\nThe guard fought back and hit you hard.\n(You received 3 damage)");
        choice1.setText("\"Sorry\"");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void crossRoad() {
        position = "crossRoad";
        mainTextArea.setText("You now at a cross road (The town is at the west side).\nWhere would you go?");
        choice1.setText("Head North");
        choice2.setText("Head East");
        choice3.setText("Head South");
        choice4.setText("Head West");
    }

    public void deadScreen(){
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
                            townGate();
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
                        case "c4":
                            townGate();
                            break;
                    }
            }
        }
    }

    private void generateMap(int size) {
        Random rand = new Random();
        this.size = size;
        this.map = new int[size][size];

        if (rand.nextBoolean()) generateRiver();

    }

    private void generateRiver() {
        Random rand = new Random();
        Point point = new Point(size, 1);

        map[point.x][point.y] = GameObject.RIVER.ordinal();

        while (point.y <= size) {
            int direction = rand.nextInt(3);

            if (direction == 0 && map[point.x - 1][point.y] == 0) point.x -= 1;
            else if (direction == 1 && map[point.x][point.y + 1] == 0) point.y += 1;
            else if (direction == 2 && map[point.x + 1][point.y] == 0) point.x += 1;

            map[point.x][point.y] = GameObject.RIVER.ordinal();
        }
    }
}
