package net.shchoo.solaris.cards;

import net.shchoo.solaris.Main;

public class Cards {
    public static final Card Attack = new Card("Attack", 1) {
        @Override
        public Destination onPlay(Main game) {
            game.addDamageEvent(1);
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
    public static final Card Scrape = new Card("Draw 2", 2) {
        @Override
        public Destination onPlay(Main game) {
            game.pendingDraw += 2;
            return Destination.EXILE;
        }
    };
    public static final Card DoubleTap = new Card("Attack\nTwice", 2) {
        public Destination onPlay(Main game) {
            game.addDamageEvent(1);
            game.addDamageEvent(1);
            return Destination.DISCARD;
        }
    };
}
