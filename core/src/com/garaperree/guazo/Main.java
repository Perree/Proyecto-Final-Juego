package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	SpriteBatch batch;
	Fumiko fumiko;
	Texture img;
	
	public void show() {
		
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Biker_hurt.png");
//		fumiko = new Fumiko(100,100,200,200);
	}

	@Override
	public void render () {
		
		batch.begin();
		batch.draw(img, 0, 0);
//		fumiko.dibujar(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
