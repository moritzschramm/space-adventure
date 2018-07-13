package com.comet_commit.space_adventure.AddOns;

import com.badlogic.gdx.math.Vector3;
import com.comet_commit.space_adventure.GameObjects.GameObject;

public class Laser extends GameObject{

    private GameObject shouter;

    private final int intensity = 15;
    private final float VEL_FACTOR = 1;

    public Laser(GameObject shouter, float targetX, float targetY){
        super(shouter.getPosition().x, shouter.getPosition().y, "laser.png", "Laser");
        this.shouter = shouter;

        float   delX = targetX - shouter.getPosition().x,
                delY = targetY - shouter.getPosition().y,
                abs = (float) Math.sqrt(delX + delY),
                velX = delX / abs,
                velY = delY / abs;

        super.setVelocity(new Vector3(VEL_FACTOR * velX, VEL_FACTOR * velY, 0));
    }

    public int getIntensity() {
        return intensity;
    }

//    @Override
//    public void update(float dt) {
//        super.getPosition().x += super.getVelocity().x;
//        super.getPosition().y += super.getVelocity().y;
//        super.getBounds().x = super.getPosition().x;
//        super.getBounds().y = super.getPosition().y;
//    }

//    @Override
//    public void dispose() {
//        super.getTexture().dispose();
//    }
}
