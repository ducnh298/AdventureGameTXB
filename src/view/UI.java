package view;

import controller.Game;
import model.Difficulty;

import javax.swing.*;
import java.awt.*;

public class UI {
    JFrame window;
    public JPanel titleNamePanel, difficultySelectPanel, startButtonPanel, startImagePanel, mainTextPanel, choiceButtonPanel, playerStatPanel;
    JLabel titleNameLabel, difficultyLabel, startImageLabel, hpLabel, hpNumberLabel, weaponLabel, armorLabel;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem1, menuItem2;
    JComboBox difficultyComboBox, weaponComboBox;
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 85);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 38);
    Font menuFont = new Font("Times New Roman", Font.PLAIN, 25);
    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});
    double difficultRate = 1;

    public void createUI(Game.ChoiceHandler choiceHandler) {

        // WINDOW
        window = new JFrame();
        window.setTitle("AdventureGameTXB");
        window.setSize(1000, 850);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.decode("#D4FAFA"));
        window.setLayout(null);
        window.setLocationRelativeTo(null);

        //MENU
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        menu.setFont(menuFont);
        menuItem1 = new JMenuItem("Restart");
        menuItem1.setFont(menuFont);
        menuItem1.addActionListener(choiceHandler);
        menuItem1.setActionCommand("restart");
        menuItem2 = new JMenuItem("Quit");
        menuItem2.setFont(menuFont);
        menuItem2.addActionListener(choiceHandler);
        menuItem2.setActionCommand("quit");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        window.setJMenuBar(menuBar);

        // TITLE SCREEN
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
        startButton.addActionListener(choiceHandler);
        startButton.setActionCommand("start");
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

        // GAME SCREEN
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 120, 800, 350);
        mainTextPanel.setBackground(Color.DARK_GRAY);
        mainTextArea = new JTextArea("This is main text area");
        mainTextArea.setBounds(100, 120, 800, 350);
        mainTextArea.setBackground(Color.DARK_GRAY);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 500, 500, 200);
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
        playerStatPanel.setBounds(100, 0, 800, 100);
        playerStatPanel.setBackground(Color.decode("#D4FAFA"));
        playerStatPanel.setLayout(new GridLayout(1, 5));

        hpLabel = new JLabel("HP: ");
        hpLabel.setForeground(Color.RED);
        hpLabel.setFont(normalFont);
        hpNumberLabel = new JLabel();
        hpNumberLabel.setForeground(Color.RED);
        hpNumberLabel.setFont(normalFont);
        armorLabel = new JLabel("+Armor");
        armorLabel.setForeground(Color.decode("#D4FAFA"));
        armorLabel.setFont(normalFont);
        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setForeground(Color.decode("#03c03c"));
        weaponLabel.setFont(normalFont);
        weaponComboBox = new JComboBox();
        weaponComboBox.setForeground(Color.decode("#03c03c"));
        weaponComboBox.setFont(normalFont);
        weaponComboBox.setSize(150, 100);
        weaponComboBox.setFocusable(false);

        playerStatPanel.add(hpLabel);
        playerStatPanel.add(hpNumberLabel);
        playerStatPanel.add(armorLabel);
        playerStatPanel.add(weaponLabel);
        playerStatPanel.add(weaponComboBox);
        mainTextPanel.add(mainTextArea);
        window.add(mainTextPanel);
        window.add(choiceButtonPanel);
        window.add(playerStatPanel);

    }
}
