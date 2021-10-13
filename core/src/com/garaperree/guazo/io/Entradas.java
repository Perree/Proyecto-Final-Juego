package com.garaperree.guazo.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.garaperree.guazo.pantallas.PantallaMenu;

public class Entradas implements InputProcessor {

	private boolean abajo = false, arriba = false;

	PantallaMenu app;

	public Entradas(PantallaMenu app) {
		this.app = app;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isArriba() {
		return arriba;
	}

	@Override
	public boolean keyDown(int keycode) {
		app.tiempo = 0.08f;
		
		if (keycode == Keys.DOWN) {
			abajo = true;
		} else if (keycode == Keys.UP) {
			arriba = true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.DOWN) {
			abajo = false;
		} 
		if (keycode == Keys.UP) {
			arriba = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {

		return false;
	}

}
