package com.word.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.word.example.helpers.Dictionary;
import com.word.example.states.PlayState;
import com.word.example.states.StateManager;

public class ExampleGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	StateManager stateManager;


	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		stateManager = new StateManager();
		stateManager.push(new PlayState(stateManager));
		Dictionary.init();	//just reads the files once and puts the words in a list
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(spriteBatch);
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();

	}
}
