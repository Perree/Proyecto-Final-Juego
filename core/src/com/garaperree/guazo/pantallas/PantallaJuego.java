package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.cliente.Cliente;
import com.garaperree.guazo.diseños.Recursos;
import com.garaperree.guazo.diseños.Texto;
import com.garaperree.guazo.escenas.Hud;
import com.garaperree.guazo.io.JuegoEventListener;
import com.garaperree.guazo.io.KeyListener;
import com.garaperree.guazo.sprites.Fumiko;
import com.garaperree.guazo.sprites.Objetos.B2WorldCreator;
import com.garaperree.guazo.utiles.Render;
import com.garaperree.guazo.utiles.Utiles;
import com.garaperree.guazo.utiles.WorldContactListener;

public class PantallaJuego implements Screen, JuegoEventListener{
	// Referenciar a nuestro personaje principal (sprites)
	private Fumiko jugador1, jugador2;
	
	// Control de las teclas
	private KeyListener teclas;
	
	// Diseños
	private Texto espera;
	
	// Referenciar a nuestro Juego, para setear las pantallas
	private Main game;
	private TextureAtlas atlas;
	
	// Booleanos para la red
	private boolean empieza = false;
	private int jugador = 0;
	
	// Red
	private Cliente cliente;
	
	// Control de camara
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	
	// Overlays 
	private Hud hud;
	
	// Variables del Tiled map
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer; // Dibuja el mapa
	
	// Variables de Box2d
	private World world;
	private Box2DDebugRenderer b2dr;
	
