package model;

public class Armor {
    private String name;
    private int damageReduced;

    public Armor(String name, int damageReduced) {
        this.name = name;
        this.damageReduced = damageReduced;
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
}
