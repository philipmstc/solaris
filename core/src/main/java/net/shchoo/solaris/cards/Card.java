package net.shchoo.solaris.cards;

import com.badlogic.ashley.core.Entity;

import net.shchoo.solaris.Main;

public abstract class Card extends Entity {
   // Entity has Components
   // Entity is handled by an EntityEngine 
   // has a "is drawable" component 
   // has a "is in the deck" component
   
    public final String name;

    public abstract Destination onPlay(Main game);

    public Card(String name) {
        this.name = name;
    }

    public enum Destination {
        DISCARD,
        DRAW,
        EXILE,
        REMOVED
    }

}
