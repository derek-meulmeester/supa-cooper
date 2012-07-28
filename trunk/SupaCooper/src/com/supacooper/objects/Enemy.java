package com.supacooper.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.supacooper.screens.LevelScreen;

public class Enemy {
	
	public static final int SIMPLE_BOUNCE = 1; //
	public static final int BOUNCE_AND_OCCASIONAL_ATTACK = 2; 
	public static final int CURVY_BALLER = 3; 
	
	public static final int SLOW_CREEPER = 4; //
	private static final float SLOW_CREEPER_REFRESH_FREQUENCY = 0.2f;
	
	public static final int FREQUENT_ATTACK = 5; //
	private static final float FREQUENT_ATTACK_FREQUENCY = 3f;
	
	public static final int SINUSOIDAL_MANAIAC = 6;
	public static final int DECAYING_EXPO = 7;
	
	private int health;
	
	private int behavior;
	
	private LevelScreen myScreen;
	
	public float enemyDamage = 0;
	
	public static int NUM_ENEMY_TYPES = 4;
	public int NUM_FRAMES_PER_SPRITE = 7;
	public static int FRAME_WIDTH = 128;
	public static int BOSS_FRAME_WIDTH = 256;
	private static final float DEATH_ANIMATION_SPEED = 0.2f;
	
	//Enemy movement and position variables
	private float radius;
	public boolean enteredScreen = false;
	private boolean isDead = false;
	private boolean isDying = false;
	//Enemy movement variables
	private Vector2 velocity;
	private Vector2 position;
	public Vector2 center;
	private float width;
	private float height;
	public float originalWidth;
	public float originalHeight;
	private float startDelay;
	private boolean ISBOSS = false;
	
	//Graphics variables
	private SpriteBatch batch;
	private Texture enemyTexture;
	private TextureRegion[] spriteFrames;
	private Animation deathAnimation;
	private int enemyType; //random number from 1-4
	private float rotation;
	private float scaleX;
	private float scaleY;
	
	private float currentFrameTime;
	private float currentDeathFrameTime;
	private float absoluteFrameTime;
	
	public void die() {
		isDying = true;
		currentDeathFrameTime = 0.0f;
	}
	
	public Boolean isDead() {
		return isDead;
	}
	
	public Boolean isDying() {
		return isDying;
	}
	
	public Enemy(Vector2 newPosition, Vector2 newVelocity, float newScale, float startDelay , int behaviour, float enemyDamage, int health, LevelScreen newScreen) {
		this.position = newPosition;
		this.center = new Vector2(position.x+64, position.y+64);
		this.startDelay = startDelay;
		this.behavior = behaviour;
		this.enemyDamage = enemyDamage;
		this.velocity = newVelocity;
		this.scaleX = newScale;
		this.scaleY = newScale;
		this.health = health;
		this.radius = 39f;
		
		width = FRAME_WIDTH;
		height = FRAME_WIDTH;
		
		myScreen = newScreen;
		
		rotation = 0;
		
		// assign the enemy a random type
		Random random = new Random();
		this.batch = new SpriteBatch();
		
		//load Sprite texture, split into frames, and create animation
		if(newScale == 4) {
			this.enemyTexture = new Texture(Gdx.files.internal("images/sprites/bossSpriteDie.png"));
			TextureRegion[][] regions = TextureRegion.split(enemyTexture, BOSS_FRAME_WIDTH, BOSS_FRAME_WIDTH);
			spriteFrames = regions[0];
			NUM_FRAMES_PER_SPRITE = 4;
			ISBOSS = true;
		} else {
			enemyType = random.nextInt(NUM_ENEMY_TYPES-1);
			this.enemyTexture = new Texture(Gdx.files.internal("images/sprites/enemyDeath"+Integer.toString(enemyType) + ".png"));
			TextureRegion[][] regions = TextureRegion.split(enemyTexture, FRAME_WIDTH, FRAME_WIDTH);
			spriteFrames = regions[0];
		}
		
		deathAnimation = new Animation(DEATH_ANIMATION_SPEED, spriteFrames);
		
		currentFrameTime = 0f;
		currentDeathFrameTime = 0f;
		absoluteFrameTime = 0f;
	}
	
