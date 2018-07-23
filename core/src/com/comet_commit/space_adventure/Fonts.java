package com.comet_commit.space_adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {

    private BitmapFont big_font, normal_font, small_font;

    public BitmapFont getBig_font() {
        return big_font;
    }

    public BitmapFont getNormal_font() {
        return normal_font;
    }

    public BitmapFont getSmall_font() {
        return small_font;
    }

    /**
     * Allows to print good looking Strings to the screen
     * See more at: https://github.com/libgdx/libgdx/wiki/Gdx-freetype
     */
    public Fonts(){
        FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(Gdx.files.internal("FFF_Tusj.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 90;
        big_font = ftfg.generateFont(parameter);

        parameter.size = 35;
        small_font = ftfg.generateFont(parameter);

        parameter.size = 60;
        normal_font = ftfg.generateFont(parameter);

        ftfg.dispose();
    }

}
