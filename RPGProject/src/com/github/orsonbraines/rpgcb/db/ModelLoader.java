package com.github.orsonbraines.rpgcb.db;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Orson Baines
 */
public class ModelLoader {
    static String loadAllQuery = "SELECT * from model;";
    public static Map<String,Spatial> loadAll(AssetManager assetManager, Connection con) throws SQLException{
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(loadAllQuery);
        Map<String,Spatial> res = new TreeMap<>();
        while(rs.next()){
            res.put(rs.getString(1), assetManager.loadModel(rs.getString(2)));
        }
        return res;
    }
}
