package model;

public class Spell {
    protected String name;
    protected int damage;
    protected Effect effect;
    protected String description;
    protected int coolDown;
    protected int coolDownRemain = 0;

    public Spell(String name, int damage, int coolDown, Effect effect) {
        this.name = name;
        this.damage = damage;
        this.coolDown = coolDown;
        this.effect = effect;
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
}
