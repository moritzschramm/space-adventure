package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.comet_commit.space_adventure.SpaceAdventure;

public abstract class State implements InputProcessor {

    private final static int POINTER_AMOUNT = 10;   // number of concurrent touch pointers that can be handled

    protected OrthographicCamera cam;
    protected ExtendViewport viewport;
    protected GameStateManager gsm;
    protected Vector3[] tp;
    protected boolean[] touchDown;
//    protected Stage stage;
    protected AssetManager assetManager;

    protected State(GameStateManager gsm, AssetManager assetManager) {
        this.gsm = gsm;
        this.assetManager = assetManager;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);

        viewport = new ExtendViewport(SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT, cam);
        tp = new Vector3[POINTER_AMOUNT];
        for(int i = 0; i < POINTER_AMOUNT; i++) {
            tp[i] = new Vector3();
        }

        touchDown = new boolean[POINTER_AMOUNT];
        for(int i = 0; i < POINTER_AMOUNT; i++) {
            touchDown[i] = false;
        }
        Gdx.input.setInputProcessor(this);
    }

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer >= 10) return false;
        cam.unproject(tp[pointer].set(screenX, screenY, 0));
        touchDown[pointer] = true;
        System.out.println("down: "+screenX + " " + screenY + " " + pointer);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer >= 10) return false;
        cam.unproject(tp[pointer].set(screenX, screenY, 0));
        System.out.println("up: "+screenX + " " + screenY + " " + pointer);
        touchDown[pointer] = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!touchDown[pointer] || pointer >= 10) return false;
        cam.unproject(tp[pointer].set(screenX, screenY, 0));
        return true;
    }



    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
