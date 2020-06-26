package dev.lyze.retro.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.czyzby.kiwi.log.Logger;
import com.github.czyzby.kiwi.log.LoggerService;
import dev.lyze.retro.RetroTowerdefence;
import dev.lyze.retro.game.Assets;

/** First screen of the application. Displayed after the application is created. */
public class MenuScreen extends Stage implements Screen {
	private static final Logger logger = LoggerService.forClass(MenuScreen.class);

	public MenuScreen(RetroTowerdefence towerdefence, Assets ass) {
		super (new FitViewport(160, 144));

		addActor(new Image(ass.getMainMenu()));

		Gdx.input.setInputProcessor(this);

		addActor(new MenuButton(towerdefence, 0, 8, 64, 48, 32));
		addActor(new MenuButton(towerdefence, 1, 104, 64, 48, 32));
		addActor(new MenuButton(towerdefence, 2, 8, 8, 48, 32));
		addActor(new MenuButton(towerdefence, 3, 104, 8, 48, 32));
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		getViewport().apply();
		act(delta);
		draw();
	}

	@Override
	public void resize(int width, int height) {
	    logger.info("Resizing to {0}x{1}", width, height);

	    getViewport().update(width, height, true);
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
	}
}