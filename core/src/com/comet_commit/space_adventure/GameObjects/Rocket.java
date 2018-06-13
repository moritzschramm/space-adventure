package com.comet_commit.space_adventure.GameObjects;


public class Rocket extends GameObject {

    private float acceleration, accelerateTo;
    private boolean accelerate;
    private float MAX_ACC = 0.1f;
    private int accDir = 1;

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

            if(s1 >= Math.abs(accelerateTo - getPosition().y)) {

                acceleration = -1 * MAX_ACC * accDir;

            } else {

                acceleration = MAX_ACC * accDir;
            }

        } else {

            acceleration = 0;
        }
        getVelocity().y += acceleration;
        getPosition().y += getVelocity().y;

        if((int)getPosition().y == (int)accelerateTo) {
            accelerate = false;
            getVelocity().y = 0;
        }
    }

    public void moveTo(float y) {

        if(y != getPosition().y) {
            accelerateTo = y;
            accelerate = true;
            accDir = (getPosition().y - y > 0) ? -1 : 1;
        }
    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
