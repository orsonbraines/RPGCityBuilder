package com.github.orsonbraines.rpgcb.core;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Orson Baines
 */
public class Items {
    private final Map<Item,Integer> items;
    
    public Items(){
        items = new TreeMap<>();
    }
    
    public boolean has(Item item){
        return items.get(item) != null;
    }
    
    public void add(Item item){
        Integer n = items.get(item);
        items.put(item, n == null ? 1 : n + 1);
    }
}
