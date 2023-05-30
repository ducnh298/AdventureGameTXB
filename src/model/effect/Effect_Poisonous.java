package model.effect;

import model.Effect;

public class Effect_Poisonous extends Effect {
    public Effect_Poisonous() {
        super("Poisonous", 5, 2);
        description = "The monster is poisoned, taking " + damage + " damage each round as the venom courses through its body";
    }
}
