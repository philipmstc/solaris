package net.shchoo.solaris.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.Entity;
import net.shchoo.solaris.Main;
import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Cards;

public class Deck implements Entity {

    public ArrayList<Card> cards;

    // default --- please make better?
    public Deck() {
        this.cards = new ArrayList<Card>() {{
            add(Cards.Attack);
            add(Cards.Scrape);
            add(Cards.Scrape);
            add(Cards.Defend);
        }};
    }

    public List<Card> draw(int count) { 
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawn.add(cards.remove(0));
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
