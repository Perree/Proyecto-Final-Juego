package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Meta extends ObjetosInteractivos{
	
	private boolean meta = false;

	public Meta(PantallaJuego screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(Main.META_BIT);
	}

	@Override
	public void contactColision() {
		Gdx.app.log("Meta", "Collision");
		System.out.println("Ganaste boludo!");
		meta = true;
		setCategoryFilter(Main.DESTROYED_BIT);
		//sonido
//		Main.manager.get("audio/sounds/next_level.wav", Sound.class).play();
	}
	
	public boolean isMeta() {
		return meta;
	}

}
