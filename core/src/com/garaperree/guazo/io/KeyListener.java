package com.garaperree.guazo.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.garaperree.guazo.utiles.Utiles;

public class KeyListener implements InputProcessor {
	
	private boolean up = false, left = false, right = false;

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.UP) {
			up = true;
		}	
		
		if(keycode==Keys.LEFT) {
			left = true;
		}
		
		if(keycode==Keys.RIGHT) {
			right = true;	
		}
		
		Utiles.listener.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Keys.UP) {
			up = false;
		}	
		
		if(keycode==Keys.LEFT) {
			left = false;
		}
		
		if(keycode==Keys.RIGHT) {
			right = false;	
		}	
		
		Utiles.listener.keyUp(keycode);
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

	public boolean isUp() {
		return up;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}
	
	

}
