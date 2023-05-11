package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerHP;
    private List<Weapon> weaponList = new ArrayList<>();
    private Armor armor;

    public Player(int playerHP) {
        this.playerHP = playerHP;
    }

    public Player(int playerHP, List<Weapon> weaponList) {
        this.playerHP = playerHP;
        this.weaponList = weaponList;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void restoreHP(int amount) {
        playerHP += amount;
        if (playerHP > 20)
            playerHP = 20;
    }

    public void loseHP(int amount) {
        playerHP -= amount;
        if (playerHP < 0)
            playerHP = 0;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public void addWeapon(Weapon weapon) {
        weaponList.add(weapon);
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
