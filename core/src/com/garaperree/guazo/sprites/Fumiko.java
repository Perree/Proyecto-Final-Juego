package com.garaperree.guazo.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;

public class Fumiko extends Sprite{
	public World world;
	public Body b2body;
	
	
	public Fumiko(World world) {
		this.world = world;
		defineFumiko();
	}


	private void defineFumiko() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(512/Main.PPM, 512/Main.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10/Main.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
	
	}

}
