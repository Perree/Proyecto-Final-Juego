package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Meta extends ObjetosInteractivos{

	public Meta(PantallaJuego screen, MapObject object) {
		super(screen, object);
		fixture.setUserData(this);
		setCategoryFilter(Main.META_BIT);
	}

	@Override
	public void contactColision() {
		Gdx.app.log("Meta", "Collision");
		setCategoryFilter(Main.DESTROYED_BIT);
		//sonido
//		Main.manager.get("audio/sounds/next_level.wav", Sound.class).play();
	}

}
