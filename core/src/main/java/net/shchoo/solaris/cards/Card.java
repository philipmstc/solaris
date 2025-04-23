package net.shchoo.solaris.cards;

import java.util.function.Consumer;
import com.badlogic.ashley.core.Entity;

import net.shchoo.solaris.Main;
import net.shchoo.solaris.CardGame;

public class Card extends Entity {
   // Entity has Components
   // Entity is handled by an EntityEngine 
   // has a "is drawable" component 
   // has a "is in the deck" component
    
    private String name;
    private Side side1; 
    private Side side2;

    public Card(String name, int cost1, CardType type1, Consumer<CardGame> side1, 
                             int cost2, CardType type2, Consumer<CardGame> side2) {
        this.name = name;
        this.side1 = new Side(cost1, type1) { public void onPlay(CardGame game) { side1.accept(game);}};
        this.side2 = new Side(cost2, type2) { public void onPlay(CardGame game) { side2.accept(game);}};
    }

    public static abstract class Side {
        // you can represent numbers in myriad ways
        // integers can be represented as `int`, `long`, `short`, `byte`
                                    //     32b,    64b,     16b,    8b -> -128 - 127
        private int cost;
        private CardType type; 
        public abstract void onPlay(CardGame game);

        public Side(int cost, CardType type) {
            this.cost = cost;
            this.type = type;
        }

        public void onPlayCard(CardGame game) {
            game.cardBatch.start();
            this.onPlay(game);
            game.cardBatch.end();
        }
    }

    public enum CardType {
        COMBAT,
        RESEARCH,
        EXPLORATION
    }

    public enum Components {
        ENEMY,
        PLAYER
    }
}
