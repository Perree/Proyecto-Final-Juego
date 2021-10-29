package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.escenas.Hud;

public class PantallaJuego implements Screen{

	//Referenciar a nuestro Juego, para setear las pantallas
	private Main game; 
	
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
	
	
	public PantallaJuego(Main game) {
		this.game = game;
		
		// Crea una camara para seguir al personaje a travez del mundo
		gamecam = new OrthographicCamera(); 
		
		// Crea un FitViewport para mantenar el aspecto virtual
		gamePort = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, gamecam);
		
		// Crea una hud para puntos/tiempos/niveles
		hud = new Hud(game.batch); 
		
		//cargando el mapa
		maploader = new TmxMapLoader();
		map = maploader.load("nivel1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		world = new World(new Vector2(0, 0 ), true);
		b2dr = new Box2DDebugRenderer();
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		// Crear el piso
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		// Crear los pinches
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		// Crear lava
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		// Crear meta
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY() + rect.getHeight()/2);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
				
				
		
	}
	
	@Override
	public void show() {
		
		
	}
	
	//mover la posicion de la camara hacia la derecha
	private void handleInput(float dt) {
		if(Gdx.input.isTouched())
			gamecam.position.x += 100 * dt;
	}
	
	public void update(float dt) {
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		gamecam.update();
		
		//renderiza lo que la camara puede ver
		renderer.setView(gamecam);
	}


	@Override
	public void render(float delta) {
		update(delta);
		
		// Limpear pantalla con negro
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		
		//renderizar el mapa del juego
		renderer.render();
		
		//renderizar Box2DDebugRenderer
		b2dr.render(world, gamecam.combined);
		
		//Fijar el batch para dibujar el hud
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
		game.batch.begin();
		game.batch.end();
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
		// TODO Auto-generated method stub
		
	}
	
	
}


