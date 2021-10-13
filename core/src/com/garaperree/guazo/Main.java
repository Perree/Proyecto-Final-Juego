package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.pantallas.PantallaMenu;
import com.garaperree.guazo.utiles.Render;

public class Main extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaMenu());

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
