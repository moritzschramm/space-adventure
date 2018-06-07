package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.SpaceAdventure;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, SpaceAdventure.WIDTH / 2, SpaceAdventure.HEIGHT / 2);

        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(super.touchDown)
            gsm.set(new PlayState(gsm));

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
