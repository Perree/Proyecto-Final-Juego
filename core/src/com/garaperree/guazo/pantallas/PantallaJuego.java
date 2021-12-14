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
import com.garaperree.guazo.utiles.Render;
import com.garaperree.guazo.utiles.Utiles;

public class PantallaJuego implements Screen, JuegoEventListener {
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
	private boolean finJuego = false;
	private int jugador = 0;
	private int juga;

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

	public PantallaJuego(Main game) {
		this.game = game;

		// Crea una camara para seguir al personaje a traves del mundo
		gamecam = new OrthographicCamera();

		// Crea un FitViewport para mantenar el aspecto virtual
		gamePort = new FitViewport(Main.V_WIDTH / Main.PPM, Main.V_HEIGHT / Main.PPM, gamecam);

		// Crea un hud para puntos/tiempos/niveles
		hud = new Hud(game.batch);

		// cargando el mapa
		maploader = new TmxMapLoader();
		map = maploader.load("mapas/nivel1/nivel1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);

		// Inicializando la camara del juego para poder estar centrado al comienzo
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

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
		espera.setPosition((Main.V_WIDTH / 2) - (espera.getAncho() / 2), (Main.V_HEIGHT / 2) + (espera.getAlto() / 2));

		Utiles.listener = this;

		Gdx.input.setInputProcessor(teclas);

		// Hilo cliente
		cliente = new Cliente();
		cliente.enviarMensaje("Conexion");
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	@Override
	public void show() {
	}

	public void update(float dt) {
		jugador1.update(dt);
		jugador2.update(dt);
		hud.update(dt);

		// Actualiza la camara del juego con las coordenadas correctas despues de hacer
		// los cambios
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

	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		if (!empieza) {
			Render.begin();
			espera.dibujar();
			Render.end();
		} else {
			// Separa la actualizacion logica del renderizado
			update(delta);

			// Limpiar pantalla con negro
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			// Renderizar el mapa del juego
			renderer.render();

			game.batch.setProjectionMatrix(gamecam.combined);
			game.batch.begin();
			jugador1.draw(game.batch);
			jugador2.draw(game.batch);
			game.batch.end();

			// Setea el batch para dibujar el hud
			game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
			hud.stage.draw();
			
			if (finJuego) {
				if(juga==1) {
					game.setScreen(new PerdioJuego(game));
					dispose();
				}
				if(juga==2){
					game.setScreen(new FinDelJuego(game));
					dispose();
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// Actualizar nuestro viewport game
		gamePort.update(width, height);
	}

	public TiledMap getMap() {
		return map;
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

		if (keycode == Keys.UP) {
			cliente.enviarMensaje("NoApretoArriba");
		}

		if (keycode == Keys.LEFT) {
			cliente.enviarMensaje("NoApretoIzquierda");
		}

		if (keycode == Keys.RIGHT) {
			cliente.enviarMensaje("NoApretoDerecha");
		}
	}

	@Override
	public void keyDown(int keycode) {
		if (keycode == Keys.UP) {
			cliente.enviarMensaje("ApretoArriba");
		}

		if (keycode == Keys.LEFT) {
			cliente.enviarMensaje("ApretoIzquierda");
		}

		if (keycode == Keys.RIGHT) {
			cliente.enviarMensaje("ApretoDerecha");
		}
	}

	@Override
	public void asignarJugador(int jugador) {
		this.jugador = jugador;
	}

	@Override
	public void asignarPos(int nroJugador, float x, float y) {
		if (nroJugador == 1) {
			jugador1.setPosition(x, y);
		} else {
			jugador2.setPosition(x, y);
		}
	}

	@Override
	public void actualizarAnimacion(int nroJugador, String Animacion) {
		if (nroJugador == 1) {
			jugador1.setCurrentState(Animacion);
		} else {
			jugador2.setCurrentState(Animacion);
		}

	}

	@Override
	public void actualizarTiempo(int tiempo) {
		hud.setWorldTimer(tiempo);
	}

	@Override
	public void terminoJuego(int nroJugador) {
		if (nroJugador == this.jugador) {
			juga=1;
		} else {
			juga=2;
		}
		
		finJuego = true;		
	}

	@Override
	public void ganoJuego(int nroJugador) {
		if (nroJugador == this.jugador) {
			juga=2;
		} else {
			juga=1;
		}

		finJuego = true;
	}
}
