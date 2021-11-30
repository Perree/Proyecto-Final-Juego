package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Main extends Game {
	// Dimensiones pixeles por metros
	public static final int V_WIDTH = 1024;
	public static final int V_HEIGHT = 768;
	public static final float PPM = 100;
	
	//Box2D Collision Bits
	public static final short NOTHING_BIT = 0;
	public static final short DEFAULT_BIT = 1;
	public static final short FUMIKO_BIT = 2;
	public static final short META_BIT = 4;
	public static final short PINCHES_BIT = 8;
	public static final short LAVA_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short OBJECT_BIT = 64;
	public static final short FUMIKO_DERECHA_BIT = 128;
	public static final short FUMIKO_IZQUIERDA_BIT = 256;
	public static final short FUMIKO_ABAJO_BIT = 512;
	
	
	public SpriteBatch batch;
	
	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/MatWyre_Deep_Dawn.mp3", Music.class);
//		manager.load("audio/sfx/muere.wav", Sound.class);
//		manager.load("audio/sfx/next_level.wav", Sound.class);
		manager.finishLoading();
		
//		this.setScreen(new PantallaCarga());
		setScreen(new PantallaJuego(this));
	}

	@Override
	public void render () {
		// delegar el metodo de render para la pantalla del juego
		super.render(); 

	}
}
