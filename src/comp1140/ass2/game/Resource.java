package comp1140.ass2.game;

public enum Resource {
    GOLD('m'),
    BRICK('b'),
    LUMBER('l'),
    GRAIN('g'),
    WOOL('w'),
    ORE('o');

    private final char c;

    Resource(char c) {
        this.c = c;
    }

    public char getId() {
        return this.c;
    }

    public static Resource decodeChar(char c) {
        for (var value : Resource.values()) {
            if (value.c == c) {
                return value;
            }
        }
        throw new IllegalArgumentException("invalid resource");
    }
}
