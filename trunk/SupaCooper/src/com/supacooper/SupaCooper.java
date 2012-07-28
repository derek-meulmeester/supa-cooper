package com.supacooper;

import com.badlogic.gdx.Game;
import com.supacooper.screens.AboutScreen;
import com.supacooper.screens.GameCompleteScreen;
import com.supacooper.screens.GameOverScreen;
import com.supacooper.screens.LevelScreen;
import com.supacooper.screens.MainMenuScreen;
import com.supacooper.screens.SplashScreen;
import com.supacooper.screens.TutorialScreen;
import com.supacooper.screens.TutorialScreen2;
import com.supacooper.screens.TutorialScreen3;
import com.supacooper.screens.VictoryScreen;
import com.supacooper.screens.levels.Level1Screen;
import com.supacooper.screens.levels.Level2Screen;
import com.supacooper.screens.levels.Level3Screen;
import com.supacooper.screens.levels.Level4Screen;

public class SupaCooper extends Game {
	
	
	public int SelectedWeapon;
	public float x_scale;
	public float y_scale;
	
	public boolean init = true;
	public int playerScore = 0;
	public int killStreak = 0;
	public int pointMultiplier = 1;
	
	SplashScreen splashScreen;
	MainMenuScreen mainMenuScreen;
	public LevelScreen levelScreen;
	AboutScreen aboutScreen;
	GameOverScreen gameOverScreen;
	VictoryScreen victoryScreen;
	TutorialScreen tutorialScreen;
	TutorialScreen2 tutorialScreen2;
	TutorialScreen3 tutorialScreen3;
	GameCompleteScreen gameCompleteScreen;
	
	public int currLevel = 1;
	
	@Override
	public void create() {
		this.SelectedWeapon = 0;
		this.x_scale = 0.8f;
		this.y_scale = 0.8f;
		
		splashScreen = new SplashScreen(this);
		aboutScreen = new AboutScreen(this);
		gameOverScreen = new GameOverScreen(this);
		tutorialScreen = new TutorialScreen(this);
		tutorialScreen2 = new TutorialScreen2(this);
		tutorialScreen3 = new TutorialScreen3(this);
		victoryScreen = new VictoryScreen(this);
		gameCompleteScreen = new GameCompleteScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		
		setScreen(splashScreen);
		
	}
	
	
	public void setMainMenuScreen() {
		this.x_scale = 0.8f;
		this.y_scale = 0.8f;
		this.SelectedWeapon = 0;
		currLevel = 1;
		setScreen(mainMenuScreen);
	}
	
	public void setLevelScreen(boolean levelPassed) 
	{
		if(levelScreen != null) {
			levelScreen.cleanup();
		}
		if (levelPassed) {
			currLevel++;
		} else {
			if(currLevel == 1) {
				playerScore = 0;
			}
			killStreak = 0;
		}
		
		switch (currLevel) 
		{
			  case 1: {
						levelScreen = new Level1Screen(this); setScreen(levelScreen);
						levelScreen.selectedWeapon=0;
						levelScreen.x_scale = 0.6f; levelScreen.y_scale = 0.6f;
						break;
			} case 2: {
						levelScreen = new Level2Screen(this); setScreen(levelScreen);
						levelScreen.selectedWeapon=this.SelectedWeapon;
						levelScreen.x_scale = this.x_scale; levelScreen.y_scale = this.y_scale;
						break;
			} case 3: {
						levelScreen = new Level3Screen(this); setScreen(levelScreen);
						levelScreen.selectedWeapon=this.SelectedWeapon;
						levelScreen.x_scale = this.x_scale; levelScreen.y_scale = this.y_scale;
						break;
			} case 4: {
						levelScreen = new Level4Screen(this); setScreen(levelScreen);
						levelScreen.selectedWeapon=this.SelectedWeapon;
						levelScreen.x_scale = this.x_scale; levelScreen.y_scale = this.y_scale;
						break;
			} default: setGameCompleteScreen(); break;
		}
		

	}
	
	public void setAboutScreen() {
		setScreen(aboutScreen);
	}

	public void setTutorialScreen() {
		setScreen(tutorialScreen);
	}
	
	public void setTutorialScreen2() {
		setScreen(tutorialScreen2);
	}
	
	public void setTutorialScreen3() {
		setScreen(tutorialScreen3);
	}
	
	public void setGameCompleteScreen() {
		setScreen(gameCompleteScreen);
	}
	public void setVictoryScreen() {
		setScreen(victoryScreen);
	}
	
	public void setGameOverScreen() {
		setScreen(gameOverScreen);
	}
	
	public void performAction(int buttonType) 
	{
		if (buttonType == SupaCooper.PLAY)
		{
			setLevelScreen(false);
		}
		else if (buttonType == SupaCooper.ABOUT)
		{
			setAboutScreen();
		}
		else if (buttonType == SupaCooper.TUTORIAL)
		{
			setTutorialScreen();
		}
	}
	
	/**********************************************
	 * Constants/Image Directory Paths
	 **********************************************/
	public static final int PLAY = 0;
	public static final int TUTORIAL = 1;
	public static final int ABOUT = 2;
	public static final String HELP_ARROW_IMG = "images/introHelp/arrow.png";
	public static final String HELP_FINGER_BOTTOM_IMG = "images/introHelp/finger_Bottom.png";
	public static final String HELP_TAP_TARGET_IMG = "images/introHelp/tap_Target.png";
	public static final String HELP_TAP_MSG_IMG = "images/introHelp/tap_Msg.png";
	public static final String HELP_PULL_BACK_MSG_IMG = "images/introHelp/pull_Msg.png";
	public static final String WEAPON_MENUBAR_IMG = "weapon/weaponMenuBar.png";
	public static final String HEALTH_BAR_IMG = "weapon/healthBar.png";
	public static final String x2_IMG = "score/x2.png";
	public static final String x3_IMG = "score/x3.png";
	public static final String x5_IMG = "score/x5.png";
	public static final String x7_IMG = "score/x7.png";
	public static final String scoreContainer = "score/scoreContainer.png";
	public static final String WEAPON_TRAIL_IMG = "weapon/weaponTrail.png";
}
