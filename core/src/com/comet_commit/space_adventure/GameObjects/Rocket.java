package com.comet_commit.space_adventure.GameObjects;


public class Rocket extends GameObject {

    public Rocket(float x, float y) {
        super(x, y, 0, 0, 205, 205,  "rocket.png");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
