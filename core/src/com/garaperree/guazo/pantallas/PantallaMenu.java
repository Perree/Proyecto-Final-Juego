package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.elementos.Imagen;
import com.garaperree.guazo.utiles.Config;
import com.garaperree.guazo.utiles.Recursos;
import com.garaperree.guazo.utiles.Render;

public class PantallaMenu implements Screen{
	Imagen fondo;
	SpriteBatch b;
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.batch;
	}

	@Override
	public void render(float delta) {
		b.begin();
		fondo.dibujar();
		b.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}
	

}
