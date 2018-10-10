package com.comet_commit.space_adventure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.comet_commit.space_adventure.GameObjects.Background;
import com.comet_commit.space_adventure.States.GameStateManager;
import com.comet_commit.space_adventure.States.LoadAppState;

/**
 * @author Moo_Quadrat (Development-Team)
 */
public class SpaceAdventure extends ApplicationAdapter {

	public static final int WIDTH = 1280, HEIGHT = WIDTH / 16 * 9;
	public static final String TITLE = "Space Adventure";

	SpriteBatch batch;
	GameStateManager gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new LoadAppState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		Background.dispose();
		batch.dispose();
	}
}
