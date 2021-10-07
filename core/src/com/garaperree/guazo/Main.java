package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.pantallas.PantallaCarga;
import com.garaperree.guazo.utiles.Render;

public class Main extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaCarga());
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
