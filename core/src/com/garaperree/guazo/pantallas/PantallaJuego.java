package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.escenas.Hud;

public class PantallaJuego implements Screen{

	private Main game; 
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	public Hud hud;
	
	//*** Para el mapa
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer; // dibuja el mapa
	
	
	public PantallaJuego(Main game) {
		this.game = game;
		gamecam = new OrthographicCamera(); // Crea una camara para seguir al personaje a travez del mundo
		gamePort = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, gamecam);
		hud = new Hud(game.batch); // Crea una hud para puntos/tiempos/niveles
		
		//cargando el mapa
		maploader = new TmxMapLoader();
		map = maploader.load("MapaNivel1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
	}
	
	@Override
	public void show() {
		
		
	}
	
	private void handleInput(float dt) {
		if(Gdx.input.isTouched())
			gamecam.position.x += 100 * dt;
	}
	
	public void update(float dt) {
		handleInput(dt);
		
		gamecam.update();
		renderer.setView(gamecam);
	}


	@Override
	public void render(float delta) {
		update(delta);
		
		// Limpear pantalla con negro
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		
		renderer.render();
		
		//Fijar el batch para dibujar el hud
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
		game.batch.begin();
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


