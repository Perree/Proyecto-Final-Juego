package com.garaperree.guazo.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.garaperree.guazo.sprites.ObjetosInteractivos;

public class WorldContactListener implements ContactListener {

	//TODO revisar el video 12
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		
		switch (cDef){
        case Main.MARIO_HEAD_BIT | MarioBros.COIN_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.MARIO_HEAD_BIT)
                ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Mario) fixA.getUserData());
            else
                ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Mario) fixB.getUserData());
            break;
        case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
                ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
            else
                ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
            break;
        case MarioBros.ENEMY_BIT | MarioBros.OBJECT_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.ENEMY_BIT)
                ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
            else
                ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
            break;
        case MarioBros.MARIO_BIT | MarioBros.ENEMY_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.MARIO_BIT)
                ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
            else
                ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
            break;
        case MarioBros.ENEMY_BIT | MarioBros.ENEMY_BIT:
            ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
            ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
            break;
        case MarioBros.ITEM_BIT | MarioBros.OBJECT_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
                ((Item)fixA.getUserData()).reverseVelocity(true, false);
            else
                ((Item)fixB.getUserData()).reverseVelocity(true, false);
            break;
        case MarioBros.ITEM_BIT | MarioBros.MARIO_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.ITEM_BIT)
                ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
            else
                ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
            break;
        case MarioBros.FIREBALL_BIT | MarioBros.OBJECT_BIT:
            if(fixA.getFilterData().categoryBits == MarioBros.FIREBALL_BIT)
                ((FireBall)fixA.getUserData()).setToDestroy();
            else
                ((FireBall)fixB.getUserData()).setToDestroy();
            break;
    }
		
			//derecha
			if((fixA.getUserData() == "head1" || fixB.getUserData() == "head1")) {
				Fixture head1 = fixA.getUserData() == "head1" ? fixA : fixB;
				Fixture object = head1 == fixA ? fixB : fixA;
				
				if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
					((ObjetosInteractivos) object.getUserData()).onHeadHit();
				}
				
			}
			
			//izquierda
			if((fixA.getUserData() == "head2" || fixB.getUserData() == "head2")) {
				Fixture head2 = fixA.getUserData() == "head2" ? fixA : fixB;
				Fixture object = head2 == fixA ? fixB : fixA;
				
				if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
					((ObjetosInteractivos) object.getUserData()).onHeadHit();
				}
				
			}
			
			//abajo
			if((fixA.getUserData() == "abajo" || fixB.getUserData() == "abajo")) {
				Fixture abajo = fixA.getUserData() == "abajo" ? fixA : fixB;
				Fixture object = abajo == fixA ? fixB : fixA;
				
				if(object.getUserData() != null && ObjetosInteractivos.class.isAssignableFrom(object.getUserData().getClass())) {
					((ObjetosInteractivos) object.getUserData()).onHeadHit();
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
