package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.SpaceAdventure;

public class GameOverState extends State {

    private Texture game_over;
    private Background background;

    protected GameOverState(GameStateManager gsm, int startBgAt) {
        super(gsm);

        game_over = new Texture("game_over.png");

        background = new Background(startBgAt);
    }

    @Override
    protected void handleInput() {
        if(super.touchDown)
            gsm.set(new PlayState(gsm, background.getRelativePosition()));
    }

    @Override
    public void update(float dt) {
        handleInput();

        background.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        background.draw(sb);

        sb.draw(game_over, 0, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);

        sb.end();
    }

    @Override
    public void dispose() {
        game_over.dispose();
    }
}
