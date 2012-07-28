package com.supacooper.screens.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.supacooper.SupaCooper;
import com.supacooper.objects.Enemy;
import com.supacooper.screens.LevelScreen;

public class Level2Screen extends LevelScreen {

	private float currentTime;
	private int currentWave;
	private static final int NUM_WAVES = 3;
	
	public Level2Screen(SupaCooper newSupaCooper) {
		super(newSupaCooper);
		currentTime = 0f;
		currentWave = 1;
	}
	
	@Override
	public void setBackground() {
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/levelMapTwo.png"));
	}
	
	/*
	 * Enemy(position, velocity, startDelay, behaviour, damageToPlayer, this)
	 * x approx [0..800]
	 * y approx [0..480]
	 */
	@Override
	public void setAudio() {
		this.levelMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("sounds/levelTwo.mp3", FileType.Internal));
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
			}
	}
	
	private void generateWave1() 
	{
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(-100, 100);
		velocity = new Vector2(1, 0).nor().mul(1f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);

		position = new Vector2(900, 100);
		velocity = new Vector2(-1, 0).nor().mul(1.5f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(200, 600);
		velocity = new Vector2(0, -1).nor().mul(2f);
		newEnemy = new Enemy(position, velocity, 1, 1f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);

		position = new Vector2(500, -100);
		velocity = new Vector2(0, 1).nor().mul(2f);
		newEnemy = new Enemy(position, velocity, 1, 2f, Enemy.SLOW_CREEPER, 0.006f, 2, this);
		this.addEnemy(newEnemy);
	}

	private void generateWave2() 
	{
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(50, 400);
		velocity = new Vector2(0, -1).nor().mul(2);
		newEnemy = new Enemy(position, velocity, 1, 1f, Enemy.SINUSOIDAL_MANAIAC, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(100, 100);
		velocity = new Vector2(0, 1).nor().mul(3);
		newEnemy = new Enemy(position, velocity, 1, 1f, Enemy.SINUSOIDAL_MANAIAC, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(50, 0);
		velocity = new Vector2(1, 0).nor().mul(2);
		newEnemy = new Enemy(position, velocity, 1, 1f, Enemy.FREQUENT_ATTACK, 0.006f, 2, this);
		this.addEnemy(newEnemy);
	}
	
	private void generateWave3()
	{
		Vector2 position, velocity;
		Enemy newEnemy;
		
		position = new Vector2(-100, 50);
		velocity = new Vector2(1, 0).nor().mul(1f);
		newEnemy = new Enemy(position, velocity, 1, 0f, Enemy.FREQUENT_ATTACK, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(200, 500);
		velocity = new Vector2(0, -1).nor().mul(1.5f);
		newEnemy = new Enemy(position, velocity, 1, 0.5f, Enemy.FREQUENT_ATTACK, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(900, 0);
		velocity = new Vector2(-1, 1).nor().mul(2f);
		newEnemy = new Enemy(position, velocity, 1, 1f, Enemy.FREQUENT_ATTACK, 0.006f, 2, this);
		this.addEnemy(newEnemy);
		
		position = new Vector2(100, -100);
		velocity = new Vector2(0, 1).nor().mul(3f);
		newEnemy = new Enemy(position, velocity, 1, 1.5f, Enemy.FREQUENT_ATTACK, 0.006f, 2, this);
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