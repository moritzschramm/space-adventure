package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.SpaceAdventure;

public class GameOverState extends State {

    private Background background;
    private String gameover_str;
    private int latest_score;

    public GameOverState(GameStateManager gsm, AssetManager assetManager, int startBgAt, int score, int go_reason) {
        super(gsm, assetManager);

        background = new Background(startBgAt);

        latest_score = score;

        if(go_reason == 0)      gameover_str = "rocket crashed";
        else if(go_reason == 1) gameover_str = "lost in space";

    }

    @Override
    protected void handleInput() {
        if(super.touchDown[0]) {

            if(tp[0].x < SpaceAdventure.WIDTH/2 + 100 && tp[0].x > SpaceAdventure.WIDTH/2 -100
                    && tp[0].y < SpaceAdventure.HEIGHT/2 + 100 && tp[0].y > SpaceAdventure.HEIGHT/2 - 100) {

                gsm.set(new PlayState(gsm, assetManager, background.getRelativePosition()));
            }
        }
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

        assetManager.get("normalFont.ttf", BitmapFont.class).draw(sb, "Score: " + latest_score,
                                    SpaceAdventure.WIDTH / 2 - 160,
                                    SpaceAdventure.HEIGHT / 2 - 80);

        assetManager.get("normalFont.ttf", BitmapFont.class).draw(sb, "play again >", SpaceAdventure.WIDTH / 2 - 200, SpaceAdventure.HEIGHT/2 + 10);
            // Instead of drawing the string "Play again", we could draw a reverse-symbol (solves Problem: where to click)

        assetManager.get("bigFont.ttf", BitmapFont.class).draw(sb, gameover_str, SpaceAdventure.WIDTH/ 2  - 280,
                                                   SpaceAdventure.HEIGHT - 150);

        sb.end();
    }

    @Override
    public void dispose() { }

}


//    private void setMoney(int money){  //set money in storage.txt
//
//        String filename = "storage";
//        String fileContents = "Hello world!";
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private int getMoney(){
//        int money = 0;
//
//        //load money from storage.txt
//
//        return money;
//    }
