package model;

public abstract class Spell {
    protected String name;
    protected int damage;
    protected Effect effect;
    protected String description;
    protected int coolDown;
    protected int coolDownRemain = 0;

    protected Spell(String name, int damage, int coolDown) {
        this.name = name;
        this.damage = damage;
        this.coolDown = coolDown;
    }

    public String getName() {
        return name;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public int getCoolDownRemain() {
        return coolDownRemain;
    }

    public void resetCoolDown() {
        this.coolDownRemain = coolDown;
    }

    public void decreaseCoolDown() {
        if (coolDownRemain > 0)
            coolDownRemain--;
    }

    public int getDamage() {
        return damage;
    }

    public Effect getEffect() {
        return effect;
    }

    public String getDescription() {
        return description;
    }

    public abstract void activeEffect();
}
