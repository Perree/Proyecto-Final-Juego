package com.garaperree.guazo.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Fumiko extends Sprite {

	public enum State {
		FALLING, JUMPING, STANDING, RUNNING, DEAD
	};

	public boolean fin = false;
	public boolean mueveArriba, mueveIzquierda, mueveDerecha;

	public String currentState;
	public String previousState;

	private Animation<?> fumikoStand;
	private Animation<?> fumikoRun;
	private Animation<?> fumikoJump;
	private float stateTimer;
	private boolean runningRight;
	private boolean fumikoIsDead;

	public Fumiko(PantallaJuego screen) {
		super(screen.getAtlas().findRegion("fumiko"));

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
	}

	// Se va actualizando la posicion del personaje
	public void update(float dt) {
		setRegion(getFrame(dt));
	}

//	 Obtengo el frame exacto dependiendo lo que el jugador este haciendo
	public TextureRegion getFrame(float dt) {
//		currentState = getState();

		TextureRegion region;
		switch (currentState) {
		case "JUMPING":
			region = (TextureRegion) fumikoJump.getKeyFrame(stateTimer);
			break;

		case "RUNNING":
			region = (TextureRegion) fumikoRun.getKeyFrame(stateTimer, true);
			break;

		default:
			region = (TextureRegion) fumikoStand.getKeyFrame(stateTimer);
			break;
		}

		// Controlar los lados del jugador
		if ((!runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		} else if ((runningRight) && region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}

		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;

	}

	public void jump() {
	}

//	
	public void right() {
	}

//	
	public void left() {
	}

	// Definimos el cuerpo, flitros y colisiones
	private void defineFumiko() {
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
