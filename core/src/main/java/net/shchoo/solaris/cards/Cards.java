package net.shchoo.solaris.cards;

import net.shchoo.solaris.Entity;
import net.shchoo.solaris.Main;

public class Cards {
    public static final Card Attack = new Card("Attack", 1) {
        @Override
        public Destination onPlay(Main game, Entity target) {
            game.addDamageEvent(1, target);
            return Destination.DISCARD;
        }
    };
    public static final Card Defend = new Card("Defend", 1) {
        @Override
        public Destination onPlay(Main game, Entity self) {
            game.player.block += 1;
            return Destination.DISCARD;
        }
    };
    public static final Card Scrape = new Card("Draw 2", 2) {
        @Override
        public Destination onPlay(Main game, Entity none) {
            game.pendingDraw += 2;
            return Destination.EXILE;
        }
    };
    public static final Card DoubleTap = new Card("Attack\nTwice", 2) {
        public Destination onPlay(Main game, Entity enemy) {
            game.addDamageEvent(1, enemy);
            game.addDamageEvent(1, enemy);
            return Destination.DISCARD;
        }
    };

    // Testing even more queued attacks
    public static final Card MachineGun = new Card("Attack\nFour Times", 4) {
        public Destination onPlay(Main game, Entity enemy) {
            game.addDamageEvent(1, enemy);
            game.addDamageEvent(1, enemy);
            game.addDamageEvent(1, enemy);
            game.addDamageEvent(1, enemy);
            return Destination.EXILE;
        }
    };

    public static final Card Enrage = new Card("Damage\nx1.1", 1) {
        public Destination onPlay(Main game, Entity self) {
            game.player.damageBase += 0.1f;
            return Destination.EXILE;
        }
    };

    public static final Card Sharpen = new Card("Damage\n+1", 1) {
        public Destination onPlay(Main game, Entity self) {
            game.player.damageMod += 1;
            return Destination.EXILE;
        }
    };

    public static final Card Relax = new Card("Block 2\nDraw 2", 3) {
        public Destination onPlay(Main game, Entity self) {
            game.player.block += 2;
            game.pendingDraw += 2;
            return Destination.DISCARD;
        }
    };
}
