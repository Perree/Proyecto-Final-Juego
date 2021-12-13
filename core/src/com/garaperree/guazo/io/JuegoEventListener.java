package com.garaperree.guazo.io;

import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface JuegoEventListener extends EventListener {

	public void empieza();
	public void keyUp(int keycode);
	public void keyDown(int keycode);
	public void asignarJugador(int jugador);
	public void asignarCoordenadas(int nroJugador, float coordenadas);
	public void terminoJuego(int nroJugador);

//	public void actualizarPelota(float posX, float posY);
//	public void actualizarPuntaje(int nroJugador);
//	public void terminoJuego(int nroJugador);
}
