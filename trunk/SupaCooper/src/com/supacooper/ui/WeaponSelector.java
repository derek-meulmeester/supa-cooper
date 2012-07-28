package com.supacooper.ui;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;

public class WeaponSelector implements InputProcessor{

	private SupaCooper myGame;
	private float x_scale;
	private float y_scale;
	public int x_pos;
	public int y_pos;
	
	private SpriteBatch menuBatch;
	private Texture menuTexture;
	private Texture menuBarTexture;
	private Texture healthBarTexture;
	
	private Sound weaponSelectSoundEffect = Gdx.audio.newSound(Gdx.files.getFileHandle("sounds/weaponSelect.mp3", FileType.Internal));
	
	public int selectedWeapon;
	
	public WeaponSelector(int type, SupaCooper newSupaCooper) {
		myGame = newSupaCooper;
		this.selectedWeapon = type;
		
		this.x_scale = 0.8f;
		this.y_scale = 0.8f;
		
		this.menuBatch = new SpriteBatch();
		this.menuBarTexture = new Texture(Gdx.files.internal(SupaCooper.WEAPON_MENUBAR_IMG));
		this.healthBarTexture = new Texture(Gdx.files.internal(SupaCooper.HEALTH_BAR_IMG));
		switch(selectedWeapon){
		case(0):
			this.menuTexture = new Texture(Gdx.files.internal("weapon/smallWeaponSelect.png"));
			break;
		case(1):
			this.menuTexture = new Texture(Gdx.files.internal("weapon/mediumWeaponSelect.png"));
			break;
		case(2):
			this.menuTexture = new Texture(Gdx.files.internal("weapon/largeWeaponSelect.png"));
			break;
		}
	}
	
	public void onDraw(){
		this.x_pos = Gdx.graphics.getWidth()-100;
		this.y_pos = Gdx.graphics.getHeight()-220;
		
		menuBatch.begin();
	
		menuBatch.draw(menuBarTexture,
						  (float)(Gdx.graphics.getWidth()-302),
						  (float)(Gdx.graphics.getHeight()-230),
						  256/2, 256/2,
						  256, 256,
						  this.x_scale*1.7f, this.y_scale,
						  0f,
						  0, 0,
						  256, 256,
						  false, false);
		
		menuBatch.draw(menuTexture,
						  this.x_pos, this.y_pos,
						  128/2, 256/2,
						  128, 256,
						  this.x_scale, this.y_scale,
						  0f,
						  0, 0,
						  128, 256,
						  false, false);
		
		menuBatch.draw(healthBarTexture,
						  (float)(Gdx.graphics.getWidth()-317),
						  (float)(Gdx.graphics.getHeight()-29),
						  0, 0,
						  32, 32,
						  (float)(((this.myGame.levelScreen.player.x_scale-0.26)*1.85185)*7.2), 0.27f,
						  0f,
						  0, 0,
						  32, 32,
						  false, false);
		
		menuBatch.enableBlending();
		menuBatch.end();
		
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
		if(isSmallWeaponSelected(x,y)) {
			this.weaponSelectSoundEffect.play();
			this.selectedWeapon = 0;
			this.menuTexture = new Texture(Gdx.files.internal("weapon/smallWeaponSelect.png"));
			return true;
			
		} else if(isMediumWeaponSelected(x, y)) {
			this.weaponSelectSoundEffect.play();
			this.selectedWeapon = 1;
			this.menuTexture = new Texture(Gdx.files.internal("weapon/mediumWeaponSelect.png"));
			return true;
			
		} else if(isLargeWeaponSelected(x, y)) {
			this.weaponSelectSoundEffect.play();
			this.selectedWeapon = 2;
			this.menuTexture = new Texture(Gdx.files.internal("weapon/largeWeaponSelect.png"));
			return true;
			
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}
	
	public boolean isSmallWeaponSelected(int x, int y){
		if(x>Gdx.graphics.getWidth()-52 && y<48) {
			return true;
		}
		return false;
	}
	
	public boolean isMediumWeaponSelected(int x, int y){
		if(x>Gdx.graphics.getWidth()-52 && y<112 && y>48) {
			return true;
		}
		return false;
	}
	
	public boolean isLargeWeaponSelected(int x, int y){
		if(x>Gdx.graphics.getWidth()-61 && y<178 && y>112) {
			return true;
		}
		return false;
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

}
