package com.garaperree.guazo.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class personajePrincipal {
	
	private int x, y;
	private Animation animation;
	private float tiempo;
	private TextureRegion [] regionMovimientos;
	private Texture imagen;
	private TextureRegion frameActual;
	private Sprite sprite;
	
	
	public personajePrincipal(int x, int y) {
		this.x = x;
		this.y = y;
		
		//cargar la imagen
		sprite = new Sprite (new Texture(Gdx.files.internal("Biker_run.png")), 48, 48);
		TextureRegion [][] tmp = TextureRegion.split(imagen, imagen.getWidth(), imagen.getHeight());
		
		regionMovimientos = new TextureRegion[6];
		for (int i = 0; i < 8; i++) regionMovimientos[i] = tmp[0][i];
		
		animation = new Animation(1,regionMovimientos);
		
		tiempo = 0f;
	}



	public void render(final SpriteBatch batch) {
		tiempo += Gdx.graphics.getDeltaTime();
		frameActual = (TextureRegion) animation.getKeyFrame(tiempo,true);
		batch.draw(sprite, x, y);
	}
}
