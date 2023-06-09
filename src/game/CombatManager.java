package game;

import model.Effect;
import model.Monster;
import model.Spell;
import model.Weapon;
import model.monsters.Monster_DemonKing;
import model.monsters.Monster_Goblin;
import model.monsters.Monster_RiverMonster;
import model.monsters.Monster_ShadowSerpent;
import model.spells.Spell_PoisonBreeze;
import model.weapons.Weapon_Trident;

import javax.swing.*;
import java.util.Random;

public class CombatManager {
    StoryManager sm;
    GameModel gm;
    UI ui;
    Random rand = new Random();

    public CombatManager(StoryManager storyManager) {
        this.sm = storyManager;
        this.gm = storyManager.gm;
        this.ui = storyManager.ui;
    }

    public void encounterGoblin() {
        if (gm.goblin == null)
            gm.goblin = new Monster_Goblin(gm.difficultRate);
        if (!gm.position.equalsIgnoreCase("encounterGoblin"))
            gm.lastPosition = gm.position;
        gm.position = "encounterGoblin";
        ui.gameImageLabel.setIcon(ui.goblinImg);
        encounterMonster(gm.goblin);
    }

    public void encounterEvilWitch() {
        if (gm.poisonBreeze == null) {
            gm.poisonBreeze = new Spell_PoisonBreeze();
        }
        if (!gm.position.equalsIgnoreCase("encounterEvilWitch"))
            gm.lastPosition = gm.position;
        gm.position = "encounterEvilWitch";
        ui.gameImageLabel.setIcon(ui.evilWitchImg);

        JOptionPane.showMessageDialog(ui.window, "The poison breeze slowly drains your health, leaving you weakened and vulnerable.");
        gm.poisonBreeze.activeEffect();
        gm.player.addEffect(gm.poisonBreeze.getEffect());
        encounterMonster(gm.evilWitch);
    }

    public void encounterRiverMonster() {
        if (gm.riverMonster == null)
            gm.riverMonster = new Monster_RiverMonster(gm.difficultRate);
        if (!gm.position.equalsIgnoreCase("encounterRiverMonster"))
            gm.lastPosition = gm.position;
        gm.position = "encounterRiverMonster";
        ui.gameImageLabel.setIcon(ui.riverMonsterImg);

        JOptionPane.showMessageDialog(ui.window, "You come face to face with a formidable river monster. " +
                "This fearsome creature poses a significant threat.");
        encounterMonster(gm.riverMonster);
    }

    public void encounterShadowSerpent() {
        if (gm.shadowSerpent == null)
            gm.shadowSerpent = new Monster_ShadowSerpent(gm.difficultRate);
        if (!gm.position.equalsIgnoreCase("encounterShadowSerpent"))
            gm.lastPosition = gm.position;
        gm.position = "encounterShadowSerpent";
        ui.gameImageLabel.setIcon(ui.shadowSerpentImg);

        JOptionPane.showMessageDialog(ui.window, "In the depths of the demon's lair, you come face to face with a menacing shadow serpent, ready to strike.");
        encounterMonster(gm.shadowSerpent);
    }

    public void encounterDemonKing() {
        if (gm.demonKing == null)
            gm.demonKing = new Monster_DemonKing(gm.difficultRate);
        if (!gm.position.equalsIgnoreCase("encounterDemonKing"))
            gm.lastPosition = gm.position;
        gm.position = "encounterDemonKing";
        ui.gameImageLabel.setIcon(ui.demonKingImg);

        JOptionPane.showMessageDialog(ui.window, "You finally confront the demon king, standing face-to-face with the embodiment of evil itself.");
        encounterMonster(gm.demonKing);
    }

    public void attackGoblin(boolean useSpell) {
        if (attackMonster(gm.goblin, useSpell)) {
            if (gm.goblin.getMonsterCurrentHP() == 0) {
                sm.displayTextSlowly(gm.goblin.getName() + "'s HP: " + gm.goblin.getMonsterCurrentHP() + "/" + gm.goblin.getMonsterMaxHP());
                JOptionPane.showMessageDialog(ui.window, "You have defeated the goblin!");

                ui.mapButton.setEnabled(true);
                gm.isALiveGoblin = false;
                gm.goblin = null;

                sm.insideGoblinCave();
            } else encounterMonster(gm.goblin);
        }
    }

    public void attackEvilWitch(boolean useSpell) {
        if (attackMonster(gm.evilWitch, useSpell)) {
            if (gm.evilWitch.getMonsterCurrentHP() <= 1) {
                gm.evilWitch.setMonsterCurrentHP(1);
                sm.displayTextSlowly(gm.evilWitch.getName() + "'s HP: " + gm.evilWitch.getMonsterCurrentHP() + "/" + gm.evilWitch.getMonsterMaxHP());
                JOptionPane.showMessageDialog(ui.window, "You have defeated the Evil Witch!");

                for (Effect effect : gm.player.getEffectList()) {
                    if (effect.getName().equalsIgnoreCase("Poisonous")) {
                        gm.player.removeEffect(effect);
                        break;
                    }
                }
                ui.mapButton.setEnabled(true);
                gm.isDefeatedEvilWitch = true;

                sm.talkWitch4();
            } else encounterMonster(gm.evilWitch);
        }
    }

