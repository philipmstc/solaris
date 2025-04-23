package net.shchoo.solaris;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import net.shchoo.solaris.ui.CardMenu;

public class CardGameScreen extends DefaultInputScreen {
    private final CardMenu menu;
    public CardGameScreen(Main game) {
        super(game);
        this.menu = new CardMenu(
            Arrays.asList("Attack\n1 damage", "Defend\n1 block", "Scrape\nDraw 3"),
            () -> game.viewport.getWorldWidth() / 6, 
            () -> -2.0f + (game.viewport.getWorldHeight() / 2),
            1.1f, 
            0.0f
        );
    }
    
    @Override 
    public void render(float delta) { 
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.shape.setProjectionMatrix(game.viewport.getCamera().combined);
        
        menu.render(game);
    }

    @Override
    public boolean keyDown(int keycode) {
        menu.handleKeyPress(keycode);
        return false;
    }
}
