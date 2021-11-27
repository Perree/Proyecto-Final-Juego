package com.garaperree.guazo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Pinches extends ObjetosInteractivos{

	public Pinches(PantallaJuego screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(Main.PINCHES_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Pinches", "Collision");
		
		
		//sonido
		//Main.manager.load("audio/sfx/muere.wav", Sound.class);
	}

}
