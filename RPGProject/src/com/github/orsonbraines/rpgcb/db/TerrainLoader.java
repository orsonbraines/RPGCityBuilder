package com.github.orsonbraines.rpgcb.db;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Orson Baines
 */
public class TerrainLoader {
    static String loadAllQuery = "SELECT model.path_str, terrain.x, terrain.y, terrain.z " +
            "from terrain inner join model on terrain.model = model.name;";
    public static List<Spatial> loadAll(AssetManager assetManager, Connection con) throws SQLException{
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(loadAllQuery);
        List<Spatial> res = new ArrayList<>();
        while(rs.next()){
            Spatial spatial = assetManager.loadModel(rs.getString(1));
            spatial.setLocalTranslation(rs.getFloat(2), rs.getFloat(3), rs.getFloat(4));
            res.add(spatial);
        }
        return res;
    }
}
