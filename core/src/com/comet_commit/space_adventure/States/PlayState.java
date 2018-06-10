package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.comet_commit.space_adventure.GameObjects.Comet;
import com.comet_commit.space_adventure.GameObjects.Rocket;
import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.ArrayList;

public class PlayState extends State {

    private Rocket rocket;
    private ArrayList<Comet> comets;

    private Texture background;
    private Texture background2;
    private int bgPosition, bgPosition2;
    private float time, last_comet_insertion, comet_interval;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        bgPosition = 0;
        bgPosition2 = SpaceAdventure.WIDTH;
        background = new Texture("sky.png");
        background2 = new Texture("sky2.png");

        time = 0;
        last_comet_insertion = 0;
        comet_interval = 1;

        comets = new ArrayList<Comet>();

        rocket = new Rocket(SpaceAdventure.WIDTH / 20f, SpaceAdventure.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

        if(super.touchDown) {

            if(rocket.getBounds().contains(new Vector2(tp.x, tp.y))) {

                Vector3 pos = new Vector3();
                pos.x = rocket.getPosition().x;
                pos.y = tp.y - rocket.getBounds().height/2;
                rocket.setPosition(pos);
            }
        }
    }

    @Override
    public void update(float dt) {

        handleInput();

        rocket.update(dt);

        for(int i = 0; i < comets.size(); i++){
            comets.get(i).update(dt);

            if(comets.get(i).getPosition().x < -200) {
                comets.get(i).dispose();
                comets.remove(i);
            }
        }

        time += dt;
        if(time >= last_comet_insertion + comet_interval){
            comets.add(new Comet(150,150));
            last_comet_insertion = time;
        }


        if(bgPosition <= -1*SpaceAdventure.WIDTH) bgPosition = SpaceAdventure.WIDTH + bgPosition2;
        if(bgPosition2 <= -1*SpaceAdventure.WIDTH) bgPosition2 = SpaceAdventure.WIDTH + bgPosition;
        bgPosition -= Math.round(dt*50);
        bgPosition2 -= Math.round(dt*50);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, bgPosition, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);
        sb.draw(background2, bgPosition2, 0, SpaceAdventure.WIDTH, SpaceAdventure.HEIGHT);

        for(Comet comet : comets){
            sb.draw(comet.getTexture(), comet.getPosition().x, comet.getPosition().y, Comet.COMET_SIZE, Comet.COMET_SIZE);
        }

        sb.draw(rocket.getTexture(), rocket.getPosition().x, rocket.getPosition().y);


        sb.end();
    }

    @Override
    public void dispose() {
        rocket.dispose();

        for(Comet comet : comets)
            comet.dispose();

    }
}