	public PantallaJuego(Main game) {
		this.game = game;
		
		// Crea una camara para seguir al personaje a traves del mundo
		gamecam = new OrthographicCamera(); 
		
		// Crea un FitViewport para mantenar el aspecto virtual
		gamePort = new FitViewport(Main.V_WIDTH/Main.PPM, Main.V_HEIGHT/Main.PPM, gamecam);
		
		// Crea un hud para puntos/tiempos/niveles
		hud = new Hud(game.batch); 
		
		//cargando el mapa
		maploader = new TmxMapLoader();
		map = maploader.load("mapas/nivel1/nivel1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1/Main.PPM);	
	
		// Inicializando la camara del juego para poder estar centrado al comienzo
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		// Crea el box2d world, sin gravedad en el eje x, -10 en el eje, permitiendo que los cuerpos descansen
		world = new World(new Vector2(0, -15), true);
		
		// Permite hacer los debugs de nuestro box2d world
		b2dr = new Box2DDebugRenderer();
		
		// Crea los objetos para poder ser colisionados 
		new B2WorldCreator(this);
		
		// Carga las texturas del personaje
		atlas = new TextureAtlas("fumiko/personaje.atlas");
		
		// Crear personajes en nuestro juego
		jugador1 = new Fumiko(this);
		jugador2 = new Fumiko(this);
		
		// Cuando el cliente deja de presionar la tecla 
		teclas = new KeyListener();
		
		// Texto para la conexion
		espera = new Texto(Recursos.FUENTE, 100, Color.WHITE, false);
		espera.setTexto("Conectando...");
		espera.setPosition((Main.V_WIDTH/2)-(espera.getAncho()/2), (Main.V_HEIGHT/2)+(espera.getAlto()/2));
		
		Utiles.listener = this;
		
		Gdx.input.setInputProcessor(teclas);
		
		// Hilo cliente
		cliente = new Cliente();
		cliente.enviarMensaje("Conexion");
		
		// Momento al colisionar
		world.setContactListener(new WorldContactListener());
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
	@Override
	public void show() {
	}
	
	// Controlar jugador
	private void handleInput(float dt) {

		if(teclas.isUp()) {
			if(this.jugador==1 
					&& jugador1.currentState != Fumiko.State.DEAD) {
				jugador1.jump(); // Evitamos un poco el retraso
			}else if(jugador2.currentState != Fumiko.State.DEAD){
				jugador2.jump();
			}
		}
		
		if(teclas.isRight() ) {
			if(this.jugador==1 
					&& jugador1.b2body.getLinearVelocity().x <=2 
					&& jugador1.currentState != Fumiko.State.DEAD) {
				jugador1.right();
			}else if (jugador2.b2body.getLinearVelocity().x <=2 
					&& jugador2.currentState != Fumiko.State.DEAD){
				jugador2.right();
			}
		}
		
		if(teclas.isLeft()) {
			if(this.jugador==1 
					&& jugador1.b2body.getLinearVelocity().x >=-2 
					&& jugador1.currentState != Fumiko.State.DEAD) {
				jugador1.left();
			}else if(jugador2.b2body.getLinearVelocity().x >=-2 
					&& jugador2.currentState != Fumiko.State.DEAD){
				jugador2.left();
			}
		}
		// controlar a nuestro jugador mediante impulsos
//		if(jugador1.currentState != Fumiko.State.DEAD) {
//			if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
//				jugador1.jump();
//			}
//				
//			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && jugador1.b2body.getLinearVelocity().x <=2) {
//				jugador1.b2body.applyLinearImpulse(new Vector2(0.1f, 0),jugador1.b2body.getWorldCenter(), true);
//			}
//			
//			if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && jugador1.b2body.getLinearVelocity().x >=-2) {
//				jugador1.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),jugador1.b2body.getWorldCenter(), true);
//			}	
//		}
	}
	
	public void update(float dt) {
		// Maneja la entradas del usuario
		handleInput(dt);
		 
		// Toma 1 paso en fisicas (60 veces por segundo)
		world.step(1/60f, 6, 2);
		
		jugador1.update(dt);
		jugador2.update(dt);
		hud.update(dt);
		
		jugadorGanaMuere();
		
		// Actualiza la camara del juego con las coordenadas correctas despues de hacer los cambios
		gamecam.update();
		
		// Renderiza lo que la camara puede ver
		renderer.setView(gamecam);
	}

	public Fumiko getJugador1() {
		return jugador1;
	}


	public Fumiko getJugador2() {
		return jugador2;
	}


	private void jugadorGanaMuere() {
		// Cuando el personaje se cae en la lava
		if (jugador1.getY() < 0) {
			jugador1.currentState = Fumiko.State.DEAD;
		}
		
		// Pinches 1
		if((jugador1.getX() > 2.42f && jugador1.getY() >= 4.50f) && 
				(jugador1.getX() <= 2.81f && jugador1.getY() <= 5.15f)) {
			jugador1.currentState = Fumiko.State.DEAD;
		}
		
		// Pinches 2
		if((jugador1.getX() >= 4.9895763 && jugador1.getY() >= 4.98f) && 
				(jugador1.getX() <= 6.335001 && jugador1.getY() <= 4.99f)) {
			jugador1.currentState = Fumiko.State.DEAD;
		}
		
		// Pinches 3
		if((jugador1.getX() >= 5.12f && jugador1.getY() <= 1.5f) && 
				(jugador1.getX() <= 5.55f && jugador1.getY() >= 1.46f)) {
			jugador1.currentState = Fumiko.State.DEAD;
		}
		
		// Usamos la ubicacion del personaje para poder determinar la meta
		if ((jugador1.getX() <= 1.64f && jugador1.getY() >= 1.46f) &&
				(jugador1.getX() >= 1.32f && jugador1.getY() <= 1.6f)) {
			jugador1.llegoSalida();
		}
		
		//Cuando el personaje se cae en la lava
		if (jugador2.getY() < 0) {
			jugador2.currentState = Fumiko.State.DEAD;
		}
		
		// Pinches 1
		if((jugador2.getX() > 2.42f && jugador2.getY() >= 4.50f) && 
				(jugador2.getX() <= 2.81f && jugador2.getY() <= 5.15f)) {
			jugador2.currentState = Fumiko.State.DEAD;
		}
		
		// Pinches 2
		if((jugador2.getX() >= 4.9895763 && jugador2.getY() >= 4.98f) && 
				(jugador2.getX() <= 6.335001 && jugador2.getY() <= 4.99f)) {
			jugador2.currentState = Fumiko.State.DEAD;
		}
		
		// Pinches 3
		if((jugador2.getX() >= 5.12f && jugador2.getY() <= 1.5f) && 
				(jugador2.getX() <= 5.55f && jugador2.getY() >= 1.46f)) {
			jugador2.currentState = Fumiko.State.DEAD;
		}
		
		// Usamos la ubicacion del personaje para poder determinar la meta
		if ((jugador2.getX() <= 1.64f && jugador2.getY() >= 1.46f) &&
				(jugador2.getX() >= 1.32f && jugador2.getY() <= 1.6f)) {
			jugador2.llegoSalida();
		}
	}


	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		if(!empieza) {		
			Render.begin();
			espera.dibujar();
			Render.end();
		}else {
			// Separa la actualizacion logica del renderizado
			update(delta);
			
			// Limpiar pantalla con negro
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
			
			// Renderizar el mapa del juego
			renderer.render();
			
			// Renderizar Box2DDebugRenderer
			b2dr.render(world, gamecam.combined);
			
			game.batch.setProjectionMatrix(gamecam.combined);
			game.batch.begin();
			jugador1.draw(game.batch);
			jugador2.draw(game.batch);
			game.batch.end();
			
			// Setea el batch para dibujar el hud
			game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
			hud.stage.draw();
			
			// Si el tiempo se acaba, se termina el juego
			if(hud.getWorldTimer()==0) {
				acaboTiempo();
			}
			
			// Llego a la meta GANO!
			if(jugador1.isPuedeSalir()) {
				ganadorJugador();
			}
			
			// Llego a la meta GANO!
			if(jugador2.isPuedeSalir()) {
				ganadorJugador();
			}
			
			// El personaje perdio
			if(FinJuego()) {
				perdedor();
			}
		}
	}
	
	// Se termino el tiempo
	private void acaboTiempo() {
		game.setScreen(new AcaboTiempo(game));
		dispose();
	}

	// Un jugador ha muerto y por lo tanto ha ganado el otro
		public void perdedor() {
			game.setScreen(new PerdioJuego(game));
			dispose();
		}
	
	// Un jugador ha ganado
		public void ganadorJugador() {
			game.setScreen(new FinDelJuego(game));
			dispose();
		}
	
	// Se corrobora que si el estado del jugador esta muerto y el tiempo
	public boolean FinJuego() {
		if (jugador1.currentState == Fumiko.State.DEAD) {
			return true;
		}else if (jugador2.currentState == Fumiko.State.DEAD){
			return true;
		}
		return false;
	}
	
	@Override
	public void resize(int width, int height) {
		// Actualizar nuestro viewport game
		gamePort.update(width, height);
	}
	
	public TiledMap getMap() {
		return map;
	}
	
	public World getWorld() {
		return world;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	}
	
	public Hud getHud() {
		return hud;
	}

	@Override
	public boolean handle(Event event) {
		return false;
	}

	@Override
	public void empieza() {
		this.empieza = true;
	}

	@Override
	public void keyUp(int keycode) {
		
		if(keycode==Keys.UP) {
			cliente.enviarMensaje("NoApretoArriba");
		}	
		
		if(keycode==Keys.LEFT) {
			cliente.enviarMensaje("NoApretoIzquierda");
		}
		
		if(keycode==Keys.RIGHT) {
			cliente.enviarMensaje("NoApretoDerecha");
		}	
	}

	@Override
	public void keyDown(int keycode) {
		if(keycode==Keys.UP) {
			cliente.enviarMensaje("ApretoArriba");
		}	
		
		if(keycode==Keys.LEFT) {
			cliente.enviarMensaje("ApretoIzquierda");
		}
		
		if(keycode==Keys.RIGHT) {
			cliente.enviarMensaje("ApretoDerecha");
		}
	}

	@Override
	public void asignarJugador(int jugador) {
		this.jugador = jugador;
	}

	@Override
	public void asignarCoordenadas(int nroJugador, float coordenadas) {
		if(nroJugador==1) {
			jugador1.setY(coordenadas);
//			jugador1.setX(coordenadas);
		}else {
			jugador2.setY(coordenadas);
//			jugador2.setX(coordenadas);
		}
	}

	@Override
	public void terminoJuego(int nroJugador) {
		if(nroJugador==this.jugador) {
			// Ganaste
			game.setScreen(new FinDelJuego(game));
			dispose();
		} else {
			// Perdiste
			game.setScreen(new PerdioJuego(game));
			dispose();
		}
		
	}

	@Override
	public void actualizarTiempo() {
		// TODO actualizar tiempo en los clientes 
		
	}

	@Override
	public void controlarAccion(int nroJugador, String Accion) {
		if(nroJugador==1) {
			if(Accion.equals("saltar")) {
				if(jugador1.currentState != Fumiko.State.DEAD) 
					jugador1.jump();
			}
			if(Accion.equals("izquierda")) { 
					jugador1.left();
			}
			if(Accion.equals("derecha")) {
					jugador1.right();
			}
		}else {
			if(Accion.equals("saltar")) {
				if(jugador2.currentState != Fumiko.State.DEAD) 
					jugador2.jump();
			}
			if(Accion.equals("izquierda")) {
					jugador2.left();
			}
			if(Accion.equals("derecha")) {
					jugador2.right();
			}
		}
	}

	@Override
	public void asignarPos(int nroJugador, float x, float y) {
		if(nroJugador==1) {
			jugador1.setPosition(x, y);
		} else {
			jugador2.setPosition(x, y);
		}

	}
}

