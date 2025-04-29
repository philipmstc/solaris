package net.shchoo.solaris.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.shchoo.solaris.Entity;
import net.shchoo.solaris.Main;

public class Player implements Entity {

    @Override
    public void render(Main game, float x, float y, float delta) {
        game.shape.begin(ShapeType.Filled);
        game.shape.setColor(Color.GREEN);
        game.shape.rect(
            game.viewport.getWorldWidth()/3, 
            game.viewport.getWorldHeight() - 1.5f,
            (game.viewport.getWorldWidth() / 3 ) * (game.playerHealth / game.playerMaxHealth),
            0.2f);
        game.shape.end();
        game.shape.begin(ShapeType.Filled);
        game.shape.setColor(Color.CYAN);
        game.shape.rect(
            game.viewport.getWorldWidth()/3, 
            game.viewport.getWorldHeight() - 1.5f,
            (game.viewport.getWorldWidth() / 3 ) * (game.playerBlock / game.playerMaxHealth),
            0.2f);
        game.shape.end();
    }

}