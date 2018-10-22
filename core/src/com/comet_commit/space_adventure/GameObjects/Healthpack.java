package com.comet_commit.space_adventure.GameObjects;

public class Healthpack extends GameObject {

    public static final int HP = 25;

    public Healthpack(float x, float y, float velx, float vely) {
        super(x, y, velx, vely, "healthpack.png", "healthpack");
    }
}
