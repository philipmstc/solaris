package net.shchoo.solaris;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Cards;
import net.shchoo.solaris.entity.Deck;
import net.shchoo.solaris.entity.Enemy;
import net.shchoo.solaris.entity.Player;
import net.shchoo.solaris.ui.CardMenu;
import net.shchoo.solaris.ui.DisplayableCard;

public class CardGameScreen extends DefaultInputScreen {
    private CardMenu menu;  
    private Enemy enemy;
    private Player player;
    private Deck deck;
    private List<Card> hand;

    public CardGameScreen(Main game, Enemy enemy, Player player, Deck deck) {
        super(game);
        this.hand = deck.draw(3);
        this.enemy = enemy;
        this.player = player;
        this.deck = deck; 
        this.menu = new CardMenu(
            hand,
            () -> game.viewport.getWorldWidth() / 6, 
            () -> -2.0f + (game.viewport.getWorldHeight() / 2),
            1.1f, 
            0.0f
        );
    }
    
    @Override 
    public void render(float delta) { 
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.shape.setProjectionMatrix(game.viewport.getCamera().combined);
        deck.render(game, game.viewport.getWorldWidth()/8, -2.0f + (game.viewport.getWorldHeight()/2), delta);
        enemy.render(game, game.viewport.getWorldWidth()/3, game.viewport.getWorldHeight() - 2.0f,delta);
        player.render(game, 0, 0, delta);
        menu.render(game);
    }

    @Override
    public boolean keyDown(int keycode) {
        menu.handleKeyPress(keycode);
        if (keycode == Input.Keys.ENTER) { 
            menu.selection.onPlay(game);
            if (menu.selection.equals(Cards.Scrape)) {
                // TODO lists and stuff
                List<Card> drawn = deck.draw(1);
                this.hand.add(drawn.get(0));
            }
        }
        return false;
    }
}
