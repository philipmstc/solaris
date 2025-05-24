package net.shchoo.solaris;

import java.util.List;
import java.util.function.Consumer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.cards.Card.Destination;

public class Main extends Game {
   public SpriteBatch batch;
   public BitmapFont smallFont;
   public BitmapFont bigFont;
   public FitViewport viewport;
   public CardGame cardGame;
   public ShapeRenderer shape;

   public float enemyHealth = 10;
   public float enemyMaxHealth = 10;
   public float playerHealth = 8;
   public float playerMaxHealth = 12;
   public float playerBlock = 0;
   public int playerEnergy = 4;
   public int playerMaxEnergy = 4;

   public int pendingDraw = 0;
   public boolean isPlayerTurn = true;
   public float enemyTurnTimer = 0;
   public float enemyTurnTotal = 100;

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

   public void processCardEffects(List<Consumer<Main>> effects) {
      effects.forEach(c -> c.accept(this));
   }
}
