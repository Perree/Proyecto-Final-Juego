package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.sprites.Fumiko;

public class Pinches extends ObjetosInteractivos{

	public Pinches(PantallaJuego screen, MapObject object) {
		super(screen, object);
		fixture.setUserData(this);
		setCategoryFilter(Main.PINCHES_BIT);
	}

	@Override
	public void contactColision(Fumiko fumiko) {
		Gdx.app.log("Pinches", "Collision");
		setCategoryFilter(Main.DESTROYED_BIT);
		
		//sonido
		//Main.manager.load("audio/sfx/muere.wav", Sound.class);
	}

}
