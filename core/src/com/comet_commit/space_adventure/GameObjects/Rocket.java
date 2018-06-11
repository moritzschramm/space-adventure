package com.comet_commit.space_adventure.GameObjects;


import com.badlogic.gdx.math.Vector3;

public class Rocket extends GameObject {

    private Vector3 twitch, acceleration;

    public Rocket(float x, float y) {
        super(x, y, 0, 0, 205, 205,  "rocket.png");

        twitch = new Vector3(0,0,0);
        acceleration = new Vector3(0,0,0);
    }

    @Override
    public void update(float dt) {

        acceleration = acceleration.add(twitch);
        setVelocity(getVelocity().add(acceleration));
        setPosition(getPosition().add(getVelocity()));
    }

    public void setTwitch(float nextY){ //rocket only moves in y direction
        twitch.y = nextY - getPosition().y;
    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
