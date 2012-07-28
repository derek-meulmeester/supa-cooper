package com.supacooper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;

public class TutorialScreen implements Screen {

	SupaCooper myGame;
	
	private SpriteBatch spriteBatch;
	private Texture backgroundTexture;
	private Texture nextButtonTexture;
	private Texture prevButtonTexture;
	
	public TutorialScreen(SupaCooper newSupaCooper) {
		myGame = newSupaCooper;
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/tutorialOne.png"));
		nextButtonTexture = new Texture(Gdx.files.internal("images/buttons/arrowRight.png"));
		prevButtonTexture = new Texture(Gdx.files.internal("images/buttons/arrowLeft.png"));
		
		spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		 spriteBatch.begin();
		 spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 spriteBatch.draw(nextButtonTexture, 680, 175, 100, 100);
		 spriteBatch.draw(prevButtonTexture, 20, 175, 100, 100);
		 spriteBatch.end();
		 
		 if(Gdx.input.justTouched()) {
			 if(Gdx.input.getY()<Gdx.graphics.getHeight()*0.75) {
				 if(Gdx.input.getX()<Gdx.graphics.getWidth()/2) {
					 myGame.setMainMenuScreen();
				 } else {
					 myGame.setTutorialScreen2();
				 }
			 }
		 }
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
