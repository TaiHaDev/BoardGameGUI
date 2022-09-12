package comp1140.ass2.game.helper;

public enum Resource {
    GOLD,
    BRICK,
    LUMBER,
    GRAIN,
    WOOL,
    ORE;

    public static Resource decodeChar(char c) {
        return switch (c) {
            case 'm' -> GOLD;
            case 'b' -> BRICK;
            case 'l' -> LUMBER;
            case 'g' -> GRAIN;
            case 'o' -> ORE;
            case 'w' -> WOOL;
            default -> throw new IllegalArgumentException("Character " + c + " does not map to a valid resource.");
        };
    }
}
