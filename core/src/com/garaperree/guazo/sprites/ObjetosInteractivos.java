package com.garaperree.guazo.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public abstract class ObjetosInteractivos {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bounds;
	protected Body body;
	protected Fixture fixture;
	
	
	public ObjetosInteractivos(PantallaJuego screen, Rectangle bounds) {
		this.world = screen.getWorld();
		this.map = screen.getMap();
		this.bounds = bounds;
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX() + bounds.getWidth()/2)/Main.PPM, (bounds.getY() + bounds.getHeight()/2)/Main.PPM);
		
		body = world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth()/2/Main.PPM, bounds.getHeight()/2/Main.PPM);
		fdef.shape = shape;
		fixture = body.createFixture(fdef);
		
	}
	
	public abstract void onHeadHit();
	
	public void setCategoryFilter(short filterBit) {
		Filter filter = new Filter();
		filter.categoryBits = filterBit;
		fixture.setFilterData(filter);
	}
	
}