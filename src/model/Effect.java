package model;

public class Effect {
    protected String name;
    protected int remain;
    protected int damage;
    protected String description;

    public Effect(String name, int remain, int damage) {
        this.name = name;
        this.remain = remain;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getRemain() {
        return remain;
    }

    public void reduceRemain() {
        remain--;
    }

    public int getDamage() {
        return damage;
    }

    public String getDescription() {
        return description;
    }
}
