package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.utiles.Render;

public class Main extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		Render.Batch = new SpriteBatch();
	}

	@Override
	public void render () {
		
		
	}
	
	private void update() {
	
		}

	@Override
	public void dispose () {
		Render.Batch.dispose();
	}
}
