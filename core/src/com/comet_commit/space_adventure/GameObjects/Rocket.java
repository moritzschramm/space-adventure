package com.comet_commit.space_adventure.GameObjects;


public class Rocket extends GameObject {

    public Rocket(float x, float y) {
        super(x, y,  "rocket.png");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
