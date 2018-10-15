package com.comet_commit.space_adventure.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.SpaceAdventure;

import static java.lang.Math.round;

public class Background {

    public static final int SPEED = 100;

    private static Texture background;
    private static Texture background2;
    private int bgPosition, bgPosition2;
    private int relWidth, relHeight;

    public Background(int startAt) {

        if(background == null) background = new Texture("background1.png");
        if(background2== null) background2= new Texture("background2.png");

        relHeight= SpaceAdventure.HEIGHT;
        relWidth = (int) (background.getWidth() * (double) SpaceAdventure.HEIGHT / (double) background.getHeight());

        bgPosition = startAt;
        if(startAt <= 0) bgPosition2 = relWidth + startAt;
        else bgPosition2 = startAt - relWidth;
    }

    public int getRelativePosition() {

        return bgPosition % relWidth;
    }

    public void update(float dt) {

        if(Math.abs(bgPosition) >= relWidth) {
            bgPosition = relWidth + bgPosition2;
        }
        if(Math.abs(bgPosition2) >= relWidth) {
            bgPosition2 = relWidth + bgPosition;
        }
        bgPosition -= round(dt * SPEED);
        bgPosition2 -= round(dt * SPEED);
    }

    public void draw(SpriteBatch sb) {

        sb.draw(background, bgPosition, 0, relWidth, relHeight);
        sb.draw(background2, bgPosition2, 0, relWidth, relHeight);
    }

    public static void dispose() {

        background.dispose();
        background2.dispose();
    }
}
