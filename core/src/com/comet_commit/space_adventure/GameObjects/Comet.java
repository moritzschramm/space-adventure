package com.comet_commit.space_adventure.GameObjects;

import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.Random;

public class Comet extends Enemy {

    private static final int MAX_SPEED_X = 24;
    private static final int MIN_SPEED_X = 10;
    private static final int MAX_SPEED_Y = 3;
    private static final int MIN_SPEED_Y = -3;


    public Comet(float rocketY) {
        super(0, 0, "comet.png", "Comet", 10, 20);
        reset(rocketY);
    }

    public Comet(float x, float y) {
        super(x, y, "comet.png", "Comet", 10, 20);
    }

    public void reset() {

        reset(0);
    }

    public void reset(float rocketY) {

        Random r = new Random();

        // position:
        // x is width + some offset (spawn somewhere where player can't see comet)
        // y is random value from 10 to height-10
        super.getPosition().x = SpaceAdventure.WIDTH + 20;
        super.getPosition().y = r.nextFloat() * (SpaceAdventure.HEIGHT - 20) + 10;

        // velocity:
        super.getVelocity().x = -1 * ((MAX_SPEED_X - MIN_SPEED_X) * r.nextFloat() + MIN_SPEED_X);
        super.getVelocity().y = (MAX_SPEED_Y - MIN_SPEED_Y) * r.nextFloat() + MIN_SPEED_Y;


        if(rocketY - super.getPosition().y < 0) {

            super.getVelocity().y = -1 * Math.abs(super.getVelocity().y);

        } else {

            super.getVelocity().y = Math.abs(super.getVelocity().y);
        }
    }
}
