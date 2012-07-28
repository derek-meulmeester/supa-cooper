package com.supacooper.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Button {

	public TextureRegion pressedRegion;
	public TextureRegion unpressedRegion;
	public boolean pressed = false;
	public float width;
	public float height;
	public float originX;
	public float originY;
	
	
	public Button(String name, Texture texture){
		originX = texture.getWidth() / 2.0f;
		originY = texture.getHeight() / 2.0f;
		width = texture.getWidth();
		height = texture.getHeight();
		pressedRegion = new TextureRegion(texture);
		unpressedRegion = new TextureRegion(texture);
		
	}
}
