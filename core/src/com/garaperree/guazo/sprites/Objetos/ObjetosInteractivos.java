package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.pantallas.PantallaJuego;

public abstract class ObjetosInteractivos {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bounds;
	protected Body body;
	protected Fixture fixture;
	protected PantallaJuego screen;
	protected MapObject object;

	// Definimos los objetos con los cuales el personaje va a interactuar
	public ObjetosInteractivos(PantallaJuego screen, MapObject object) {
//		this.object = object;
//      this.screen = screen;
//		this.world = screen.getWorld();
//		this.map = screen.getMap();
//		this.bounds = ((RectangleMapObject) object).getRectangle();
//		
//		BodyDef bdef = new BodyDef();
//		FixtureDef fdef = new FixtureDef();
//		PolygonShape shape = new PolygonShape();
//		
//		bdef.type = BodyDef.BodyType.StaticBody;
//		bdef.position.set((bounds.getX() + bounds.getWidth()/2)/Main.PPM, (bounds.getY() + bounds.getHeight()/2)/Main.PPM);
//		
//		body = world.createBody(bdef);
//		
//		shape.setAsBox(bounds.getWidth()/2/Main.PPM, bounds.getHeight()/2/Main.PPM);
//		fdef.shape = shape;
//		fixture = body.createFixture(fdef);
	}

	// Metodo que detecta cuando hay colision
//	public abstract void contactColision(Fumiko fumiko);

	// Seteamos el filtro
//	public void setCategoryFilter(short filterBit) {
//		Filter filter = new Filter();
//		filter.categoryBits = filterBit;
//		fixture.setFilterData(filter);
//	}

}
