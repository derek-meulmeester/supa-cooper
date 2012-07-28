package com.supacooper.screens.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.supacooper.SupaCooper;
import com.supacooper.objects.Enemy;
import com.supacooper.screens.LevelScreen;

public class Level3Screen extends LevelScreen {

	private float currentTime;
	private int currentWave;
	private static final int NUM_WAVES = 4;
	
	public Level3Screen(SupaCooper newSupaCooper) {
		super(newSupaCooper);
		currentTime = 0f;
		currentWave = 1;
	}
	
	@Override
	public void setBackground() {
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/levelMapThree.png"));
	}
	
	/*
	 * Enemy(position, velocity, startDelay, behaviour, damageToPlayer, this)
	 * x approx [0..800]
	 * y approx [0..480]
	 */
	@Override
	public void setAudio() {
		this.levelMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("sounds/levelThree.mp3", FileType.Internal));
	}
	
	@Override
	public void generateFirstEnemies() {
		
		generateWave1();
		
	}
	
	@Override
	public void allEnemiesKilled() 
	{
		currentWave++;
		if (currentWave > NUM_WAVES)
			this.endLevel();
		else
			switch (currentWave)
			{
				case 2: generateWave2(); break;
				case 3: generateWave3(); break;
				case 4: generateWave4(); break;
			}
	}
	
	
	
	private void generateWave1() 
	{
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(0, 400);
		velocity = new Vector2(1, -1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SINUSOIDAL_MANAIAC, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		
		
		position = new Vector2(-116f, 0);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 480);
		velocity = new Vector2(-1, -1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
	}

	private void generateWave2() 
	{
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(0, 400);
		velocity = new Vector2(1f, -6f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(500, 20);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SINUSOIDAL_MANAIAC, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(0, 0);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
	}
	
	private void generateWave3() 
	{
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(0, 400);
		velocity = new Vector2(6f, -1f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(650, 100);
		velocity = new Vector2(-6f, 1f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SIMPLE_BOUNCE, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		
		position = new Vector2(20, 200);
		velocity = new Vector2(2, 1);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 100);
		velocity = new Vector2(2, 1);
		newEnemy = new Enemy(position, velocity, 1, 3f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(400, -116);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 3f, Enemy.SINUSOIDAL_MANAIAC, 0.006f, 2, this);
		this.addEnemy(newEnemy);
	}
	
	private void generateWave4(){
		
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(0, 200);
		velocity = new Vector2(.5f, 0);
		newEnemy = new Enemy(position, velocity, 4, 0f, Enemy.SIMPLE_BOUNCE, 0.006f, 15, this);
		
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 400);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 2f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 50);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 4f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 200);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 6f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 50);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 8f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 400);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 10f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 200);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 12f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(800, 50);
		velocity = new Vector2(1, 1);
		newEnemy = new Enemy(position, velocity, 1, 14f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		
	}
	
	@Override
	public void extendedRender() 
	{
		currentTime += Gdx.graphics.getDeltaTime();
		if (currentTime > 5f)
		{
			//DO SOMETHING AWESOME!
		}
	}
	
	public void cleanup() {
		
	}
	
}