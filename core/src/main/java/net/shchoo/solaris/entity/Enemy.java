package net.shchoo.solaris.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.shchoo.solaris.Entity;
import net.shchoo.solaris.Main;

public class Enemy implements Entity {
    @Override
    public void render(Main game, float x, float y, float delta) {
        game.shape.begin(ShapeType.Filled);
        game.shape.setColor(Color.RED);
        game.shape.rect(
            game.viewport.getWorldWidth()/3,
            game.viewport.getWorldHeight() - 2.0f,
            (game.viewport.getWorldWidth() / 3 ) * (game.enemyHealth / game.enemyMaxHealth),
            0.2f );
        game.shape.end();
    }
}