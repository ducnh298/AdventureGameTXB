package model;

import java.util.LinkedList;
import java.util.List;

public class Monster {
    private String name;
    private int monsterMaxHP;
    private int monsterCurrentHP;
    private int attackDamage;
    private int criticalAttackDamage;
    private List<Effect> effects = new LinkedList<>();

    public Monster(String name, int monsterMaxHP, int attackDamage, int criticalAttackDamage, double difficultRate) {
        this.name = name;
        this.monsterMaxHP = this.monsterCurrentHP = (int) Math.ceil(monsterMaxHP * difficultRate);
        this.attackDamage = (int) Math.ceil(attackDamage * difficultRate);
        this.criticalAttackDamage = (int) Math.ceil(criticalAttackDamage * difficultRate);
    }

    public String getName() {
        return name;
    }

    public int getMonsterMaxHP() {
        return monsterMaxHP;
    }

    public void restoreHP(int amount) {
        monsterCurrentHP += amount;
        if (monsterCurrentHP > monsterMaxHP)
            monsterCurrentHP = monsterMaxHP;
    }

    public void loseHP(int amount) {
        monsterCurrentHP -= amount;
        if (monsterCurrentHP < 0)
            monsterCurrentHP = 0;
    }

    public int getMonsterCurrentHP() {
        return monsterCurrentHP;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getCriticalAttackDamage() {
        return criticalAttackDamage;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void addEffect(Effect effect) {
        this.effects.add(effect);
    }

    public void removeEffect(Effect effect) {
        this.effects.remove(effect);
    }
}
