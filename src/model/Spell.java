package model;

public class Spell {
    protected String name;
    protected int damage;
    protected String specialEffect;
    protected int coolDown;
    protected int coolDownRemain = 0;
    protected String hexColorCode;

    public Spell(String name, int damage, int coolDown, String hexColorCode) {
        this.name = name;
        this.damage = damage;
        this.coolDown = coolDown;
        this.hexColorCode = hexColorCode;
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

    public String getSpecialEffect() {
        return specialEffect;
    }

    public String getHexColorCode() {
        return hexColorCode;
    }
}
