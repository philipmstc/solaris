package net.shchoo.solaris.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.Entity;
import net.shchoo.solaris.Main;

import static net.shchoo.solaris.utils.MathUtils.geZero;

public class Enemy extends Entity {

    @Override
    public void render(Main game, float x, float y, float delta) {
        game.batch.begin();
        game.smallFont.setColor(Color.WHITE);
        game.smallFont.draw(game.batch, "Enemy HP: ", x - 1.30f, y+0.180f, 0.00f, Align.left, false);
        game.batch.end();
        game.shape.begin(ShapeType.Filled);
        game.shape.setColor(Color.RED);
        game.shape.rect(
            game.viewport.getWorldWidth()/3,
            game.viewport.getWorldHeight() - 2.0f,
            (game.viewport.getWorldWidth() / 3 ) * geZero(health / maxHealth),
            0.2f );
        game.shape.end();
    }
}
