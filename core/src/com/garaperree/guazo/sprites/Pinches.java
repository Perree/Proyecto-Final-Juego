package com.garaperree.guazo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;

public class Pinches extends ObjetosInteractivos{

	public Pinches(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(Main.PINCHES_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Pinches", "Collision");
		setCategoryFilter(Main.DESTROYED_BIT);
		
		
	}

}
