package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.comet_commit.space_adventure.GameObjects.Comet;
import com.comet_commit.space_adventure.GameObjects.Rocket;
import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.ArrayList;

import static java.lang.Math.*;

public class PlayState extends State {

    private Rocket rocket;
    private ArrayList<Comet> comets;

    private Texture background;
    private Texture background2;
    private int bgPosition, bgPosition2;
    private float time, last_comet_insertion, comet_interval;

    private boolean isHolding;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage();

        bgPosition = 0;
        bgPosition2 = SpaceAdventure.WIDTH;
        background = new Texture("sky.png");
        background2 = new Texture("sky2.png");

        time = 0;
        last_comet_insertion = 0;
        comet_interval = 1;

        isHolding = false;

        comets = new ArrayList<Comet>();

        rocket = new Rocket(SpaceAdventure.WIDTH / 20f, SpaceAdventure.HEIGHT / 2);

        stage.addActor(rocket);

    }

    @Override
    protected void handleInput() {

        if(isHolding)
            rocket.setAcc((tp.y - SpaceAdventure.HEIGHT / 2f));
        else {
            rocket.setAcc(0);
            rocket.decreaseVel();
        }

        if(super.touchDown) {

            if(tp.x < SpaceAdventure.WIDTH / 2)
                isHolding = true;

        } else {

            isHolding = false;
        }
    }

    @Override
    public void update(float dt) {
        time += dt;

        handleInput();

        rocket.update(dt);

        updateComets(dt);
        addComets();

        handleCollision(); //TODO implement!
        checkRocketPosition(rocket.getPosition().y, rocket.getTexture().getHeight());

        checkIsDead();


        if(bgPosition <= -1*SpaceAdventure.WIDTH) bgPosition = SpaceAdventure.WIDTH + bgPosition2;
        if(bgPosition2 <= -1*SpaceAdventure.WIDTH) bgPosition2 = SpaceAdventure.WIDTH + bgPosition;
        bgPosition -= round(dt*50);
        bgPosition2 -= round(dt*50);
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

    private void updateComets(float dt){
        for(int i = 0; i < comets.size(); i++){
            comets.get(i).update(dt);

            if(comets.get(i).getPosition().x < -200) {
                comets.get(i).dispose();
                comets.remove(i);
            }
        }
    }

    private void addComets(){
        if(time >= last_comet_insertion + comet_interval){
            comets.add(new Comet(150,150));
            last_comet_insertion = time;
        }
    }

    private void handleCollision(){

        for(Comet c : comets){
            if(rocket.collision(c.getBounds(), true) != null)
                rocket.setLP(rocket.getLP()-1);

        }

    }

    private void checkRocketPosition(float y, float height){
        if(y + height < 0 || y > SpaceAdventure.HEIGHT)
            gsm.set(new GameOverState(gsm));
    }

    private void checkIsDead(){
        if(rocket.getLP() < 0)
            gsm.set(new GameOverState(gsm));
    }

}
