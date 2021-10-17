package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.utiles.Config;

public abstract class PantallaMenu extends InputAdapter implements Screen {
	
	public Main game;
	
	public OrthographicCamera oCam;
	public SpriteBatch b;
	public Stage stage;
	
	public PantallaMenu(Main game) {
		this.game = game;
		
		stage = new Stage(new StretchViewport(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
		
		oCam = new OrthographicCamera(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		oCam.position.set(Config.SCREEN_WIDTH/2f, Config.SCREEN_HEIGHT/2f, 0);
		
		InputMultiplexer input = new InputMultiplexer(this, stage);
		Gdx.input.setInputProcessor(input);
		
		b = new SpriteBatch();
	}
	
//	Imagen fondo;
//	
//	
//	private personajePrincipal i, j, k;
//	
//	Texto opciones[] = new Texto[4];
//	String textos[] = {"Nueva partida","Online","Opciones","salir"};
//
//	int opc = 1;
//	
//	public float tiempo = 0;
//
//	Entradas entradas = new Entradas(this);
	
	@Override
	public void render(float delta) {
		update(delta);
		
		stage.act(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		draw(delta);
		
		stage.draw();
		
		
//		b.begin();
//		
//		i.render(b);
//		j.render(b);
//		k.render(b);
//		
////		fondo.dibujar();
////		for (int i = 0; i < opciones.length; i++) {
////			opciones[i].dibujar();
////		}
//		b.end();
//
//		tiempo += delta;
//		
//		if (entradas.isAbajo()) {
//			if (tiempo > 0.1f) {
//				tiempo = 0;
//				opc++;
//				if (opc > 4) {
//					opc = 1;
//				}
//			}
//
//		}
//		if (entradas.isArriba()) {
//			if (tiempo > 0.1f) {
//				tiempo = 0;
//				opc--;
//				if (opc < 1) {
//					opc = 4;
//				}
//			}
//
//		}
//		
//		for (int i = 0; i < opciones.length; i++) {
//			if(i==(opc-1)) {
//				opciones[i].setColor(Color.YELLOW);
//			}else {
//			opciones[i].setColor(Color.WHITE);	
//			}
//			
//		}
	}
	
	public abstract void draw(float delta);
	
	public abstract void update(float delta);
	

	@Override
	public void show() {
		
//		i = new personajePrincipal(100, 100);
//		j = new personajePrincipal(400, 150);
//		k = new personajePrincipal(200, 200);
//		
//		fondo = new Imagen(Recursos.FONDOMENU);
//		fondo.setSize(Config.ANCHO, Config.ALTO);
//		b = Render.batch;
//
//		Gdx.input.setInputProcessor(entradas);
//		int avance = 50;
//
//		for (int i = 0; i < opciones.length; i++) {
//			opciones[i] = new Texto(Recursos.FUENTEMENU, 60, Color.WHITE, true);
//			opciones[i].setTexto(textos[i]);
//			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2),
//					((Config.ALTO / 2) + (opciones[0].getAlto() / 2)) - ((opciones[i].getAlto()+(avance * i))));
//		}

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
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
