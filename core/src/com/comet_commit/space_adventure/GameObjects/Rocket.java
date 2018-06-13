package com.comet_commit.space_adventure.GameObjects;


import com.badlogic.gdx.math.Vector3;
import com.comet_commit.space_adventure.SpaceAdventure;

public class Rocket extends GameObject {

    private static final float SLOWER_ACC = 500;
    private static final double VEL_DECREASE = 0.05;

    private float acceleration;
    private float MAX_ACC = 0.1f;
    private int accDir = 1;

    public Rocket(float x, float y) {
        super(x, y, 0, 0, 205, 205,  "rocket.png");

        acceleration = 0;
//        accelerateTo = y; //accelerate to start point
    }

    @Override
    public void update(float dt) {
        if(super.getPosition().y < 0) {
            super.getVelocity().y = 0;
            super.getPosition().y = 0;
        }else if(super.getPosition().y > SpaceAdventure.HEIGHT - super.getTexture().getHeight()){
            super.getVelocity().y = 0;
            super.getPosition().y = SpaceAdventure.HEIGHT - super.getTexture().getHeight();
        }else{
            super.getVelocity().y += acceleration;
            super.getPosition().y += super.getVelocity().y;
        }



    }

    public void setAcc(float y) {
        acceleration = y / SLOWER_ACC;
    }

    public void decreaseVel(){
        if(super.getVelocity().y < -VEL_DECREASE){
            super.getVelocity().y += VEL_DECREASE;
        }else if(super.getVelocity().y > VEL_DECREASE){
            super.getVelocity().y -= VEL_DECREASE;
        }else{
            super.getVelocity().y = 0;
        }

    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
