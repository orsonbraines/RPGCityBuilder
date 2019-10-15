/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.orsonbraines.rpgcb.client;

import static com.jme3.math.FastMath.DEG_TO_RAD;
import static com.jme3.math.FastMath.cos;
import static com.jme3.math.FastMath.sin;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author Orson Baines
 */
public class ThirdPersonCamera {
    private Camera camera;
    private float theta, phi, r;
    private Vector3f center;
    private Vector3f loc,worldUp;
    private final float minTheta = 10 * DEG_TO_RAD;
    private final float maxTheta = 80 * DEG_TO_RAD;

    public ThirdPersonCamera(Camera camera, float theta, float phi, float r, Vector3f center) {
        this.camera = camera;
        this.theta = theta;
        this.phi = phi;
        this.r = r;
        this.center = center;
        loc = new Vector3f();
        worldUp = new Vector3f(0,1,0);
        update();
    }
    
    private void update(){
        loc.set(r*sin(theta)*cos(phi) + center.getX(),r*cos(theta) + center.getY(), 
            r*sin(theta)*sin(phi) + center.getZ());
        
        System.out.println(loc);
        camera.setLocation(loc);
        camera.lookAt(center, worldUp);
    }
    
    public void incTheta(float delta){
        theta += delta;
        if(theta > maxTheta) theta = maxTheta;
        if(theta < minTheta) theta = minTheta;
        update();
    }
    
    public void incPhi(float delta){
        phi += delta;
        update();
    }
    
    public void move(float x, float y, float z){
        center.addLocal(x, y, z);
        update();
    }
    
    public float getCX(){
        return center.getX();
    }
    
    public float getCY(){
        return center.getY();
    }
    
    public float getCZ(){
        return center.getZ();
    }
}
