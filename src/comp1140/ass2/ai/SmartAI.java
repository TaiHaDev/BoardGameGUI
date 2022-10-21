package comp1140.ass2.ai;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.buildings.Knight;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.*;
import java.util.stream.Stream;
// author Matthew
public record SmartAI(Player player) implements AIPlayer {

    public double evaluate(String boardState) {
        GameInstance game = new GameInstance(boardState);

        // static game influence (encourage building)
        long knights = game.getBoard().getKnightBoard().values().stream().filter(knight -> player.equals(knight.getOwner())).filter(Knight::isJoker).count();
        int score = player.getScore();
        long settlementsAndCities = game.getBoard().getResidentialBuilding().values().stream().filter(building -> building.getOwner() != null && player.getUniqueId().equals(building.getOwner().getUniqueId())).count();
        long roads = Stream.of(game.getBoard().getRoads()).filter(building -> building.getOwner() != null && player.getUniqueId().equals(building.getOwner().getUniqueId())).count();
        int longestRoad = CatanDiceExtra.longestRoad(game.getAsEncodedString())[player.getUniqueId().charAt(0) - 'W'];

        // transient game influence (encourage rerolling to afford structures)
        Map<Resource, Integer> roll = game.getDiceResult();
        double roadAffordability = roll.getOrDefault(Resource.LUMBER, 0) * 0.5 + roll.getOrDefault(Resource.BRICK, 0) * 0.5;
        double cityAffordability = roll.getOrDefault(Resource.GRAIN, 0) * 0.2 + roll.getOrDefault(Resource.ORE, 0) * 0.2;

        double eval = 0;
        // building cities should be our main priority
        eval += 4 * settlementsAndCities;

        // only the first two knights are important, then we experience diminishing returns
        eval += 4 * Math.min(knights, 2);

        // roads are next in order of importance
        eval += 3 * roads;

        // if we are not winning the longest road award, let's put some more focus on that, as it takes points
        // away from our opponent if we overtake them and gives them to us
        eval += (game.getCurrentPlayer().equals(game.getLongestRoad()) ? 1 : 2) * longestRoad;

        // and just for good measure...
        eval += score;

        // encourage rerolling to afford buildings
        // we add an exponential term to strongly encourage ignoring roads
        // and taking on a strong focus on rerolling until a city can be
        // afforded as the score goes up.
        // this switch from exploitation (being greedy on expanding territory via roads)]
        // to exploration and just rerolling and rerolling when our score is high allows us
        // to accept that we have enough reach and should probably just reroll until we can
        // afford a city, to more quickly finish the game.
        eval += 0.75 * roadAffordability + Math.exp(cityAffordability * score);

        return eval;
    }

}
