package com.greedlycore.clickerGF;

public class Item {

    private final String name;
    private final long damage;
    private static int count = 0;
    private final float coeficient;

    public Item(String name, long damage, float coef, int count) {
        this.name = name;
        this.damage = damage;
        this.count = 0;

        this.coeficient = coef;

    }

    public long getPrice() {
        return Math.round(this.coeficient * Math.exp(this.count));
    }


    public String getName() {
        return this.name;
    }

    public double getPower() {
        return this.damage * this.count;
    }

    public long getDamage() {
        return this.damage;
    }

    public int getCount() {
        return this.count;
    }

    public void buy() {
        this.count++;
    }
}
