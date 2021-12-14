package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.maps.MapObject;
import com.garaperree.guazo.pantallas.PantallaJuego;

public class Meta extends ObjetosInteractivos{

	public Meta(PantallaJuego screen, MapObject object) {
		super(screen, object);
		fixture.setUserData(this);
//		setCategoryFilter(Main.META_BIT);
	}

//	@Override
//	public void contactColision(Fumiko fumiko) {
//		setCategoryFilter(Main.DESTROYED_BIT);
//	}

}
