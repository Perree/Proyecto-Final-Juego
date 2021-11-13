package com.garaperree.guazo.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Fumiko extends Sprite{
	public World world;
	public Body b2body;
	private TextureRegion fumikoStand;
	
	
	public Fumiko(World world, PantallaJuego pantalla) {
		super(pantalla.getAtlas().findRegion("Biker_run"));
		this.world = world;
		defineFumiko();
		fumikoStand = new TextureRegion(getTexture(), 0, 0, 32, 32);
		setBounds(0, 0, 32 / Main.PPM, 32 / Main.PPM);
		setRegion(fumikoStand);
	}
	
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2,b2body.getPosition().y - getHeight() /2);
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
