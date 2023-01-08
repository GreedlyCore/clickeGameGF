package com.greedlycore.clickerGF;

public class Enemy {

    private static double health;
    private static int reward;
    private final double const_health;
    public static double price;


    public Enemy(double health, int reward) {
        this.health = health;
        this.const_health = health;
        this.reward = reward;
        this.price = 0;

    }

    public void attack(double damage) {
        this.health -= damage;


    }

    public double getHP() {
        return this.health;
    }

    public boolean is_dead() {
        if (this.health < 0) {
            return true;
        }else{
            return false;
        }

    }

    public void revive() {
        this.health = this.const_health;
        this.setReward((int) (this.getReward() * 1.075));
    }

    public void die() {
        this.health = 0;
    }

    public int getReward() {
        return this.reward;
    }

    public void setReward(int value) {
        this.reward = value;
    }


}
