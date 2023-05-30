package model;

public class Armor {
    private String name;
    private int damageReduced;
    private String hexColorCode;

    public Armor(String name, int damageReduced, String hexColor) {
        this.name = name;
        this.damageReduced = damageReduced;
        this.hexColorCode = hexColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamageReduced() {
        return damageReduced;
    }

    public void setDamageReduced(int damageReduced) {
        this.damageReduced = damageReduced;
    }

    public String getHexColorCode() {
        return hexColorCode;
    }
}
