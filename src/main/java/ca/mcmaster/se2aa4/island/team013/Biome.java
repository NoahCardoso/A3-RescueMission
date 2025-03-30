package ca.mcmaster.se2aa4.island.team013;

enum Biome{
    OCEAN("OCEAN"),
    LAKE("LAKE"),
    BEACH("BEACH"),
    GRASSLAND("GRASSLAND"),
    MANGROVE("MANGROVE"),
    TROPICAL_RAIN_FOREST("TROPICAL_RAIN_FOREST"),
    TROPICAL_SEASONAL_FOREST("TROPICAL_SEASONAL_FOREST"),
    TEMPERATE_DECIDUOUS_FOREST("TEMPERATE_DECIDUOUS_FOREST"),
    TEMPERATE_RAIN_FOREST("TEMPERATE_RAIN_FOREST"),
    TEMPERATE_DESERT("TEMPERATE_DESERT"),
    TAIGA("TAIGA"),
    SNOW("SNOW"),
    TUNDRA("TUNDRA"),
    ALPINE("ALPINE"),
    GLACIER("GLACIER"),
    SHRUBLAND("SHRUBLAND"),
    SUB_TROPICAL_DESERT("SUB_TROPICAL_DESERT");

    private final String message; 

    Biome(String message) {  
        this.message = message;
    }

    public String toString() {  
        return message;
    }

    public static Biome fromString(String text) {
        for (Biome biome : Biome.values()) {
            if (biome.message.equals(text)) {
                return biome;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}