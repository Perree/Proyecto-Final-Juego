package com.garaperree.guazo.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.garaperree.guazo.utiles.Utiles;

public class KeyListener implements InputProcessor {
	
	private boolean up1 = false, left1 = false, right1 = false;

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.UP) {
			up1 = true;
		}	
		
		if(keycode==Keys.LEFT) {
			left1 = true;
		}
		
		if(keycode==Keys.RIGHT) {
			right1 = true;	
		}
		
		Utiles.listener.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Keys.UP) {
			up1 = false;
		}	
		
		if(keycode==Keys.LEFT) {
			left1 = false;
		}
		
		if(keycode==Keys.RIGHT) {
			right1 = false;	
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
		return up1;
	}

	public boolean isRight() {
		return right1;
	}

	public boolean isLeft() {
		return left1;
	}
	
	

}
