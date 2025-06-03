package net.shchoo.solaris.cards;


import net.shchoo.solaris.Main;
import net.shchoo.solaris.Entity;

public abstract class Card extends com.badlogic.ashley.core.Entity{
   // Entity has Components
   // Entity is handled by an EntityEngine
   // has a "is drawable" component
   // has a "is in the deck" component

    public final String name;
    public final int cost;

    public abstract Destination onPlay(Main game, Entity target);

    public Card(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public enum Destination {
        DISCARD,
        DRAW,
        EXILE,
        REMOVED
    }

}
