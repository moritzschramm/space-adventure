package com.comet_commit.space_adventure.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


/**
 * GameObject is the abstract class above all gameobjects and sets all the shared features and settings.
 * This class should contain as much properties as possible.
 */
public abstract class GameObject extends Actor {

    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;


    public GameObject(float x, float y, String internalPath) {
        this(x, y, 0, 0, 0, 0, internalPath);
    }

    public GameObject(float x, float y, float vx, float vy, String internalPath) {
        this(x, y, vx, vy, 0, 0, internalPath);
    }

    public GameObject(float x, float y, float vx, float vy, float width, float height, String internalPath) {
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(vx, vy, 0);
        this.texture = new Texture(internalPath);
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public abstract void update(float dt);

    public abstract void dispose();


    public void setPosition(Vector3 position) {
        this.position = position;
        this.bounds.x = position.x;
        this.bounds.y = position.y;
    }

    public void setPosition(float x, float y, float z){
        position.x = x;
        position.y = y;
        position.z = z;
        bounds.x = x;
        bounds.y = y;
    }

    @Override
    public Actor hit (float x, float y, boolean touchable) { //TODO Test!!!
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return bounds.contains(x, y) ? this : null;
    }

    public Actor collision(Rectangle rect, boolean touchable){ //TODO Test!!!
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return bounds.overlaps(rect) ? this : null;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public Vector3 getVelocity() {
        return this.velocity;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }
}
