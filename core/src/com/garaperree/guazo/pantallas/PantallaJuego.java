package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.utiles.Config;

public class PantallaJuego implements Screen{
	
	private Main game; 
	Texture texture;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	
	public PantallaJuego(Main game) {
		this.game = game;
		texture = new Texture("Biker_run.png");
		gamecam = new OrthographicCamera(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		gamePort = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, gamecam);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Limpear pantalla
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		game.batch.draw(texture, 0, 0);
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
		
	}


