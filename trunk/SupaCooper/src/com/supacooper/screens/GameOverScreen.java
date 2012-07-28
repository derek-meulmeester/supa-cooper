package com.supacooper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;

public class GameOverScreen implements Screen {

	SupaCooper myGame;
	private SpriteBatch spriteBatch;
	private Texture backgroundTexture;
	private Texture mainMenuButton;
	private Texture retryLevel;
	
	private Sound gameOverSound;
	
	private BitmapFont font;
	private BitmapFont font2;
	private String score;
	private int offset;
	
	private float currTime;
	public GameOverScreen(SupaCooper newSupaCooper) {
		myGame = newSupaCooper;
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/gameOverscreen.png"));
		mainMenuButton = new Texture(Gdx.files.internal("images/buttons/gameOverMenuUp.png"));
		retryLevel = new Texture(Gdx.files.internal("images/buttons/gameOverRetryUp.png"));
		spriteBatch = new SpriteBatch();
		gameOverSound = Gdx.audio.newSound(Gdx.files.getFileHandle("sounds/gameOverMusic.mp3", FileType.Internal));
		
		font = new BitmapFont();
		font.setColor(Color.toFloatBits(0,230,0,100));
		font.setScale(4f, 3f);
		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.setScale(4f, 3f);
		offset = 105;
	}
	
	@Override
	public void show() {
		currTime = 0;
		gameOverSound.play();
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        spriteBatch.begin();
        
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(Gdx.input.isTouched()) {
        	//Overrides any highLevel userInput
        }
        
        currTime += Gdx.graphics.getDeltaTime();
        
        if(currTime > 4.5) {
        	spriteBatch.draw(mainMenuButton, -5, 105);
            spriteBatch.draw(retryLevel, 640, 100);
	        if(Gdx.input.isTouched()) {
	        	if(Gdx.input.getX() > Gdx.graphics.getWidth()/2) {
	        		myGame.setLevelScreen(false);
	        	} else {
	        		myGame.setMainMenuScreen();
	        	}
	        }
        }
        score = "Score: "+myGame.playerScore;
        if(myGame.playerScore > 9) {
        	offset = 123;
        }
        if(myGame.playerScore > 99) {
        	offset = 141;
        }
        if(myGame.playerScore > 999) {
        	offset = 159;
        }
        if(myGame.playerScore > 9999) {
        	offset = 174;
        }
        if(myGame.playerScore > 19999) {
        	score = "Score: ~ Infinity";
        	offset = 200;
        }
        font2.drawMultiLine(spriteBatch, score, Gdx.graphics.getWidth()/2-offset, 102);
        font.drawMultiLine(spriteBatch, score, Gdx.graphics.getWidth()/2-offset, 101);
        font.drawMultiLine(spriteBatch, score, Gdx.graphics.getWidth()/2-offset, 100);
        spriteBatch.end();
        	
	}
	
	public boolean checkRetry() {
		if(Gdx.input.getX()<Gdx.graphics.getWidth()/2)
			return false;
		else
			return true;
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

	}

}
