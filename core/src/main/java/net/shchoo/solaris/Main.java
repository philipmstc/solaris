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
import net.shchoo.solaris.utils.Provider;
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

   public float enemyHealth = 5;
   public float enemyMaxHealth = 5;
   public float playerHealth = 8;
   public float playerMaxHealth = 12;
   public float playerBlock = 0;
   public int playerEnergy = 4;
   public int playerMaxEnergy = 4;
   public float playerDamageBase = 1;
   public float playerDamageMod = 0;

   public int pendingDraw = 0;
   public boolean isPlayerTurn = true;
   public List<Timer> timers = new ArrayList<>();
   public Timer enemyTurnTimer = new Timer(100, () -> {});
   public Timer playerDamageTimer = new Timer(60, () -> {});

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

   public Destination play(Card card) {
      playerEnergy -= card.cost;
      return card.onPlay(this);
   }

   public void addEnemyDamageEvent(float damage, Runnable other) {
       enemyTurnTimer = new Timer(100, () -> {
           if (playerBlock > 0) {
               float remainingBlock = playerBlock - damage;
               playerHealth -= geZero(-remainingBlock);
               playerBlock = geZero(remainingBlock);
           } else {
               playerHealth -= damage;
           }
           other.run();
       });

   }

   public void addDamageEvent(float damage)
   {
       // TODO replace queueable timers with a linked list, lmao
       Timer newDamageEvent = new Timer(60, () -> {
           this.enemyHealth -= (playerDamageBase * damage) + playerDamageMod;
       });

       if (playerDamageTimer != null && !playerDamageTimer.isTicking()) {
           this.playerDamageTimer = newDamageEvent;
           playerDamageTimer.start();
       }
       else if (playerDamageTimer != null) { // queue subsequent damages?
           Runnable old = playerDamageTimer.onStop;
           playerDamageTimer.onStop = () -> {
               old.run();
               playerDamageTimer = newDamageEvent;
               playerDamageTimer.start();
           };
       }
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
