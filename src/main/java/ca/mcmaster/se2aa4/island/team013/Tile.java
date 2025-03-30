package ca.mcmaster.se2aa4.island.team013;

import java.util.ArrayList;

import org.json.JSONObject;

abstract class Tile extends Observer{

    private TileType type;
    private ArrayList<Biome> biomes = null;

    Tile(TileType type){
        this.type = type;
    }

    public void addBiome(Biome type){
        biomes.add(type);
    }

    public boolean hasBiome(Biome type){
        return biomes.contains(type);
    }

    public TileType getTileType(){
        return this.type;
    }

    public void update(JSONObject data){
        if (data.has("biomes")) {
            if (data.getJSONArray("biomes").length() >= 1) {
                for(int i = 0; i < data.getJSONArray("biomes").length(); i ++){
                    String text = data.getJSONArray("biomes").getString(i);
                    biomes.add(Biome.fromString(text));
                }
            }
        }
    }

}