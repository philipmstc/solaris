package net.shchoo.solaris.cards;

import net.shchoo.solaris.Main;

public class Cards {
    public static final Card Attack = new Card("Attack", 1) {
        @Override
        public Destination onPlay(Main game) {
            game.enemyHealth -= 1;
            return Destination.DISCARD;
        }
    };
    public static final Card Defend = new Card("Defend", 1) {
        @Override
        public Destination onPlay(Main game) {
            game.playerBlock += 1;
            return Destination.DISCARD;
        }
    };
    public static final Card Scrape = new Card("Draw 3", 2) {
        @Override
        public Destination onPlay(Main game) {
            game.pendingDraw += 3;
            return Destination.EXILE;
        }
    };
}
