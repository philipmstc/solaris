package net.shchoo.solaris.ui;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.Main;
import net.shchoo.solaris.utils.Provider;

public class StringMenu extends Menu<String> {

    private float xPos;
    private float yPos;

    public StringMenu(List<String> selections, Map<Integer, Integer> inputMap, Provider<Float> xStart,
            Provider<Float> yStart, float xOffset, float yOffset) {
        super(selections,
                inputMap,
                xStart,
                yStart,
                xOffset,
                yOffset);
    }

    @Override
    public void init() {
        xPos = xStart.get();
        yPos = yStart.get();
    }

    @Override
    public void renderImpl(Main game) {
        for (int i = 0; i < selections.size(); i++) {
            float sequence = selections.size() - i - 1;
            if (current == i) {
                game.smallFont.setColor(Color.CYAN);
            } else {
                game.smallFont.setColor(Color.WHITE);
            }
            game.smallFont.draw(game.batch,
                    this.selections.get(i),
                    (xOffset * sequence) + xPos,
                    (yOffset * sequence) + yPos,
                    0,
                    Align.center,
                    false);
        }
    }

}