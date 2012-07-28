package com.supacooper.objects;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsBall implements ApplicationListener {

	Body myBody;
	int xPos;
	int yPos;
	int width;
	int height;
	SpriteBatch spriteBatch;
	Texture ballTexture;
	
	public PhysicsBall() {
		ballTexture = new Texture(Gdx.files.internal("weapon/WeaponTrail.png"));
		xPos = 0;
		yPos = 0;
		width = 0;
		height = 0;
	}
	
	public void setBody(Body newBody) {
		myBody = newBody;
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
	public void render() {
		updateBallDetails();
        spriteBatch.begin();
        spriteBatch.draw(ballTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
	}
	
	public void updateBallDetails() {
		spriteBatch.begin();

		Vector2 position = myBody.getPosition(); // that's the box's center position
		xPos = (int) position.x;
		yPos = (int) position.y;
//		float angle = MathUtils.radiansToDegrees * myBody.getAngle(); // the rotation angle around the center
//		
		spriteBatch.draw(ballTexture, xPos, yPos);
		
//		spriteBatch.draw(ballTexture, xPos, yPos, // the bottom left corner of the box, unrotated
//			1f, 1f, // the rotation center relative to the bottom left corner of the box
//			2, 2, // the width and height of the box
//			1, 1, // the scale on the x- and y-axis
//			angle); // the rotation angle
		
		spriteBatch.end();
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
