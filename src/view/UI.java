package view;

import controller.Game;
import model.Difficulty;

import javax.swing.*;
import java.awt.*;

public class UI {
    JFrame window;
    public JPanel titleNamePanel, difficultySelectPanel, startButtonPanel, startImagePanel, gameImagePanel, mainTextPanel, choiceButtonPanel, playerStatPanel;
    JLabel titleNameLabel, difficultyLabel, startImageLabel, hpLabel, armorLabel, gameImageLabel;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem1, menuItem2;
    JComboBox difficultyComboBox, weaponComboBox;
    JButton startButton, mapButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;

    ImageIcon mapImg, townGateImg, guardImg, angryGuardImg, crossRoadImg, goblinCaveImg, goblinImg, riverSideImg,riverMonsterImg, northRiverImg, southRiverImg, jungleImg, blackSmithHouseImg;

    int width = 1280;
    int height = 960;
    int widthGameImg = width * 7 / 12;
    int heightGameImg = height * 7 / 12;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 85);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 38);
    Font textFont = new Font("Times New Roman", Font.PLAIN, 30);
    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});
    double difficultRate = 1;

    public void createUI(Game.ChoiceHandler choiceHandler) {

        // WINDOW
        window = new JFrame();
        window.setTitle("AdventureGameTXB");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.decode("#D4FAFA"));
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        //MENU
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        menu.setFont(normalFont);
        menuItem1 = new JMenuItem("Restart");
        menuItem1.setFont(textFont);
        menuItem1.addActionListener(choiceHandler);
        menuItem1.setActionCommand("restart");
        menuItem2 = new JMenuItem("Quit");
        menuItem2.setFont(textFont);
        menuItem2.addActionListener(choiceHandler);
        menuItem2.setActionCommand("quit");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        window.setJMenuBar(menuBar);

        // TITLE SCREEN
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(width / 2 - 400, height / 12 - 50, 800, 100);
        titleNamePanel.setBackground(Color.GREEN);
        titleNameLabel = new JLabel("ADVENTURE GAME");
        titleNameLabel.setForeground(Color.RED);
        titleNameLabel.setFont(titleFont);

        startImagePanel = new JPanel();
        startImagePanel.setBounds(width / 2 - 325, height / 2 - 300, 650, 400);
        startImagePanel.setBackground(Color.WHITE);
        Icon imgIcon = new ImageIcon(this.getClass().getResource("/img/adventurer.gif"));
        startImageLabel = new JLabel(imgIcon);

        difficultySelectPanel = new JPanel();
        difficultySelectPanel.setBounds(width / 2 - 150, height / 2 + 150, 300, 80);
        difficultySelectPanel.setBackground(Color.decode("#D4FAFA"));
        difficultySelectPanel.setLayout(new GridLayout(2, 1));
        difficultyLabel = new JLabel("DIFFICULTY", SwingConstants.CENTER);
        difficultyLabel.setForeground(Color.MAGENTA);
        difficultyLabel.setFont(normalFont);
        difficultyComboBox = new JComboBox(difficulties);
        difficultyComboBox.setFont(normalFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(width / 2 - 125, height / 2 + 250, 250, 100);
        startButtonPanel.setBackground(Color.decode("#D4FAFA"));
        startButton = new JButton("START");
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.addActionListener(choiceHandler);
        startButton.setActionCommand("start");
        startButton.setFocusPainted(false);

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

        // GAME SCREEN
        playerStatPanel = new JPanel();
        playerStatPanel.setBounds(width * 8 / 12, height / 60, width * 3 / 12, height * 6/12);
        playerStatPanel.setBackground(Color.decode("#D4FAFA"));
        playerStatPanel.setLayout(new GridLayout(4, 1));

        hpLabel = new JLabel("HP: ");
        hpLabel.setForeground(Color.RED);
        hpLabel.setFont(normalFont);

        armorLabel = new JLabel("+Armor");
        armorLabel.setForeground(Color.decode("#D4FAFA"));
        armorLabel.setFont(normalFont);

        weaponComboBox = new JComboBox();
        weaponComboBox.setForeground(Color.decode("#03c03c"));
        weaponComboBox.setFont(normalFont);
        weaponComboBox.setSize(150, 50);
        weaponComboBox.setFocusable(false);

        mapButton = new JButton("Map");
        mapButton.setFont(normalFont);
        mapButton.setSize(100, 80);
        mapButton.setForeground(Color.decode("#B1907F"));
        mapButton.setBackground(Color.decode("#D4FAFA"));
        mapButton.setFocusPainted(false);
        mapButton.setBorderPainted(false);
        mapButton.addActionListener(choiceHandler);
        mapButton.setActionCommand("map");
        mapButton.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(width * 1 / 36, height * 15/24, width * 7 / 12, height * 4 / 12);
        mainTextPanel.setBackground(Color.decode("#D4FAFA"));
        mainTextArea = new JTextArea("This is main text area");
        mainTextArea.setBounds(width / 60, height / 60, width * 7 / 12, height * 4 / 12);
        mainTextArea.setBackground(Color.WHITE);
        mainTextArea.setForeground(Color.BLACK);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(width * 8 / 12 , height * 15/24, width * 7 / 24, height * 3 / 12);
        choiceButtonPanel.setBackground(Color.WHITE);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.decode("#6E78FF"));
        choice1.setForeground(Color.WHITE);
        choice1.setFont(textFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.decode("#6E78FF"));
        choice2.setForeground(Color.WHITE);
        choice2.setFont(textFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.decode("#6E78FF"));
        choice3.setForeground(Color.WHITE);
        choice3.setFont(textFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.decode("#6E78FF"));
        choice4.setForeground(Color.WHITE);
        choice4.setFont(textFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        gameImagePanel = new JPanel();
        gameImagePanel.setBounds(width * 1 / 36, 0, width * 7 / 12, height * 7 / 12);
        gameImagePanel.setBackground(Color.decode("#D4FAFA"));
        gameImagePanel.setVisible(false);

        gameImageLabel = new JLabel();
        mapImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/map.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        townGateImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/townGate.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        crossRoadImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/crossRoad.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        guardImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/guard.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        goblinCaveImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/goblinCave.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        goblinImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/goblin.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        riverSideImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/riverSide.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        northRiverImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/northRiver.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        southRiverImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/southRiver.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        riverMonsterImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/riverMonster.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        blackSmithHouseImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/blacksmithHouse.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        jungleImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/jungle.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));

        playerStatPanel.add(hpLabel);
        playerStatPanel.add(armorLabel);
        playerStatPanel.add(weaponComboBox);
        playerStatPanel.add(mapButton);
        mainTextPanel.add(mainTextArea);
        gameImagePanel.add(gameImageLabel);
        window.add(mainTextPanel);
        window.add(choiceButtonPanel);
        window.add(playerStatPanel);
        window.add(gameImagePanel);
    }
}
