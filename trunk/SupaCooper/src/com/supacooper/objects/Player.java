package com.supacooper.objects;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.supacooper.SupaCooper;
import com.supacooper.screens.LevelScreen;
import com.supacooper.ui.WeaponSelector;

public class Player implements ApplicationListener,InputProcessor {
	
	//Object Information
	public int x_pos;
	public int x_center;
	public int y_pos;
	public int y_center;
	public int desired_x_pos;
	public int desired_y_pos;
	public float x_scale;
	public float y_scale;
	public int x_vel;
	public int y_vel;
	public int playerWidth;
	public int playerHeight;
	private float playerRadius;
	public float playerRotation;
	
	public boolean isTouched = false;
	public int touchDown_x;
	public int touchDown_y;
	public int release_x;
	public int release_y;
	public float trailScaling;
	
	//Graphics Information
	private SpriteBatch playerBatch;
	private Texture swingAnimationSprite;
	private Texture weaponTrailTexture;
	private TextureRegion[] swingAnimationFrames;
	private int currFrame = 0;
	private int FRAME_WIDTH = 256;
	private int FRAME_HEIGHT = 256;
	//private Animation swingAnimation;
	
	private Weapon weaponInstance;
	public WeaponSelector weaponSelector;
	private LevelScreen myScreen;
	
	private boolean initScreen = true;
	
	Sound weaponFireSoundEffect = Gdx.audio.newSound(Gdx.files.getFileHandle("sounds/weaponFire.mp3", FileType.Internal));

	/**
	 * Constructor to initialize the player
	 * @param playerWidth
	 * @param playerHeight
	 * @param game
	 */
	public Player(int playerWidth, int playerHeight, LevelScreen myScreen,
					float x_scale, float y_scale, SupaCooper myGame) {

		this.myScreen = myScreen;
		this.weaponSelector = new WeaponSelector(myScreen.selectedWeapon, myGame);
		this.weaponSelector.selectedWeapon = myScreen.selectedWeapon;
		this.playerWidth = playerWidth;
		
		this.x_scale = x_scale;
		this.y_scale = y_scale;
		this.playerRadius = 48f;
		this.playerHeight = playerHeight;
		this.playerRotation = 0f;
		this.x_pos = (Gdx.graphics.getWidth()/2)-(this.playerWidth/2)+150;
		this.y_pos = (Gdx.graphics.getHeight()/2)-(this.playerHeight/2)-40;
		this.x_center = (Gdx.graphics.getWidth()/2)+150;
		this.y_center = (Gdx.graphics.getHeight()/2)-40;
		this.desired_x_pos = this.x_center;
		this.desired_y_pos = this.y_center;
		this.x_vel = 1;
		this.y_vel = 1;
		this.touchDown_x = 0;
		this.touchDown_y = 0;
		this.release_x = 0;
		this.release_y = 0;
		this.trailScaling = 1f;
		
		playerBatch = new SpriteBatch();

		setupSprite();
	}
	
	private void setupSprite() {
		weaponTrailTexture = new Texture(Gdx.files.internal(SupaCooper.WEAPON_TRAIL_IMG));
		swingAnimationSprite = new Texture(Gdx.files.internal("images/sprites/swingsprite.png"));
		TextureRegion[][] regions = TextureRegion.split(swingAnimationSprite, FRAME_WIDTH, FRAME_HEIGHT);
		swingAnimationFrames = regions[0];
	}
	
	public float getRadius() {
		float sub = this.playerRadius;
		return (this.x_scale*sub);
	}
	
	/**
	 * This is the method to call to have the Object Draw itself
	 */
	public void onDraw() {
		this.weaponSelector.onDraw();
		
		this.playerBatch.begin();
		
		this.playerBatch.draw(this.swingAnimationFrames[currFrame],
							  this.x_pos, this.y_pos, 
							  playerWidth/2, playerHeight/2,
							  playerWidth, playerHeight,
							  this.x_scale, this.y_scale, 
							  playerRotation);
		if(isTouched) {
			int x = (int) ((int)this.x_pos+(Math.cos(((double)playerRotation*2*Math.PI)/360)*110*x_scale));
			int y = (int) ((int)this.y_pos+(Math.sin(((double)playerRotation*2*Math.PI)/360)*110*x_scale));
			/*This is the purple Weapon Trail.. Decided not to incorporate it in the published copy */ 
			this.playerBatch.draw(weaponTrailTexture,
								  x, y,
								  256/2, 256/2,
								  256, 256,
								  1f, trailScaling,
								  playerRotation,
								  0, 0,
								  256, 256,
								  false, false);
		}
		this.playerBatch.enableBlending();
		this.playerBatch.end();
	}
	
