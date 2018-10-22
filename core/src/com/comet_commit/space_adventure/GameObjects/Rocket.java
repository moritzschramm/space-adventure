package com.comet_commit.space_adventure.GameObjects;


public class Rocket extends GameObject {

    private static final float VELOCITY = 18;

    private float destinationY = 0.0f;

    private int lifePoints;
    private int max_lifePoints;

    public float rotate;


    public Rocket(float x, float y, int LP) {
        super(x, y, "rocket.png", "Rocket");
        destinationY = y;

        rotate = 0;

        lifePoints = LP;
        max_lifePoints = LP;
    }

    public void setLP(int lifePoints){
        if(lifePoints > max_lifePoints) this.lifePoints = max_lifePoints;
        else this.lifePoints = lifePoints;
    }

    public int getLP(){
        return lifePoints;
    }

    private float stayInBounds(float var, float min, float max){
        if(var > max) return max;
        if(var < min) return min;
        return var;
    }

    private float smoothChange(float old_val, float new_val, float change){

        if(old_val < 0){
            return stayInBounds(new_val,
                    old_val * (1f + change) - 1f,
                    old_val * (1f - change) + 1f);
        }
        return stayInBounds(new_val,
                old_val * (1f - change) - 1f,
                old_val * (1f + change) + 1f);
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


        rotate = stayInBounds(
                smoothChange(rotate, 2 * super.getVelocity().y, 0.2f),
                -45,
                45);
    }

    public void moveTo(float y) {

        destinationY = y;
    }
}
