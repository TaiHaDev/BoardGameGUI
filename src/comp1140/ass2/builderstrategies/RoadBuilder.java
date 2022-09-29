package comp1140.ass2.builderstrategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.Arrays;

public record RoadBuilder(GameInstance game, Player player) implements BuilderStrategy {
    @Override
    public void build(String input) {
        int start = Integer.parseInt(input.charAt(0) + "" + input.charAt(1));
        int end = Integer.parseInt(input.charAt(2) + "" + input.charAt(3));
        Arrays.stream(game.getBoard().getRoads())
                .filter(road -> road.getStart() == start)
                .filter(road -> road.getEnd() == end)
                .findFirst()
                .ifPresent(road -> road.setOwner(player));
    }
}
