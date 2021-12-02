package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Lava extends ObjetosInteractivos {

	public Lava(PantallaJuego screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(Main.LAVA_BIT);
	}

	@Override
	public void contactColision() {
		Gdx.app.log("Lava", "Collision");
		setCategoryFilter(Main.DESTROYED_BIT);
		//sonido
		//Main.manager.load("audio/sfx/muere.wav", Sound.class);
	}

}
