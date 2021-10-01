package com.garaperree.guazo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.garaperree.guazo.utiles.Render;

public class Fumiko {

	public Texture textura;
	public Sprite spr;

	public Fumiko(float x, float y, float ancho, float alto) {
		textura = new Texture("Biker_hurt.png");
		spr = new Sprite(textura);
		spr.setPosition(x, y);
		spr.setSize(ancho, alto);
	}
	

	public void dibujar(SpriteBatch batch) {
		spr.draw(Render.batch);
	}
	
	public void setX(float x) {
		spr.setX(x);
	}

	public void setY(float y) {
		spr.setX(y);
	}
}
