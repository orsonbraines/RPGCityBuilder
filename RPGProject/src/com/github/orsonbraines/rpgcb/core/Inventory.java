package com.github.orsonbraines.rpgcb.core;

/**
 *
 * @author Orson Baines
 */
public class Inventory {
    class InventoryFullException extends Exception{
        InventoryFullException(String msg){
            super(msg);
        }
    }
    private final Items items;
    private final int size, maxWeight;
    private int numElements, weight;

    public Inventory(int size, int maxWeight) {
        this.size = size;
        this.maxWeight = maxWeight;
        items = new Items();
        numElements = 0;
        weight = 0;
    }

    public int getSize() {
        return size;
    }

    public int getMazWeight() {
        return maxWeight;
    }
    
    public void add(Item item) throws InventoryFullException{
        if(weight + item.getWeight() > maxWeight)
            throw new InventoryFullException("Item too heavy");
        if(items.has(item)){
            if(!item.isStackable()){
                if(numElements == size) throw new InventoryFullException("All item slots are full");
                numElements++;
            }
            items.add(item);
        }
        else{
            if(numElements == size) throw new InventoryFullException("All item slots are full");
            items.add(item);
            numElements++;
        }
        weight += item.getWeight();
    }
}
