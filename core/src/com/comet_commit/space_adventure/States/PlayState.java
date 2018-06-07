package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.GameObjects.Rocket;
import com.comet_commit.space_adventure.SpaceAdventure;

public class PlayState extends State {

    private Rocket rocket;

    public PlayState(GameStateManager gsm) {

        super(gsm);
        rocket = new Rocket(SpaceAdventure.WIDTH / 10f, SpaceAdventure.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

        if(super.touchDown) {

            // TODO add tp.x to velocity of rocket
        }
    }

    @Override
    public void update(float dt) {

        handleInput();

        rocket.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(rocket.getTexture(), rocket.getPosition().x, rocket.getPosition().y);

        sb.end();
    }

    @Override
    public void dispose() {

        rocket.dispose();
    }
}
