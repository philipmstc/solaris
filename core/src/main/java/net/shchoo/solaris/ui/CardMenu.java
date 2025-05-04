package net.shchoo.solaris.ui;

import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.Main;
import net.shchoo.solaris.cards.Card;
import net.shchoo.solaris.utils.Provider;

public class CardMenu extends Menu<Card> {
    private float xPos;
    private float yPos;
    private boolean isInit = false;

    public CardMenu(List<Card> cards,
                    Provider<Float> xStart, 
                    Provider<Float> yStart, 
                    float xOffset, 
                    float yOffset ) {
        super(cards,
              xStart, 
              yStart, 
              xOffset, 
              yOffset);
    }

    public Card removeCurrent() {
        return this.selections.remove(current);
    }

    @Override 
    public void init() {
        xPos = xStart.get();
        yPos = yStart.get();
    }

    @Override
    public void render(Main game) { 
        if (!isInit) { 
            init();
            isInit = false;
        }
        for (int i = 0; i < selections.size(); i++) { 
            float sequence = i ;
            if (current == i) {
                game.smallFont.setColor(Color.CYAN);
            }
            else { 
                game.smallFont.setColor(Color.WHITE);
            }
            game.shape.begin(ShapeType.Line);
			game.shape.setColor(Color.YELLOW);
			game.shape.rect(
                (xOffset * (sequence - 0.5f)) + xPos,
                (yOffset * (sequence - 0.5f)) + yPos - 0.5f, 
                xOffset - (xOffset*0.05f), 
                1.0f);
			game.shape.end();
            
            game.batch.begin(); 
            game.smallFont.draw(game.batch,
                this.selections.get(i).name, 
                (xOffset * sequence) + xPos, 
                (yOffset * sequence) + yPos,
                0,
                Align.center,
                false
            );
            game.batch.end();
        }
    }
}