package model.spells;

import model.Spell;
import model.effect.Effect_Poisonous;

public class Spell_PoisonBreeze extends Spell {
    public Spell_PoisonBreeze() {
        super("Poison Breeze", 0, 5, new Effect_Poisonous());
        description = "The monster is now afflicted by poison, its health gradually deteriorating as the venom courses through its body.";
    }
}
