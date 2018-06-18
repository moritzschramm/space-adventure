package com.comet_commit.space_adventure.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.SpaceAdventure;

import static java.lang.Math.round;

public class Background {

    public static final int SPEED = 150;

    private static Texture background;
    private static Texture background2;
    private int bgPosition, bgPosition2;

    public Background(int startAt) {

        bgPosition = startAt;
        if(startAt <= 0) bgPosition2 = SpaceAdventure.WIDTH + startAt;
        else bgPosition2 = startAt - SpaceAdventure.WIDTH;

        if(background == null) background = new Texture("sky.png");
        if(background2 == null) background2 = new Texture("sky2.png");
    }

    public int getRelativePosition() {

        return bgPosition % SpaceAdventure.WIDTH;
    }

    public void update(float dt) {

        if(Math.abs(bgPosition) >= SpaceAdventure.WIDTH) {
            bgPosition = SpaceAdventure.WIDTH + bgPosition2;
        }
        if(Math.abs(bgPosition2) >= SpaceAdventure.WIDTH) {
            bgPosition2 = SpaceAdventure.WIDTH + bgPosition;
        }
        bgPosition -= round(dt * SPEED);
        bgPosition2 -= round(dt * SPEED);
    }

    public void draw(SpriteBatch sb) {

        sb.draw(background, bgPosition, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);
        sb.draw(background2, bgPosition2, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);
    }

    public static void dispose() {

        background.dispose();
        background2.dispose();
    }
}
