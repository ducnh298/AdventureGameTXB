package model;

public class Player {
    private int playerHP;
    private Weapon weapon;

    public Player(int playerHP, Weapon weapon) {
        this.playerHP = playerHP;
        this.weapon = weapon;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void restoreHP(int amount) {
        playerHP += amount;
        if (playerHP > 10)
            playerHP = 10;
    }

    public void loseHP(int amount) {
        playerHP -= amount;
        if (playerHP < 0)
            playerHP = 0;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
