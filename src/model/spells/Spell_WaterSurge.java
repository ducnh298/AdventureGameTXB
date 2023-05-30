package model.spells;

import model.Spell;

public class Spell_WaterSurge extends Spell {
    public Spell_WaterSurge() {
        super("Water Surge", -6, 3, "#7DF9FF");
        specialEffect = "Deflecting the monster attack and heal you by " + (-damage) + ".";
    }
}
