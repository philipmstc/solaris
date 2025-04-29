package net.shchoo.solaris.cards;

import com.badlogic.ashley.utils.Bag;
import net.shchoo.solaris.CardGame;
import net.shchoo.solaris.Main;

public class Cards {
    public static final Card Attack = new Card("Attack\nDeal 1 Damage") {
        @Override
        public void onPlay(Main game) {
            game.enemyHealth -= 1;
        }
    };
    public static final Card Defend = new Card("Defend\nBlock 1") {
        @Override
        public void onPlay(Main game) {
            game.playerBlock += 1;
        }
    };
    public static final Card Scrape = new Card("Scrape\nDraw 1") {
        @Override
        public void onPlay(Main game) {

        }
    };
    public static final Card X = new Card("X") {

        @Override
        public void onPlay(Main game) {
        }
    };
    public static final Card Y = new Card("Y") {

        @Override
        public void onPlay(Main game) {
        }
    };

}
