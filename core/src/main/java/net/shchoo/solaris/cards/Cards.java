package net.shchoo.solaris.cards;

import net.shchoo.solaris.Main;

public class Cards {
    public static final Card Attack = new Card("Attack") {
        @Override
        public Destination onPlay(Main game) {
            game.enemyHealth -= 1;
            return Destination.DISCARD;
        }
    };
    public static final Card Defend = new Card("Defend") {
        @Override
        public Destination onPlay(Main game) {
            game.playerBlock += 1;
            return Destination.DISCARD;
        }
    };
    public static final Card Scrape = new Card("Draw 1") {
        @Override
        public Destination onPlay(Main game) {
            game.pendingDraw += 1;
            return Destination.EXILE;
        }
    };
    public static final Card X = new Card("X") {

        @Override
        public Destination onPlay(Main game) {
            return Destination.DISCARD;
        }
    };
    public static final Card Y = new Card("Y") {

        @Override
        public Destination onPlay(Main game) {
            return Destination.DISCARD;
        }
    };

}
