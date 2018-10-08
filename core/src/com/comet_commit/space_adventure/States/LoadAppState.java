package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.Fonts;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.SpaceAdventure;

public class LoadAppState extends State{

    private boolean loading;
    private float time;
    private Background background;
    private Texture loading_img;

    public LoadAppState(GameStateManager gsm, Fonts fonts) {
        super(gsm, fonts);

        loading = true;
        time = 0;

        background = new Background(0);
        loading_img = new Texture("loading.png");
    }

    @Override
    protected void handleInput() { }

    @Override
    public void update(float dt) {

        time += dt;

        if(time > 2.0)
            loading = false;     //TODO find a smart way to set loading on true and load the Font in parallel (if fonts != null)

        if(!loading)
            gsm.set(new MenuState(gsm, new Fonts(), background.getRelativePosition()));

        background.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        background.draw(sb);
        sb.draw(loading_img, (SpaceAdventure.WIDTH - 200) / 2, (SpaceAdventure.HEIGHT - 100) / 2, 200, 100);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