	private Texture bossTexture;
	private TextureRegion[] bossFrames;
	private Animation bossAnimation;
	private static final float BOSS_ANIMATION_SPEED = 0.2f;
	
	private Texture bossDeathTexture;
	private TextureRegion[] bossDeathFrames;
	private Animation bossDeathAnimation;
	private static final float BOSS_DEATH_ANIMATION_SPEED = 0.2f;
	
	private boolean isBoss = false;
	
	public void convertToBadAss() {
		bossTexture = new Texture(Gdx.files.internal("images/sprites/bossSprite.png"));
		TextureRegion[][] bossRegions = TextureRegion.split(bossTexture, FRAME_WIDTH*2, FRAME_WIDTH*2);
		bossFrames = bossRegions[0];
		bossAnimation = new Animation(BOSS_ANIMATION_SPEED, bossFrames);
		
		bossDeathTexture = new Texture(Gdx.files.internal("images/sprites/bossDeathSprite.png"));
		TextureRegion[][] bossDeathRegions = TextureRegion.split(bossDeathTexture, FRAME_WIDTH*2, FRAME_WIDTH*2);
		bossDeathFrames = bossDeathRegions[0];
		bossDeathAnimation = new Animation(BOSS_DEATH_ANIMATION_SPEED, bossDeathFrames);
		
		this.width = this.width*2;
		this.height = this.height*2;
		
		isBoss = true;
	}
	
	public void lowerHealth(int amount) {
		this.health -= amount;
		if(this.health <= 0) {
			die();
		}
		this.scaleX = this.scaleX-(((float)amount/10f)*2);
		this.scaleY = this.scaleX;
	}
	
	
	public void onDraw() {
		
		this.batch.begin();
		if (isDying) 
		{
			if (isBoss)
			{
				TextureRegion frame =  bossDeathAnimation.getKeyFrame(currentDeathFrameTime, false);
				batch.draw(frame, position.x, position.y);
				
				if (currentDeathFrameTime/BOSS_DEATH_ANIMATION_SPEED > NUM_FRAMES_PER_SPRITE) {
					isDead = true;
				}
				currentDeathFrameTime += Gdx.graphics.getDeltaTime();
			}
			else
			{
				TextureRegion frame =  deathAnimation.getKeyFrame(currentDeathFrameTime, false);
				batch.draw(frame, position.x, position.y);
				
				
				
				if (currentDeathFrameTime/DEATH_ANIMATION_SPEED > NUM_FRAMES_PER_SPRITE) {
					isDead = true;
				}
				currentDeathFrameTime += Gdx.graphics.getDeltaTime();
			}

		}
		else 
		{
			if (isBoss)
			{
				TextureRegion frame =  bossAnimation.getKeyFrame(absoluteFrameTime, true);
				batch.draw(frame, position.x, position.y);
			}
			else
			{
				if(ISBOSS)
					rotation = 90;
				
				batch.draw(spriteFrames[0], position.x, position.y, width/2, height/2, 
						width, height, scaleX, scaleY, rotation, true);
			}
		}
		batch.enableBlending();
		this.batch.end();
		
		update();
		
		// if entered Screen
		if(isInsideScreen() && !enteredScreen)
			enteredScreen = true;
	}
	

	
	public void update() {
		
		if (!enteredScreen)
			simpleBounce();
		
		switch (behavior) 
		{
			case SIMPLE_BOUNCE: 		simpleBounce(); break;
			case FREQUENT_ATTACK: 		frequentAttack(); break;
			case SLOW_CREEPER:			slowCreeper(); break;
			case SINUSOIDAL_MANAIAC: 	sinusoidalManiac(); break;
			case DECAYING_EXPO:			decayingExpo();break;
			default: 					simpleBounce(); break;
		}
		
		if (absoluteFrameTime > startDelay)
			updatePosition();

		currentFrameTime += Gdx.graphics.getDeltaTime();
		absoluteFrameTime += Gdx.graphics.getDeltaTime();
		
		calculateBounds();

	}
	
