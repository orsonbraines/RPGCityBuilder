package com.github.orsonbraines.rpgcb.client;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;


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

        
        makeTree(new Vector3f(0,0,-10),4,1,2);
        makeTree(new Vector3f(0,0,10),4,1,2);
        makeTree(new Vector3f(-10,0,0),4,1,2);
        makeTree(new Vector3f(10,0,0),4,1,2);
        
        cam.setLocation(new Vector3f(0,1,0));

        rootNode.attachChild(geom);
        viewPort.setBackgroundColor(ColorRGBA.Blue);

    }
    
    private void makeTree(Vector3f base,float th, float tr, float lr){
        Box treeTrunk = new Box(tr/2,th/2,tr/2);
        Geometry treeTrunkGeom = new Geometry("treeTrunk", treeTrunk);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Brown);
        treeTrunkGeom.setMaterial(mat2);
        treeTrunkGeom.setLocalTranslation(base.add(0,th/2,0));
        
        Sphere treeLeaves = new Sphere(50,50,lr);
        Geometry treeLeaveGeom = new Geometry("treeLeaves", treeLeaves);
        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color",  new ColorRGBA(0,0.5f,0,0));
        treeLeaveGeom.setMaterial(mat3);
        treeLeaveGeom.setLocalTranslation(base.add(0,th + lr/2,0));
        
        System.out.println(treeTrunkGeom.getLocalTransform());
        System.out.println(treeLeaveGeom.getLocalTransform());
        
        rootNode.attachChild(treeTrunkGeom);
        rootNode.attachChild(treeLeaveGeom);
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
