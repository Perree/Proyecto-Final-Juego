package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.pantallas.PantallaCarga;
import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.sprites.Render;

public class Main extends Game {
	public static final int V_WIDTH = 1024;
	public static final int V_HEIGHT = 768;
	public static final float PPM = 100;
	
	public static final short DEFAULT_BIT = 1;
	public static final short FUMIKO_BIT = 2;
	public static final short META_BIT = 4;
	public static final short PINCHES_BIT = 8;
	public static final short DESTROYED_BIT = 12;	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
//		this.setScreen(new PantallaCarga());
		setScreen(new PantallaJuego(this));
		

	}

	@Override
	public void render () {
		// delegar el metodo de render para la pantalla del juego
		super.render(); 
		
	}
}
