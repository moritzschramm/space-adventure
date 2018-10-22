package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.SpaceAdventure;

public class LoadAppState extends State{

    private AssetManager assetManager;
    private Background background;
    private Texture loading_img;
    private Texture moon;
    private int position_y, x_index;
    private int [] position_x;
    private float time;
    private final float interval = 0.5f;

    public LoadAppState(GameStateManager gsm) {
        super(gsm, null);
        time = 0;

        assetManager = new AssetManager();

        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter bigFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        bigFont.fontFileName = "FFF_Tusj.ttf";
        bigFont.fontParameters.size = 90;
        assetManager.load("bigFont.ttf", BitmapFont.class, bigFont);

        FreetypeFontLoader.FreeTypeFontLoaderParameter normalFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        normalFont.fontFileName = "FFF_Tusj.ttf";
        normalFont.fontParameters.size = 60;
        assetManager.load("normalFont.ttf", BitmapFont.class, normalFont);

        FreetypeFontLoader.FreeTypeFontLoaderParameter smallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        smallFont.fontFileName = "FFF_Tusj.ttf";
        smallFont.fontParameters.size = 35;
        assetManager.load("smallFont.ttf", BitmapFont.class, smallFont);

        FreetypeFontLoader.FreeTypeFontLoaderParameter ultraSmallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        ultraSmallFont.fontFileName = "FFF_Tusj.ttf";
        ultraSmallFont.fontParameters.size = 25;
        ultraSmallFont.fontParameters.color = Color.CYAN;
        assetManager.load("ultraSmallFont.ttf", BitmapFont.class, ultraSmallFont);

        background = new Background(0);
        moon = new Texture("moon.png");
        loading_img = new Texture("rocket.png");

        position_x = new int[3];
        position_x[0] = SpaceAdventure.WIDTH / 2 - 90;
        position_x[1] = SpaceAdventure.WIDTH / 2 - 30;
        position_x[2] = SpaceAdventure.WIDTH / 2 + 30;
        x_index = 0;

        position_y = (SpaceAdventure.HEIGHT - 45) / 2;

    }

    @Override
    protected void handleInput() { }

    @Override
    public void update(float dt) {

        if(assetManager.update())
            gsm.set(new MenuState(gsm, assetManager, background.getRelativePosition()));

        time += dt;
        if(time >= interval) {
            x_index++;
            time = 0;
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        background.draw(sb);
        sb.draw(moon, (SpaceAdventure.WIDTH - 200) / 2, (SpaceAdventure.HEIGHT - 200) / 2, 200, 200);
        sb.draw(loading_img, position_x[x_index%position_x.length], position_y, 60, 45);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
