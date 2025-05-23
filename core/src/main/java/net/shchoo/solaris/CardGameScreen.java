package net.shchoo.solaris;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Card.Destination;
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
        this.menu = drawHand(player.startingHandSize);
    }

    private CardMenu drawHand(int handSize) {
        List<Card> drawn = deck.draw(handSize, discard);
        return new CardMenu(
                drawn,
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
        renderDiscard();
        renderEnergy();
        enemy.render(game, game.viewport.getWorldWidth() / 3, game.viewport.getWorldHeight() - 2.0f, delta);
        player.render(game, game.viewport.getWorldWidth() / 3, game.viewport.getWorldHeight() - 1.5f, delta);
        menu.render(game);

        if (!game.isPlayerTurn) {
            game.shape.begin(ShapeType.Filled);
            game.shape.circle(
                game.viewport.getWorldWidth()/2,
                game.viewport.getWorldHeight()/2,
                1 * (game.enemyTurnTimer / game.enemyTurnTotal));
            game.shape.end();

            // TODO this should probably NOT live in render... 
            // I presume theres a logical processing equivalent to rendering
            game.enemyTurnTimer -= 1;
            if (game.enemyTurnTimer <= 0) {
                // deal damage lmao
                game.playerHealth -= 1;

                // reset to player turn
                game.isPlayerTurn = true;
                game.playerEnergy = game.playerMaxEnergy;
                game.enemyTurnTimer = 0;

                discard.addAll(menu.selections);
                menu = drawHand(player.startingHandSize);
            }
        }
    }

    private void renderDiscard() {
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
    }

    private void renderEnergy() {
        game.batch.begin();
        game.smallFont.setColor(Color.GOLDENROD);
        game.smallFont.draw(game.batch,
            "Energy: " + game.playerEnergy + " / " + game.playerMaxEnergy,
            2.0f + (game.viewport.getWorldWidth()/2),
            0.25f + (game.viewport.getWorldHeight()/2),
            0.00f,
            Align.center,
            false);
        game.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!game.isPlayerTurn) {
            return false;
        }
        menu.handleKeyPress(keycode);
        if (keycode == Input.Keys.ESCAPE) {
            endPlayerTurn();
        }
        if (keycode == Input.Keys.ENTER) {
            if (!isPlayable(menu.selection)) {
                // no energy, or no selection
                return false;
            }
            Destination d = game.play(menu.selection);
            Card played = menu.removeCurrent();
            if (d == Destination.DISCARD) { 
                discard.add(played);
            }
            else if (d == Destination.EXILE) {
                // do _not_ discard
            }
            if (game.pendingDraw > 0) {
                this.menu.addSelections(deck.draw(game.pendingDraw, discard));
                game.pendingDraw = 0;
            }
            menu.reset();
        }
        return false;
    }
    
    private void endPlayerTurn() {
        game.isPlayerTurn = false;
        game.enemyTurnTimer = game.enemyTurnTotal;
    }

    private boolean isPlayable(Card card) {
        // thoughts -- "X" cost cards may need more than simple property
        return card != null && card.cost <= game.playerEnergy;
    }
}
