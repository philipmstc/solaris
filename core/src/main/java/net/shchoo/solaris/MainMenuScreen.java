package net.shchoo.solaris;

import java.util.Arrays;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import net.shchoo.solaris.ui.Menu;

public class MainMenuScreen extends DefaultInputScreen {
    private final Menu<String> menu;

    public MainMenuScreen(final Main game) { 
        super(game);
        this.menu = Menu.basicMenu(
            Arrays.asList("New Game", "Continue", "Statistics", "Exit"),
            () -> game.viewport.getWorldWidth() / 2,
            () -> -0.33f + game.viewport.getWorldHeight() / 2,
            0.0f,
            0.33f
        );
    }
    
    private boolean isInitted = false;

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
    
        game.batch.begin();
        game.bigFont.draw(game.batch, 
            "S O L A R I S",
            game.viewport.getWorldWidth() /2,
            1.2f + game.viewport.getWorldHeight() /2,
            0,
            Align.center,
            false
        );
        menu.render(game);

        game.batch.end();
    }    
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            switch (menu.current) { 
                case 0: 
                    game.setScreen(new GameScreen(game));
                    dispose();
                    break;
                case 1: 
                    game.setScreen(new GameScreen(game));
                    dispose();
                    break;
                case 2: 
                    System.exit(1);
            }
        }
        else {
            menu.handleKeyPress(keycode);
        }
        return false;
    }
}
