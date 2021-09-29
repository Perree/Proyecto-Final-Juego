package com.garaperree.guazo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	SpriteBatch batch;
	Fumiko fumiko;
	Texture img;
	int cont=0, cont2=0;
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Biker_hurt.png");
//		fumiko = new Fumiko(100,100,200,200);
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cont++;
		cont2++;
		batch.begin();
		batch.draw(img, cont, cont2, 100, 200);
//			fumiko.dibujar(batch);
		batch.end();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
