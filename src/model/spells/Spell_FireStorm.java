package model.spells;

import model.Spell;

public class Spell_FireStorm extends Spell {
    public Spell_FireStorm() {
        super("Fire Storm", 7, 3, null);
        description = "Scorching the monster, deal " + damage + " damage.";
    }
}
