package model.spells;

import model.Spell;

public class Spell_FireBall extends Spell {
    public Spell_FireBall() {
        super("Fire ball", 7, 3, "#cf1920");
        specialEffect = "Deal " + damage + " damage to the monster.";
    }
}
