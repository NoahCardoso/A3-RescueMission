package ca.mcmaster.se2aa4.island.team013;

public class TileFactory{

    public Tile getTile(TileType type){
        switch (type){
            case TileType.OCEAN:
                return new OceanTile();
            case TileType.LAND:
                return new LandTile();
            case TileType.UNKNOWN:
                return new UnknownTile();
            default:
                return new UnknownTile();
        }
    }
}