package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerHP;
    private int playerMaxHP;
    private int baseAttack = 0;
    private List<Weapon> weaponList = new ArrayList<>();
    private Armor armor;
    private Spell spell;

    public Player(int playerMaxHP) {
        this.playerMaxHP = this.playerHP = playerMaxHP;
    }

    public Player(int playerMaxHP, int baseAttack) {
        this.playerMaxHP = this.playerHP = playerMaxHP;
        this.baseAttack = baseAttack;
        this.spell = null;
        this.armor = null;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getPlayerMaxHP() {
        return playerMaxHP;
    }

    public void increasePlayerMaxHP(int hpIncrease) {
        playerHP += hpIncrease;
        playerMaxHP += hpIncrease;
    }

    public void restoreHP(int amount) {
        playerHP += amount;
        if (playerHP > playerMaxHP)
            playerHP = playerMaxHP;
    }

    public void loseHP(int amount) {
        playerHP -= amount;
        if (playerHP < 0)
            playerHP = 0;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void increaseBaseAttack(int baseAttackIncreased) {
        baseAttack += baseAttackIncreased;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public void addWeapon(Weapon weapon) {
        weaponList.add(weapon);
    }

    public Weapon getWeapon(String name) {
        return weaponList.stream().filter(weapon -> weapon.getName().equalsIgnoreCase(name)).findAny()
                .orElse(new Weapon("unknown weapon", 0, 1));
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }
}
