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

    public int count;
    public ArrayList<Card> cards;

    // default --- please make better?
    public Deck() {
        this.cards = new ArrayList<Card>() {{
            add(Cards.Attack);
            add(Cards.Scrape);
            add(Cards.Attack);
            add(Cards.Defend);
        }};
        this.count = cards.size();
    }

    public List<Card> draw(int count) { 
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawn.add(cards.remove(0));
        }
        this.count -= count;
        return drawn;
    }

    @Override
    public void render(Main game, float x, float y, float delta) { 
        game.shape.begin(ShapeType.Line);
        game.shape.setColor(Color.WHITE);
        game.shape.rect(
            game.viewport.getWorldWidth() / 8,
            (game.viewport.getWorldHeight()/1) - 2.0f,
            0.0f,
            0.0f);
        game.shape.end();
        game.batch.begin();
        game.smallFont.draw(game.batch,
            count +"", 
            -1.05f+(game.viewport.getWorldWidth()/9),
            -1.05f+(game.viewport.getWorldHeight()/2)-2.0f,
            -1,
            Align.center,
            false) ;
        game.batch.end();
    }
    
}
