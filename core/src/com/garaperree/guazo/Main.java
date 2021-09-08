package com.garaperree.guazo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.garaperree.guazo.personajes.Fumiko;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Fumiko fumiko;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fumiko = new Fumiko(100,100,200,200);
	}

	@Override
	public void render () {
		
		batch.begin();
			fumiko.dibujar(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
