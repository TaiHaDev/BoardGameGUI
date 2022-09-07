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
            case 'w' -> WOOL;
            default -> ORE;
        };
    }
}
