package com.comet_commit.space_adventure.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


/**
 * GameObject is the abstract class above all gameobjects and sets all the shared features and settings.
 * This class should contain as much properties as possible.
 */
public abstract class GameObject {

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;


    public GameObject(int x, int y){
        position = new Vector3(x, y, 0);
    }

    public GameObject(int x, int y, int vx, int vy){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(vx, vy, 0);
    }



    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
