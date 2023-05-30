package model.spells;

import model.Spell;

public class Spell_LightningBolt extends Spell {
    public Spell_LightningBolt() {
        super("Lightning Bolt", 2, 3, "#FCC01E");
        specialEffect = "Deal " + damage + " damage and momentarily stun the monster.";
    }
}
