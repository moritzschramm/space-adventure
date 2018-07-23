package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.Fonts;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.SpaceAdventure;

import java.io.FileOutputStream;

public class GameOverState extends State {

    private Background background;
    private String gameover_str;
    private int latest_score;

    public GameOverState(GameStateManager gsm, Fonts fonts, int startBgAt, int score, int go_reason) {
        super(gsm, fonts);

        background = new Background(startBgAt);

        latest_score = score;

        if(go_reason == 0)      gameover_str = "rocket crashed";
        else if(go_reason == 1) gameover_str = "lost in space";

    }

    private int getMoney(){
        int money = 0;

        //load money from storage.txt




        return money;
    }

    private void setMoney(int money){
        //set money in storage.txt

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

    }

    @Override
    protected void handleInput() {
        if(super.touchDown)
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

        fonts.getNormal_font().draw(sb, "Score: " + latest_score,
                                    SpaceAdventure.WIDTH / 2 - 160,
                                    SpaceAdventure.HEIGHT / 2 - 80);
        fonts.getBig_font().draw(sb, gameover_str, SpaceAdventure.WIDTH/ 2  - 280,
                                                   SpaceAdventure.HEIGHT - 150);

        sb.end();
    }

    @Override
    public void dispose() { }

}
