package com.supacooper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;

public class GameCompleteScreen implements Screen {

	SupaCooper myGame;
	private SpriteBatch spriteBatch;
	private Texture backgroundTexture;
	
	
	private BitmapFont font;
	private BitmapFont font2;
	private String endCredits;
	private float y;
	private float y_vel;
	private boolean key = false;
	private float currTime;
	
	public GameCompleteScreen(SupaCooper newSupaCooper) {
		myGame = newSupaCooper;
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/victory.png"));
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		font.setScale(2);
		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.setScale(2);
		y_vel = 2.0f;
		y = -2;
	}
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
		endCredits = "CONGRATULATIONS!\n"+
				 "you have defeated all the VIRA\n"+
				 "Final Score: "+myGame.playerScore+"\n"+
				 "\n" +
				 "If you enjoyed the game or\n"+
				 "have any issues send an email to:\n"+
				 "supa.cooper.dev@gmail.com\n";
		if(key) {
			endCredits = endCredits + "\n\nTap the Screen to go to Main Menu\n"+
									  "   ~<< Indeed Indubitably Inc. >>~";
		}
		if(this.y < Gdx.graphics.getHeight() - 40)
			this.y += this.y_vel;
		else {
			key=true;
		}
			
					 
		
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font2.drawMultiLine(spriteBatch, endCredits, 12, this.y-1);
        font2.drawMultiLine(spriteBatch, endCredits, 11, this.y-1);
        font.drawMultiLine(spriteBatch, endCredits, 10, this.y);
        font.drawMultiLine(spriteBatch, endCredits, 9, this.y+1);
        spriteBatch.end();
        
        currTime += Gdx.graphics.getDeltaTime();
        
        if(currTime > 0.5) {
	        if(Gdx.input.justTouched()) {
	        		myGame.setMainMenuScreen();
	        }
        }
        	
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
		y = -2;

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
