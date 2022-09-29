package comp1140.ass2.builderstrategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public class BuilderFactory {

    private final CastleBuilder castleBuilder;
    private final KnightBuilder knightBuilder;
    private final KnightBuilder jokerBuilder;
    private final RoadBuilder roadBuilder;
    private final CityBuilder cityBuilder;
    private final SettlementBuilder settlementBuilder;

    private BuilderFactory(GameInstance game, Player player) {
        settlementBuilder = new SettlementBuilder(game, player);
        cityBuilder = new CityBuilder(game, player);
        roadBuilder = new RoadBuilder(game, player);
        knightBuilder = new KnightBuilder(game, player, false);
        jokerBuilder = new KnightBuilder(game, player, true);
        castleBuilder = new CastleBuilder(game, player);
    }

    public static BuilderFactory of(GameInstance game, Player player) {
        return new BuilderFactory(game, player);
    }

    public BuilderStrategy getBuilderById(char id) {
        return switch(id) {
            case 'C' -> castleBuilder;
            case 'R' -> roadBuilder;
            case 'S' -> settlementBuilder;
            case 'T' -> cityBuilder;
            case 'J' -> jokerBuilder;
            case 'K' -> knightBuilder;
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

}
