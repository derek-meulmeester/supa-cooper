package com.supacooper.screens.levels;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.supacooper.SupaCooper;
import com.supacooper.objects.Enemy;
import com.supacooper.screens.LevelScreen;

public class Level1Screen extends LevelScreen {

	private double currentTime;
	private int currentWave;
	private static final int NUM_WAVES = 5;
	private SpriteBatch spriteBatch;
	
	/* *********************
	 * IntroHelp Information
	 * *********************/
	private int FREQUENCY = 3;
	private float ARROW_AMPLITUDE = 0.6f;
	
	private TextureRegion[] arrowAnimation;
	private TextureRegion[] fingerAnimation;
	private TextureRegion[] tapTargetAnimation;
	private Texture tapTargetMsg;
	private Texture pullBackMsg;
	private Texture tmpTexture;
	private int closingSpeed = 5;
	private float tapMsgWidth = 350;
	private float tapMsgHeight = 100;
	private float pullMsgWidth = 350;
	private float pullMsgHeight = 100;
	private int FINGER_FRAME_INDEX = 0;
	private int ARROW_FRAME_INDEX = 0;
	private int TAP_TARGET_FRAME_INDEX = 0;
	private int FRAME_WIDTH = 256;
	private int FRAME_HEIGHT = 256;
	private float arrowXScale = 0.6f;
	private float arrowYScale = 0.8f;
	private float fingerXScale = 0.8f;
	private float fingerYScale = 1.0f;
	private float tapXScale = 0.1f;
	private float tapYScale = 0.1f;

	public Level1Screen(SupaCooper newSupaCooper) {
		super(newSupaCooper);
		currentWave = 1;
		
		this.spriteBatch = new SpriteBatch();
		tmpTexture = new Texture(Gdx.files.internal(SupaCooper.HELP_ARROW_IMG));
		TextureRegion[][] regions = TextureRegion.split(tmpTexture, FRAME_WIDTH, FRAME_HEIGHT);
		arrowAnimation = regions[ARROW_FRAME_INDEX];
		
		this.tapTargetMsg = new Texture(Gdx.files.internal(SupaCooper.HELP_TAP_MSG_IMG));
		tmpTexture = new Texture(Gdx.files.internal(SupaCooper.HELP_TAP_TARGET_IMG));
		regions = TextureRegion.split(tmpTexture, 64, 64);
		tapTargetAnimation = regions[TAP_TARGET_FRAME_INDEX];
		
		this.pullBackMsg = new Texture(Gdx.files.internal(SupaCooper.HELP_PULL_BACK_MSG_IMG));
		tmpTexture = new Texture(Gdx.files.internal(SupaCooper.HELP_FINGER_BOTTOM_IMG));
		regions = TextureRegion.split(tmpTexture, FRAME_WIDTH, FRAME_HEIGHT);
		fingerAnimation = regions[FINGER_FRAME_INDEX];
	}
	
	@Override
	public void setBackground() {
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/levelMapOne.png"));
	}
	
