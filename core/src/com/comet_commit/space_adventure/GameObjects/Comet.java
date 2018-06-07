package com.comet_commit.space_adventure.GameObjects;

public class Comet extends GameObject {

    public Comet(float x, float y) {
        super(x, y, "comet.png");
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
