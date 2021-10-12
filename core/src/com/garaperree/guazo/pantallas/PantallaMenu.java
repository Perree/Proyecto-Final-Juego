package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.elementos.Imagen;
import com.garaperree.guazo.elementos.Texto;
import com.garaperree.guazo.io.Entradas;
import com.garaperree.guazo.utiles.Config;
import com.garaperree.guazo.utiles.Recursos;
import com.garaperree.guazo.utiles.Render;

public class PantallaMenu implements Screen{
	Imagen fondo;
	SpriteBatch b;
	Texto o1,o2,o3,o4;
	
    
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.batch;

		Gdx.input.setInputProcessor(new Entradas());
		
		o1 = new Texto(Recursos.FUENTEMENU,60,Color.YELLOW,true);
		o1.setTexto("Nueva partida");
	
		float posInY = (Config.ALTO/2)-(o1.getAlto()/2);
		float avance = o1.getAlto()+40;
		
		
		o1.setPosition((Config.ANCHO/2)-(o1.getAncho()/2),posInY);
		
		
		o2 = new Texto(Recursos.FUENTEMENU,60,Color.WHITE,true);
		o2.setTexto("Online");
		o2.setPosition((Config.ANCHO/2)-(o2.getAncho()/2),posInY-(avance*1));
		
		o3 = new Texto(Recursos.FUENTEMENU,60,Color.WHITE,true);
		o3.setTexto("Opciones");
		o3.setPosition((Config.ANCHO/2)-(o3.getAncho()/2),posInY-(avance*2));
		
		o4 = new Texto(Recursos.FUENTEMENU,60,Color.WHITE,true);
		o4.setTexto("Salir");
		o4.setPosition((Config.ANCHO/2)-(o4.getAncho()/2),posInY-(avance*3));
		
	}

	@Override
	public void render(float delta) {
		b.begin();
		fondo.dibujar();
			o1.dibujar();
			o2.dibujar();
			o3.dibujar();
			o4.dibujar();
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
