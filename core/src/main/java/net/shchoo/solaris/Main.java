package net.shchoo.solaris;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Card.Destination;
import net.shchoo.solaris.cards.Cards;
import net.shchoo.solaris.entity.Enemy;
import net.shchoo.solaris.entity.Player;
import net.shchoo.solaris.sys.EnemyGenerator;
import net.shchoo.solaris.utils.Timer;

import static net.shchoo.solaris.utils.MathUtils.geZero;

public class Main extends Game {
   public SpriteBatch batch;
   public BitmapFont smallFont;
   public BitmapFont bigFont;
   public FitViewport viewport;
   public CardGame cardGame;
   public ShapeRenderer shape;
   public Screen previousScreen = null;


   public int pendingDraw = 0;
   public boolean isPlayerTurn = true;
   public List<Timer> timers = new ArrayList<>();
   public Timer enemyTurnTimer = new Timer(100, () -> {});
   public Timer playerDamageTimer = new Timer(60, () -> {});

   public Player player = new Player();

   public EnemyGenerator enemyGenerator = new EnemyGenerator();

   public List<Card> BASE_DECK = new ArrayList<Card>() {{
       add(Cards.Attack);
       add(Cards.Attack);
       add(Cards.Attack);
       add(Cards.Scrape);
       add(Cards.DoubleTap);
       add(Cards.Defend);
       add(Cards.Defend);
       add(Cards.Defend);
   }};

   public void create() {
      batch = new SpriteBatch();
      shape = new ShapeRenderer();

      smallFont = new BitmapFont(Gdx.files.internal("comic-mono.fnt"));
      bigFont = new BitmapFont(Gdx.files.internal("comic-mono.fnt"));
      viewport = new FitViewport(8, 5);

      smallFont.setUseIntegerPositions(false);
      smallFont.getData().setScale(0.66f * (viewport.getWorldHeight() / Gdx.graphics.getHeight() ));
      bigFont.setUseIntegerPositions(false);
      bigFont.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

      timers.add(playerDamageTimer);
      timers.add(enemyTurnTimer);

      this.setScreen(new MainMenuScreen(this));
   }

   public void render() {
      super.render(); // important, apparently
   }

   public void dispose() {
      batch.dispose();
      smallFont.dispose();
      bigFont.dispose();
   }

   public Destination play(Card card, Entity enemy) {
      player.energy -= card.cost;
      return card.onPlay(this, enemy);
   }

   public void addEnemyDamageEvent(float damage, Runnable other) {
       enemyTurnTimer = new Timer(100, () -> {
           if (player.block > 0) {
               float remainingBlock = player.block - damage;
               player.health -= geZero(-remainingBlock);
               player.block = geZero(remainingBlock);
           } else {
               player.health -= damage;
           }
           other.run();
       });

   }

   public void addDamageEvent(float damage, Entity enemy)
   {
       // TODO replace queueable timers with a linked list, lmao
       Timer newDamageEvent = new Timer(60, () -> {
           enemy.health -= (player.damageBase * damage) + player.damageMod;
       });

       if (playerDamageTimer == null || !playerDamageTimer.isTicking()) {
           this.playerDamageTimer = newDamageEvent;
           playerDamageTimer.start();
       }
       else if (playerDamageTimer != null) {
           Timer queued = playerDamageTimer;
           while (queued.next != null) {
               queued = queued.next;
           }
           queued.next = newDamageEvent;
       }
   }

   public Enemy newEnemy() {
       return enemyGenerator.generate();
   }

   public void timers()
   {
       enemyTurnTimer.remaining -= 1;
       playerDamageTimer.remaining -= 1;
   }

   public void processCardEffects(List<Consumer<Main>> effects) {
      effects.forEach(c -> c.accept(this));
   }
}
