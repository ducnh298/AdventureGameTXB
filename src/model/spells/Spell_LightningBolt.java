package model.spells;

import model.Spell;
import model.effect.Effect_Paralyzed;

public class Spell_LightningBolt extends Spell {
    public Spell_LightningBolt() {
        super("Lightning Bolt", 2, 3, new Effect_Paralyzed());
        description = "Deal " + damage + " damage and momentarily stun the monster.";
    }
}
