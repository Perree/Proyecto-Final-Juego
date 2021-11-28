package com.garaperree.guazo.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Fumiko extends Sprite{
	
	public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD};
	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	private Animation<?> fumikoStand;
	private Animation<?> fumikoRun;
	private Animation<?> fumikoJump;
	private float stateTimer;
	private boolean runningRight;
	
	
	public Fumiko(PantallaJuego screen) {
		super(screen.getAtlas().findRegion("fumiko"));
		
		// variables
		this.world = screen.getWorld();
//		currentState = State.STANDING;
//		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		// metemos los frames de la animacion a un array
		Array<TextureRegion> frames = new Array<TextureRegion>();
		
		//animacion Correr
		for (int i = 0; i < 6; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 18, 52, 52));
			fumikoRun = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		//animacion Saltar
		for (int i = 6; i < 10; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 18, 52, 52));
			fumikoJump = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		//animacion Parado
		for (int i = 10; i < 14; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 18, 52, 52));	
			fumikoStand = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		defineFumiko();
		setBounds(0, 0, 52 / Main.PPM, 52 / Main.PPM);
//		setRegion(fumikoStand);
	}
	


	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() /2,b2body.getPosition().y - getHeight() /2);
		this.setOriginCenter();
		setRegion(getFrame(dt));
	}
	
	// con este metodo obtenemos el frame exacto dependiendo lo que el jugador este haciendo
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case JUMPING:
			region = (TextureRegion) fumikoJump.getKeyFrame(stateTimer);
			break;
			
		case RUNNING:
			region = (TextureRegion) fumikoRun.getKeyFrame(stateTimer, true);
			break;
			
		case FALLING:
		case STANDING:
			
		default:
			region = (TextureRegion) fumikoStand.getKeyFrame(stateTimer);
			break;
		}
		
		// Controlar los lados del jugador 
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
		
	}
	
	// con este metodo sabemos que esta haciendo el jugador (correr, saltar, etc)
	public State getState() {
		if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
			return State.JUMPING;
		
		else if(b2body.getLinearVelocity().y < 0) 
			return State.FALLING;
		
		else if(b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		
		else
			return State.STANDING;
	}


	private void defineFumiko() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(128/Main.PPM, 512/Main.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		// Cuan grande es el circulo
		shape.setRadius(6/Main.PPM);
		fdef.filter.categoryBits = Main.FUMIKO_BIT;
		fdef.filter.maskBits = Main.DEFAULT_BIT | Main.META_BIT | Main.PINCHES_BIT | Main.LAVA_BIT;
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		// colisiones
		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2/ Main.PPM, 6/Main.PPM), new Vector2(2/ Main.PPM, 6/Main.PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("head");
	}
	
	public void hit() {
		System.out.println("Me dieroon!");
	}
	
	public float getStateTimer() {
		return stateTimer;
	}

}
