package dev.lyze.retro.ui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.retro.game.Game;

public class CoinButton extends Button {
    private final BitmapFont numbersFont;
    private Vector2 numberFontCoords;

    public CoinButton(Game game, String up, String down) {
        super(game, up, down);

        numbersFont = game.getAss().getNumbersFont();
    }

    @Override
    protected void setState(boolean state) {

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (numberFontCoords == null || numberFontCoords.x < 100) {
            numberFontCoords = localToStageCoordinates(new Vector2(17, numbersFont.getLineHeight() + 6));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        numbersFont.draw(batch, String.valueOf(game.getPlayer().getCoins()), numberFontCoords.x, numberFontCoords.y);
    }
}
