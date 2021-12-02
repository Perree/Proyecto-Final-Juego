package com.garaperree.guazo.sprites;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.FinDelJuego;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Fumiko extends Sprite{
	
	public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD};
	
	public boolean fin = false;
	
	private TextureRegion fumikoDead;
	
	public State currentState;
	public State previousState;
	
	public World world;
	public Body b2body;
	
	private Animation<?> fumikoStand;
	private Animation<?> fumikoRun;
	private Animation<?> fumikoJump;
	
	private float stateTimer;
	
	private boolean runningRight;
	private boolean fumikoIsDead;
	private boolean caca = false;
	
	private FinDelJuego screen;
	
	
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
			frames.add(new TextureRegion(getTexture(), i * 48, 14, 52, 52));
			fumikoRun = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		//animacion Saltar
		for (int i = 6; i < 10; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 14, 52, 52));
			fumikoJump = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		//animacion Parado
		for (int i = 10; i < 14; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 14, 52, 52));	
			fumikoStand = new Animation<Object>(0.1f, frames);
			frames.clear();
			
		//muere Fumiko
			fumikoDead = new TextureRegion(screen.getAtlas().findRegion("fumiko"), 96, 0, 52, 52);
			caca = true;
			if(caca) {
				System.out.println("Funca");
			}
		
		// definimos a fumiko en box2d
		defineFumiko();
		
		//seteamos los valores de posicion para fumiko
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
		case DEAD:
            region = fumikoDead;
            break;
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
	
	// Con este metodo sabemos que esta haciendo el jugador (correr, saltar, etc)
	public State getState() {
		if(fumikoIsDead)
	        return State.DEAD;
		else if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
			return State.JUMPING;
		
		else if(b2body.getLinearVelocity().y < 0) 
			return State.FALLING;
		
		else if(b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.STANDING;
	}
	
	public void hit(){
		if (!isDeadFumiko()) {
			System.out.println("Puto!");
			Main.manager.get("audio/music/MatWyre_Deep_Dawn.mp3", Music.class).stop();
//          Main.manager.get("audio/sounds/muere.wav", Sound.class).play();
            fumikoIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = Main.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }
	
	public void jump(){
        if ( currentState != State.JUMPING ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }

	private void defineFumiko() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(128/Main.PPM, 512/Main.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		// Cuan grande es el circulo
		shape.setRadius(12/Main.PPM);
		fdef.filter.categoryBits = Main.FUMIKO_BIT;
		fdef.filter.maskBits = Main.DEFAULT_BIT | Main.META_BIT | Main.PINCHES_BIT | Main.LAVA_BIT | Main.OBJECT_BIT;
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		// colision derecha
		EdgeShape head1 = new EdgeShape();
		head1.set(new Vector2(11/ Main.PPM, -10/Main.PPM), new Vector2(11/ Main.PPM, 10/Main.PPM));
		fdef.shape = head1;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("head1");
		
		// colision izquierda
		EdgeShape head2 = new EdgeShape();
		head2.set(new Vector2(-11/ Main.PPM, 10/Main.PPM), new Vector2(-11/ Main.PPM, -10/Main.PPM));
		fdef.shape = head2;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("head2");
		
		// colision abajo
		EdgeShape abajo = new EdgeShape();
		abajo.set(new Vector2(-2/ Main.PPM, -12/Main.PPM), new Vector2(2/ Main.PPM, -12/Main.PPM));
		fdef.shape = abajo;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("abajo");
	}
	
	public boolean isDeadFumiko() {
		System.out.println("Me dieroon!");
		return fumikoIsDead;
	}
	
	public void llegoSalida() {
		fin = true;
	}
	
	public float getStateTimer() {
		return stateTimer;
	}
	
	public Boolean isPuedeSalir() {
		return fin;
	}

}
