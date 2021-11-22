package com.garaperree.guazo.escenas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;

public class Hud implements Disposable{
	
	// Stage maneja la ventana gráfica (Viewport) y distribuye los eventos de entrada.
	public Stage stage; 
	// Administra una cámara y determina cómo se asignan las coordenadas mundiales hacia y desde la pantalla.
	private Viewport viewport; 
	
	// Marcadores y tiempos
	private Integer worldTimer;
	private Integer score;
	private float timeCount;
	
	
	// Scene2d widgets
	private Label countdownLabel;
	private Label scoreLabel;
	private Label timeLabel;
	private Label levelLabel;
	private Label worldLabel;
	
	public Hud(SpriteBatch sb) {
		
		//TODO como hacer para que el hud se quede quieto
		
		// Variables
		worldTimer = 300;
		timeCount = 0;
		score = 0;

		viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb); 
		
		Table table = new Table(); 
		table.top();
		table.setFillParent(true);
		
		//TODO boton de salir y boton de pausa
		
		countdownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("TIEMPO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		worldLabel = new Label("NIVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		// agregar los labels, padding top
		table.add(worldLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		
		// agrega una segundo fila
		table.row();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();		
		
		//agrega tabla al stage
		stage.addActor(table);
		
	}
	
	public void update(float dt) {
		// Disminuyendo el tiempo
		timeCount += dt;
		if(timeCount>= 1) {
			worldTimer--;
			countdownLabel.setText(String.format("%03d",worldTimer));
			timeCount = 0;
		}
	}
	
	public void addScore(int value){
		score += value;
		scoreLabel.setText(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		
	}
	
}
