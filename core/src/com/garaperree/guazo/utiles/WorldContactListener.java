package com.garaperree.guazo.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.sprites.Fumiko;
import com.garaperree.guazo.sprites.Objetos.ObjetosInteractivos;

public class WorldContactListener implements ContactListener {

	//TODO revisar el video 12
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		//derecha
		if((fixA.getUserData() == "derecha" || fixB.getUserData() == "derecha")) {
			Fixture derecha = fixA.getUserData() == "derecha" ? fixA : fixB;
			Fixture object = derecha == fixA ? fixB : fixA;
			
			if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
				((ObjetosInteractivos) object.getUserData()).contactColision();
			}
			
		}
		
		//izquierda
		if((fixA.getUserData() == "izquierda" || fixB.getUserData() == "izquierda")) {
			Fixture izquierda = fixA.getUserData() == "izquierda" ? fixA : fixB;
			Fixture object = izquierda == fixA ? fixB : fixA;
			
			if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
				((ObjetosInteractivos) object.getUserData()).contactColision();
			}
			
		}
		
		//porDeBajo
		if((fixA.getUserData() == "porDeBajo" || fixB.getUserData() == "porDeBajo")) {
			Fixture porDeBajo = fixA.getUserData() == "porDeBajo" ? fixA : fixB;
			Fixture object = porDeBajo == fixA ? fixB : fixA;
			
			if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
				((ObjetosInteractivos) object.getUserData()).contactColision();
			}
			
		}
		
		switch (cDef){
	        case Main.FUMIKO_BIT | Main.META_BIT:
	            if(fixA.getFilterData().categoryBits == Main.FUMIKO_BIT)
	                ((Fumiko)fixA.getUserData()).termino();
	            else
	                ((Fumiko)fixB.getUserData()).termino();
	            break;
		}
	}

	@Override
	public void endContact(Contact contact) {
		Gdx.app.log("End Contact", "");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
