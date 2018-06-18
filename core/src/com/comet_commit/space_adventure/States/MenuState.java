package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.GameObjects.Background;

public class MenuState extends State {

    private Background background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm, int startBgAt) {
        super(gsm);

        background = new Background(startBgAt);
        playBtn = new Texture("playbtn.png");
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
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
    }
}
