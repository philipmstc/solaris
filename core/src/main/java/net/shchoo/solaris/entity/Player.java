package net.shchoo.solaris.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.Entity;
import net.shchoo.solaris.Main;

import static net.shchoo.solaris.utils.MathUtils.geZero;

public class Player implements Entity {

    public int startingHandSize = 5;
    @Override
    public void render(Main game, float x, float y, float delta) {
        game.batch.begin();
        game.smallFont.setColor(Color.WHITE);
        game.smallFont.draw(game.batch, "Player HP:", x - 1.45f, y+0.180f, 0.00f, Align.left, false);
        game.batch.end();
        game.shape.begin(ShapeType.Filled);
        game.shape.setColor(Color.GREEN);
        game.shape.rect(
            game.viewport.getWorldWidth()/3,
            game.viewport.getWorldHeight() - 1.5f,
            (game.viewport.getWorldWidth() / 3 ) * geZero(game.playerHealth / game.playerMaxHealth),
            0.2f);
        game.shape.end();
        game.shape.begin(ShapeType.Filled);
        game.shape.setColor(Color.CYAN);
        game.shape.rect(
            game.viewport.getWorldWidth()/3,
            game.viewport.getWorldHeight() - 1.5f,
            (game.viewport.getWorldWidth() / 3 ) * geZero(game.playerBlock / game.playerMaxHealth),
            0.2f);
        game.shape.end();
    }

}
