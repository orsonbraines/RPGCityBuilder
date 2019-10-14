package com.github.orsonbraines.rpgcb.client;

import com.github.orsonbraines.rpgcb.core.Terrain;
import com.github.orsonbraines.rpgcb.db.ModelLoader;
import com.github.orsonbraines.rpgcb.db.TerrainLoader;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Orson Baines
 */
public class GameScene {
    Node root;
    AssetManager assetManager;
    List<TerrainSpatial> ts;

    public GameScene(Node root, AssetManager assetManager) {
        this.root = root;
        this.assetManager = assetManager;
        ts = new ArrayList<>();
        for(int i=0;i<5;i++){
            Spatial s = makeTree(i);
            s.setLocalTranslation(5,0,-10*i);
            ts.add(new TerrainSpatial(new Terrain(),s));
        }
        for(TerrainSpatial s : ts){
            root.attachChild(s.model);
        }
        loadTerrain();
    }
    
    private void loadTerrain(){
        String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + "/assets/db/db.sqlite3";
        try(Connection con = DriverManager.getConnection(url);){
            System.out.println("Connection to SQLite has been established.");
            List<Spatial> terrains = TerrainLoader.loadAll(assetManager, con);
            for(Spatial s : terrains){
                root.attachChild(s);
                System.out.println("s: " + s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Spatial makeTree(int i){
        Node node = new Node("node " + i);
        float th = 4;
        float tr = 1;
        float lr = 2;
        Box treeTrunk = new Box(tr/2,th/2,tr/2);
        Geometry treeTrunkGeom = new Geometry("treeTrunk", treeTrunk);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Brown);
        treeTrunkGeom.setMaterial(mat2);
        treeTrunkGeom.setLocalTranslation(0,th/2,0);
        
        Sphere treeLeaves = new Sphere(30,30,lr);
        Geometry treeLeaveGeom = new Geometry("treeLeaves", treeLeaves);
        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color",  new ColorRGBA(0,0.5f,0,0));
        treeLeaveGeom.setMaterial(mat3);
        treeLeaveGeom.setLocalTranslation(0,th + lr/2,0);
        
        node.attachChild(treeTrunkGeom);
        node.attachChild(treeLeaveGeom);
        return node;
    }
}
