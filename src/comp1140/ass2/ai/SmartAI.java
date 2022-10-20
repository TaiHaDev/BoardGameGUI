package comp1140.ass2.ai;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.buildings.Knight;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.*;
import java.util.stream.Stream;

public record SmartAI(GameInstance game, Player player) implements AIPlayer {

    public double evaluate(String boardState) {
        GameInstance game = new GameInstance(boardState);
        long knights = game.getBoard().getKnightBoard().values().stream().filter(knight -> player.equals(knight.getOwner())).filter(Knight::isJoker).count();
        int score = player.getScore();
        long settlementsAndCities = game.getBoard().getResidentialBuilding().values().stream().filter(building -> building.getOwner() != null && player.getUniqueId().equals(building.getOwner().getUniqueId())).count();
        long roads = Stream.of(game.getBoard().getRoads()).filter(building -> building.getOwner() != null && player.getUniqueId().equals(building.getOwner().getUniqueId())).count();
        int longestRoad = CatanDiceExtra.longestRoad(game.getAsEncodedString())[player.getUniqueId().charAt(0) - 'W'];

        double eval = 0;
        // building cities should be our main priority
        eval += 4 * settlementsAndCities + 1.5 * roads;

        // only the first two knights are important, then we experience diminishing returns
        eval += 3 * Math.min(knights, 2);

        // roads are next in order of importance
        eval += 2 * roads;

        // if we are not winning the longest road award, let's put some more focus on that, as it takes points
        // away from our opponent if we overtake them and gives them to us
        eval += (game.getCurrentPlayer().equals(game.getLongestRoad()) ? 1 : 2) * longestRoad;

        // and just for good measure...
        eval += score;

        return eval;
    }

    @Override
    public String[] selectActionSequence(String boardState) {
        return Stream.of(CatanDiceExtra.generateAllPossibleActionSequences(boardState))
                .max(Comparator.comparingDouble(sequence -> evaluate(CatanDiceExtra.applyActionSequence(boardState, sequence))))
                .orElse(new String[] {});
    }

}
