package comp1140.ass2.ai;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.buildings.Knight;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.Comparator;
import java.util.stream.Stream;

public record GreedyAI(GameInstance game, Player player) implements AIPlayer {

    @Override
    public double evaluate() {
        return evaluate( game.getAsEncodedString());
    }

    public double evaluate(String boardState) {
        GameInstance game = new GameInstance(boardState);
        return 5 * game.getBoard().getKnightBoard().values().stream().filter(knight -> player.equals(knight.getOwner())).filter(Knight::isJoker).count()
                + 2.5 * player.getScore()
                + game.getBoard().getResidentialBuilding().values().stream().filter(building -> player.equals(building.getOwner())).count()
                + Stream.of(game.getBoard().getRoads()).filter(building -> player.equals(building.getOwner())).count()
                + 2 * CatanDiceExtra.longestRoad(game.getAsEncodedString())[player.getUniqueId().charAt(0) - 'W'];
    }

    @Override
    public String[] selectActionSequence() {
        String boardState = game.getAsEncodedString();
        return Stream.of(CatanDiceExtra.generateAllPossibleActionSequences(boardState))
                .max(Comparator.comparingDouble(sequence -> evaluate(CatanDiceExtra.applyActionSequence(boardState, sequence))))
                .orElse(new String[] {});
    }

}
