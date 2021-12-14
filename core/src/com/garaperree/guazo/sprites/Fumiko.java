package com.garaperree.guazo.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Fumiko extends Sprite{
	
	public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD};
	
	public boolean fin = false;
	public boolean mueveArriba, mueveIzquierda, mueveDerecha;
	
	public String currentState;
	public String previousState;
	
//	public World world;
//	public Body b2body;
	
	private Animation<?> fumikoStand;
	private Animation<?> fumikoRun;
	private Animation<?> fumikoJump;
	private float stateTimer;
	private boolean runningRight;
	private boolean fumikoIsDead;
	
	public Fumiko(PantallaJuego screen) {
		super(screen.getAtlas().findRegion("fumiko"));
		
//		this.world = screen.getWorld();
		currentState = "STANDING";
		previousState = "STANDING";
		stateTimer = 0;
		runningRight = true;
		
		// Metemos los frames de la animacion a un array
		Array<TextureRegion> frames = new Array<TextureRegion>();
		
		// Animacion Correr
		for (int i = 0; i < 6; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 14, 52, 52));
			fumikoRun = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		// Animacion Saltar
		for (int i = 6; i < 10; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 14, 52, 52));
			fumikoJump = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		// Animacion Parado
		for (int i = 10; i < 14; i++) 
			frames.add(new TextureRegion(getTexture(), i * 48, 14, 52, 52));	
			fumikoStand = new Animation<Object>(0.1f, frames);
			frames.clear();
		
		// Definimos a fumiko en box2d
		defineFumiko();
		
		// Seteamos los valores de posicion para fumiko
		setBounds(0, 0, 52 / Main.PPM, 52 / Main.PPM);
//		setRegion(fumikoStand);
	}
	
	// Se va actualizando la posicion del personaje
	public void update(float dt) {
//		setPosition(b2body.getPosition().x - getWidth() /2,b2body.getPosition().y - getHeight() /2);
//		this.setOriginCenter();
		setRegion(getFrame(dt));
	}
	
//	 Obtengo el frame exacto dependiendo lo que el jugador este haciendo
	public TextureRegion getFrame(float dt) {
//		currentState = getState();
		
		TextureRegion region;
		switch(currentState) {
		case "JUMPING":
			region = (TextureRegion) fumikoJump.getKeyFrame(stateTimer);
			break;
			
		case "RUNNING":
			region = (TextureRegion) fumikoRun.getKeyFrame(stateTimer, true);
			break;
			
		case "FALLING":
		case "STANDING":
			
		default:
			region = (TextureRegion) fumikoStand.getKeyFrame(stateTimer);
			break;
		}
		
		// Controlar los lados del jugador 
		if((/*b2body.getLinearVelocity().x < 0 || */!runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		else if((/*b2body.getLinearVelocity().x > 0 ||*/ runningRight) && region.isFlipX()){
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
		
	}
	
	// Con este metodo sabemos que esta haciendo el jugador (correr, saltar, etc)
//	public State getState() {
//
//		if(fumikoIsDead) 
//			return State.DEAD;
//		
//		if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
//			return State.JUMPING;
//		
//		else if(b2body.getLinearVelocity().y < 0) 
//			return State.FALLING;
//		
//		else if(b2body.getLinearVelocity().x != 0)
//			return State.RUNNING;
//		
//		else
//			return State.STANDING;
//	}
	
	public void jump(){
//        if (currentState != State.JUMPING) {
//            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
//            currentState = State.JUMPING;
//        }
    }
//	
	public void right(){
//		b2body.applyLinearImpulse(new Vector2(0.1f, 0),b2body.getWorldCenter(), true);
    }
//	
	public void left(){
//		b2body.applyLinearImpulse(new Vector2(-0.1f, 0),b2body.getWorldCenter(), true);
    }

	// Definimos el cuerpo, flitros y colisiones
	private void defineFumiko() {
//		BodyDef bdef = new BodyDef();
//		bdef.position.set(128/Main.PPM, 512/Main.PPM);
//		bdef.type = BodyDef.BodyType.DynamicBody;
//		b2body = world.createBody(bdef);
//		
//		FixtureDef fdef = new FixtureDef();
//		CircleShape shape = new CircleShape();
//		
//		// Cuan grande es el circulo
//		shape.setRadius(12/Main.PPM);
//		
//		// Filtros
//		fdef.filter.categoryBits = Main.FUMIKO_BIT;
//		fdef.filter.maskBits = 
//				Main.DEFAULT_BIT | 
//				Main.META_BIT | 
//				Main.PINCHES_BIT | 
//				Main.LAVA_BIT | 
//				Main.OBJECT_BIT;
//		
//		fdef.shape = shape;
//		b2body.createFixture(fdef).setUserData(this);
//		
//		// colision derecha
//		EdgeShape derecha = new EdgeShape();
//		derecha.set(new Vector2(11/ Main.PPM, -10/Main.PPM), 
//				new Vector2(11/ Main.PPM, 10/Main.PPM));
//		fdef.filter.categoryBits = Main.DERECHA_BIT;
//		fdef.shape = derecha;
//		fdef.isSensor = true;
//		b2body.createFixture(fdef).setUserData(this);
//		
//		// Colision izquierda
//		EdgeShape izquierda = new EdgeShape();
//		izquierda.set(new Vector2(-11/ Main.PPM, 10/Main.PPM), 
//				new Vector2(-11/ Main.PPM, -10/Main.PPM));
//		fdef.filter.categoryBits = Main.IZQUIERDA_BIT;
//		fdef.shape = izquierda;
//		fdef.isSensor = true;
//		b2body.createFixture(fdef).setUserData(this);
//		
//		// Colision abajo
//		EdgeShape porDeBajo = new EdgeShape();
//		porDeBajo.set(new Vector2(-2/ Main.PPM, -12/Main.PPM), 
//				new Vector2(2/ Main.PPM, -12/Main.PPM));
//		fdef.filter.categoryBits = Main.POR_DEBAJO_BIT;
//		fdef.shape = porDeBajo;
//		fdef.isSensor = true;
//		b2body.createFixture(fdef).setUserData(this);
	}

	public float getStateTimer() {
		return stateTimer;
	}
	
	public void llegoSalida() {
		fin = true;
	}
	
	public Boolean isPuedeSalir() {
		return fin;
	}
	
	public boolean isDead() {
		return fumikoIsDead;
	}

	public void setCurrentState(String animacion) {
		currentState = animacion;
		
	}
}