    public void attackRiverMonster(boolean useSpell) {
        if (attackMonster(gm.riverMonster, useSpell)) {
            if (gm.riverMonster.getMonsterCurrentHP() == 0) {
                sm.displayTextSlowly(gm.riverMonster.getName() + "'s HP: " + gm.riverMonster.getMonsterCurrentHP() + "/" + gm.riverMonster.getMonsterMaxHP());
                JOptionPane.showMessageDialog(ui.window, "Victorious against the river monster, you claim a gleaming trident as your reward.");

                if (gm.trident == null)
                    gm.trident = new Weapon_Trident();
                sm.obtainWeapon(gm.trident);

                gm.isALiveRiverMonster = false;
                gm.riverMonster = null;
                ui.mapButton.setEnabled(true);

                sm.selectedPosition(gm.lastPosition);
            } else encounterMonster(gm.riverMonster);
        }
    }

    public void attackShadowSerpent(boolean useSpell) {
        if (attackMonster(gm.shadowSerpent, useSpell)) {
            if (gm.shadowSerpent.getMonsterCurrentHP() == 0) {
                sm.displayTextSlowly(gm.shadowSerpent.getName() + "'s HP: " + gm.shadowSerpent.getMonsterCurrentHP() + "/" + gm.shadowSerpent.getMonsterMaxHP());
                JOptionPane.showMessageDialog(ui.window, "You have defeated the shadow serpent!");

                gm.isAliveShadowSerpent = false;
                gm.shadowSerpent = null;
                ui.mapButton.setEnabled(true);

                sm.insideDemonHideout();
            } else encounterMonster(gm.shadowSerpent);
        }
    }

    public void attackDemonKing(boolean useSpell) {
        if (attackMonster(gm.demonKing, useSpell)) {
            if (gm.demonKing.getMonsterCurrentHP() == 0) {
                ui.mapButton.setEnabled(true);
                gm.isAliveDemonKing = false;
                gm.demonKing = null;
                sm.selectedPosition("defeatDemonKing");
            } else encounterMonster(gm.demonKing);
        }
    }

    public void encounterMonster(Monster monster) {
        ui.mapButton.setEnabled(false);
        StringBuilder monsterStatus = new StringBuilder(monster.getName() + "'s HP: " + monster.getMonsterCurrentHP() + "/" + monster.getMonsterMaxHP() + "\n");
        if (monster.getEffectList().isEmpty())
            for (Effect effect : monster.getEffectList()) {
                monsterStatus.append(monster.getName());
                monsterStatus.append(" is ");
                monsterStatus.append(effect.getName());
                if (effect.getRemain() > 1) {
                    monsterStatus.append(" (Remain: ");
                    monsterStatus.append(effect.getRemain());
                    monsterStatus.append(")");
                }
                monsterStatus.append("\n");
            }
        sm.displayTextSlowly(monsterStatus.toString());

        if (gm.player.getSpellList().isEmpty()) {
            sm.updateSpellStatus();
            sm.setChoicesAndNextPositions(new String[]{"Fight", "Use spell", "Try to run", "", "", "", "tryToRun", ""});
        } else sm.setChoicesAndNextPositions(new String[]{"Fight", "Try to run", "", "", "", "tryToRun", "", ""});

        if (monster.equals(gm.goblin)) {
            sm.nextPosition1 = "attackGoblin";
            sm.nextPosition2 = (gm.player.getSpellList().isEmpty()) ? "attackGoblinWithSpell" : "tryToRun";
        } else if (monster.equals(gm.riverMonster)) {
            sm.nextPosition1 = "attackRiverMonster";
            sm.nextPosition2 = (gm.player.getSpellList().isEmpty()) ? "attackRiverMonsterWithSpell" : "tryToRun";
        } else if (monster.equals(gm.shadowSerpent)) {
            sm.nextPosition1 = "attackShadowSerpent";
            sm.nextPosition2 = (gm.player.getSpellList().isEmpty()) ? "attackShadowSerpentWithSpell" : "tryToRun";
        } else if (monster.equals(gm.demonKing)) {
            sm.nextPosition1 = "attackDemonKing";
            sm.nextPosition2 = (gm.player.getSpellList().isEmpty()) ? "attackDemonKingWithSpell" : "tryToRun";
        } else if (monster.equals(gm.evilWitch)) {
            sm.nextPosition1 = "attackEvilWitch";
            sm.nextPosition2 = (gm.player.getSpellList().isEmpty()) ? "attackEvilWitchWithSpell" : "tryToRun";
        }
    }

