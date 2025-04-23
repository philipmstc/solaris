package net.shchoo.solaris;

import java.util.List;
import java.util.function.Consumer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {
   public SpriteBatch batch;
   public BitmapFont smallFont;
   public BitmapFont bigFont;
   public FitViewport viewport;
   public CardGame cardGame;
   public ShapeRenderer shape;

   public void create() {
      batch = new SpriteBatch();
      shape = new ShapeRenderer(); 
      
      
      smallFont = new BitmapFont(Gdx.files.internal("comic-mono.fnt"));
      bigFont = new BitmapFont(Gdx.files.internal("comic-mono.fnt"));
      viewport = new FitViewport(8, 5);

      // shape.scale(
      //    viewport.getWorldWidth()/Gdx.graphics.getWidth(),
      //    viewport.getWorldHeight() / Gdx.graphics.getHeight(),
      //    1   
      // );
      
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

   public void processCardEffects(List<Consumer<Main>> effects) {
      effects.forEach(c -> c.accept(this));
   }
}
