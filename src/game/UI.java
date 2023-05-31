package game;

import model.Difficulty;

import javax.swing.*;
import java.awt.*;

public class UI {
    JFrame window;
    JPanel titleNamePanel, difficultySelectPanel, startButtonPanel, startImagePanel, gameImagePanel, mainTextPanel, choiceButtonPanel, playerStatPanel;
    JLabel titleNameLabel, difficultyLabel, startImageLabel, hpLabel, armorLabel, gameImageLabel;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem1, menuItem2;
    JComboBox difficultyComboBox;
    JComboBox<String> weaponComboBox, spellComboBox;
    JButton startButton, mapButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;

    ImageIcon mapImg, townGateImg, guardImg, crossRoadImg,
            goblinCaveImg, insideGoblinCaveImg, goblinImg,
            riverSideImg, riverSideWithWitchImg, witchImg, defeatedWitchImg, evilWitchImg, riverMonsterImg, northRiverImg, southRiverImg, blackSmithHouseImg,
            jungleImg, mountainImg, mountainTopImg,
            demonHideoutImg, demonHideoutAfterBattleImg, shadowSerpentImg, demonKingImg, explosionImg,
            deadScreenImg, theEndImg;

    int width, height, widthGameImg, heightGameImg;
    Font titleFont, statFont, textFont;
    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});

    public UI() {
        width = 1280;
        height = 720;
        setFont();
    }

    public UI(int width, int height) {
        if (width < 1280) width = 1280;
        if (height < 820) height = 820;
        this.width = width;
        this.height = height;
        setFont();
    }

    public void setFont() {
        int acreage = width * height;
        titleFont = new Font("Times New Roman", Font.PLAIN, (int) acreage / 20000);
        statFont = new Font("Times New Roman", Font.PLAIN, acreage / 35000);
        textFont = new Font("Times New Roman", Font.PLAIN, acreage / 45000);
    }

    public void createWindow(Game.ChoiceHandler choiceHandler) {
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
        menu.setFont(statFont);
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
    }

    public void createTitleScreen(Game.ChoiceHandler choiceHandler) {
        // TITLE SCREEN
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(width * 2 / 24, height / 12 - 50, width * 10 / 12, height / 12);
        titleNamePanel.setBackground(Color.GREEN);
        titleNameLabel = new JLabel("ADVENTURE GAME");
        titleNameLabel.setForeground(Color.RED);
        titleNameLabel.setFont(titleFont);

        startImagePanel = new JPanel();
        startImagePanel.setBounds(width / 4, height * 9 / 48, width / 2, height / 2);
        startImagePanel.setBackground(Color.WHITE);
        Icon imgIcon = new ImageIcon(this.getClass().getResource("/img/adventurer.gif"));
        startImageLabel = new JLabel(imgIcon);

        difficultySelectPanel = new JPanel();
        difficultySelectPanel.setBounds(width * 3 / 8, height * 17 / 24, width / 4, height / 9);
        difficultySelectPanel.setBackground(Color.decode("#D4FAFA"));
        difficultySelectPanel.setLayout(new GridLayout(2, 1));
        difficultyLabel = new JLabel("DIFFICULTY", SwingConstants.CENTER);
        difficultyLabel.setForeground(Color.MAGENTA);
        difficultyLabel.setFont(statFont);
        difficultyComboBox = new JComboBox(difficulties);
        difficultyComboBox.setFont(statFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(width * 7 / 16, height * 10 / 12, width / 8, height / 10);
        startButtonPanel.setBackground(Color.decode("#D4FAFA"));
        startButton = new JButton("START");
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(statFont);
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

        UIManager.put("OptionPane.messageFont", textFont);
        UIManager.put("OptionPane.buttonFont", textFont);

        window.setVisible(true);
    }

    // GAME SCREEN
    public void createGameScreen(Game.ChoiceHandler choiceHandler) {
        playerStatPanel = new JPanel();
        playerStatPanel.setBounds(width * 8 / 12, height / 60, width * 11 / 36, height * 6 / 12);
        playerStatPanel.setBackground(Color.decode("#D4FAFA"));
        playerStatPanel.setLayout(new GridLayout(5, 1));

        hpLabel = new JLabel("HP: ");
        hpLabel.setForeground(Color.RED);
        hpLabel.setFont(statFont);

        armorLabel = new JLabel("");
        armorLabel.setFont(statFont);
        armorLabel.setVisible(false);

        weaponComboBox = new JComboBox();
        weaponComboBox.setForeground(Color.decode("#03c03c"));
        weaponComboBox.setFont(statFont);
        weaponComboBox.setFocusable(false);

        spellComboBox = new JComboBox();
        spellComboBox.setForeground(Color.decode("#C576F6"));
        spellComboBox.setFont(statFont);
        spellComboBox.setFocusable(false);
        spellComboBox.setVisible(false);

        mapButton = new JButton("Map");
        mapButton.setFont(statFont);
        mapButton.setSize(100, 80);
        mapButton.setForeground(Color.decode("#B1907F"));
        mapButton.setBackground(Color.decode("#D4FAFA"));
        mapButton.setFocusPainted(false);
        mapButton.setBorderPainted(false);
        mapButton.addActionListener(choiceHandler);
        mapButton.setActionCommand("map");
        mapButton.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(width * 1 / 36, height * 15 / 24, width * 7 / 12, height * 4 / 12);
        mainTextPanel.setBackground(Color.decode("#D4FAFA"));
        mainTextArea = new JTextArea("This is main text area");
        mainTextArea.setBounds(width / 60, height / 60, width * 7 / 12, height * 4 / 12);
        mainTextArea.setBackground(Color.WHITE);
        mainTextArea.setForeground(Color.BLACK);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(width * 8 / 12, height * 15 / 24, width * 11 / 36, height * 3 / 12);
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
        gameImagePanel.setBounds(width * 1 / 36, 20, width * 7 / 12, height * 7 / 12);
        gameImagePanel.setBackground(Color.decode("#D4FAFA"));
        gameImagePanel.setVisible(false);

        gameImageLabel = new JLabel();

        window.add(mainTextPanel);
        window.add(choiceButtonPanel);
        window.add(playerStatPanel);
        window.add(gameImagePanel);
        playerStatPanel.add(hpLabel);
        playerStatPanel.add(armorLabel);
        playerStatPanel.add(weaponComboBox);
        playerStatPanel.add(spellComboBox);
        playerStatPanel.add(mapButton);
        mainTextPanel.add(mainTextArea);
        gameImagePanel.add(gameImageLabel);
    }

    public void loadImg() {
        widthGameImg = width * 7 / 12;
        heightGameImg = height * 7 / 12;
        townGateImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/townGate.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        crossRoadImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/crossRoad.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        guardImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/guard.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        mapImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/map.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        goblinCaveImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/goblinCave.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        insideGoblinCaveImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/insideGoblinCave.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        goblinImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/goblin.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        riverSideImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/riverSide.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        riverSideWithWitchImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/riverSideWithWitch.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        witchImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/witch.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        defeatedWitchImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/defeatedWitch.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        evilWitchImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/evilWitch.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        northRiverImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/northRiver.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        southRiverImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/southRiver.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        riverMonsterImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/riverMonster.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        blackSmithHouseImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/blacksmithHouse.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        jungleImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/jungle.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        mountainImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mountain.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        mountainTopImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/mountainTop.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        demonHideoutImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/demonHideout.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        shadowSerpentImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/shadowSerpent.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        demonKingImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/demonKing.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        explosionImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/explosion.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        demonHideoutAfterBattleImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/demonHideoutAfterBattle.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        deadScreenImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/deadScreen.png")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
        theEndImg = new ImageIcon(new ImageIcon(this.getClass().getResource("/img/theEnd.gif")).getImage().getScaledInstance(widthGameImg, heightGameImg, Image.SCALE_DEFAULT));
    }
}
