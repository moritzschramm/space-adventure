package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.SpaceAdventure;

public class GameOverState extends State {

    private Texture game_over, background, background2;
    private int bgPosition, bgPosition2;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);

        game_over = new Texture("game_over.png");

        bgPosition = 0;
        bgPosition2 = SpaceAdventure.WIDTH;
        background = new Texture("sky.png");
        background2 = new Texture("sky2.png");
    }

    @Override
    protected void handleInput() {
        if(super.touchDown)
            gsm.set(new PlayState(gsm));
    }

    @Override
    public void update(float dt) {
        handleInput();

        if(bgPosition <= -1* SpaceAdventure.WIDTH) bgPosition = SpaceAdventure.WIDTH + bgPosition2;
        if(bgPosition2 <= -1*SpaceAdventure.WIDTH) bgPosition2 = SpaceAdventure.WIDTH + bgPosition;
        bgPosition -= Math.round(dt*50);
        bgPosition2 -= Math.round(dt*50);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, bgPosition, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);
        sb.draw(background2, bgPosition2, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);

        sb.draw(game_over, 0, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);

        sb.end();
    }

    @Override
    public void dispose() {
        game_over.dispose();
        background.dispose();
        background2.dispose();
    }
}
