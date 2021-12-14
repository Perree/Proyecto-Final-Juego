package com.garaperree.guazo.sprites.Objetos;

public class B2WorldCreator {
//	public B2WorldCreator(PantallaJuego screen) {

//		World world = screen.getWorld();
//		TiledMap map = screen.getMap();
//		
//		// Crear variables del body y fixture
//		BodyDef bdef = new BodyDef();
//		PolygonShape shape = new PolygonShape();
//		FixtureDef fdef = new FixtureDef();
//		Body body;
//		
////		// Crear el piso
//		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
//			
//			Rectangle rect = ((RectangleMapObject) object).getRectangle();
//			
//			bdef.type = BodyDef.BodyType.StaticBody;
//			bdef.position.set((rect.getX() + rect.getWidth()/2)/Main.PPM, (rect.getY() + rect.getHeight()/2)/Main.PPM);
//			
//			body = world.createBody(bdef);
//			
//			shape.setAsBox(rect.getWidth()/2/Main.PPM, rect.getHeight()/2/Main.PPM);
//			fdef.shape = shape;
//			body.createFixture(fdef);
//		}
//		
//		// Crear los pinches
//		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
//			new Pinches(screen, object);
//		}
//		// Crear lava
//		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
//			new Lava(screen, object);
//		}
//		
//		// Crear meta
//		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
//			new Meta(screen, object);
//		}
//	}
}
