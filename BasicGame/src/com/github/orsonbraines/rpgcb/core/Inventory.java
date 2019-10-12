package com.github.orsonbraines.rpgcb.core;

import java.util.Map;
import java.util.TreeMap;

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
    private final Map<Item,Integer> items;
    private final int size, maxWeight;
    private int numElements, weight;

    public Inventory(int size, int maxWeight) {
        this.size = size;
        this.maxWeight = maxWeight;
        items = new TreeMap<>();
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
        Integer n = items.get(item);
        // the item is not currently in the inventory and must be added 
        if(n == null){
            if(numElements == size) throw new InventoryFullException("All item slots are full");
            items.put(item,1);
            numElements++;
        }
        else{
            if(!item.isStackable()){
                if(numElements == size) throw new InventoryFullException("All item slots are full");
                numElements++;
            }
            items.replace(item, n+1);
        }
        weight += item.getWeight();
    }
}
