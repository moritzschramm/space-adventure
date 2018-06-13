package com.comet_commit.space_adventure.GameObjects;

import com.badlogic.gdx.math.Vector3;
import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.Random;

public class Comet extends GameObject {

    public static final int COMET_SIZE = 120;

    public Comet(float x, float y) {
        super(x, y, "comet.png");

        Random r = new Random();

        super.setPosition(new Vector3(SpaceAdventure.WIDTH + 20, r.nextFloat() *(SpaceAdventure.HEIGHT - 20) + 10,0));
        super.setVelocity(new Vector3(-2*(r.nextFloat()+4),2*r.nextFloat() - 1,0));
    }

    @Override
    public void update(float dt) {

        super.getPosition().x += super.getVelocity().x;
        super.getPosition().y += super.getVelocity().y;
    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
