package comp1140.ass2.game;

public enum Resource {

    GOLD('m'),
    BRICK('b'),
    LUMBER('l'),
    GRAIN('g'),
    WOOL('w'),
    ORE('o');
    
    private final char id;

    Resource(char id) {
        this.id = id;
    }
    
    public char getId() {
        return this.id;
    }

    public static Resource decodeChar(char c) {
        for (Resource resource : Resource.values()) {
            if (c == resource.getId()) {
                return resource;
            }
        }
        return null;
    }
    
}
