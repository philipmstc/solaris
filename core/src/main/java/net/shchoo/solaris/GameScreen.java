package net.shchoo.solaris;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import net.shchoo.solaris.ui.DisplayableString;
import net.shchoo.solaris.ui.Menu;

public class GameScreen extends DefaultInputScreen {
    public final Menu<DisplayableString> menu;
    public GameScreen(Main game) {
        super(game);
        this.menu = Menu.basicMenu(
            Arrays.asList("X", "O", "#"),
            () -> game.viewport.getWorldWidth() / 4, 
            () -> 0.33f + game.viewport.getWorldHeight() / 2, 
            2.0f, 
            0.0f);
    }
    
    @Override 
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        menu.render(game);
        game.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) { 
        if (keycode == Input.Keys.ENTER) { 
            switch (menu.selection) { 
                case 0: 
                    game.setScreen(new CardGameScreen(game));
                    dispose();
                    break;
            }
        }
        menu.handleKeyPress(keycode);

        return false;
    }
}
