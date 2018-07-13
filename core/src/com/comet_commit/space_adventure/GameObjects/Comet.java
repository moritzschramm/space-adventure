package com.comet_commit.space_adventure.GameObjects;

import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.Random;

public class Comet extends Enemy {

    private static final int MAX_SPEED_X = 12;
    private static final int MIN_SPEED_X = 8;
    private static final int MAX_SPEED_Y = 3;
    private static final int MIN_SPEED_Y = -3;


    public Comet() {
        super(0, 0, "comet.png", "Comet", 10, 20);
        reset();
    }

    public Comet(float x, float y) {
        super(x, y, "comet.png", "Comet", 10, 20);
    }


    public void reset() {

        Random r = new Random();

        // position:
        // x is width + some offset (spawn somewhere where player can't see comet)
        // y is random value from 10 to height-10
        super.getPosition().x = SpaceAdventure.WIDTH + 20;
        super.getPosition().y = r.nextFloat() * (SpaceAdventure.HEIGHT - 20) + 10;

        // velocity:
        super.getVelocity().x = -1 * ((MAX_SPEED_X - MIN_SPEED_X) * r.nextFloat() + MIN_SPEED_X);
        super.getVelocity().y = (MAX_SPEED_Y - MIN_SPEED_Y) * r.nextFloat() + MIN_SPEED_Y;
    }

//    @Override
//    public void update(float dt) {
//
//        super.getPosition().x += super.getVelocity().x;
//        super.getPosition().y += super.getVelocity().y;
//        super.getBounds().x = super.getPosition().x;
//        super.getBounds().y = super.getPosition().y;
//    }

//    @Override
//    public void dispose() {
//        super.getTexture().dispose();
//    }
}
