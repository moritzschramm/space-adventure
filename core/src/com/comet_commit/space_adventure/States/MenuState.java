package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.Fonts;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.SpaceAdventure;

public class MenuState extends State {

    private Background background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm, Fonts fonts, int startBgAt) {
        super(gsm, fonts);
        this.fonts = fonts;

        background = new Background(startBgAt);
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if(super.touchDown[0])
            gsm.set(new PlayState(gsm, fonts, background.getRelativePosition()));

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
        fonts.getBig_font().draw(sb, "Space Adventures", SpaceAdventure.WIDTH/ 2  - 360,
                                                           SpaceAdventure.HEIGHT - 100);
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
    }
}
