package com.comet_commit.space_adventure.GameObjects;

import com.badlogic.gdx.graphics.Texture;

public class Comet extends GameObject {


    public Comet(int x, int y) {
        super(x, y);
        super.setTexture(new Texture("comet.png"));
    }


}
