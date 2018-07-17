package com.comet_commit.space_adventure.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.AddOns.Laser;
import com.comet_commit.space_adventure.Fonts;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.GameObjects.Comet;
import com.comet_commit.space_adventure.GameObjects.Enemy;
import com.comet_commit.space_adventure.GameObjects.Rocket;
import com.comet_commit.space_adventure.SpaceAdventure;

import java.util.ArrayList;

public class PlayState extends State {

    private Rocket rocket;
    private ArrayList<Enemy> enemies;   //Rename from comets to enemies
    private ArrayList<Laser> lasers;    //set of laser-beams
    private Background background;
    private Fonts fonts;

    private int score;
    private float time;
    private float cometInterval = 1;
    private float laserInterval = 0.5f;
    private float nextLaser, nextComet;
    private boolean isHolding;


    public PlayState(GameStateManager gsm, Fonts fonts, int startBgAt) {
        super(gsm);
        this.fonts = fonts;

        score = 0;
        time = 0;
        nextComet = cometInterval;
        nextLaser = laserInterval;
        isHolding = false;

        lasers = new ArrayList<Laser>();
        enemies = new ArrayList<Enemy>();

        for(int i = 0; i < 5; i++) enemies.add(new Comet(-200, 0));
        rocket = new Rocket(SpaceAdventure.WIDTH / 20f, SpaceAdventure.HEIGHT / 2);
        background = new Background(startBgAt);

    }


    private void removeEnemy(int i){
        enemies.get(i).dispose();
        enemies.remove(i);
    }

    private void checkRocketPosition(float y, float height) {
        if(y + height < 0 || y > SpaceAdventure.HEIGHT) {
            gsm.set(new GameOverState(gsm, fonts, background.getRelativePosition(), score, 1));
            System.out.println("----------\nlost in space\n----------");
        }
    }

    private void checkCrashed() {
        if(rocket.getLP() <= 0) {
            gsm.set(new GameOverState(gsm, fonts, background.getRelativePosition(), score, 0));
            System.out.println("----------\ncrashed\n----------");
        }
    }

    private void updateLasers(float dt) {

        for(int i = 0; i < lasers.size(); i++){
            Laser laser = lasers.get(i);
            laser.update(dt);

            if(laser.getPosition().x > SpaceAdventure.WIDTH + 10 ||
                    laser.getPosition().y > SpaceAdventure.HEIGHT+ 10 ||
                    laser.getPosition().y < 0) {
                lasers.remove(i);
                laser.dispose();
            }
        }
    }

    private void updateEnemies(float dt) {

        for(int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.update(dt);

            if(enemy.getHP() <= 0 || enemy.getPosition().x < -300) removeEnemy(i);

            else if(nextComet <= 0){
                enemies.add(new Comet());
                nextComet = cometInterval;
            }
        }
    }

    private void handleCollision() {
        for(int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            if(rocket.collision(enemy.getBounds(), true) != null) {
                rocket.setLP(rocket.getLP() - enemy.getDMG());
                removeEnemy(i);
                // delete enemy (and add Animation e.g. bursting comet)
                System.out.println("Collision\n"+"LifePoints: " + rocket.getLP());
            }
        }
    }

    private void handleLaserCollision() {

        for(int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            for (Enemy enemy : enemies) {
                if (laser.collision(enemy.getBounds(), true) != null) {
                    enemy.setHP(enemy.getHP() - laser.getIntensity());
                    lasers.remove(i);
                    laser.dispose();
                    score += 10;
                    break;
                }
            }
        }
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

            if(tp.x < SpaceAdventure.WIDTH / 4)
                isHolding = true;

            else if(nextLaser <= 0) {
                lasers.add(new Laser(rocket, tp.x, tp.y));
                nextLaser = laserInterval;
            }

        } else {
            isHolding = false;
        }
    }

    @Override
    public void update(float dt) {
        time += dt;
        nextComet -= dt;
        nextLaser -= dt;
        cometInterval = (float) (1 / (Math.log(time+5)-1));

        if(time - (int) time <= dt) score++; //incr. score each sec

        handleInput();

        rocket.update(dt);
        updateLasers(dt);
        updateEnemies(dt);

        handleCollision();
        handleLaserCollision();

        checkRocketPosition(rocket.getPosition().y, rocket.getTexture().getHeight());
        checkCrashed();

        background.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        background.draw(sb);

        for(Enemy enemy : enemies)
            sb.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y,   //enemies
                    enemy.getBounds().width, enemy.getBounds().height);
        for(Laser laser : lasers)
            sb.draw(laser.getTexture(), laser.getPosition().x, laser.getPosition().y);  //lasers

        sb.draw(rocket.getTexture(), rocket.getPosition().x, rocket.getPosition().y);   //rocket

        fonts.getSmall_font().draw(sb, String.valueOf(score), SpaceAdventure.WIDTH - 80, 50);  //score

        sb.end();
    }

    @Override
    public void dispose() {
        rocket.dispose();

        for(Enemy enemy : enemies)
            enemy.dispose();

        for(Laser laser : lasers)
            laser.dispose();
    }
}
