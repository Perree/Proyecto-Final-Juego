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
import com.garaperree.guazo.sprites.Objetos.Pinches;

public class WorldContactListener implements ContactListener {

	//TODO revisar el video 12
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		
//		switch (cDef){
//	        case Main.FUMIKO_BIT | Main.PINCHES_BIT:
//	            if(fixA.getFilterData().categoryBits == Main.FUMIKO_BIT)
//	                ((Fumiko)fixA.getUserData()).hit();
//	            else
//	                ((Fumiko)fixB.getUserData()).hit();
//	            break;
//		}
		
			//derecha
			if((fixA.getUserData() == "head1" || fixB.getUserData() == "head1")) {
				Fixture head1 = fixA.getUserData() == "head1" ? fixA : fixB;
				Fixture object = head1 == fixA ? fixB : fixA;
				
				if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
					((ObjetosInteractivos) object.getUserData()).contactColision();
				}
				
			}
			
			//izquierda
			if((fixA.getUserData() == "head2" || fixB.getUserData() == "head2")) {
				Fixture head2 = fixA.getUserData() == "head2" ? fixA : fixB;
				Fixture object = head2 == fixA ? fixB : fixA;
				
				if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
					((ObjetosInteractivos) object.getUserData()).contactColision();
				}
				
			}
			
			//abajo
			if((fixA.getUserData() == "abajo" || fixB.getUserData() == "abajo")) {
				Fixture abajo = fixA.getUserData() == "abajo" ? fixA : fixB;
				Fixture object = abajo == fixA ? fixB : fixA;
				
				if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
					((ObjetosInteractivos) object.getUserData()).contactColision();
				}
				
			}
		
	}

	@Override
	public void endContact(Contact contact) {
		Gdx.app.log("End Contact", "");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
