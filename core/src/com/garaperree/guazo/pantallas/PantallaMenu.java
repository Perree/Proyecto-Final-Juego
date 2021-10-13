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

public class PantallaMenu implements Screen {
	Imagen fondo;
	SpriteBatch b;
	
	Texto opciones[] = new Texto[4];
	String textos[] = {"Nueva partida","Online","Opciones","salir"};

	int opc = 1;
	
	public float tiempo = 0;

	Entradas entradas = new Entradas(this);

	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.batch;

		Gdx.input.setInputProcessor(entradas);
		int avance = 30;

		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTEMENU, 60, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
					((Config.ALTO / 2) + (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto() * i) * (avance * i)));
		}

	}

	@Override
	public void render(float delta) {
		b.begin();
		fondo.dibujar();
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		b.end();

		tiempo += delta;
		
		if (entradas.isAbajo()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc++;
				if (opc > 4) {
					opc = 1;
				}
			}

		}
		if (entradas.isArriba()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc--;
				if (opc < 1) {
					opc = 4;
				}
			}

		}
		
		for (int i = 0; i < opciones.length; i++) {
			if(i==(opc-1)) {
				opciones[i].setColor(Color.YELLOW);
			}else {
			opciones[i].setColor(Color.WHITE);	
			}
			
		}
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
