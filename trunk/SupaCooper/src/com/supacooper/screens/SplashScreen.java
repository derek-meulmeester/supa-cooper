package com.supacooper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;

public class SplashScreen implements Screen {

	SupaCooper myGame;
	private SpriteBatch spriteBatch;
	private Texture backgroundTexture;
	private float currTime;
	
	public SplashScreen(SupaCooper newSupaCooper) {
		myGame = newSupaCooper;
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/splashscreen.png"));
		spriteBatch = new SpriteBatch();
		this.currTime = 0;
		
	}
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
        
        currTime += Gdx.graphics.getDeltaTime();
        if(currTime > 6) {
        	myGame.setMainMenuScreen();
        	currTime = 0;
        }
        	
	}

	@Override
	public void resize(int width, int height) {

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
		if(this.spriteBatch != null)
			this.spriteBatch.dispose();
		if(this.backgroundTexture != null)
			this.backgroundTexture.dispose();
	}

}
