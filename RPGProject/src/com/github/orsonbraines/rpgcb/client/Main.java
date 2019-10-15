package com.github.orsonbraines.rpgcb.client;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import static com.jme3.math.FastMath.DEG_TO_RAD;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;


/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    Node chnode;
    Vector2f chdir = new Vector2f(1,0);
    ThirdPersonCamera camera;

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
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        
        chnode = new Node("chnode");
        Vector3f camCenter = new Vector3f(-5,5,-20);
        chnode.setLocalTranslation(camCenter);
        Spatial ch = assetManager.loadModel("Models/character/RPGcharacter1pcs.j3o");
        ch.setLocalTranslation(0,-5,0);
        chnode.attachChild(ch);
        rootNode.attachChild(chnode);
        
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(0,1,0));
        camera = new ThirdPersonCamera(cam,0.5f,0,20,camCenter);
        
        System.out.println(cam.getViewMatrix());
        System.out.println(cam.getProjectionMatrix());

        rootNode.attachChild(geom);

        viewPort.setBackgroundColor(ColorRGBA.Blue);
       
                
        System.out.println(rootNode.getChildren());
        
        initKeys(); 
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("CamLeft",  new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("CamRight",   new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("CamUp",  new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("CamDown", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Forward",  new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Backward",  new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        // Add the names to the action listener.
        inputManager.addListener(analogListener, "Left", "Right", "Forward","Backward",
                "CamLeft","CamRight","CamUp","CamDown");

    }
    
    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            System.out.println(name + " " + value);
            float dAngle = value * speed;
            float d = 5 * value * speed;
            switch (name) {
                case "CamLeft":
                    camera.incPhi(dAngle);
                    break;
                case "CamRight":
                    camera.incPhi(-dAngle);
                    break;
                case "CamUp":
                    camera.incTheta(-dAngle);
                    break;
                case "CamDown":
                    camera.incTheta(dAngle);
                    break;
                case "Forward":
                    camera.move(chdir.getX() * d, 0, chdir.getY() * d);
                    break;
                case "Backward":
                    camera.move(-chdir.getX() * d, 0, -chdir.getY() * d);
                    break;
                case "Left":
                    camera.move(chdir.getY() * d, 0, -chdir.getX() * d);
                    break;
                case "Right":
                    camera.move(-chdir.getY() * d, 0, chdir.getX() * d);
                    break;
                default:
                    break;
            }
            chnode.setLocalTranslation(camera.getCX(), camera.getCY(), camera.getCZ());
        }
    };
}
