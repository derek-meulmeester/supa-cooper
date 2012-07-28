package com.supacooper.objects;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;

public class Button1 implements ApplicationListener, InputProcessor {

	private SupaCooper myGame;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private SpriteBatch spriteBatch;
	private Texture upTexture;
	private Texture downTexture;
	private Boolean isDown;
	private int widthReduction;
	public float scale;
	
	private int buttonType;
	
	public Button1(int newX, int newY, Texture newUpTexture, Texture newDownTexture, int newType, SupaCooper newGame) {
		xPos = newX;
		yPos = newY;
		upTexture = newUpTexture;
		downTexture = newDownTexture;
		width = upTexture.getWidth();
		height = upTexture.getHeight();
		
		isDown = false;
		buttonType = newType;
		
		myGame = newGame;
		spriteBatch = new SpriteBatch();
		scale = 1;
	}
	
	
	public void reduceWidth(int widthReduction)
	{
		this.widthReduction = widthReduction;
	}
	
	public void scale(float scale)
	{
		this.scale = scale;
		width = (int) (width*scale);
		height = (int) (height*scale);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if ( isInButton(x, y) )
			isDown = true;
		else
			isDown = false;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (isDown == true)
			if ( isInButton(x, y) )
			{
				myGame.performAction(buttonType);
			}
		isDown = false;
		
		return false;
	}
	
	
	@Override
	public void render() {
        spriteBatch.begin();
        if(isDown)
        	spriteBatch.draw(downTexture, xPos, yPos, 0, 0, width, height);
        else
        	spriteBatch.draw(upTexture, xPos, yPos, 0, 0, width, height);
        spriteBatch.end();
	}
	
	private Boolean isInButton(int x, int y)
	{
		y = Gdx.graphics.getHeight() - y;
		if ( ((x > xPos) && (x < xPos + width - widthReduction)) && ( (y > yPos) && (y < yPos + height) ) )
		{
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
