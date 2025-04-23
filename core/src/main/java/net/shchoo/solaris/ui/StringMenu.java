package net.shchoo.solaris.ui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

import net.shchoo.solaris.Main;
import net.shchoo.solaris.utils.Provider;

public class StringMenu extends Menu<DisplayableString> {

    private float xPos;
    private float yPos;
    private boolean isInit = false;

    public StringMenu(List<String> selections, Map<Integer, Integer> inputMap, Provider<Float> xStart,
            Provider<Float> yStart, float xOffset, float yOffset) {
        super(selections.stream().map(DisplayableString::new).collect(Collectors.toList()),
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
    public void render(Main game) {
        if (!isInit) { 
            init();
            isInit = false;
        }
        for (int i = 0; i < count; i++) {
            float sequence = count - i - 1;
            if (selection == i) {
                game.smallFont.setColor(Color.CYAN);
            } else {
                game.smallFont.setColor(Color.WHITE);
            }
            game.smallFont.draw(game.batch,
                    this.selections.get(i).selection,
                    (xOffset * sequence) + xPos,
                    (yOffset * sequence) + yPos,
                    0,
                    Align.center,
                    false);
        }
    }

}