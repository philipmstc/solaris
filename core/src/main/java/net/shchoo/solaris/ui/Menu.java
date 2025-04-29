package net.shchoo.solaris.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Input;
import net.shchoo.solaris.Main;
import net.shchoo.solaris.utils.Provider;

public abstract class Menu<D> {
    public int current = 0;
    public int count;
    public D selection;
    protected List<D> selections;
    protected final float xOffset;
    protected final float yOffset;
    protected final Provider<Float> xStart;
    protected final Provider<Float> yStart;
    public final Map<Integer, Integer> inputMap;

    public Menu(List<D> selections, 
                Provider<Float> xStart, 
                Provider<Float> yStart, 
                float xOffset, 
                float yOffset) {
        this.selections = selections;
        this.count = this.selections.size();
        this.inputMap = xOffset != 0 ? Menu.horizontalKeyboardInput : Menu.verticalKeyboardInput;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public Menu(List<D> selections,
            Map<Integer, Integer> inputMap,
            Provider<Float> xStart, Provider<Float> yStart, float xOffset, float yOffset) {
        this.count = selections.size();
        this.selections = selections;
        this.inputMap = inputMap;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    // this must be called within Screen::render between batch begin/end
    public abstract void render(Main game);
    
    // to avoid repeatedly loading lazy parameters
    public abstract void init();

    public void handleKeyPress(int keycode) { 
        if (inputMap.containsKey(keycode)) {
            int dir = inputMap.get(keycode);
            if (dir == 1) { 
                current = (current + 1) % count;
            }
            else if (dir == -1) { 
                if (current == 0) { 
                    current = count - 1;
                }
                else { 
                    current = current - 1;
                }
            }
            selection = selections.get(current);
        }
    }

    public void addSelection(D selection) { 
        this.selections.add(selection);
        this.count = selections.size();
    }

    public static Menu<DisplayableString> basicMenu(
            List<String> selections,
            Provider<Float> xStart,
            Provider<Float> yStart,
            float xOffset,
            float yOffset) {
        return new StringMenu(
                selections,
                xOffset != 0 ? Menu.horizontalKeyboardInput : Menu.verticalKeyboardInput,
                xStart,
                yStart,
                xOffset,
                yOffset);
    }

    private static Map<Integer, Integer> horizontalKeyboardInput = new HashMap<Integer, Integer>() {
        {
            put(Input.Keys.LEFT, -1);
            put(Input.Keys.RIGHT, 1);
        }
    };

    private static Map<Integer, Integer> verticalKeyboardInput = new HashMap<Integer, Integer>() {
        {
            put(Input.Keys.UP, -1);
            put(Input.Keys.DOWN, 1);
        }
    };

}