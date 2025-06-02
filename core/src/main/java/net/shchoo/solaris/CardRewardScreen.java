package net.shchoo.solaris;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Cards;
import net.shchoo.solaris.ui.CardMenu;

import java.util.ArrayList;
import java.util.List;

public class CardRewardScreen extends DefaultInputScreen {

    private CardMenu menu;

    public CardRewardScreen(Main game) {
        super(game);
        this.menu = new CardMenu(
            randomCards(3),
            () -> game.viewport.getWorldWidth() / 4,
            () -> 0.33f + game.viewport.getWorldHeight() / 2,
            2.0f,
            0.0f);
    }

    private static List<Card> randomCards(int count) {
        return new ArrayList<Card>() {{
            add(Cards.Enrage);
            add(Cards.Relax);
            add(Cards.Sharpen);
        }};
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.shape.setProjectionMatrix(game.viewport.getCamera().combined);
        menu.render(game);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            game.BASE_DECK.add(menu.selection);
            game.setScreen(new GameScreen(game));
            dispose();
        }
        menu.handleKeyPress(keycode);
        return false;
    }
}
