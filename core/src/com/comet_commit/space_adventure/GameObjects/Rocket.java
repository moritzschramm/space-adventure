package com.comet_commit.space_adventure.GameObjects;


public class Rocket extends GameObject {

    private static final float SLOWER_ACC = 500;
    private static final double VEL_DECREASE = 0.05;

    private float acceleration;
    private float MAX_ACC = 0.1f;
    private int accDir = 1;

    private int lifePoints;


    public Rocket(float x, float y) {
        super(x, y, "rocket.png", "Rocket");

        acceleration = 0;
        lifePoints = 100;
//        accelerateTo = y; //accelerate to start point
    }

    @Override
    public void update(float dt) {

            super.getVelocity().y += acceleration;
            super.getPosition().y += super.getVelocity().y;
            super.getBounds().y = super.getPosition().y;

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

    public int getLP(){
        return lifePoints;
    }

    public void setLP(int lifePoints){
        this.lifePoints = lifePoints;
    }
}
