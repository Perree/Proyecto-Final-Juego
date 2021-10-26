package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.utiles.Render;

public class Main extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaJuego(this));
		

	}

	@Override
	public void render () {
		super.render();
		
	}
	
	private void update() {
	
		}

	@Override
	public void dispose () {
		Render.batch.dispose();
	}
}
