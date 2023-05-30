package game;

import model.*;
import model.armors.Armor_GoldenArmor;
import model.armors.Armor_IronArmor;
import model.armors.Armor_SilverArmor;
import model.monsters.Monster_DemonKing;
import model.monsters.Monster_Goblin;
import model.monsters.Monster_RiverMonster;
import model.monsters.Monster_ShadowSerpent;
import model.spells.Spell_FireBall;
import model.spells.Spell_LightningBolt;
import model.spells.Spell_WaterSurge;
import model.weapons.Weapon_DemonSword;
import model.weapons.Weapon_Knife;
import model.weapons.Weapon_LongSword;
import model.weapons.Weapon_Trident;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Story {
    Game game;
    UI ui;
    VisibilityManager vm;
    private Thread previousThread;
    public String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    DefaultComboBoxModel difficulties = new DefaultComboBoxModel(new Difficulty[]{Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD, Difficulty.EXTREMEHARD});
    double difficultRate;
    Player player;
    Weapon knife = new Weapon_Knife();
    Weapon longSword = new Weapon_LongSword();
    Weapon trident = new Weapon_Trident();
    Weapon demonSword = new Weapon_DemonSword();

    Armor[] armors = {new Armor_IronArmor(), new Armor_SilverArmor(), new Armor_GoldenArmor()};
    Spell fireBall, lightningBolt, waterSurge;

    Monster goblin, riverMonster, shadowSerpent, demonKing;

    String position, lastPosition;
    boolean isAngryGuard, isRestAtTent, isTakenArmor, isTakenLongSword, isTakenPower,
            isALiveRiverMonster, isALiveGoblin, isAliveShadowSerpent, isAliveDemonKing;
    int appleOnTree;
    Spell selectedSpell;
    Random rand = new Random();

    public Story(Game game, UI ui, VisibilityManager vm) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
    }

    public void gameSetup() {
        difficultRate = ((Difficulty) difficulties.getElementAt(ui.difficultyComboBox.getSelectedIndex())).getValue();

        player = new Player(20);
        updatePlayerHp(0);
        player.addWeapon(knife);
        ui.weaponComboBox.removeAllItems();
        ui.weaponComboBox.addItem(knife.getName());
        ui.armorLabel.setVisible(false);
        ui.mapButton.setVisible(false);
        ui.spellLabel.setVisible(false);

        isAngryGuard = isRestAtTent = isTakenArmor = isTakenLongSword = isTakenPower = false;
        isALiveRiverMonster = isALiveGoblin = isAliveShadowSerpent = isAliveDemonKing = true;
        appleOnTree = 3;

        goblin = new Monster_Goblin(difficultRate);
        riverMonster = new Monster_RiverMonster(difficultRate);
        shadowSerpent = new Monster_ShadowSerpent(difficultRate);
        demonKing = new Monster_DemonKing(difficultRate);

        fireBall = new Spell_FireBall();
        lightningBolt = new Spell_LightningBolt();
        waterSurge = new Spell_WaterSurge();

        vm.showGameScreen();
        townGate();
    }

    public void selectedPosition(String nextPosition) {
        switch (nextPosition) {
            case "start":
                vm.showTitleScreen();
                break;
            case "quit":
                System.exit(0);
                break;
            case "townGate":
                townGate();
                break;
            case "map":
                map();
                break;
            case "talkGuard1":
                talkGuard1();
                break;
            case "talkGuard2":
                talkGuard2();
                break;
            case "talkGuard3":
                talkGuard3();
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
            case "insideGoblinCave":
                insideGoblinCave();
                break;
            case "takeLongSword":
                takeLongSword();
                break;
            case "encounterGoblin":
                encounterGoblin();
                break;
            case "attackGoblin":
                attackGoblin(false);
                break;
            case "attackGoblinWithSpell":
                attackGoblin(true);
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
                attackRiverMonster(false);
                break;
            case "attackRiverMonsterWithSpell":
                attackRiverMonster(true);
                break;
            case "tryToRun":
                tryToRun();
                break;
            case "jungle":
                jungle();
                break;
            case "hitTheAppleTree":
                hitTheAppleTree();
                break;
            case "mountain":
                mountain();
                break;
            case "mountainTop":
                mountainTop();
                break;
            case "touchStatue":
                touchStatue();
                break;
            case "offerPower":
                offerPower();
                break;
            case "strengthPower":
                strengthPower();
                break;
            case "firePower":
                firePower();
                break;
            case "lightningPower":
                lightningPower();
                break;
            case "waterPower":
                waterPower();
                break;
            case "takeStrengthPower":
                takeStrengthPower();
                break;
            case "takePower":
                takePower();
                break;
            case "demonHideout":
                demonHideout();
                break;
            case "encounterShadowSerpent":
                encounterShadowSerpent();
                break;
            case "attackShadowSerpent":
                attackShadowSerpent(false);
                break;
            case "attackShadowSerpentWithSpell":
                attackShadowSerpent(true);
                break;
            case "encounterDemonKing":
                encounterDemonKing();
                break;
            case "attackDemonKing":
                attackDemonKing(false);
                break;
            case "attackDemonKingWithSpell":
                attackDemonKing(true);
                break;
            case "defeatDemonKing":
                defeatDemonKing();
                break;
            case "wakeUpAfterFinalBattle":
                wakeUpAfterFinalBattle();
                break;
            case "theEnd":
                theEnd();
                break;
        }
    }

    private void map() {
        ui.gameImageLabel.setIcon(ui.mapImg);

        ui.mainTextArea.setText("You're checking the map.");
        setChoicesAndNextPositions(new String[]{"Leave", "", "", "", "", "", "", ""});
        nextPosition1 = position;
    }

    private void townGate() {
        position = "townGate";
        ui.gameImageLabel.setIcon(ui.townGateImg);
        setChoicesAndNextPositions(new String[]{"", "", "Enter town", "Leave", "talkGuard1", "attackGuard", "talkGuard1", "crossRoad"});

        if (!isAliveDemonKing) {
            isAngryGuard = false;
            displayTextSlowly("Returning to the town, you spot the familiar figure of the guard standing at his post. " +
                    "As you approach, he looks up and recognition flashes across his face. \"You've returned,\" he says with a mix of surprise and relief. ");
        } else
            displayTextSlowly("You approach the closed town gate, barred and fortified against potential threats. " +
                    "A vigilant guard stands before you, clad in armor and armed with a sword. " +
                    "Their gaze is unwavering, assessing your presence with a watchful eye.");

        if (isAngryGuard) {
            ui.choice1.setText("Talk to the angry guard");
            ui.choice2.setText("Attack the guard again");
        } else {
            ui.choice1.setText("Talk to the guard");
            ui.choice2.setText("Attack the guard");
        }
    }

    private void talkGuard1() {
        position = "talkGuard1";
        ui.gameImageLabel.setIcon(ui.guardImg);
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "talkGuard2", "townGate", "", ""});

        if (!isAliveDemonKing) {
            displayTextSlowly("\"I heard rumors of the demon king's defeat. Is it true?\" " +
                    "You nod, sharing the details of your epic battle and the destruction of the demon king's hideout. " +
                    "The guard's stern expression softens, replaced by a genuine smile. " +
                    "\"You've done the town a great service. We owe you our gratitude.\"");
            ui.choice1.setText("\"Can i enter the town now?\"");
        } else {
            if (isAngryGuard)
                displayTextSlowly("The guard's anger is evident as they reprimand your attack. " +
                        "With disappointment in their eyes, they stress the importance of town safety. Trust and redemption seem distant now. " +
                        "Quickly, you apologize and explain that you are a traveler in search of your brother, following clues that brought you here.");
            else
                displayTextSlowly("Guard: \"Hello, stranger! I cannot let unfamiliar faces into our town. " +
                        "Prove yourself trustworthy, or find another way in.\" " +
                        "You explain your purpose as a traveler searching for your brother, guided by the clues that brought you here. " +
                        "Seeking assistance, you inquire about gaining entry, hoping for guidance to reach your destination.");
            ui.choice1.setText("\"How can i enter the town?\"");
        }
    }

    private void talkGuard2() {
        position = "talkGuard2";
        ui.gameImageLabel.setIcon(ui.guardImg);
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "", "townGate", "", ""});

        if (!isAliveDemonKing) {
            displayTextSlowly("The guard, now aware of your heroic triumph, opens the town gate for you without hesitation.");
            ui.choice1.setText("Enter the town");
            nextPosition1 = "theEnd";
        } else {
            displayTextSlowly("Guard:\" Recently, the demon king has been encroaching on our territory, posing a grave threat to our safety. " +
                    "We are in desperate need of a hero to protect our town. " +
                    "If you can repel the demon king, I will grant you entry.\"");
            ui.choice1.setText("\"How am i supposed to do that\"");
            nextPosition1 = "talkGuard3";
        }
    }

    private void talkGuard3() {
        position = "talkGuard3";
        ui.gameImageLabel.setIcon(ui.guardImg);
        ui.mapButton.setVisible(true);

        displayTextSlowly("The guard acknowledges your inadequate gear to repulse the Demon King. " +
                "They provide a map with the demon king's hideout marked and suggest visiting the blacksmith in the north for better equipment.\"");
        setChoicesAndNextPositions(new String[]{"\"Ok! Thank you.\"", "Leave", "", "", "townGate", "townGate", "", ""});
    }

    private void attackGuard() {
        position = "attackGuard";

        ui.gameImageLabel.setIcon(ui.guardImg);
        isAngryGuard = true;

        int hpLost = (int) Math.ceil(4 * difficultRate);
        if (!updatePlayerHp(-hpLost))
            return;
        if (player.getPlayerHP() > 0) {
            displayTextSlowly("Attacked by the guard, you receive a retaliatory blow as a consequence of your actions. " +
                    "The guard's response is swift and forceful, causing you to suffer " + (int) Math.ceil(4 * difficultRate) + " damage.");
            setChoicesAndNextPositions(new String[]{"\"Sorry\"", "Leave", "", "", "crossRoad", "crossRoad", "", ""});
        }
    }

    private void crossRoad() {
        position = "crossRoad";
        ui.gameImageLabel.setIcon(ui.crossRoadImg);

        displayTextSlowly("You find yourself at a crossroad, standing at the intersection of multiple paths. " +
                "The choices laid out offering different directions to explore.\nWhere would you go?");
        setChoicesAndNextPositions(new String[]{"Go North", "Go East", "Go South", "Go West (the town)", "blackSmithHouse", "riverSide", "goblinCave", "townGate"});
    }

    private void blackSmithHouse() {
        position = "blackSmithHouse";
        ui.gameImageLabel.setIcon(ui.blackSmithHouseImg);

        displayTextSlowly("As you approach the blacksmith shop," +
                "the rhythmic clang of hammer on metal fills the air. " +
                "The skilled owner diligently works at the forge, their expertise evident in every strike.");
        setChoicesAndNextPositions(new String[]{"Talk to the blacksmith", "Go East", "Go South", "", "talkBlackSmith", "northRiver", "crossRoad", ""});
    }

    private void talkBlackSmith() {
        position = "talkBlackSmith";
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "", "blackSmithHouse", "", ""});

        if (isALiveGoblin) {
            displayTextSlowly("The blacksmith, aware of your unfamiliarity, kindly requests a favor before continuing the conversation. " +
                    "They describe the troubles caused by a mischievous goblin and reveal its last known location—a cave to the south." +
                    "They ask that you return once you have vanquished the goblin, emphasizing the importance of this task. ");
            ui.choice1.setText("\"Yeah! I'm on it.\"");
            nextPosition1 = "blackSmithHouse";
        } else {
            displayTextSlowly("\"Look like you've killed that goblin. Thank you,\" they acknowledge with gratitude. Their attention then turns to your quest to repulse the Demon King. " +
                    "\"You're on the way to repulse the Demon King, huh? I have a reward for you,\" they reveal, a glimmer of excitement in their voice. ");
            if (!isTakenArmor) {
                ui.choice1.setText("Take reward");
                nextPosition1 = "takeArmor";
            } else if (isTakenArmor) {
                displayTextSlowly("\"With that armor, you are ready to face the challenge of repulsing the Demon King,\" they affirm, their voice filled with encouragement. " +
                        "They acknowledge the gravity of your quest and express unwavering support, believing in your ability to overcome the formidable foe. " +
                        "The blacksmith's words fuel your determination as you set forth, knowing that you carry not only their craftsmanship but also their faith in your success.");
                ui.choice1.setText("\"Thank you\"");
                nextPosition1 = "blackSmithHouse";
            }
        }
    }

    private void takeArmor() {
        Armor armor = armors[rand.nextInt(3)];
        player.setArmor(armor);
        ui.armorLabel.setVisible(true);
        ui.armorLabel.setText("+" + armor.getName());
        ui.armorLabel.setForeground(Color.decode(armor.getHexColorCode()));

        isTakenArmor = true;
        displayTextSlowly("The BlackSmith: \"Take this " + armor.getName() + ".Maybe it could help you.\"" +
                "It seems that they have prepared a special armor, recognizing the importance of your mission and the need for enhanced protection.");
        setChoicesAndNextPositions(new String[]{"\"Thank you\"", "Leave", "", "", "talkBlackSmith", "blackSmithHouse", "", ""});
    }

    private void goblinCave() {
        position = "goblinCave";
        ui.gameImageLabel.setIcon(ui.goblinCaveImg);

        displayTextSlowly("Inside the entrance of the goblin cave, a stone tunnel leads deeper into darkness. " +
                "Amidst the dimly lit surroundings, patches of green and white grass sporadically dot the rocky floor, adding a touch of life to the otherwise desolate environment.");
        setChoicesAndNextPositions(new String[]{"Go inside the cave", "Go North", "Go East", "", "insideGoblinCave", "crossRoad", "southRiver", ""});
    }

    private void insideGoblinCave() {
        position = "insideGoblinCave";
        ui.gameImageLabel.setIcon(ui.insideGoblinCaveImg);
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "", "goblinCave", "", ""});

        if (isALiveGoblin) {
            displayTextSlowly("As you navigate the depths of the goblin cave, a sudden commotion catches your attention. " +
                    "Startled, you find yourself face-to-face with a goblin, wearing a rat suit and wielding a knife, as it rushes towards you with aggressive intent. " +
                    "Brace yourself for an imminent clash, as you prepare to defend against the goblin's swift and frenzied assault.");
            ui.choice1.setText("Encounter goblin");
            nextPosition1 = "encounterGoblin";
        } else if (!isTakenLongSword) {
            displayTextSlowly("In the depths of the cave, you stumble upon an old long sword, its weathered appearance hinting at its age. " +
                    "Despite its worn condition, the sword still holds a sense of strength and resilience, ready to be wielded once again.");
            ui.choice1.setText("Take the long sword");
            nextPosition1 = "takeLongSword";
        } else {
            displayTextSlowly("As you delve deeper into the cave, you find that there is not much to discover. " +
                    "The darkness surrounds you, revealing no hidden treasures or significant sights. " +
                    "It appears that this part of the cave holds no remarkable secrets, prompting you to continue your exploration elsewhere.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    private void takeLongSword() {
        isTakenLongSword = true;
        obtainWeapon(longSword);
        insideGoblinCave();
    }

    private void riverSide() {
        position = "riverSide";
        ui.gameImageLabel.setIcon(ui.riverSideImg);

        displayTextSlowly("You stand at the edge of a tranquil river, its waters flowing peacefully before you. " +
                "The river presents a natural barrier, separating you from the unknown that lies beyond.");
        setChoicesAndNextPositions(new String[]{"Go North", "Go East (cross the river)", "Go South", "Go West", "northRiver", "jungle", "southRiver", "crossRoad"});
    }

    private void northRiver() {
        position = "northRiver";
        ui.gameImageLabel.setIcon(ui.northRiverImg);
        setChoicesAndNextPositions(new String[]{"", "Go East(cross the river)", "Go South", "Go West", "", "mountain", "riverSide", "blackSmithHouse"});

        if (!isRestAtTent) {
            displayTextSlowly("North of the river, you come across a cozy fireplace, emanating warmth and inviting you to take a moment's respite. " +
                    "Adjacent to it, an old tent is set up by the riverside, offering a place to rest and recover 10 HP. " +
                    "Unfortunately, the path ahead has been blocked, denying any further progress in that direction.");
            ui.choice1.setText("Take a rest");
            nextPosition1 = "takeARest";
        } else {
            displayTextSlowly("North of the river, you come across a cozy fireplace, emanating warmth and inviting you to take a moment's respite. " +
                    "Adjacent to it, an old tent is set up by the riverside. " +
                    "Unfortunately, the path ahead has been blocked, denying any further progress in that direction.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    private void takeARest() {
        JOptionPane.showMessageDialog(ui.window, "Resting at a tent, you regain 10 HP, revitalizing your strength for the challenges that lie ahead.");
        updatePlayerHp(10);
        isRestAtTent = true;
        northRiver();
    }

    private void southRiver() {
        position = "southRiver";
        ui.gameImageLabel.setIcon(ui.southRiverImg);
        setChoicesAndNextPositions(new String[]{"", "Go North", "Go East(cross the river)", "Go West", "", "riverSide", "demonHideout", "goblinCave"});

        if (isALiveRiverMonster) {
            displayTextSlowly("South of the river, a strange vortex captivates your gaze from the center of the water. " +
                    "However, progress along the southern path is halted by a powerful waterfall, blocking any further advancement.");
            ui.choice1.setText("Throw a rock into it");
            nextPosition1 = "encounterRiverMonster";
        } else {
            displayTextSlowly("To the south of the river, a cascading waterfall creates a formidable barrier, blocking any progress along the southern path.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    private boolean crossTheRiver() {
        if (isALiveRiverMonster)
            if (rand.nextInt(3) == 0) {
                encounterRiverMonster();
                return false;
            }
        return true;
    }

    private void jungle() {
        if (position.equalsIgnoreCase("riverSide") && !crossTheRiver())
            return;

        position = "jungle";
        ui.gameImageLabel.setIcon(ui.jungleImg);
        displayTextSlowly("You find yourself in a peaceful forest clearing, surrounded by tall, ancient trees. " +
                "Shafts of sunlight filter through the dense foliage, casting a gentle glow on the lush grass beneath your feet.\n" +
                "\nYou see an apple tree.");
        setChoicesAndNextPositions(new String[]{"Hit the tree", "Go North", "Go South", "Go West(cross the river)", "hitTheAppleTree", "mountain", "demonHideout", "riverSide"});
    }

    private void hitTheAppleTree() {
        position = "hitTheAppleTree";
        int c1 = rand.nextInt(4);
        if (appleOnTree > 0 && c1 % 2 == 0) {
            int c2 = JOptionPane.showConfirmDialog(ui.window, "You hit the apple tree, causing a ripe apple to fall. " +
                    "You have the option to consume the apple for a boost of 4 HP.");
            if (c2 == 0) {
                updatePlayerHp(4);
                appleOnTree--;
            }
        } else if (c1 == 1) {
            JOptionPane.showMessageDialog(ui.window, "You hit the apple tree, angering a nearby monkey. " +
                    "It retaliates by throwing a stick at you, causing " + (int) Math.ceil(2 * difficultRate) + " damage.");
            if (updatePlayerHp(-((int) Math.ceil(2 * difficultRate))))
                return;
        } else
            JOptionPane.showMessageDialog(ui.window, "Nothing happen.");
        jungle();
    }

    private void mountain() {
        if (position.equalsIgnoreCase("northRiver") && !crossTheRiver())
            return;

        position = "mountain";
        ui.gameImageLabel.setIcon(ui.mountainImg);
        displayTextSlowly("As you approach the towering mountain, your gaze is drawn to the peculiar sight atop its peak. " +
                "A cluster of massive rocks hovers effortlessly in the air, defying the natural laws of gravity. " +
                "Intrigued by this enchanting spectacle, you feel an irresistible pull to ascend the mountain and uncover the secrets hidden within the realm of the floating rocks.");
        setChoicesAndNextPositions(new String[]{"Climb to the top", "Go South", "Go West(cross the river)", "", "mountainTop", "jungle", "northRiver", ""});
    }

    private void mountainTop() {
        position = "mountainTop";
        ui.gameImageLabel.setIcon(ui.mountainTopImg);
        displayTextSlowly("Ascending the rugged slopes of the mountain, you persevere until you reach the summit. " +
                "Ahead of you stands a majestic stone gate, its weathered surface hinting at the passage of time. " +
                "Passing through the gate, you enter a sacred space surrounded by a circle of five towering stone statues. " +
                "Their imposing presence and intricate carvings exude an aura of ancient power, leaving you in awe of the mystic energy that permeates the area.");
        setChoicesAndNextPositions(new String[]{"Touch the middle statue", "Leave", "", "", "touchStatue", "mountain", "", ""});
        if (isTakenPower)
            nextPosition1 = "";
    }

    private void touchStatue() {
        position = "touchStatue";
        displayTextSlowly("The statues bestow power upon the traveler, recognizing their courage and determination. " +
                "Legends foretell the arrival of a chosen hero who would combat the forces of darkness, and the statues identify the traveler as the fulfillment of that prophecy. " +
                "With their heroic blood resonating with the statues' essence, the traveler is honored for their journey and bestowed the choice of a power that suits their strengths and upcoming challenges.");
        setChoicesAndNextPositions(new String[]{"...", "Leave", "", "", "offerPower", "mountain", "", ""});
    }

    private void offerPower() {
        position = "offerPower";
        displayTextSlowly("A mystical voice reverberates through the air, its words laced with ancient knowledge and reverence. " +
                "\"Knowing the heroic blood that flows within you, we offer you a choice,\" the voice declares. " +
                "\"Embrace the path of strength, wield the power of scorching fire, command the paralyzing force of lightning, or harness the soothing energy of healing water.\"");
        setChoicesAndNextPositions(new String[]{"Strength", "Fire power", "Lightning power", "Water power", "strengthPower", "firePower", "lightningPower", "waterPower"});
    }

    private void strengthPower() {
        position = "strengthPower";
        displayTextSlowly("Strength Power: This power enhances the traveler's physical capabilities by increasing their HP and base attack.\n" +
                "Tap into your inner strength and experience a surge of power. " +
                "Your maximum HP is increased by 3, fortifying your resilience and granting you the endurance to withstand greater challenges. " +
                "Additionally, your base attack is enhanced by 2, empowering your strikes and making your every blow more impactful.");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takeStrengthPower", "offerPower", "", ""});
    }

    private void takeStrengthPower() {
        isTakenPower = true;
        player.increasePlayerMaxHP(3);
        player.increaseBaseAttack(2);
        updatePlayerHp(0);
        mountainTop();
    }

    private void firePower() {
        selectedSpell = fireBall;
        position = "firePower";
        displayTextSlowly("Fire Power: This power grants the traveler mastery over the element of fire, enabling them to unleash devastating fireballs that inflict immense damage upon their enemies.\n" +
                "Unleash a mighty magic fireball that scorches your enemies, dealing a significant " + fireBall.getDamage() + " damage.");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takePower", "offerPower", "", ""});
    }

    private void lightningPower() {
        position = "lightningPower";
        selectedSpell = lightningBolt;
        displayTextSlowly("Lightning Power: Harness the electrifying energy of lightning to deal substantial damage to your foes, while also stunning them in their tracks.\n" +
                "Unleash bolts of lightning that deal " + lightningBolt.getDamage() + " damage and momentarily stun monsters, granting you the opportunity to follow up with an additional attack.");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takePower", "offerPower", "", ""});
    }

    private void waterPower() {
        selectedSpell = waterSurge;
        position = "waterPower";
        displayTextSlowly("As the water spell surges forth, it forms a protective barrier that shields you from an impending monster attack, deflecting its harm. " +
                "Simultaneously, the restorative properties of the water envelop you, replenishing your vitality and restoring 7 points of health. ");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takePower", "offerPower", "", ""});
    }

    private void takePower() {
        isTakenPower = true;
        player.setSpell(selectedSpell);
        ui.spellLabel.setVisible(true);
        ui.spellLabel.setForeground(Color.decode(selectedSpell.getHexColorCode()));
        updateSpellStatus();
        mountainTop();
    }

    private void demonHideout() {
        if (position.equalsIgnoreCase("southRiver") && !crossTheRiver()) {
            return;
        }
        position = "demonHideout";
        setChoicesAndNextPositions(new String[]{"", "Go North", "Go West", "", "", "jungle", "southRiver", ""});

        if (isAliveDemonKing) {
            ui.gameImageLabel.setIcon(ui.demonHideoutImg);
            displayTextSlowly("Approaching the demon's hideout, a sinister and dark landscape greeted me. " +
                    "The eerie atmosphere consumed the sunlight, casting shadows that danced with malevolence. " +
                    "The entrance to the demon's lair stood before me. " +
                    "Steeling myself for the impending battle, I braced for the horrors that awaited within.");
            ui.choice1.setText("Enter");
            if (isAliveShadowSerpent)
                nextPosition1 = "encounterShadowSerpent";
            else nextPosition1 = "encounterDemonKing";
        } else {
            ui.gameImageLabel.setIcon(ui.demonHideoutAfterBattleImg);
            displayTextSlowly("The battlefield, once ruled by the malevolent presence of the demon king, now lies in utter devastation and ruin. " +
                    "The ground is strewn with debris and the remnants of a fierce struggle, a testament to the epic clash that unfolded here. " +
                    "Silence hangs heavy in the air, broken only by the echoes of past chaos and the solemn whispers of victory.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    private void insideDemonHideout() {
        position = "insideDemonHideout";
        ui.gameImageLabel.setIcon(ui.demonHideoutImg);
        displayTextSlowly("After triumphing over the black serpent, you forge ahead, delving deeper into the heart of the demon's hideout. " +
                "The air grows heavier, and an eerie silence engulfs the surroundings, heightening the sense of foreboding. " +
                "Every step brings you closer to the ultimate confrontation with the demon king, filling you with a mix of anticipation and trepidation.");
        setChoicesAndNextPositions(new String[]{"Go deeper", "Leave", "", "", "encounterDemonKing", "demonHideout", "", ""});
    }

    private void defeatDemonKing() {
        position = "defeatDemonKing";
        ui.gameImageLabel.setIcon(ui.explosionImg);
        displayTextSlowly("As the final blow lands on the demon king, its form erupts in a cataclysmic explosion, engulfing the hideout in a blaze of destruction. " +
                "In the midst of the chaos, you find yourself on the edge of consciousness, barely clinging to life. " +
                "Overwhelmed by the intense battle and the aftermath, you succumb to exhaustion and faint, your fate hanging in the balance.");
        setChoicesAndNextPositions(new String[]{"...", "......", "", "", "wakeUpAfterFinalBattle", "wakeUpAfterFinalBattle", "", ""});
    }

    private void wakeUpAfterFinalBattle() {
        position = "wakeUpAfterFinalBattle";
        ui.gameImageLabel.setIcon(ui.demonHideoutAfterBattleImg);
        displayTextSlowly("As you slowly regain consciousness, your eyes flutter open to a scene of utter devastation. " +
                "The battlefield, once dominated by the demonic presence of the demon king, now lies in ruins. " +
                "Amidst the wreckage, your gaze falls upon a gleaming object—the demon sword. " +
                "Disoriented and weak, you struggle to gather your bearings and assess the aftermath of the epic clash.");
        player.setPlayerHP(1);
        updatePlayerHp(0);
        JOptionPane.showMessageDialog(ui.window, "You have defeated the demon king!");
        obtainWeapon(demonSword);
        setChoicesAndNextPositions(new String[]{"Leave", "", "", "", "townGate", "", "", ""});
    }

    private void encounterGoblin() {
        if (!position.equalsIgnoreCase("encounterGoblin"))
            lastPosition = position;
        position = "encounterGoblin";
        ui.gameImageLabel.setIcon(ui.goblinImg);
        encounterMonster(goblin);
    }

    private void encounterRiverMonster() {
        if (!position.equalsIgnoreCase("encounterRiverMonster"))
            lastPosition = position;
        position = "encounterRiverMonster";
        ui.gameImageLabel.setIcon(ui.riverMonsterImg);

        JOptionPane.showMessageDialog(ui.window, "You come face to face with a formidable river monster. " +
                "This fearsome creature poses a significant threat.");
        encounterMonster(riverMonster);
    }

    private void encounterShadowSerpent() {
        if (!position.equalsIgnoreCase("encounterShadowSerpent"))
            lastPosition = position;
        position = "encounterShadowSerpent";
        ui.gameImageLabel.setIcon(ui.shadowSerpentImg);

        JOptionPane.showMessageDialog(ui.window, "In the depths of the demon's lair, you come face to face with a menacing black serpent, ready to strike.");
        encounterMonster(shadowSerpent);
    }

    private void encounterDemonKing() {
        if (!position.equalsIgnoreCase("encounterDemonKing"))
            lastPosition = position;
        position = "encounterDemonKing";
        ui.gameImageLabel.setIcon(ui.demonKingImg);

        JOptionPane.showMessageDialog(ui.window, "You finally confront the demon king, standing face-to-face with the embodiment of evil itself.");
        encounterMonster(demonKing);
    }

    private void attackGoblin(boolean useSpell) {
        if (attackMonster(goblin, useSpell)) {
            if (goblin.getMonsterCurrentHP() == 0) {
                ui.mapButton.setEnabled(true);
                isALiveGoblin = false;
                JOptionPane.showMessageDialog(ui.window, "You have defeated the goblin!");
                insideGoblinCave();
            } else encounterMonster(goblin);
        }
    }

    private void attackRiverMonster(boolean useSpell) {
        if (attackMonster(riverMonster, useSpell)) {
            if (riverMonster.getMonsterCurrentHP() == 0) {
                ui.mapButton.setEnabled(true);
                isALiveRiverMonster = false;
                JOptionPane.showMessageDialog(ui.window, "Victorious against the river monster, you claim a gleaming trident as your reward.");
                obtainWeapon(trident);
                selectedPosition(lastPosition);
            } else encounterMonster(riverMonster);
        }
    }

    private void attackShadowSerpent(boolean useSpell) {
        if (attackMonster(shadowSerpent, useSpell)) {
            if (shadowSerpent.getMonsterCurrentHP() == 0) {
                ui.mapButton.setEnabled(true);
                isAliveShadowSerpent = false;
                JOptionPane.showMessageDialog(ui.window, "You have defeated the shadow serpent!");
                insideDemonHideout();
            } else encounterMonster(shadowSerpent);
        }
    }

    private void attackDemonKing(boolean useSpell) {
        if (attackMonster(demonKing, useSpell)) {
            if (demonKing.getMonsterCurrentHP() == 0) {
                isAliveDemonKing = false;
                ui.mapButton.setEnabled(true);
                selectedPosition("defeatDemonKing");
            } else encounterMonster(demonKing);
        }
    }

    private void encounterMonster(Monster monster) {
        ui.mapButton.setEnabled(false);
        displayTextSlowly(monster.getName() + "'s HP: " + monster.getMonsterCurrentHP() + "/" + monster.getMonsterMaxHP() + "\n" + (monster.getEffect().equalsIgnoreCase("stunned") ? monster.getName() + " is " + monster.getEffect() : "") + "\nWhat would you do?");
        if (player.getSpell() != null) updateSpellStatus();
        if (player.getSpell() != null && player.getSpell().getCoolDownRemain() == 0) {
            setChoicesAndNextPositions(new String[]{"Fight", "Use " + player.getSpell().getName(), "Try to run", "", "", "", "tryToRun", ""});
        } else setChoicesAndNextPositions(new String[]{"Fight", "Try to run", "", "", "", "tryToRun", "", ""});

        if (monster.getName().equalsIgnoreCase("Goblin")) {
            nextPosition1 = "attackGoblin";
            nextPosition2 = (player.getSpell() != null && player.getSpell().getCoolDownRemain() == 0) ? "attackGoblinWithSpell" : "tryToRun";
        } else if (monster.getName().equalsIgnoreCase("River monster")) {
            nextPosition1 = "attackRiverMonster";
            nextPosition2 = (player.getSpell() != null && player.getSpell().getCoolDownRemain() == 0) ? "attackRiverMonsterWithSpell" : "tryToRun";
        } else if (monster.getName().equalsIgnoreCase("Shadow serpent")) {
            nextPosition1 = "attackShadowSerpent";
            nextPosition2 = (player.getSpell() != null && player.getSpell().getCoolDownRemain() == 0) ? "attackShadowSerpentWithSpell" : "tryToRun";
        } else if (monster.getName().equalsIgnoreCase("Demon king")) {
            nextPosition1 = "attackDemonKing";
            nextPosition2 = (player.getSpell() != null && player.getSpell().getCoolDownRemain() == 0) ? "attackDemonKingWithSpell" : "tryToRun";
        }
    }

    private boolean attackMonster(Monster monster, boolean useSpell) {
        Spell spell = null;
        if (player.getSpell() != null)
            spell = player.getSpell();

        if (spell != null && useSpell) {
            JOptionPane.showMessageDialog(ui.window, "You cast " + spell.getName() + ". " + spell.getSpecialEffect());
            spell.resetCoolDown();

            if (spell.getName().equalsIgnoreCase("Water Surge"))
                return updatePlayerHp(-spell.getDamage());
            else if (spell.getName().equalsIgnoreCase("Lightning Bolt")) {
                monster.loseHP(spell.getDamage());
                monster.setEffect("stunned");
                return true;
            } else if (spell.getName().equalsIgnoreCase("Fire Ball"))
                monster.loseHP(spell.getDamage());
        } else {
            Weapon currentWeapon = player.getWeapon(ui.weaponComboBox.getSelectedItem().toString());
            int playerDamage = player.getBaseAttack() + rand.nextInt(currentWeapon.getCriticalAttackDamage() - currentWeapon.getAttackDamage()) + currentWeapon.getAttackDamage();
            JOptionPane.showMessageDialog(ui.window, "You attacked the monster with " + currentWeapon.getName() + " and gave " + playerDamage + " damage!");
            monster.loseHP(playerDamage);

            if (spell != null)
                spell.decreaseCoolDown();
        }

        if (monster.getEffect().equalsIgnoreCase("stunned")) {
            JOptionPane.showMessageDialog(ui.window, "The lightning power courses through the monster, leaving it stunned and unable to launch any further attacks against you.");
            monster.setEffect("");
            return true;
        } else {
            int monsterDamage = rand.nextInt(monster.getAttackDamage()) + 1 - (player.getArmor() != null ? player.getArmor().getDamageReduced() : 0);
            if (monsterDamage < 0) monsterDamage = 0;
            JOptionPane.showMessageDialog(ui.window, "The monster attacked you." + (player.getArmor() != null ? (" With " + player.getArmor().getName()) : "") + " you took " + monsterDamage + " damage!");

            return updatePlayerHp(-monsterDamage);
        }
    }

    private void tryToRun() {
        if (rand.nextBoolean()) {
            JOptionPane.showMessageDialog(ui.window, "You dodged the monster's attack and run away!");
            ui.mapButton.setEnabled(true);
            selectedPosition(lastPosition);
        } else {
            int damageTaken = (int) Math.ceil(2 * difficultRate);
            JOptionPane.showMessageDialog(ui.window, "You've failed to escape and taken " + damageTaken + " damage from the monster!");
            if (updatePlayerHp(-damageTaken))
                selectedPosition(position);
        }
    }

    private boolean updatePlayerHp(int hpAmount) {
        if (hpAmount < 0)
            player.loseHP(-hpAmount);
        else player.restoreHP(hpAmount);
        ui.hpLabel.setText("HP: " + player.getPlayerHP() + "/" + player.getPlayerMaxHP());
        if (player.getPlayerHP() == 0) {
            deadScreen();
            return false;
        }
        return true;
    }

    private void updateSpellStatus() {
        Spell spell = player.getSpell();
        if (spell.getCoolDownRemain() == 0)
            ui.spellLabel.setText(spell.getName() + " (Ready)");
        else ui.spellLabel.setText(spell.getName() + " (CD: " + spell.getCoolDownRemain() + ")");
    }

    private void obtainWeapon(Weapon weapon) {
        player.addWeapon(weapon);
        ui.weaponComboBox.addItem(weapon.getName());
        ui.weaponComboBox.setSelectedItem(weapon.getName());
        JOptionPane.showMessageDialog(ui.window, "You obtained the " + weapon.getName() + "!!!");
    }

    private void theEnd() {
        ui.gameImageLabel.setIcon(ui.theEndImg);
        displayTextSlowly("Congratulations! You have successfully completed the game and overcome numerous challenges to emerge victorious. " +
                "Well done on your remarkable journey and accomplishments!");
        setChoicesAndNextPositions(new String[]{"Play again", "Quit game", "", "", "start", "quit", "", ""});
    }

    private void deadScreen() {
        ui.gameImageLabel.setIcon(ui.deadScreenImg);
        displayTextSlowly("YOU DIED!!!!");
        setChoicesAndNextPositions(new String[]{"Try again", "Quit game", "", "", "start", "quit", "", ""});
    }

    private void setChoicesAndNextPositions(String[] choicesAndNextPositions) {
        ui.choice1.setText(choicesAndNextPositions[0]);
        ui.choice2.setText(choicesAndNextPositions[1]);
        ui.choice3.setText(choicesAndNextPositions[2]);
        ui.choice4.setText(choicesAndNextPositions[3]);
        nextPosition1 = choicesAndNextPositions[4];
        nextPosition2 = choicesAndNextPositions[5];
        nextPosition3 = choicesAndNextPositions[6];
        nextPosition4 = choicesAndNextPositions[7];
    }

    private void displayTextSlowly(String paragraph) {
        ui.mainTextArea.setText("");
        Thread thread = new Thread(() -> {
            int currentIndex = 0;
            try {
                while (currentIndex < paragraph.length()) {
                    Thread.sleep(15);
                    String currentChar = Character.toString(paragraph.charAt(currentIndex));
                    ui.mainTextArea.append(currentChar);
                    currentIndex++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        stopPreviousThreads();
        thread.start();
        previousThread = thread;
    }

    private void stopPreviousThreads() {
        if (previousThread != null && previousThread.isAlive()) {
            previousThread.stop();
        }
    }
}
