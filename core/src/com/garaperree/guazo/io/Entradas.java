package com.garaperree.guazo.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

	private boolean abajo = false, arriba = false;
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.DOWN) {
			abajo = true;
		}

		if (keycode == Keys.UP) {
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
