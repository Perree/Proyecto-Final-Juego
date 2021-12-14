package com.garaperree.guazo.io;

import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface JuegoEventListener extends EventListener {

	public void empieza();

	public void keyUp(int keycode);

	public void keyDown(int keycode);

	public void asignarJugador(int jugador);

	public void asignarPos(int nroJugador, float x, float y);

	public void actualizarAnimacion(int nroJugador, String Animacion);

	public void actualizarTiempo(int tiempo);

	public void terminoJuego(int nroJugador);

	public void ganoJuego(int nroJugador);

	public void seAcaboElTiempo();
}
