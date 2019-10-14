package com.github.orsonbraines.rpgcb.client;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Box ground = new Box(10000,0.1f,10000);
        Geometry geom = new Geometry("ground", ground);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geom.setMaterial(mat);
        geom.setLocalTranslation(new Vector3f(-5000,0,-5000));

        
        GameScene scene = new GameScene(rootNode, assetManager);
        
        cam.setLocation(new Vector3f(0,1,0));

        rootNode.attachChild(geom);
        System.out.println(rootNode.getChildren());
        System.out.println(((Node)rootNode.getChild("node 0")).getChildren());
        viewPort.setBackgroundColor(ColorRGBA.Blue);

    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
