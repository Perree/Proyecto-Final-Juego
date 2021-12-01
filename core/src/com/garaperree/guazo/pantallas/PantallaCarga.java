package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.utiles.Imagen;
import com.garaperree.guazo.utiles.Recursos;
import com.garaperree.guazo.utiles.Render;

public class PantallaCarga implements Screen{
	
	Imagen fondo;
	SpriteBatch b;

	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU);
		b = Render.batch;
	}

	@Override
	public void render(float delta) {
		Render.limpairPantalla();
		b.begin();
			fondo.dibujar();
		b.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
