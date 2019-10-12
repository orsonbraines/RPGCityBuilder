package com.github.orsonbraines.rpgcb.core;

/**
 *
 * @author Orson Baines
 */
public class Item {
    private final int weight, value;
    private final boolean stackable;
    private final String description;
    private final int id;

    public Item(int weight, int value, boolean stackable, String description, int id) {
        this.weight = weight;
        this.value = value;
        this.stackable = stackable;
        this.description = description;
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public boolean isStackable() {
        return stackable;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString(){
        return description;
    }
}