	/**
	 * Method to update the velocity of the player
	 */
	public void updateVelocity() {
		if(this.desired_x_pos > this.x_center) {
			this.x_vel = 1;
		}else if(this.desired_x_pos < this.x_center) {
			this.x_vel = -1;
		} else {
			this.x_vel = 0;
		}
		
		if(this.desired_y_pos > this.y_center) {
			this.y_vel = 1;
		}else if(this.desired_y_pos < this.y_center) {
			this.y_vel = -1;
		} else {
			this.y_vel = 0;
		}
	}
	/**
	 * Method to update the position of the player
	 */
	public void updatePosition() {
		if(isTouched) {

			float x = (float)Gdx.input.getX()-(float)(this.x_center);
			float y = (float)Gdx.input.getY()-((float)Gdx.graphics.getHeight()-(this.y_center));
			this.currFrame = (int)Math.sqrt((x*x)+(y*y))/35;
			if(this.currFrame > 6) {
				this.currFrame = 6;
			}
			
			this.playerRotation = ((float)Math.atan2(x,y)*57.2957f);
			
			float Y1 = (float)Math.cos(this.playerRotation/57.295779)*(float)((float)Gdx.input.getY()-this.touchDown_y)/30f;
			float X1 = (float)Math.sin(this.playerRotation/57.295779)*(float)((float)Gdx.input.getX()-this.touchDown_x)/30f;

			trailScaling = X1+Y1;
			if(trailScaling<0)
				trailScaling = trailScaling * -1;
			if(trailScaling>9) {
				trailScaling = 9;
			}
		} else {
			updateVelocity();
			this.x_pos += this.x_vel;
			this.y_pos += this.y_vel;
		}
		
		this.x_center = this.x_pos + this.playerWidth/2;
		this.y_center = (this.y_pos + this.playerHeight/2);
	
	}
	/**
	 * Method to update the players size
	 */
	public void updateSize(float scale) {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		myScreen.TEST = 10000;
		myScreen.PREV_TEST = -1;
		if(this.myScreen.screenShowing) {
			initScreen = false;
			if(!initScreen) {
				if(!this.weaponSelector.touchDown(x, y, pointer, button)) {
					double X = ((this.x_center)-x);
					double Y = ((Gdx.graphics.getHeight()-(this.y_center)-y));
					double DISTANCE_TO_PLAYER = (Math.sqrt((X*X)+(Y*Y)));
					
					if(DISTANCE_TO_PLAYER <= getRadius()*2 && !isTouched){
						isTouched = true;
						this.touchDown_x = this.x_center;
						this.touchDown_y = this.y_center;
						
						this.myScreen.createWeapon(this.weaponSelector.selectedWeapon);
						
						this.desired_x_pos = this.x_center;
						this.desired_y_pos = this.y_center;
						if(myScreen.helpIntro) {
							myScreen.hasPulledBack();
						}
			
					}
					if(!isTouched) {
						this.desired_x_pos = x;
						this.desired_y_pos = Gdx.graphics.getHeight()-y;
						
						float x_component = (float)Gdx.input.getX()-(float)(this.x_center);
						float y_component = (float)Gdx.input.getY()-((float)Gdx.graphics.getHeight()-(this.y_center));
	
						this.playerRotation = ((float)Math.atan2(x_component,y_component)*(float)57.2957)-160;
						if(myScreen.helpIntro) {
							myScreen.hasTapped();
							myScreen.preparePullBackHelp();
						}
					}
				}
			}
		}
		return false;
	}
	
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(this.myScreen.screenShowing) {
			if(isTouched) {
				isTouched = false;
				this.release_x = x;
				this.release_y = y;
				this.currFrame = 0;
				
				if(this.myScreen.weaponsList.size() > 0) {
					weaponInstance = this.myScreen.weaponsList.get(0);
					if(!weaponInstance.onRelease) {
						int x_vel = ((this.touchDown_x-this.release_x));
						int y_vel = ((Gdx.graphics.getHeight()-this.release_y))-(this.touchDown_y);
						weaponInstance.set_vel(x_vel, y_vel);
						weaponInstance.onRelease = true;
						this.weaponFireSoundEffect.play();
					}
				}
			}
		}
		return false;
	}
	
	public Vector2 getPosition() {
		return new Vector2(this.x_center, this.y_center);
	}

	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void create() {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
		
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
	
	public void cleanup() {
		if(this.playerBatch != null)
			this.playerBatch.dispose();
		if(this.swingAnimationSprite != null)
			this.swingAnimationSprite.dispose();
		
	}

}
