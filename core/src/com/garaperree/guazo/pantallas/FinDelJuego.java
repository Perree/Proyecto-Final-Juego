package com.garaperree.guazo.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.cliente.Cliente;

public class FinDelJuego implements Screen {
	private Viewport viewport;
	private Stage stage;
	private Game game;
	private Cliente cliente;

	public FinDelJuego(Game game, Cliente cliente) {
		this.game = game;
		this.cliente = cliente;
		
		viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, ((Main) game).batch);

		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

		Table table = new Table();
		table.center();
		table.setFillParent(true);

		Label finJuegoLabel = new Label("FIN DEL JUEGO", font);
		Label quienGano = new Label("Has ganado!", font);
		Label juegarDeNuevoLabel = new Label("Haz click en cualquier parte de la pantalla para salir",
				font);

		table.add(finJuegoLabel).expandX();
		table.row();
		table.add(quienGano).expandX().padTop(10f);
		table.row();
		table.add(juegarDeNuevoLabel).expandX().padTop(10f);

		stage.addActor(table);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
			cliente.enviarMensaje("desconectar");
			Gdx.app.exit();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