	/*
	 * Enemy(position, velocity, startDelay, behaviour, damageToPlayer, this)
	 * x approx [0..800]
	 * y approx [0..480]
	 */
	@Override
	public void setAudio() {
		this.levelMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("sounds/levelOne.mp3", FileType.Internal));
	}
	
	@Override
	public void generateFirstEnemies() 
	{  
	generateWave1();
	
	}
	
	@Override
	public void allEnemiesKilled() {
		currentWave ++;
		if (currentWave > NUM_WAVES)
			this.endLevel();
		else
			switch (currentWave)
			{
				case 2: generateWave2(); break;
				
				case 3: generateWave3(); break;
				case 4: generateWave4(); break;
				case 5: generateWave5(); break;
			}
	}
	
	private void generateWave1(){
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(100, 200);
		velocity = new Vector2(0,-1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		
	} 
	

	private void generateWave2(){
		
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(-116, 300);
		velocity = new Vector2(1,-0.5f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		
		
		
	} 
	
	private void generateWave3(){
		
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(-116f, 400);
		velocity = new Vector2(1.5f,-0.25f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(-116f, 0);
		velocity = new Vector2(1.5f,.30f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
	} 
	
	private void generateWave4(){
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(800, 400);
		velocity = new Vector2(-1.5f,-0.25f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(-116f, 0);
		velocity = new Vector2(1.5f,.30f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(500, -116f);
		velocity = new Vector2(-1f,.75f);
		newEnemy = new Enemy(position, velocity, 1, 2f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(-116f, 400);
		velocity = new Vector2(2f, -.75f);
		newEnemy = new Enemy(position, velocity, 1, 3f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		
		
	}
	
	private void generateWave5(){
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(-116f, 400);
		velocity = new Vector2(1.5f,-0.75f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(-116f, 240);
		velocity = new Vector2(1.5f,0f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(-116f, 0f);
		velocity = new Vector2(1.5f,.75f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 480);
		velocity = new Vector2(-1.5f, -0.75f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 240);
		velocity = new Vector2(-1.5f, 0f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 0f);
		velocity = new Vector2(-1.5f, .75f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE , .006f, 1, this);
		this.addEnemy(newEnemy);
		
		
		
		
	}
	
	
	@Override
	public void extendedRender() 
	{
		if(this.helpIntro) {
			this.spriteBatch.begin();
			currentTime += Gdx.graphics.getDeltaTime();
			this.arrowYScale = ((float)Math.sin(FREQUENCY*currentTime)+1.2f)*ARROW_AMPLITUDE;
			
			if(!this.hasTappedScreen) {
				this.spriteBatch.draw(this.tapTargetMsg,
									  10,Gdx.graphics.getHeight()-this.tapMsgHeight-10, 
									  this.tapMsgWidth, this.tapMsgHeight);
				this.tapXScale = ((float)Math.sin(FREQUENCY*currentTime)+1f)*0.4f;
				this.tapYScale = ((float)Math.sin(FREQUENCY*currentTime)+1f)*0.4f;
				this.spriteBatch.draw(this.tapTargetAnimation[TAP_TARGET_FRAME_INDEX],
									  220, 80, 
									  FRAME_WIDTH/2, FRAME_HEIGHT/2,
									  FRAME_WIDTH, FRAME_HEIGHT,
									  this.tapXScale, this.tapYScale, 
									  this.player.playerRotation);
			} else {
				if(this.tapMsgHeight > this.closingSpeed) {
					this.tapMsgHeight -= this.closingSpeed;
					this.tapMsgWidth -= 4*this.closingSpeed;
					this.spriteBatch.draw(this.tapTargetMsg,
							  10,Gdx.graphics.getHeight()-this.tapMsgHeight-10, 
							  this.tapMsgWidth, this.tapMsgHeight);
				}
			}
			
			if(!this.hasPulledBack){
				this.spriteBatch.draw(this.pullBackMsg,
									  10,Gdx.graphics.getHeight()-this.pullMsgHeight-10, 
									  this.pullMsgWidth, this.pullMsgHeight);
				this.spriteBatch.draw(this.arrowAnimation[ARROW_FRAME_INDEX],
									  this.player.x_pos, this.player.y_pos, 
									  FRAME_WIDTH/2, FRAME_HEIGHT/2,
									  FRAME_WIDTH, FRAME_HEIGHT,
									  this.arrowXScale, this.arrowYScale, 
									  this.player.playerRotation);
				
				float derivative = (float)Math.cos(FREQUENCY*currentTime);
				
				if(derivative > 0) {
					float Animation = (float)Math.sin(FREQUENCY*currentTime)*-70;
					float xAnimation = (float)Math.sin(((double)this.player.playerRotation*2*Math.PI)/360)*-Animation;
					float yAnimation = (float)Math.cos(((double)this.player.playerRotation*2*Math.PI)/360)*Animation;
					this.spriteBatch.draw(this.fingerAnimation[FINGER_FRAME_INDEX],
										  this.player.x_pos+xAnimation, this.player.y_pos+yAnimation, 
										  FRAME_WIDTH/2, FRAME_HEIGHT/2,
										  FRAME_WIDTH, FRAME_HEIGHT,
										  this.fingerXScale, this.fingerYScale, 
										  this.player.playerRotation);
				}
				
				
			} 
			if(closePullMsg) {
				if(this.pullMsgHeight > this.closingSpeed) {
					this.pullMsgHeight -= this.closingSpeed;
					this.pullMsgWidth -= 4*this.closingSpeed;
					this.spriteBatch.draw(this.pullBackMsg,
										  10,Gdx.graphics.getHeight()-this.pullMsgHeight-10, 
										  this.pullMsgWidth, this.pullMsgHeight);
				} else {
					this.helpIntro = false;
				}
			}
			
			this.spriteBatch.end();
		} else {
			if(this.pullBackMsg != null)
				this.pullBackMsg.dispose();
			if(this.tapTargetMsg != null)
				this.tapTargetMsg.dispose();
		}
	}
	
	public void cleanup() {
		if(this.spriteBatch != null)
			this.spriteBatch.dispose();
	}

}