    public boolean attackMonster(Monster monster, boolean useSpell) {
        boolean monsterAttack = true;
        Spell spell = null;

        if (useSpell) {
            spell = gm.player.getSpellList().get(ui.spellComboBox.getSelectedIndex());
            if (spell.getCoolDownRemain() > 0) {
                JOptionPane.showMessageDialog(ui.window, spell.getName() + " is not ready!");
                encounterMonster(monster);
                return true;
            }

            JOptionPane.showMessageDialog(ui.window, "You cast " + spell.getName() + ". " + spell.getDescription());
            if (spell.getDamage() > 0)
                monster.loseHP(spell.getDamage());
            if (spell.getEffect() != null) {
                spell.activeEffect();
                monster.addEffect(spell.getEffect());
            }
            if (spell.equals(gm.waterSurge)) {
                sm.updatePlayerHp(-spell.getDamage());
                monsterAttack = false;
            }
        } else {
            Weapon currentWeapon = gm.player.getWeaponList().get(ui.weaponComboBox.getSelectedIndex());
            int playerDamage = gm.player.getBaseAttack() + rand.nextInt(currentWeapon.getCriticalAttackDamage() - currentWeapon.getAttackDamage()) + currentWeapon.getAttackDamage();
            JOptionPane.showMessageDialog(ui.window, "You attacked the " + monster.getName() + " with " + currentWeapon.getName() + " and gave " + playerDamage + " damage!");
            monster.loseHP(playerDamage);
        }

        if (gm.player.getSpellList().isEmpty()) {
            for (Spell sp : gm.player.getSpellList()) {
                if (spell != null && sp.getName().equals(spell.getName()))
                    sp.resetCoolDown();
                else
                    sp.decreaseCoolDown();
            }
        }

        if (monster.getEffectList().isEmpty())
            for (Effect effect : monster.getEffectList()) {
                JOptionPane.showMessageDialog(ui.window, effect.getDescriptionToMonster());

                if (effect.getDamage() > 0)
                    monster.loseHP(effect.getDamage());

                if (effect.getName().equalsIgnoreCase("Paralyzed"))
                    monsterAttack = false;

                if (spell == null || (spell != null && !spell.getEffect().getName().equals(effect.getName()))) {
                    effect.reduceRemain();
                    if (effect.getRemain() == 0)
                        monster.removeEffect(effect);
                }
            }

        if (gm.player.getEffectList().isEmpty())
            for (Effect effect : gm.player.getEffectList()) {
                JOptionPane.showMessageDialog(ui.window, effect.getDescriptionToPlayer());

                if (effect.getDamage() > 0)
                    gm.player.loseHP((int) Math.floor(effect.getDamage() * gm.difficultRate));

                effect.reduceRemain();
                if (effect.getRemain() == 0)
                    gm.player.removeEffect(effect);
            }

        if (monsterAttack) {
            int monsterDamage = rand.nextInt(monster.getCriticalAttackDamage() - monster.getAttackDamage()) + monster.getAttackDamage() - (gm.player.getArmor() != null ? gm.player.getArmor().getDamageReduced() : 0);
            JOptionPane.showMessageDialog(ui.window, "The " + monster.getName() + " attacked you." + (gm.player.getArmor() != null ? (" With " + gm.player.getArmor().getName()) : "") + " you took " + (monsterDamage > 0 ? monsterDamage : 0) + " damage!");
            return sm.updatePlayerHp(-monsterDamage);
        }

        return true;
    }

    public void tryToRun() {
        if (gm.position.equalsIgnoreCase("encounterEvilWitch") || gm.position.equalsIgnoreCase("talkWitch3")) {
            JOptionPane.showMessageDialog(ui.window, "Escape from the clutches of the witch proves futile as she blocks your path.");
            encounterMonster(gm.evilWitch);
        } else if (rand.nextBoolean()) {
            JOptionPane.showMessageDialog(ui.window, "With a swift dodge, you evade the monster's attack and successfully escape, fleeing to safety.");
            ui.mapButton.setEnabled(true);
            sm.selectedPosition(gm.lastPosition);
        } else {
            int damageTaken = (int) Math.ceil(2 * gm.difficultRate) - (gm.player.getArmor() != null ? gm.player.getArmor().getDamageReduced() : 0);
            JOptionPane.showMessageDialog(ui.window, "Despite your best efforts to escape, you were unable to evade the monster's pursuit and suffered a blow, losing " + (damageTaken > 0 ? damageTaken : 0) + " points of health.");
            if (sm.updatePlayerHp(-damageTaken))
                sm.selectedPosition(gm.position);
        }
    }
}