	private void simpleBounce()
	{
		if(!ISBOSS)
			rotation = absoluteFrameTime*20;
		
		scaleX = scaleX + (float) Math.sin(absoluteFrameTime*5)/110;
		scaleY = scaleX;
	}

	private void frequentAttack()
	{
		//attack every 3 seconds
		if (currentFrameTime > FREQUENT_ATTACK_FREQUENCY)
		{
			currentFrameTime = 0f;
			Vector2 playerPosition = myScreen.getPlayerPosition();
			//point velocty directly at player
			Vector2 newDirection = playerPosition.sub(position).nor();
			
			float oldMagnitude = velocity.len();
			velocity = newDirection.mul(oldMagnitude+1);
		}
	}
	
	private void slowCreeper()
	{
		if (currentFrameTime > SLOW_CREEPER_REFRESH_FREQUENCY) 
		{
			//Update movement
			currentFrameTime = 0f;
			Vector2 playerPosition = myScreen.getPlayerPosition();
			Vector2 directionToPlayer = playerPosition.sub(position).nor();
			float rotateAmount = directionToPlayer.angle() - velocity.angle();
			//rotate max 10 degrees at once
			float rotateThreshold = 10;
			if (Math.abs(rotateAmount) < rotateThreshold)
				velocity.rotate(rotateAmount);
			else
				if (rotateAmount > 0)
					velocity.rotate(rotateThreshold);
				else //rotateAmoun < 0
					velocity.rotate(-rotateThreshold);
		}
		
		rotation = absoluteFrameTime*50;
		scaleX = 1f + (float) Math.sin(absoluteFrameTime*5)/10;
		scaleY = scaleX;
	}
	
	private void sinusoidalManiac()
	{
		// point toward player
		if (currentFrameTime > 5f)
		{
			currentFrameTime = 0f;
			Vector2 playerPosition = myScreen.getPlayerPosition();
			//point velocity directly at player
			Vector2 newDirection = playerPosition.sub(position).nor();
			
			float oldMagnitude = velocity.len();
			velocity = newDirection.mul(oldMagnitude+0.3f);
		}
		float sin1 = (float) ((float) Math.sin(absoluteFrameTime));
		rotation = absoluteFrameTime*100*sin1;
		
		float sin2 = (float) ((float) Math.sin(absoluteFrameTime/50));
		velocity = velocity.rotate(sin2*10);
	}
	
	private void decayingExpo() {
		
	}
	
	private void calculateBounds()
	{
		xLeftBound = -40;
		xRightBound = Gdx.graphics.getWidth()-width/2 - 20;
		yLowerBound = -40;
		yUpperBound = Gdx.graphics.getHeight()-height/2 - 20;
	}
	
	private void updatePosition() 
	{
		position = position.add(velocity);
		center.x = position.x+64;
		center.y = position.y+64;
		
		if (enteredScreen)
			deflectOffScreenEdge();
	}
	
	private float xLeftBound, xRightBound, yLowerBound, yUpperBound;
	
	private void deflectOffScreenEdge() 
	{
		if(enteredScreen) {
			if(position.x >= xRightBound)
				velocity.x = -1*velocity.x;
			if(position.x <= xLeftBound)
				velocity.x = -1*velocity.x;
			if(position.y >= yUpperBound)
				velocity.y = -1*velocity.y;
			if(position.y <= yLowerBound)
				velocity.y = -1*velocity.y;
		}
	}
	
	private boolean isInsideScreen()
	{
		float offset = 5;
		if(		(position.x > xLeftBound + offset) && (position.x < xRightBound - offset) &&
				(position.y > yLowerBound + offset) && (position.y < yUpperBound - offset) ) 
			return true;
		else
			return false;
	}
	
	public Vector2 getCenter() {
		return center;
	}
	
	public float getRadius() {
		return radius*scaleX;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	/**
	 * An extension to the dispose() method to cleanup resources
	 * created by the enemy object
	 */
	public void cleanup() {
		if(this.batch != null)
			this.batch.dispose();
		if(this.enemyTexture != null)
			this.enemyTexture.dispose();
	}

}
