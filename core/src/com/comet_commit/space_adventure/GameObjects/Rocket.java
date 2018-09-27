package com.comet_commit.space_adventure.GameObjects;


public class Rocket extends GameObject {

    private static final float VELOCITY = 18;

    private float destinationY = 0.0f;

    private int lifePoints;


    public Rocket(float x, float y, int LP) {
        super(x, y, "rocket.png", "Rocket");
        destinationY = y;

        lifePoints = LP;
    }

    public void setLP(int lifePoints){
        this.lifePoints = lifePoints;
    }

    public int getLP(){
        return lifePoints;
    }


    @Override
    public void update(float dt) {

        if(super.getPosition().y == destinationY) {

            super.getVelocity().y = 0;

        } else if(Math.abs(super.getPosition().y - destinationY) <= VELOCITY) {

            super.getVelocity().y = Math.abs(super.getPosition().y - destinationY);
            if(destinationY < super.getPosition().y) super.getVelocity().y *= -1;

        } else {

            super.getVelocity().y = VELOCITY;
            if(destinationY < super.getPosition().y) super.getVelocity().y *= -1;
        }

        super.getPosition().y += super.getVelocity().y;
        super.getBounds().y = super.getPosition().y;
    }

    public void moveTo(float y) {

        destinationY = y;
    }
}
