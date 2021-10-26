package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.utiles.Render;

public class Main extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PantallaJuego());
		

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
