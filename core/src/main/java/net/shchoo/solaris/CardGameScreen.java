package net.shchoo.solaris;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Card.Destination;
import net.shchoo.solaris.cards.Cards;
import net.shchoo.solaris.entity.Deck;
import net.shchoo.solaris.entity.Enemy;
import net.shchoo.solaris.entity.Player;
import net.shchoo.solaris.ui.CardMenu;

public class CardGameScreen extends DefaultInputScreen {
    private CardMenu menu;
    private Enemy enemy;
    private Player player;
    private Deck deck;
    private List<Card> discard;

    public CardGameScreen(Main game, Enemy enemy, Player player, Deck deck) {
        super(game);
        this.enemy = enemy;
        this.player = player;
        this.deck = deck;
        this.discard = new ArrayList<>();
        this.menu = new CardMenu(
                deck.draw(3),
                () -> game.viewport.getWorldWidth() / 6,
                () -> -2.0f + (game.viewport.getWorldHeight() / 2),
                1.1f,
                0.0f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.shape.setProjectionMatrix(game.viewport.getCamera().combined);
        deck.render(game, game.viewport.getWorldWidth() / 8, -2.0f + (game.viewport.getWorldHeight() / 2), delta);
        // discard.render
        game.batch.begin();
        game.smallFont.setColor(Color.CORAL);
        game.smallFont.draw(game.batch, 
            "Discard: " + discard.size(),
            2.0f + (game.viewport.getWorldWidth()/2), 
            (game.viewport.getWorldHeight()/2),
            0.00f,
            Align.center,
            false);
        game.batch.end();
        enemy.render(game, game.viewport.getWorldWidth() / 3, game.viewport.getWorldHeight() - 2.0f, delta);
        player.render(game, game.viewport.getWorldWidth() / 3, game.viewport.getWorldHeight() - 1.5f, delta);
        menu.render(game);
    }

    @Override
    public boolean keyDown(int keycode) {
        menu.handleKeyPress(keycode);
        if (keycode == Input.Keys.ENTER) {
            Destination d = menu.selection.onPlay(game);
            Card played = menu.removeCurrent();
            if (d == Destination.DISCARD) { 
                discard.add(played);
            }
            else if (d == Destination.EXILE) {
                // do _not_ discard
            }
            if (played == Cards.Scrape) {
                // TODO lists and stuff
                if (deck.cards.size() == 0) { 
                    for (Card disc : discard) {
                        deck.cards.add(disc);
                        // deck.shuffle();
                    }
                    discard.clear();
                }
                List<Card> drawn = deck.draw(1);
                this.menu.addSelection(drawn.get(0));
            }
            menu.reset();
        }
        return false;
    }
}
