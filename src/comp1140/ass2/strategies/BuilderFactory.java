package comp1140.ass2.strategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public class BuilderFactory {

    private final CastleBuilder castleBuilder;
    private final KnightBuilder knightBuilder;
    private final RoadBuilder roadBuilder;
    private final ResidentialBuilder settlementBuilder;

    public BuilderFactory(GameInstance game, Player player) {
        settlementBuilder = new ResidentialBuilder(game, player);
        roadBuilder = new RoadBuilder(game, player);
        knightBuilder = new KnightBuilder(game, player);
        castleBuilder = new CastleBuilder(game, player);
    }

    public BuilderStrategy getBuilderById(char id) {
        return switch(id) {
            case 'C' -> castleBuilder;
            case 'R' -> roadBuilder;
            case 'S', 'T' -> settlementBuilder;
            case 'K', 'J' -> knightBuilder;
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

}
