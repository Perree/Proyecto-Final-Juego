package com.garaperree.guazo.utiles;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.sprites.Fumiko;
import com.garaperree.guazo.sprites.Objetos.ObjetosInteractivos;

public class WorldContactListener implements ContactListener {
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		switch (cDef){
			// El shape que esta debajo del personaje colision con la Meta, Pinches y Lava
	        case Main.POR_DEBAJO_BIT | Main.META_BIT:
	        case Main.POR_DEBAJO_BIT | Main.PINCHES_BIT:
	        case Main.POR_DEBAJO_BIT | Main.LAVA_BIT:
	        	if(fixA.getFilterData().categoryBits == Main.POR_DEBAJO_BIT)
	        		((ObjetosInteractivos) fixB.getUserData()).contactColision((Fumiko) fixA.getUserData());
	            else
	            	((ObjetosInteractivos) fixA.getUserData()).contactColision((Fumiko) fixB.getUserData());
	            break;
	            
            // El shape que esta a la derecha del personaje colision con la Meta, Pinches y Lava
	        case Main.DERECHA_BIT| Main.META_BIT:
	        case Main.DERECHA_BIT| Main.PINCHES_BIT:
	        case Main.DERECHA_BIT| Main.LAVA_BIT: 
	        	if(fixA.getFilterData().categoryBits == Main.DERECHA_BIT)
	        		((ObjetosInteractivos) fixB.getUserData()).contactColision((Fumiko) fixA.getUserData());
	            else
	            	((ObjetosInteractivos) fixA.getUserData()).contactColision((Fumiko) fixB.getUserData());
	            break;
	            
	        // El shape que esta a la izquierda del personaje colision con la Meta, Pinches y Lava
	        case Main.IZQUIERDA_BIT | Main.META_BIT:
	        case Main.IZQUIERDA_BIT | Main.PINCHES_BIT:
	        case Main.IZQUIERDA_BIT | Main.LAVA_BIT:
	            if(fixA.getFilterData().categoryBits == Main.IZQUIERDA_BIT)
	            	((ObjetosInteractivos) fixB.getUserData()).contactColision((Fumiko) fixA.getUserData());
	            else
	            	((ObjetosInteractivos) fixA.getUserData()).contactColision((Fumiko) fixB.getUserData());
	            break;
		}
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
