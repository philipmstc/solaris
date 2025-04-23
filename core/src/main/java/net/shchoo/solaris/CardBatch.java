package net.shchoo.solaris;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CardBatch {
    boolean isStarted = false;
    List<Consumer<Main>> batch; 
    final Main game;

    public CardBatch(Main game) {
        this.game = game;
    }

    public void start() {
        if (isStarted) {
            throw new RuntimeException("Cannot start a batch if the previous was not ended");
        }
        batch = new ArrayList<>();
        isStarted = true;
    }

    public void end() {
        if (!isStarted) { 
            throw new RuntimeException("Cannot end a batch if it was not started!");
        }
        game.processCardEffects(batch);
        isStarted = false;
    }

}