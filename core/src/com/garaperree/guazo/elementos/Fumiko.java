package com.garaperree.guazo.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fumiko {

		public Texture textura;
		public Sprite spr;
		public float alto,ancho;
		public float x,y;
		//codigo de miche
		float personajeSpeed = 10.0f;
		
		public Fumiko(float x, float y, float ancho, float alto) {
			textura = new Texture("Fumiko.jpg");
			spr = new Sprite(textura);
			this.x = x;
			this.y = y;
			this.alto = alto;
			this.ancho = ancho;
		}

		public void dibujar(SpriteBatch batch) {
			spr.draw(batch);
		}
		
		public void Render() {
			//codigo de miche
			if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
	              x -= Gdx.graphics.getDeltaTime() * personajeSpeed;
	              System.out.println("Izquierda");
	         }else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
	              x += Gdx.graphics.getDeltaTime() * personajeSpeed;
	              System.out.println("Derecha");
	         }else if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
	              y += Gdx.graphics.getDeltaTime() * personajeSpeed;
	              System.out.println("Arriba");
	         }else if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
	              y -= Gdx.graphics.getDeltaTime() * personajeSpeed;
	              System.out.println("Abajo");
	           }
		}
	}
	

