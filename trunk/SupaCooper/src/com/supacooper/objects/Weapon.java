package com.supacooper.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
	public int pointValue = 5;
	public int x_pos;
	public int y_pos;
	public int x_vel;
	public int y_vel;
	public Vector2 center;
	public int ball_type;
	public float radius;
	public int start_x;
	public int start_y;
	public int weaponHeight;
	public int weaponWidth;
	public boolean onRelease;
	private float rotation;
	private Player player;
	
	private SpriteBatch smallweaponBatch;
	private Texture smallweaponTexture;
	
	private SpriteBatch mediumweaponBatch;
	private Texture mediumweaponTexture;
	
	private SpriteBatch largeweaponBatch;
	private Texture largeweaponTexture;
	
	private float smallWeaponDamage = 0.02f;
	private float mediumWeaponDamage = 0.12f;
	private float largeWeaponDamage = 0.18f;
	public float weaponDamage;
	
	public int weaponPower;
	
	/**
	 * Constructor
	 */
   public Weapon(Player player,int xpos,int ypos,int type) {
	   this.player = player;
	   	   
		switch(type){
		case 0 : smallweaponBatch = new SpriteBatch();
		         smallweaponTexture = new Texture(Gdx.files.internal("weapon/smallWeapon.png"));
		         this.weaponDamage = this.smallWeaponDamage;
		         weaponPower = 1;
		         radius = 3f;
		         pointValue = 5;
		         break;
		
		case 1 : mediumweaponBatch = new SpriteBatch();
	             mediumweaponTexture = new Texture(Gdx.files.internal("weapon/mediumWeapon.png")); 
	             this.weaponDamage = this.mediumWeaponDamage;
	             weaponPower = 2;
	             radius = 7.5f;
	             pointValue = 10;
	             break;
		
		case 2 : largeweaponBatch = new SpriteBatch();
				 largeweaponTexture = new Texture(Gdx.files.internal("weapon/largeWeapon.png")); 
				 this.weaponDamage = this.largeWeaponDamage;
				 weaponPower = 3;
				 radius = 12f;
				 pointValue = 20;
				 break;
	}
	
	weaponHeight = 64;
	weaponWidth = 64;  
	x_pos =  player.x_center;
	center = new Vector2(xpos+128, ypos+128);
	y_pos = player.y_center;
	x_vel = 0;
	y_vel = 0;
	ball_type = type;
	start_x = x_pos;
	start_y = y_pos;
	onRelease = false;
	
   }
   
   public void onDraw() {
			
		if(!onRelease){
			this.x_pos = (int) ((int)this.player.x_pos+(Math.cos(((double)this.player.playerRotation*2*Math.PI)/360)*110*this.player.x_scale));
			this.y_pos = (int) ((int)this.player.y_pos+(Math.sin(((double)this.player.playerRotation*2*Math.PI)/360)*110*this.player.x_scale));
			this.center.x = this.x_pos+128;
			this.center.y = this.y_pos+128;
			
			this.rotation = this.player.playerRotation;
		}
		switch(ball_type)  {
	    case 0: this.smallweaponBatch.begin();
			       this.smallweaponBatch.draw(smallweaponTexture,
											  this.x_pos, this.y_pos,
											  256/2, 256/2,
											  256, 256,
											  this.player.x_scale, this.player.y_scale,
											  this.rotation,
											  0, 0,
											  256, 256,
											  false, false);
			       if (onRelease == true)
			    	   this.updatePosition();
			    	   
			       this.smallweaponBatch.enableBlending();
			       this.smallweaponBatch.end(); break;
	    
	    case 1: this.mediumweaponBatch.begin();
	    		this.mediumweaponBatch.draw(mediumweaponTexture,
											  this.x_pos, this.y_pos,
											  256/2, 256/2,
											  256, 256,
											  this.player.x_scale, this.player.y_scale,
											  this.rotation,
											  0, 0,
											  256, 256,
											  false, false);
	    		if (onRelease == true)
			    	   this.updatePosition();
	    		this.mediumweaponBatch.enableBlending();
	    		this.mediumweaponBatch.end(); break;
	    		
	    case 2: this.largeweaponBatch.begin();
		        this.largeweaponBatch.draw(largeweaponTexture,
											  this.x_pos, this.y_pos,
											  256/2, 256/2,
											  256, 256,
											  this.player.x_scale, this.player.y_scale,
											  this.rotation,
											  0, 0,
											  256, 256,
											  false, false);
		        this.largeweaponBatch.enableBlending();
		        if (onRelease == true)
			    	   this.updatePosition();
		        this.largeweaponBatch.end(); break;
		}
   }
   
   public void updatePosition() {
	   if(x_vel == 0 && y_vel == 0) {
		   x_vel = 1;
		   y_vel = 1;
	   }
	   x_pos += x_vel; 
	   y_pos += y_vel; 
	   center.x += x_vel;
	   center.y += y_vel;
   }
   
   public void set_vel(int xvel, int yvel) {
	   switch(this.ball_type) {
		   case(0): {
			   x_vel = (xvel/20);
			   y_vel = (-1*(yvel/20));
			   break;
		   }
		   case(1): {
			   x_vel = (xvel/40);
			   y_vel = (-1*(yvel/40));
			   break;
		   }
		   case(2): {
			   x_vel = (xvel/60);
			   y_vel = (-1*(yvel/60));
			   break;
		   }
	   }
   }
	   
		
	public int getX_pos() {
	   return x_pos;
	   
   }
   
   public int getY_pos() {
	   return y_pos;
	   
   }
   
   public int getX_vel() {
	   return x_vel;
	   
   }

   public int getY_vel() {
	   return y_vel;
   }
   
   public void cleanup() {
	   if(this.smallweaponBatch != null)
		   this.smallweaponBatch.dispose();
	   if(this.smallweaponTexture != null)
		   this.smallweaponTexture.dispose();
	   if(this.mediumweaponBatch != null)
		   this.mediumweaponBatch.dispose();
	   if(this.mediumweaponTexture != null)
		   this.mediumweaponTexture.dispose();
	   if(this.largeweaponBatch != null)
		   this.largeweaponBatch.dispose();
	   if(this.largeweaponTexture != null)
		   this.largeweaponTexture.dispose();
   }
   
}
