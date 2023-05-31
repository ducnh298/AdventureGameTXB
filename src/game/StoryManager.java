package game;

import model.Armor;
import model.Spell;
import model.Weapon;
import model.armors.Armor_GoldenArmor;
import model.armors.Armor_IronArmor;
import model.armors.Armor_SilverArmor;
import model.monsters.Monster_EvilWitch;
import model.spells.Spell_FireStorm;
import model.spells.Spell_LightningBolt;
import model.spells.Spell_PoisonBreeze;
import model.spells.Spell_WaterSurge;
import model.weapons.Weapon_DemonSword;
import model.weapons.Weapon_LongSword;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StoryManager {
    Game game;
    UI ui;
    VisibilityManager vm;
    GameModel gm;
    CombatManager cm;
    Thread previousThread;
    String nextPosition1, nextPosition2, nextPosition3, nextPosition4;

    Random rand = new Random();

    public StoryManager(Game game) {
        this.gm = game.gameModel;
        this.ui = game.ui;
        this.vm = game.visibilityManager;
        this.cm = new CombatManager(this);
    }

    public void selectedPosition(String nextPosition) {
        switch (nextPosition) {
            case "start":
                vm.showTitleScreen();
                break;
            case "quit":
                System.exit(0);
                break;
            case "map":
                map();
                break;
            case "townGate":
                townGate();
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
            case "takeGoblinEar":
                takeGoblinEar();
                break;
            case "takeLongSword":
                takeLongSword();
                break;
            case "encounterGoblin":
                cm.encounterGoblin();
                break;
            case "attackGoblin":
                cm.attackGoblin(false);
                break;
            case "attackGoblinWithSpell":
                cm.attackGoblin(true);
                break;
            case "riverSide":
                riverSide();
                break;
            case "talkWitch1":
                talkWitch1();
                break;
            case "talkWitch2":
                talkWitch2();
                break;
            case "talkWitch3":
                talkWitch3();
                break;
            case "talkWitch4":
                talkWitch4();
                break;
            case "talkWitch5":
                talkWitch5();
                break;
            case "encounterEvilWitch":
                cm.encounterEvilWitch();
                break;
            case "attackEvilWitch":
                cm.attackEvilWitch(false);
                break;
            case "attackEvilWitchWithSpell":
                cm.attackEvilWitch(true);
                break;
            case "enhanceStrength":
                enhanceStrength();
                break;
            case "learnPoisonBreeze":
                learnPoisonBreeze();
                break;
            case "northRiver":
                northRiver();
                break;
            case "takeRest":
                takeRest();
                break;
            case "southRiver":
                southRiver();
                break;
            case "encounterRiverMonster":
                cm.encounterRiverMonster();
                break;
            case "attackRiverMonster":
                cm.attackRiverMonster(false);
                break;
            case "attackRiverMonsterWithSpell":
                cm.attackRiverMonster(true);
                break;
            case "tryToRun":
                cm.tryToRun();
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
                cm.encounterShadowSerpent();
                break;
            case "attackShadowSerpent":
                cm.attackShadowSerpent(false);
                break;
            case "attackShadowSerpentWithSpell":
                cm.attackShadowSerpent(true);
                break;
            case "encounterDemonKing":
                cm.encounterDemonKing();
                break;
            case "attackDemonKing":
                cm.attackDemonKing(false);
                break;
            case "attackDemonKingWithSpell":
                cm.attackDemonKing(true);
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

    public void map() {
        ui.gameImageLabel.setIcon(ui.mapImg);

        displayTextSlowly("You're checking the map.");
        setChoicesAndNextPositions(new String[]{"Done", "", "", "", "", "", "", ""});
        nextPosition1 = gm.position;
    }

    public void townGate() {
        gm.position = "townGate";
        ui.gameImageLabel.setIcon(ui.townGateImg);
        setChoicesAndNextPositions(new String[]{"", "", "Enter town", "Leave", "talkGuard1", "attackGuard", "talkGuard1", "crossRoad"});

        if (!gm.isAliveDemonKing) {
            gm.isAngryGuard = false;
            displayTextSlowly("Returning to the town, you spot the familiar figure of the guard standing at his post. " +
                    "As you approach, he looks up and recognition flashes across his face. \"You've returned,\" he says with a mix of surprise and relief. ");
        } else
            displayTextSlowly("As you draw near to the town gate, you notice that it is tightly shut and fortified, clearly designed to keep potential dangers at bay. " +
                    "Standing before the gate is a guard, fully equipped with armor and a sword, their attentive gaze fixed upon you. " +
                    "They carefully evaluate your presence, their eyes alert and watchful.");
        if (gm.isAngryGuard) {
            ui.choice1.setText("Talk to the angry guard");
            ui.choice2.setText("Attack the guard again");
        } else {
            ui.choice1.setText("Talk to the guard");
            ui.choice2.setText("Attack the guard");
        }
    }

    public void talkGuard1() {
        gm.position = "talkGuard1";
        ui.gameImageLabel.setIcon(ui.guardImg);
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "talkGuard2", "crossRoad", "", ""});

        if (!gm.isAliveDemonKing) {
            displayTextSlowly("\"I heard rumors of the demon king's defeat. Is it true?\" " +
                    "You nod, sharing the details of your epic battle and the destruction of the demon king's hideout. " +
                    "The guard's stern expression softens, replaced by a genuine smile. " +
                    "\"You've done the town a great service. We owe you our gratitude.\"");
            ui.choice1.setText("\"Can i enter the town now?\"");
        } else if (gm.isTakenArmor) {
            displayTextSlowly("The guard who notices your newly acquired armor: \"Ah, I see you're donning your new armor. It suits you well. " +
                    "There is something else you should know. Legend has it that to the north-east of here, atop the mountain, lies a sacred place. " +
                    "It is said that those who possess great courage and are recognized as the chosen hero can harness their heroic blood and choose a power that suits them.\"");
            ui.choice1.setText("\"I'll have it a try\"");
            nextPosition1 = "crossRoad";
        } else {
            if (gm.isAngryGuard)
                displayTextSlowly("The guard is clearly angry as they scold you for your attack. " +
                        "They look disappointed and emphasize how crucial it is to keep the town safe. Trust and redemption feel far away in this moment. " +
                        "You quickly apologize and clarify that you're a traveler looking for your brother, following clues that led you here.");
            else
                displayTextSlowly("Guard: \"Hello, stranger! I cannot let unfamiliar faces into our town. " +
                        "Prove yourself trustworthy, or find another way in.\" " +
                        "You explain your purpose as a traveler searching for your brother, guided by the clues that brought you here. " +
                        "Seeking assistance, you inquire about gaining entry, hoping for guidance to reach your destination.");
            ui.choice1.setText("\"How can i enter the town?\"");
        }
    }

    public void talkGuard2() {
        gm.position = "talkGuard2";
        ui.gameImageLabel.setIcon(ui.guardImg);
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "", "crossRoad", "", ""});

        if (!gm.isAliveDemonKing) {
            displayTextSlowly("The guard, now aware of your heroic triumph, opens the town gate for you without hesitation.");
            ui.choice1.setText("Enter the town");
            nextPosition1 = "theEnd";
        } else {
            displayTextSlowly("Guard: \"Lately, the demon king has been intruding into our land, endangering our safety. " +
                    "We urgently require a hero to safeguard our town. " +
                    "If you can defeat the demon king and repel the threat, I will allow you to enter.\"");
            ui.choice1.setText("\"How am i supposed to do that\"");
            nextPosition1 = "talkGuard3";
        }
    }

    public void talkGuard3() {
        gm.position = "talkGuard3";
        ui.gameImageLabel.setIcon(ui.guardImg);
        ui.mapButton.setVisible(true);

        displayTextSlowly("The guard notices that your current gear is not sufficient to defeat the Demon King. " +
                "They hand you a map with the location of the demon king's hideout marked and advise you to visit the blacksmith in the north to acquire better equipment.");
        setChoicesAndNextPositions(new String[]{"\"Ok! Thank you.\"", "Leave", "", "", "crossRoad", "crossRoad", "", ""});
    }

    public void attackGuard() {
        gm.position = "attackGuard";

        ui.gameImageLabel.setIcon(ui.guardImg);
        gm.isAngryGuard = true;

        int hpLost = (int) Math.ceil(4 * gm.difficultRate);
        if (!updatePlayerHp(-hpLost))
            return;
        if (gm.player.getPlayerHP() > 0) {
            displayTextSlowly("\n" +
                    "As a consequence of your actions, the guard retaliates and strikes you in response. " +
                    "Their response is quick and powerful, resulting in you taking " + (int) Math.ceil(4 * gm.difficultRate) + " damage.");
            setChoicesAndNextPositions(new String[]{"\"Sorry\"", "Leave", "", "", "crossRoad", "crossRoad", "", ""});
        }
    }

    public void crossRoad() {
        gm.position = "crossRoad";
        ui.gameImageLabel.setIcon(ui.crossRoadImg);

        displayTextSlowly("You find yourself at a crossroad, standing at the intersection of multiple paths. " +
                "The choices laid out offering different directions to explore.\nWhere would you go?");
        setChoicesAndNextPositions(new String[]{"Go North", "Go East", "Go South", "Go West (the town)", "blackSmithHouse", "riverSide", "goblinCave", "townGate"});
    }

    public void blackSmithHouse() {
        gm.position = "blackSmithHouse";
        ui.gameImageLabel.setIcon(ui.blackSmithHouseImg);

        displayTextSlowly("As you approach the blacksmith shop, the sound of hammer striking metal echoes in the air. " +
                "The skilled blacksmith can be seen diligently working at the forge, their expertise showcased by each precise strike.");
        setChoicesAndNextPositions(new String[]{"Talk to the blacksmith", "Go East", "Go South", "", "talkBlackSmith", "northRiver", "crossRoad", ""});
    }

    public void talkBlackSmith() {
        gm.position = "talkBlackSmith";
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "", "blackSmithHouse", "", ""});

        if (gm.isALiveGoblin) {
            displayTextSlowly("The blacksmith, acknowledging your unfamiliar presence, kindly asks for a favor before proceeding with further conversation. " +
                    "They explain the troubles caused by a mischievous goblin and provide its last known location—a cave to the south. " +
                    "They request that you return once you have successfully dealt with the goblin."
            );
            ui.choice1.setText("\"I got it\"");
            nextPosition1 = "blackSmithHouse";
        } else {
            displayTextSlowly("\"Look like you've killed that goblin. Thank you,\" they acknowledge with gratitude. Their attention then turns to your quest to repulse the Demon King. " +
                    "\"You're on the way to repulse the Demon King, huh? I have a reward for you,\" they reveal, a glimmer of excitement in their voice. ");
            if (!gm.isTakenArmor) {
                ui.choice1.setText("Take reward");
                nextPosition1 = "takeArmor";
            } else if (gm.isTakenArmor) {
                displayTextSlowly("They believe in your ability to overcome the challenge and offer their unwavering support. " +
                        "The blacksmith advises, \"There's one more thing you should know. " +
                        "The witch who wanders along the river near the goblin cave is known for her trickery. Be cautious and stay alert when you encounter her.\"");
                ui.choice1.setText("\"Thank you\"");
                nextPosition1 = "blackSmithHouse";
            }
        }
    }

    public void takeArmor() {
        if (gm.armors == null)
            gm.armors = new Armor[]{new Armor_IronArmor(), new Armor_SilverArmor(), new Armor_GoldenArmor()};

        Armor armor = gm.armors[rand.nextInt(3)];
        gm.player.setArmor(armor);
        ui.armorLabel.setVisible(true);
        ui.armorLabel.setText("+" + armor.getName());
        ui.armorLabel.setForeground(Color.decode(armor.getHexColorCode()));

        gm.isTakenArmor = true;
        displayTextSlowly("The BlackSmith: \"Take this " + armor.getName() + ".Maybe it could help you.\"" +
                "It seems that they have prepared a special armor, recognizing the importance of your mission and the need for enhanced protection.");
        setChoicesAndNextPositions(new String[]{"\"Thank you\"", "Leave", "", "", "talkBlackSmith", "blackSmithHouse", "", ""});
    }

    public void goblinCave() {
        gm.position = "goblinCave";
        ui.gameImageLabel.setIcon(ui.goblinCaveImg);

        displayTextSlowly("As you approach the entrance of the goblin cave, you see an ancient stone tunnel that leads further into the darkness. " +
                "The cave is situated in the heart of a dense forest, surrounded by tall trees and vibrant green grass adorned with a few delicate flowers. " +
                "A curious small stone catches your attention, resting mysteriously in front of the cave's entrance.");
        setChoicesAndNextPositions(new String[]{"Go inside the cave", "Go North", "Go East", "", "insideGoblinCave", "crossRoad", "southRiver", ""});
    }

    public void insideGoblinCave() {
        gm.position = "insideGoblinCave";
        ui.gameImageLabel.setIcon(ui.insideGoblinCaveImg);
        setChoicesAndNextPositions(new String[]{"", "Leave", "", "", "", "goblinCave", "", ""});

        if (gm.isALiveGoblin) {
            displayTextSlowly("As you explore deeper into the goblin cave, a sudden noise startles you. You come face-to-face with a goblin, dressed in a makeshift rat costume and brandishing a knife. " +
                    "It lunges at you with fierce determination, ready to attack.");
            ui.choice1.setText("Encounter goblin");
            nextPosition1 = "encounterGoblin";
        } else if (gm.witchQuestActive && !gm.isTakenGoblinEar) {
            displayTextSlowly("As you explore the depths of the cave, you stumble upon the lifeless body of a goblin. " +
                    "Remembering the witch's request, you reach down to retrieve the goblin's left ear.");
            ui.choice1.setText("Take the goblin's left ear");
            nextPosition1 = "takeGoblinEar";
        } else if (!gm.isTakenLongSword) {
            displayTextSlowly("As you explore the depths of the cave, you come across the lifeless body of a goblin. " +
                    "In the dim light, something shiny catches your attention, revealing an old, weathered long sword.  " +
                    "Despite its worn appearance, yet it exudes a sense of strength, ready to be wielded once again.");
            ui.choice1.setText("Take the long sword");
            nextPosition1 = "takeLongSword";
        } else {
            displayTextSlowly("As you continue deeper into the cave, you find that there is not much to discover. " +
                    "The darkness surrounds you, revealing no hidden treasures or significant sights. " +
                    "It appears that this part of the cave holds no remarkable secrets, prompting you to continue your exploration elsewhere.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    public void takeGoblinEar() {
        gm.isTakenGoblinEar = true;
        insideGoblinCave();
    }

    public void takeLongSword() {
        gm.isTakenLongSword = true;
        if (gm.longSword == null)
            gm.longSword = new Weapon_LongSword();
        obtainWeapon(gm.longSword);
        insideGoblinCave();
    }

    public void riverSide() {
        gm.position = "riverSide";
        if (!gm.isDefeatedEvilWitch) {
            ui.gameImageLabel.setIcon(ui.riverSideWithWitchImg);

            displayTextSlowly("You find yourself by the riverside, where the gentle flow of the water creates a serene ambiance. " +
                    "As you admire the tranquil scene, a witch passes by, her long dark cloak billowing in the breeze, and a pointy hat adorning her head. " +
                    "In the distance, you notice a small wooden bridge that stretches across the river.");
            setChoicesAndNextPositions(new String[]{"Talk to the Witch", "Go North", "Go East(cross the river on bridge)", "Go West", "talkWitch1", "northRiver", "jungle", "crossRoad"});
            if (gm.witchQuestActive)
                nextPosition1 = "talkWitch2";
        } else {
            ui.gameImageLabel.setIcon(ui.riverSideImg);

            displayTextSlowly("You find yourself at the edge of a river, its calm current gently winding through the landscape. " +
                    "The soothing sound of flowing water creates a peaceful atmosphere as you notice a small wooden bridge spanning across the river.");
            setChoicesAndNextPositions(new String[]{"Go North", "Go East(cross the river on bridge)", "Go South", "Go West", "northRiver", "jungle", "southRiver", "crossRoad"});
        }
    }

    public void talkWitch1() {
        gm.position = "talkWitch1";
        ui.gameImageLabel.setIcon(ui.witchImg);

        displayTextSlowly("\"Hey there, young man,\" the witch addresses you, her gaze filled with curiosity. " +
                "\"You seem strong and capable. If you do me a favor, I'll be sure to reward you handsomely." +
                "\" Her offer hangs in the air, tempting you with the promise of a worthy prize.");
        setChoicesAndNextPositions(new String[]{"Ask about the favor", "Leave", "", "", "talkWitch2", "riverSide", "", ""});
    }

    public void talkWitch2() {
        gm.position = "talkWitch2";
        ui.gameImageLabel.setIcon(ui.witchImg);

        gm.witchQuestActive = true;
        setChoicesAndNextPositions(new String[]{"\"I'm on it\"", "Attack the witch", "Leave", "", "riverSide", "talkWitch3", "riverSide", ""});

        StringBuilder text = new StringBuilder("As you inquire about the favor, the witch requests that you venture into the jungle on the other side of the river and fetch her a fresh apple. " +
                "Additionally, she asks for a goblin's left ear, explaining that it holds powerful magical properties.");
        if (gm.isTakenGoblinEar)
            text.append("\n(You had the goblin's left ear)");
        if (gm.isTakenApple)
            text.append("\n(You had the apple)");
        displayTextSlowly(text.toString());

        if (gm.isTakenGoblinEar && gm.isTakenApple) {
            ui.choice1.setText("\"Here are the items you requested.\"");
            nextPosition1 = "talkWitch3";
        }
    }

    public void talkWitch3() {
        gm.position = "talkWitch3";

        ui.gameImageLabel.setIcon(ui.witchImg);
        setChoicesAndNextPositions(new String[]{"Encounter the Evil Witch", "", "", "", "encounterEvilWitch", "", "", ""});

        displayTextSlowly("The witch swiftly snatches the items from your grasp, a sinister grin spreading across her face. " +
                "Her mocking laughter fills the air as she taunts you for your foolishness. " +
                "With a flick of her staff, she raises it menacingly, casting a spell enveloping you in a cloud of toxic poison.");
        if (gm.evilWitch == null)
            gm.evilWitch = new Monster_EvilWitch(gm.difficultRate);
    }

    public void talkWitch4() {
        gm.position = "talkWitch4";

        ui.gameImageLabel.setIcon(ui.defeatedWitchImg);
        gm.witchQuestActive = true;
        setChoicesAndNextPositions(new String[]{"Enhance your strength", "Learn Poison breeze", "Leave", "", "enhanceStrength", "learnPoisonBreeze", "riverSide", ""});

        displayTextSlowly("\"Enough! Spare my life, I'll remove the poison spell casted on you and give you a reward,\" " +
                "she begs, desperation evident in her voice. \"No more tricks, I swear.\"" +
                "\"About the reward, I can either enhance your strength, making you even more powerful, or teach you the secret of the poison breeze, a lethal technique.\"");
    }

    public void talkWitch5() {
        gm.position = "talkWitch5";

        ui.gameImageLabel.setIcon(ui.riverSideImg);

        setChoicesAndNextPositions(new String[]{"Go North", "Go south", "Go East(cross the river on bridge)", "Go West", "northRiver", "southRiver", "jungle", "crossRoad"});

        displayTextSlowly("As you spare the witch's life and take her power, " +
                "she quickly realizes the gravity of the situation and hastily retreats, no longer posing a threat to your journey. " +
                "The path to the south of the river is now clear, allowing you to continue your adventure unhindered.");
    }

    public void enhanceStrength() {
        gm.witchQuestActive = false;
        gm.player.increasePlayerMaxHP(3);
        gm.player.increaseBaseAttack(1);
        updatePlayerHp(0);
        JOptionPane.showMessageDialog(ui.window, "The witch enhances your strength, granting you a boost in power. Your maximum HP is increased by 3, and your base attack is enhanced by 1");
        riverSide();
    }

    public void learnPoisonBreeze() {
        gm.witchQuestActive = false;
        gm.poisonBreeze = new Spell_PoisonBreeze();
        gm.player.addSpell(gm.poisonBreeze);
        ui.spellComboBox.setVisible(true);
        JOptionPane.showMessageDialog(ui.window, "You learn the skill of Poison Breeze from the witch, acquiring the ability to unleash a toxic and debilitating attack against your enemies.");
        updateSpellStatus();
        riverSide();
    }

    public void northRiver() {
        if (gm.position.equalsIgnoreCase("mountain") && !crossTheRiver())
            return;

        gm.position = "northRiver";
        ui.gameImageLabel.setIcon(ui.northRiverImg);
        setChoicesAndNextPositions(new String[]{"", "Go East(swim cross the river)", "Go South", "Go West", "", "mountain", "riverSide", "blackSmithHouse"});

        if (!gm.isRestAtTent) {
            displayTextSlowly("North of the river, you discover a cozy fireplace and an old tent by the riverside. " +
                    "Resting at the tent allows you to recover 10 HP. " +
                    "Unfortunately, the path ahead has been blocked, denying any further progress in that direction.");
            ui.choice1.setText("Take a rest");
            nextPosition1 = "takeRest";
        } else {
            displayTextSlowly("North of the river, you discover a cozy fireplace and an old tent by the riverside. " +
                    "Unfortunately, the path ahead has been blocked, denying any further progress in that direction.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    public void takeRest() {
        JOptionPane.showMessageDialog(ui.window, "Resting at a tent, you regain 10 HP, revitalizing your strength for the challenges that lie ahead.");
        updatePlayerHp(10);
        gm.isRestAtTent = true;
        northRiver();
    }

    public void southRiver() {
        if (gm.position.equalsIgnoreCase("demonHideout") && !crossTheRiver())
            return;

        gm.position = "southRiver";
        ui.gameImageLabel.setIcon(ui.southRiverImg);
        setChoicesAndNextPositions(new String[]{"", "Go North", "Go East(swim cross the river)", "Go West", "", "riverSide", "demonHideout", "goblinCave"});

        if (gm.isALiveRiverMonster) {
            displayTextSlowly("South of the river, a strange vortex captivates your gaze from the center of the water. " +
                    "However, progress along the southern path is halted by a waterfall, blocking any further advancement.");
            ui.choice1.setText("Throw a rock into it");
            nextPosition1 = "encounterRiverMonster";
        } else {
            displayTextSlowly("South of the river, a waterfall creates an impassable barrier, preventing any further advancement along the southern path.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    public boolean crossTheRiver() {
        if (gm.isALiveRiverMonster)
            if (rand.nextBoolean()) {
                cm.encounterRiverMonster();
                return false;
            }
        return true;
    }

    public void jungle() {
        gm.position = "jungle";
        ui.gameImageLabel.setIcon(ui.jungleImg);
        displayTextSlowly("You find yourself in a peaceful forest clearing, surrounded by tall, ancient trees. " +
                "Shafts of sunlight filter through the dense foliage, casting a gentle glow on the lush grass beneath your feet.\n" +
                "You see an apple tree.");
        setChoicesAndNextPositions(new String[]{"Hit the tree", "Go North", "Go South", "Go West(cross the river on bridge)", "hitTheAppleTree", "mountain", "demonHideout", "riverSide"});
    }

    public void hitTheAppleTree() {
        gm.position = "hitTheAppleTree";
        int c1 = rand.nextInt(4);
        if (c1 % 2 == 0 && gm.witchQuestActive && !gm.isTakenApple) {
            int c2 = JOptionPane.showConfirmDialog(ui.window, "You hit the apple tree, causing a ripe apple to fall. " +
                    "You have the option to claim the apple for the witch's request.");
            if (c2 == 0) {
                gm.isTakenApple = true;
                return;
            }
        }
        if (gm.appleOnTree > 0 && c1 % 2 == 0) {
            int c2 = JOptionPane.showConfirmDialog(ui.window, "You hit the apple tree, causing a ripe apple to fall. " +
                    "You have the option to consume the apple for a boost of 4 HP.");
            if (c2 == 0) {
                updatePlayerHp(4);
                gm.appleOnTree--;
            }
        } else if (c1 == 1) {
            JOptionPane.showMessageDialog(ui.window, "You hit the apple tree, angering a nearby monkey. " +
                    "It retaliates by throwing a stick at you, causing " + (int) Math.ceil(2 * gm.difficultRate) + " damage.");
            updatePlayerHp(-((int) Math.ceil(2 * gm.difficultRate)));
        } else
            JOptionPane.showMessageDialog(ui.window, "Nothing happen.");
    }

    public void mountain() {
        if (gm.position.equalsIgnoreCase("northRiver") && !crossTheRiver())
            return;

        gm.position = "mountain";
        ui.gameImageLabel.setIcon(ui.mountainImg);
        displayTextSlowly("As you approach the towering mountain, your gaze is drawn to the peculiar sight atop its peak. " +
                "Clusters of massive rock hover in the air, defying the natural laws of gravity. " +
                "You feel an irresistible pull to ascend the mountain and uncover the secrets hidden within the realm of the floating rocks.");
        setChoicesAndNextPositions(new String[]{"Climb to the top", "Go South", "Go West(swim cross the river)", "", "mountainTop", "jungle", "northRiver", ""});
    }

    public void mountainTop() {
        gm.position = "mountainTop";
        ui.gameImageLabel.setIcon(ui.mountainTopImg);
        displayTextSlowly("You climb the mountain, reaching the summit where a majestic stone gate awaits. " +
                "Passing through, you enter a sacred space surrounded by five towering stone statues, their presence exuding ancient power.");
        setChoicesAndNextPositions(new String[]{"Touch the middle statue", "Leave", "", "", "touchStatue", "mountain", "", ""});
        if (gm.isTakenPower)
            nextPosition1 = "";
    }

    public void touchStatue() {
        gm.position = "touchStatue";
        displayTextSlowly("The statues grant power to the traveler as a tribute to their courage. " +
                "Recognized as the chosen hero, the traveler's heroic blood resonates with the statues, allowing them to choose a power that suits their strengths and upcoming challenges.");
        setChoicesAndNextPositions(new String[]{"...", "Leave", "", "", "offerPower", "mountain", "", ""});
    }

    public void offerPower() {
        gm.position = "offerPower";
        displayTextSlowly("A mystical voice fills the air, carrying ancient wisdom and a sense of reverence. " +
                "\"Knowing the heroic blood that flows within you, we offer you a choice,\" the voice declares. " +
                "\"Embrace the path of strength, wield the power of scorching fire, command the paralyzing force of lightning, or harness the soothing energy of healing water.\"");
        setChoicesAndNextPositions(new String[]{"Strength", "Fire power", "Lightning power", "Water power", "strengthPower", "firePower", "lightningPower", "waterPower"});
    }

    public void strengthPower() {
        displayTextSlowly("Strength Power: This power enhances the traveler's physical capabilities by increasing their HP and base attack.\n" +
                "Tap into your inner strength and experience a surge of power. " +
                "Your maximum HP is increased by 3, fortifying your resilience and granting you the endurance to withstand greater challenges. " +
                "Additionally, your base attack is enhanced by 2, empowering your strikes and making your every blow more impactful.");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takeStrengthPower", "offerPower", "", ""});
    }

    public void takeStrengthPower() {
        gm.isTakenPower = true;
        gm.player.increasePlayerMaxHP(3);
        gm.player.increaseBaseAttack(2);
        updatePlayerHp(0);
        mountainTop();
    }

    public void firePower() {
        if (gm.fireStorm == null) gm.fireStorm = new Spell_FireStorm();
        gm.selectedSpell = gm.fireStorm;
        displayTextSlowly("Fire Power: This power grants the traveler mastery over the element of fire, enabling them to unleash devastating Fire Storm that inflict immense damage upon their enemies.\n" +
                "Unleash a mighty magic Fire Storm that scorches your enemies, dealing a significant " + gm.fireStorm.getDamage() + " damage.");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takePower", "offerPower", "", ""});
    }

    public void lightningPower() {
        if (gm.lightningBolt == null) {
            gm.lightningBolt = new Spell_LightningBolt();
        }
        gm.selectedSpell = gm.lightningBolt;
        displayTextSlowly("Lightning Power: Harness the electrifying energy of lightning to deal substantial damage to your foes, while also stunning them in their tracks.\n" +
                "Unleash bolts of lightning that deal " + gm.lightningBolt.getDamage() + " damage and momentarily stun monsters, granting you the opportunity to follow up with an additional attack.");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takePower", "offerPower", "", ""});
    }

    public void waterPower() {
        if (gm.waterSurge == null) gm.waterSurge = new Spell_WaterSurge();
        gm.selectedSpell = gm.waterSurge;
        displayTextSlowly("As the water spell surges forth, it forms a protective barrier that shields you from an impending monster attack, deflecting its harm. " +
                "Simultaneously, the restorative properties of the water envelop you, replenishing your vitality and restoring 7 points of health. ");
        setChoicesAndNextPositions(new String[]{"Take power", "Back", "", "", "takePower", "offerPower", "", ""});
    }

    public void takePower() {
        gm.isTakenPower = true;
        gm.player.addSpell(gm.selectedSpell);
        ui.spellComboBox.setVisible(true);
        updateSpellStatus();
        mountainTop();
    }

    public void demonHideout() {
        if (gm.position.equalsIgnoreCase("southRiver") && !crossTheRiver()) {
            return;
        }
        gm.position = "demonHideout";
        setChoicesAndNextPositions(new String[]{"", "Go North", "Go West (swim across the river)", "", "", "jungle", "southRiver", ""});

        if (gm.isAliveDemonKing) {
            ui.gameImageLabel.setIcon(ui.demonHideoutImg);
            displayTextSlowly("As you approach the demon's hideout, the landscape turns singm.isTer and dark. " +
                    "The eerie atmosphere swallows the sunlight, the entrance to the demon's lair stands before you, and you steel yourself for the impending battle, preparing to face the horrors that await within.");
            ui.choice1.setText("Enter");
            if (gm.isAliveShadowSerpent)
                nextPosition1 = "encounterShadowSerpent";
            else nextPosition1 = "encounterDemonKing";
        } else {
            ui.gameImageLabel.setIcon(ui.demonHideoutAfterBattleImg);
            displayTextSlowly("The battlefield, once ruled by the evil presence of the demon king, now lies destroyed and desolate. " +
                    "Debris and remnants of the intense battle are scattered across the ground, a reminder of the epic clash that took place.");
            ui.choice1.setText("");
            nextPosition1 = "";
        }
    }

    public void insideDemonHideout() {
        gm.position = "insideDemonHideout";
        ui.gameImageLabel.setIcon(ui.demonHideoutImg);
        displayTextSlowly("With the shadow serpent defeated, you continue your journey, venturing deeper into the heart of the demon's hideout. " +
                "The air becomes thick and oppressive, a haunting silence fills the surroundings, increasing the feeling of imminent danger. " +
                "With each step, you draw nearer to the final showdown with the formidable demon king");
        setChoicesAndNextPositions(new String[]{"Go deeper", "Leave", "", "", "encounterDemonKing", "demonHideout", "", ""});
    }

    public void defeatDemonKing() {
        gm.position = "defeatDemonKing";
        ui.gameImageLabel.setIcon(ui.explosionImg);
        displayTextSlowly("As the final blow lands on the demon king, a powerful explosion obliterates the demon's hideout. " +
                "After the battle, you are left barely alive, drained of strength. " +
                "Exhaustion overwhelms you, and you lose consciousness, unsure of what awaits you in this dangerous situation.");
        setChoicesAndNextPositions(new String[]{"...", "......", "", "", "wakeUpAfterFinalBattle", "wakeUpAfterFinalBattle", "", ""});
    }

    public void wakeUpAfterFinalBattle() {
        gm.position = "wakeUpAfterFinalBattle";
        ui.gameImageLabel.setIcon(ui.demonHideoutAfterBattleImg);
        displayTextSlowly("As you slowly wake up, your eyes open to a scene of complete destruction. " +
                "The battlefield, previously controlled by the demon king, now lies in ruins. Among the debris, you spot a shining object—the demon sword. " +
                "Feeling disoriented and weakened, you make an effort to gather your surroundings and evaluate the aftermath of the intense battle.");
        gm.player.setPlayerHP(1);
        updatePlayerHp(0);
        JOptionPane.showMessageDialog(ui.window, "You have defeated the demon king!");
        if (gm.demonSword == null)
            gm.demonSword = new Weapon_DemonSword();
        obtainWeapon(gm.demonSword);
        setChoicesAndNextPositions(new String[]{"Leave", "", "", "", "townGate", "", "", ""});
    }

    public boolean updatePlayerHp(int hpAmount) {
        if (hpAmount < 0)
            gm.player.loseHP(-hpAmount);
        else gm.player.restoreHP(hpAmount);
        ui.hpLabel.setText("HP: " + gm.player.getPlayerHP() + "/" + gm.player.getPlayerMaxHP());
        if (gm.player.getPlayerHP() == 0) {
            deadScreen();
            return false;
        }
        return true;
    }

    public void updateSpellStatus() {
        int index = gm.player.getSpellList().size() > 1 ? ui.spellComboBox.getSelectedIndex() : 0;
        ui.spellComboBox.removeAllItems();
        for (int i = 0; i < gm.player.getSpellList().size(); i++) {
            Spell spell = gm.player.getSpellList().get(i);
            if (spell.getCoolDownRemain() == 0)
                ui.spellComboBox.addItem(spell.getName() + " (Ready)");
            else ui.spellComboBox.addItem(spell.getName() + " (CD: " + spell.getCoolDownRemain() + ")");
        }
        if (gm.player.getSpellList().size() > 1)
            ui.spellComboBox.setSelectedIndex(index);
    }

    public void obtainWeapon(Weapon weapon) {
        gm.player.addWeapon(weapon);
        ui.weaponComboBox.addItem(weapon.getName());
        ui.weaponComboBox.setSelectedItem(weapon.getName());
        JOptionPane.showMessageDialog(ui.window, "You obtained the " + weapon.getName() + "!!!");
    }

    public void theEnd() {
        ui.gameImageLabel.setIcon(ui.theEndImg);
        displayTextSlowly("Congratulations! You have successfully completed the game and overcome numerous challenges to emerge victorious. " +
                "Well done on your remarkable journey and accomplishments!");
        setChoicesAndNextPositions(new String[]{"Play again", "Quit game", "", "", "start", "quit", "", ""});
    }

    public void deadScreen() {
        ui.gameImageLabel.setIcon(ui.deadScreenImg);
        displayTextSlowly("YOU DIED!!!!");
        setChoicesAndNextPositions(new String[]{"Try again", "Quit game", "", "", "start", "quit", "", ""});
    }

    public void setChoicesAndNextPositions(String[] choicesAndNextPositions) {
        ui.choice1.setText(choicesAndNextPositions[0]);
        ui.choice2.setText(choicesAndNextPositions[1]);
        ui.choice3.setText(choicesAndNextPositions[2]);
        ui.choice4.setText(choicesAndNextPositions[3]);
        nextPosition1 = choicesAndNextPositions[4];
        nextPosition2 = choicesAndNextPositions[5];
        nextPosition3 = choicesAndNextPositions[6];
        nextPosition4 = choicesAndNextPositions[7];
    }

    public void displayTextSlowly(String paragraph) {
        ui.mainTextArea.setText("");
        Thread thread = new Thread(() -> {
            int currentIndex = 0;
            try {
                while (currentIndex < paragraph.length()) {
                    Thread.sleep(13);
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
