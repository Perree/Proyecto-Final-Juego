package com.garaperree.guazo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Imagen {
	private Texture t;
	private Sprite s;
	
	public Imagen(String ruta) {
		t= new Texture(ruta);
		s = new Sprite(t);
	}
	
	public void dibujar() {
		s.draw(Render.batch);
	}
	
	
}
