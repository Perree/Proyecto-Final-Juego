package com.garaperree.guazo.sprites.Objetos;

import com.badlogic.gdx.maps.MapObject;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.pantallas.PantallaJuego;
import com.garaperree.guazo.sprites.Fumiko;

public class Lava extends ObjetosInteractivos {

	public Lava(PantallaJuego screen, MapObject object) {
		super(screen, object);
		fixture.setUserData(this);
		setCategoryFilter(Main.LAVA_BIT);
	}

	@Override
	public void contactColision(Fumiko fumiko) {
		setCategoryFilter(Main.DESTROYED_BIT);
	}
}
