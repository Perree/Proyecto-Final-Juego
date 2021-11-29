package com.garaperree.guazo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Lava extends ObjetosInteractivos {

	public Lava(PantallaJuego screen, Rectangle bounds) {
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(Main.LAVA_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Lava", "Collision");
		setCategoryFilter(Main.DESTROYED_BIT);
		//sonido
		//Main.manager.load("audio/sfx/muere.wav", Sound.class);
	}

}
