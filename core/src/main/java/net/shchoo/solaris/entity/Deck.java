package net.shchoo.solaris.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.EntityI;
import net.shchoo.solaris.Main;
import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Cards;

public class Deck implements EntityI {

    public List<Card> cards;

    // default deck constructor -- 4x attack, 4x defend, 2x draw
    public Deck() {
        this.cards = new ArrayList<Card>() {{
            add(Cards.Attack);
            add(Cards.Attack);
            add(Cards.Attack);
            add(Cards.Scrape);
            add(Cards.DoubleTap);
            add(Cards.Defend);
            add(Cards.Defend);
            add(Cards.Defend);
        }};
    }

    public Deck(List<Card> cards) {
        Collections.shuffle(cards);
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> draw(int count, List<Card> discard) {
        List<Card> drawn = new ArrayList<>();
        while (drawn.size() < count && cards.size() > 0) {
            drawn.add(cards.remove(0));
        }

        if (drawn.size() < count && discard.size() > 0) {
            cards.addAll(discard);
            discard.clear();
            Collections.shuffle(cards);
            drawn.addAll(draw(count - drawn.size(), new ArrayList<>()));
        }

        return drawn;
    }

    @Override
    public void render(Main game, float x, float y, float delta) {

        game.batch.begin();
        game.smallFont.setColor(Color.LIME);
        game.smallFont.draw(game.batch,
            "Deck: " + cards.size(),
            (game.viewport.getWorldWidth()/2),
            (game.viewport.getWorldHeight()/2),
            0.00f,
            Align.center,
            false) ;
        game.batch.end();
    }

}
