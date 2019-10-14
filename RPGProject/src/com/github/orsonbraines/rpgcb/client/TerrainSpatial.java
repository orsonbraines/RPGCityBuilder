package com.github.orsonbraines.rpgcb.client;

import com.github.orsonbraines.rpgcb.core.Terrain;
import com.jme3.scene.Spatial;

/**
 *
 * @author Orson Baines
 */
public class TerrainSpatial {
    Terrain terrain;
    Spatial model;
    
    public TerrainSpatial(Terrain terrain, Spatial model){
        this.terrain = terrain;
        this.model = model;
    }
}
