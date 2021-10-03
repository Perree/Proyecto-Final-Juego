package com.garaperree.guazo.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fumiko {

		public Texture textura;
		public Sprite spr;
		public float alto,ancho;
		public float x,y;
		
		public Fumiko(float x, float y, float ancho, float alto) {
			textura = new Texture("Fumiko.jpg");
			spr = new Sprite(textura);
			this.x = x;
			this.y = y;
			this.alto = alto;
			this.ancho = ancho;}

		public void dibujar(SpriteBatch batch) {
			spr.draw(batch);
			}
	}
	

