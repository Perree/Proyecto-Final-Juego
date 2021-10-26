package com.garaperree.guazo.escenas;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;

public class Hud {
	public Stage stage;
	private Viewport viewport;
	
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label marioLabel;
	
	public Hud(SpriteBatch sb) {
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
	}
}
