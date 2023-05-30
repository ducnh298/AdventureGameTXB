package model;

public class Monster {
    private String name;
    private int monsterMaxHP;
    private int monsterCurrentHP;
    private int attackDamage;
    private String effect = "";

    public Monster(String name, int monsterMaxHP, int attackDamage, double difficultRate) {
        this.name = name;
        this.monsterMaxHP = this.monsterCurrentHP = (int) Math.ceil(monsterMaxHP * difficultRate);
        this.attackDamage = (int) Math.ceil(attackDamage * difficultRate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonsterMaxHP() {
        return monsterMaxHP;
    }

    public void setMonsterMaxHP(int monsterMaxHP) {
        this.monsterMaxHP = monsterMaxHP;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
