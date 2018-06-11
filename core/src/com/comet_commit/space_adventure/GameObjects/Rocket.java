package com.comet_commit.space_adventure.GameObjects;


import com.badlogic.gdx.math.Vector3;

public class Rocket extends GameObject {

    private float acceleration, accelerateTo;
    private boolean accelerate;
    private float MAX_ACC = 10;

    public Rocket(float x, float y) {
        super(x, y, 0, 0, 205, 205,  "rocket.png");

        acceleration = 0;
        accelerateTo = 0;
        accelerate = false;
    }

    @Override
    public void update(float dt) {

        if(accelerate) {

            float t1 = getVelocity().y / MAX_ACC;
            float s1 = getVelocity().y * t1 + MAX_ACC * t1 * t1;

            if(s1 >= accelerateTo) {

                acceleration = -MAX_ACC;

            } else {

                acceleration = MAX_ACC;
            }

        } else {

            acceleration = 0;
        }
        getVelocity().y += acceleration;
        getPosition().y += getVelocity().y;

        if(getPosition().y == accelerateTo) accelerate = false;
    }

    public void moveTo(float y) {

        if(y != getPosition().y) {
            accelerateTo = y;
            accelerate = true;
        }
    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
