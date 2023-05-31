package model;

public abstract class Weapon {
    private String name;
    private int attackDamage;
    private int criticalAttackDamage;

    protected Weapon(String name, int attackDamage, int criticalAttackDamage) {
        this.name = name;
        this.attackDamage = attackDamage;
        this.criticalAttackDamage = criticalAttackDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getCriticalAttackDamage() {
        return criticalAttackDamage;
    }

    public void setCriticalAttackDamage(int criticalAttackDamage) {
        this.criticalAttackDamage = criticalAttackDamage;
    }
}
