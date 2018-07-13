package com.comet_commit.space_adventure.GameObjects;


//I don't if that's the best way to do it. But it's one way
public abstract class Enemy extends GameObject {

    private int HP; //Health-Points (10 is low - 10000 is very high e.g. End-Boss Super-Alien!!!)
    private int DMG;


    public Enemy(float x, float y, String internalPath, String name, int HP, int DMG) {
        super(x, y, internalPath, name);
        this.HP = HP;
        this.DMG = DMG;
    }

    public Enemy(float x, float y, float vx, float vy, String internalPath, String name, int HP, int DMG) {
        super(x, y, vx, vy, internalPath, name);
        this.HP = HP;
        this.DMG = DMG;
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public int getDMG() {
        return DMG;
    }

    public void setDMG(int DMG) {
        this.DMG = DMG;
    }

    public abstract void reset(); //just implemented to reuse the reset method from Comet
}
