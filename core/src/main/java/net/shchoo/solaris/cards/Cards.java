package net.shchoo.solaris.cards;

import com.badlogic.ashley.utils.Bag;
import net.shchoo.solaris.CardGame;
import static net.shchoo.solaris.cards.Card.CardType;

public class Cards {
    public static final Bag cards = new Bag<Card>() {{ /*
        add(new Card("C/X Attack",
            1, CardType.COMBAT, (game) -> { game.dealDamage(game.targetedEnemy, 1); }, 
            1, CardType.EXPLORATION, (game) -> { game.exploreAttackAnalogue(1);})
        );
        add(new Card("C/X Defend",
            1, CardType.COMBAT, (game) -> { game.blockDamage(1);}, 
            1, CardType.EXPLORATION, (game) -> { game.exploreBlockAnalogue(1);})
        );
        add(new Card("C/R Buff",                   // buff attack by x for y turns
            1, CardType.COMBAT, (game) -> { game.buff(game.player, game.stats.attack, 3, 1); },
            1, CardType.RESEARCH, (game) -> { game.globalBuff(game.player, game.stats.attack, 1); })
        );
        add(new Card("X/R Buff", 
            1, CardType.EXPLORATION, (game) -> { game.buff(game.player, game.stats.explore, 3, 1); },
            1, CardType.RESEARCH, (game) -> { game.globalBuff(game.player, game.stats.explore, 1); })
        );
        add(new Card("C/R Adrenochrome",
            0, CardType.COMBAT, (game) -> { 
                game.dealDamage(game.player, 1);
                game.buff(game.player, game.stats.attack, 1, 1);
                game.buff(game.player, game.stats.defence, 1, 1);
                game.exhaust();    
            },
            0, CardType.RESEARCH, (game) -> { 
                    game.addEffect(game.startOfTurn, 
                        game.dealDamage(game.player, 10));
                    game.addEffect(game.startOfTurn, 
                        game.addIntangible(game.player, 1));
             })
        );
  */  }};
}
