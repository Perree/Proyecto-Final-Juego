package com.garaperree.guazo.utiles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.sprites.Lava;
import com.garaperree.guazo.sprites.Meta;
import com.garaperree.guazo.sprites.Pinches;

public class B2WorldCreator {
	public B2WorldCreator(PantallaJuego screen) {
		
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
		
		// Crear variables del body y fixture
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		// Crear el piso
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth()/2)/Main.PPM, (rect.getY() + rect.getHeight()/2)/Main.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2/Main.PPM, rect.getHeight()/2/Main.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		// Crear los pinches
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Pinches(screen, rect);
		}
		// Crear lava
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Lava(screen, rect);
		}
		
		// Crear meta
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Meta(screen, rect);;
		}
	}
}
