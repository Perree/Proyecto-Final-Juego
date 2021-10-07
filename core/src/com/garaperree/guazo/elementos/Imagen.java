package com.garaperree.guazo.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.garaperree.guazo.utiles.Render;

public class Imagen {

	private Texture tex;
	private Sprite spr;
	
	public Imagen(String ruta){
		tex = new Texture(ruta);
		spr = new Sprite(tex);
	}
	
	public void dibujar(){
	spr.draw(Render.batch);
	}
	
	public void setTransparencia(float a) {
		spr.setAlpha(a);
	}
}

