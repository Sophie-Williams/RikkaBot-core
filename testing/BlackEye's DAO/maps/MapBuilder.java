package com.rikkabot.rikkabotcore.dao.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.dao.collectables.Collectable;
import com.rikkabot.rikkabotcore.dao.maps.portals.Portal;
import com.rikkabot.rikkabotcore.dao.maps.stations.Station;
import com.rikkabot.rikkabotcore.dao.npcs.NPC;
import com.manulaiko.blackeye.launcher.GameManager;
import com.manulaiko.blackeye.launcher.Settings;
import com.rikkabot.rikkabotcore.dao.Builder;
import com.manulaiko.blackeye.util.Tools;

/**
 * Map builder.
 * ============
 * 
 * Builds `Map` objects.
 * 
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class MapBuilder implements Builder<Map>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    @Override
    public Map build(JSONObject json)
    {
        Map map = null;
        
        try {
            int factions_id = 0;

            try {
                factions_id = json.getInt("factions_id");
            } catch(Exception e) {
                // This might be set to NULL, therefor, the map doesn't belong to anyone
            }

            map = new Map(
                    json.getInt("id"),
                    json.getString("name"),
                    (json.getInt("is_pvp") == 1),
                    (json.getInt("is_starter") == 1),
                    factions_id,
                    json.getInt("scale_factor")
            );
            
            if(Settings.loadNPCs) {
                this._setNPCs(map, json.getJSONArray("npcs"));
            }
            if(Settings.loadPortals) {
                this._setPortals(map, json.getJSONArray("portals"));
            }
            if(Settings.loadStations) {
                this._setStations(map, json.getJSONArray("stations"));
            }
            if(Settings.loadCollectables) {
                this._setCollectables(map, json.getJSONArray("collectables"));
            }
        } catch(Exception e) {
            Console.println("Couldn't build map for `"+ json.toString() +"`");
            Console.debug(e.getMessage());
        }
        
        return map;
    }

    /**
     * Sets map's NPCs.
     * 
     * @param map  Map to build.
     * @param json JSON with NPCs.
     */
    private void _setNPCs(Map map, JSONArray json)
    {
        java.util.Map<Integer, NPC> npcs = new HashMap<>();

        Console.debug("Loading npcs for `"+ json.toString() +"`...");
        for(int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            
            int amount = obj.getInt("amount");
            int id     = obj.getInt("npcs_id");

            npcs.putAll(this._buildNPCMap(amount, id, map));
        }

        Console.debug("Done loading npcs!");

        map.npcs(npcs);
    }

    /**
     * Builds the map of NPCs.
     * 
     * @param amount Length of the map.
     * @param id     NPC ID.
     * @param map    Map instance.
     */
    private java.util.Map<Integer, NPC> _buildNPCMap(int amount, int id, Map map)
    {
        java.util.Map<Integer, NPC> npcs = new HashMap<>();

        for(int i = 0; i < amount; i++) {
            NPC npc = GameManager.npcs.find(id).clone();

            npc.entityID(-i);
            npc.position(Tools.getRandomPointByMapScale(map.scaleFactor()));
            npc.map(map);
            npc.canBeAttacked(true);

            npcs.put(npc.entityID(), npc);
        }
        
        return npcs;
    }

    /**
     * Sets map's portals.
     *
     * @param map  Building map.
     * @param json Portals JSON.
     */
    private void _setPortals(Map map, JSONArray json)
    {
        List<Portal> portals = new ArrayList<>();

        Console.debug("Loading portals for `"+ json.toString() +"`...");
        for(int i = 0; i < json.length(); i++) {
            int id = json.getInt(i);

            Portal p = GameManager.maps.portals().find(id);
            p.map(map);

            portals.add(p);
        }

        Console.debug("Done loading portals!");

        map.portals(portals);
    }

    /**
     * Sets map's stations.
     *
     * @param map  Building map.
     * @param json Stations JSON.
     */
    private void _setStations(Map map, JSONArray json)
    {
        List<Station> stations = new ArrayList<>();

        Console.debug("Loading stations for `"+ json.toString() +"`...");
        for(int i = 0; i < json.length(); i++) {
            int id = json.getInt(i);

            Station s = GameManager.maps.stations().find(id);
            s.map(map);

            stations.add(s);
        }

        Console.debug("Done loading stations!");

        map.stations(stations);
    }

    /**
     * Sets map's collectables.
     *
     * @param map  Building map.
     * @param json Collectables JSON.
     */
    private void _setCollectables(Map map, JSONArray json)
    {
        List<Collectable> collectables = new ArrayList<>();

        Console.debug("Loading collectables for `"+ json.toString() +"`...");
        for(int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);

            int id = obj.getInt("id");
            int amount = obj.getInt("amount");

            collectables.addAll(this._buildCollectablesList(id, amount, map));
        }

        Console.debug("Done loading collectables!");

        map.collectables(collectables);
    }

    /**
     * Builds and returns a list of collectables.
     *
     * @param id     ID of the collectable.
     * @param amount Amount of collectables.
     * @param map    Map instance.
     *
     * @return List of collectables.
     */
    private List<Collectable> _buildCollectablesList(int id, int amount, Map map)
    {
        List<Collectable> collectables = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            Collectable c = GameManager.collectables.find(id).clone();

            c.entityID(-i);
            c.position(Tools.getRandomPointByMapScale(map.scaleFactor()));
            c.map(map);

            collectables.add(c);
        }

        return collectables;
    }
}
