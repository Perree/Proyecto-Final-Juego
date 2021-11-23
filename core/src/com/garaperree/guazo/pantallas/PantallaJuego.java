package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.escenas.Hud;
import com.garaperree.guazo.sprites.Fumiko;
import com.garaperree.guazo.utiles.B2WorldCreator;
import com.garaperree.guazo.utiles.WorldContactListener;

public class PantallaJuego implements Screen{

	//Referenciar a nuestro Juego, para setear las pantallas
	private Main game;
	private TextureAtlas atlas;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	public Hud hud;
	
	// Variables del Tiled map
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer; // dibuja el mapa
	
	//Variables de Box2d
	private World world;
	private Box2DDebugRenderer b2dr;
	
	//Referenciar a nuestro personaje principal (sprites)
	private Fumiko fumiko;
	
	private Music music;
	
	public PantallaJuego(Main game) {
		
		atlas = new TextureAtlas("personaje.atlas");
		
		this.game = game;
		
		// Crea una camara para seguir al personaje a travez del mundo
		gamecam = new OrthographicCamera(); 
		
		// Crea un FitViewport para mantenar el aspecto virtual
		gamePort = new FitViewport(Main.V_WIDTH/Main.PPM, Main.V_HEIGHT/Main.PPM, gamecam);
		
		// Crea una hud para puntos/tiempos/niveles
		hud = new Hud(game.batch); 
		
		//cargando el mapa
		maploader = new TmxMapLoader();
		map = maploader.load("nivel1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1/Main.PPM);
		
		// inicializando la camara del juego para poder estar centrado al comienzo
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		// Crea el box2d world, sin gravedad en el eje x, -10 en el eje, permitiendo que los cuerpos descansen
		world = new World(new Vector2(0, -15), true);
		
		// Permite hacer los debugs de nuestro box2d world
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		// crear Fumiko en nuestro juego
		fumiko = new Fumiko(world, this);
		
		world.setContactListener(new WorldContactListener());
		
		music = Main.manager.get("audio/music/MatWyre_Deep_Dawn.mp3", Music.class);
		music.setLooping(true);
		music.play();
		
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	@Override
	public void show() {
		
		
	}
	
	//mover la posicion de la camara hacia la derecha
	private void handleInput(float dt) {
		
		// controlar a nuestro jugador mediante impulsos
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
			fumiko.b2body.applyLinearImpulse(new Vector2(0, 4f), fumiko.b2body.getWorldCenter(), true);
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && fumiko.b2body.getLinearVelocity().x <=2)
			fumiko.b2body.applyLinearImpulse(new Vector2(0.1f, 0),fumiko.b2body.getWorldCenter(), true);
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && fumiko.b2body.getLinearVelocity().x >=-2)
			fumiko.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),fumiko.b2body.getWorldCenter(), true);
	}
	
	public void update(float dt) {
		// maneja la entradas del usuario
		handleInput(dt);
		 
		// toma 1 paso en fisicas (60 veces por segundo)
		world.step(1/60f, 6, 2);
		
		fumiko.update(dt);
		hud.update(dt);
		
		gamecam.position.x = fumiko.b2body.getPosition().x;
		
		//actualiza la camara del juego con las coordenadas correctas despues de hacer los cambios
		gamecam.update();
		
		//renderiza lo que la camara puede ver
		renderer.setView(gamecam);
	}


	@Override
	public void render(float delta) {
		//separa la actualizacion logica del renderizado
		update(delta);
		
		// Limpear pantalla con negro
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		//renderizar el mapa del juego
		renderer.render();
		
		//renderizar Box2DDebugRenderer
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		fumiko.draw(game.batch);
		game.batch.end();
		
		// setea el batch para dibujar el hud
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//actualizar nuestro viewport game
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
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	}
	
	
}


