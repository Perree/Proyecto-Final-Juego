package com.garaperree.guazo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;

public class Lava extends ObjetosInteractivos {

	public Lava(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		fixture.setUserData(this);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Lava", "Collision");
		
		//sonido
		//Main.manager.load("audio/sfx/muere.wav", Sound.class);
	}

}
