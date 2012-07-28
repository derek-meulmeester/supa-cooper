package com.supacooper.screens;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supacooper.SupaCooper;
import com.supacooper.objects.Button1;

public class MainMenuScreen implements Screen, InputProcessor {

	SupaCooper myGame;
	private SpriteBatch spriteBatch;
	private Texture backgroundTexture;
	private Music music;
	private Sound helloSound;
	private float currTime;
	
	private boolean screenShowing = false;
	
	Button1 playButton;
	Button1 tutorialButton;
	Button1 aboutButton;
	
	public MainMenuScreen(SupaCooper newSupaCooper) {
		myGame = newSupaCooper;
		backgroundTexture = new Texture(Gdx.files.internal("images/backgrounds/mainmenubackground.png"));
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("sounds/mainMenuSong.mp3", FileType.Internal));
		helloSound = Gdx.audio.newSound(Gdx.files.getFileHandle("sounds/voiceImSupa.mp3", FileType.Internal));
		spriteBatch = new SpriteBatch();
		
		int screenWidth = Gdx.graphics.getWidth();
		int xShift = 50;
		this.currTime =  0;
		
		Texture upTexture = new Texture(Gdx.files.internal("images/buttons/playUp.png"));
		Texture downTexture = new Texture(Gdx.files.internal("images/buttons/playDown.png"));
		playButton = new Button1(xShift, 0, upTexture, downTexture, SupaCooper.PLAY, myGame);
		playButton.scale(0.8f);
		playButton.reduceWidth(60);
		
		upTexture = new Texture(Gdx.files.internal("images/buttons/tutorialUp.png"));
		downTexture = new Texture(Gdx.files.internal("images/buttons/tutorialDown.png"));
		tutorialButton = new Button1(xShift + (int) screenWidth/3, 0, upTexture, downTexture, SupaCooper.TUTORIAL, myGame);
		tutorialButton.scale(0.8f);
		tutorialButton.reduceWidth(60);
		
		upTexture = new Texture(Gdx.files.internal("images/buttons/aboutUp.png"));
		downTexture = new Texture(Gdx.files.internal("images/buttons/aboutDown.png"));
		aboutButton = new Button1(xShift + (int) screenWidth*2/3, 0, upTexture, downTexture, SupaCooper.ABOUT, myGame);
		aboutButton.scale(0.8f);
		aboutButton.reduceWidth(60);
	}
	

	@Override
	public void show() {
		music.setVolume(0.2f);
		music.setLooping(true);
		music.play();
		this.screenShowing = true;
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
        
        playButton.render();
        aboutButton.render();
        tutorialButton.render();
        currTime += Gdx.graphics.getDeltaTime();
        if(currTime > 0.7) {
	        if(myGame.init) {
				myGame.init = false;
				helloSound.play(20f);
			}
        }
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		this.screenShowing = false;
		music.pause();

	}

	@Override
	public void pause() {
		music.pause();

	}

	@Override
	public void resume() {
		music.play();

	}

	@Override
	public void dispose() {
		music.dispose();

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
		if(this.screenShowing) {
			playButton.touchDown(x, y, pointer, button);
			tutorialButton.touchDown(x, y, pointer, button);
			aboutButton.touchDown(x, y, pointer, button);
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(this.screenShowing) {
			playButton.touchUp(x, y, pointer, button);
			tutorialButton.touchUp(x, y, pointer, button);
			aboutButton.touchUp(x, y, pointer, button);
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
