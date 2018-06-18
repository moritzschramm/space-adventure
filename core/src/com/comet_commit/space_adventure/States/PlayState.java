package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.GameObjects.Comet;
import com.comet_commit.space_adventure.GameObjects.Rocket;
import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.ArrayList;

public class PlayState extends State {

    public static final float COMET_INTERVAL = 1;

    private Rocket rocket;
    private ArrayList<Comet> comets;
    private Background background;

    private float cometTime, lastCometInsertion;

    private boolean isHolding;

    public PlayState(GameStateManager gsm, int startBgAt) {
        super(gsm);

        stage = new Stage();

        cometTime = 0;
        lastCometInsertion = 0;

        isHolding = false;

        comets = new ArrayList<Comet>();

        for(int i = 0; i < 5; i++) {
            comets.add(new Comet(-300, 0));
        }

        rocket = new Rocket(SpaceAdventure.WIDTH / 20f, SpaceAdventure.HEIGHT / 2);

        background = new Background(startBgAt);

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
        cometTime += dt;

        handleInput();

        rocket.update(dt);

        updateComets(dt);

        handleCollision();
        checkRocketPosition(rocket.getPosition().y, rocket.getTexture().getHeight());

        checkIsDead();

        background.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        background.draw(sb);

        for(Comet comet : comets){
            sb.draw(comet.getTexture(), comet.getPosition().x, comet.getPosition().y, comet.getBounds().width, comet.getBounds().height);
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

    private void updateComets(float dt) {

        boolean repositionedOne = false;

        for(Comet comet: comets) {

            comet.update(dt);

            if(!repositionedOne &&
                    comet.getPosition().x < -200 &&
                    cometTime >= lastCometInsertion + COMET_INTERVAL) {
                comet.resetPositionAndVelocity();
                lastCometInsertion = cometTime;

                repositionedOne = true;
            }
        }
    }

    private void handleCollision() {

        for(Comet c : comets){
            if(rocket.collision(c.getBounds(), true) != null) {
                rocket.setLP(rocket.getLP() - 1);
                System.out.println("Collision\n"+"LifePoints: " + rocket.getLP());
            }

        }
    }

    private void checkRocketPosition(float y, float height) {
        if(y + height < 0 || y > SpaceAdventure.HEIGHT) {
            gsm.set(new GameOverState(gsm, background.getRelativePosition()));
            System.out.println("----------\nlost in space\n----------");
        }
    }

    private void checkIsDead() {
        if(rocket.getLP() < 0) {
            gsm.set(new GameOverState(gsm, background.getRelativePosition()));
            System.out.println("----------\ncrashed\n----------");
        }
    }

}
