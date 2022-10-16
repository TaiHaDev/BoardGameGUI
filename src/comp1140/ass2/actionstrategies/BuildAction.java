package comp1140.ass2.actionstrategies;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.board.Board;
import comp1140.ass2.builderstrategies.BuilderFactory;
import comp1140.ass2.buildings.*;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public record BuildAction(GameInstance game, Player player) implements ActionStrategy {
    public static void main(String[] args) {
        GameInstance game = GameInstance.getInstance();
        game.getPlayers().addAll(List.of(new Player("T"), new Player("M")));
        System.out.println(ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionFactory.ActionType.BUILD).isApplicable("R0003"));

    }
    @Override
    public boolean isApplicable(String argument) {
        // TODO refactor this
        char typeOfBuilding = argument.charAt(0);
        if (typeOfBuilding == 'R') {
            int firstPos = Integer.parseInt(argument.substring(1,3));
            int secondPos = Integer.parseInt(argument.substring(3,5));
            boolean valid = false;
            if (game.getDiceCount() == 0 &&
                    game.getBoard().isCoastalAnd5RoadsAway(firstPos, secondPos, player) &&
                    game.getBoard().isRoadValid(firstPos, secondPos)) {
                valid = true;
            } else if (game.getBoard().isRoadBuildable(firstPos, secondPos, player) &&
                    GameInstance.isResourcesSufficient(game.getDiceResult(), Road.COST)) {
                valid = true;
            }
            return valid;
        } else if (typeOfBuilding == 'C') {
            int location = Integer.parseInt(argument.substring(1,2));
            if (game.getBoard().canCastleBuild(location)) {
                return Stream.of(Resource.values()).anyMatch(resource -> GameInstance.isResourcesSufficient(game.getDiceResult(), Map.of(resource, 6)));
            }
        } else if (typeOfBuilding == 'K') {
            int location = Integer.parseInt(argument.substring(1,3));
            if (game.getBoard().canKnightBuild(location, player)) {
                return GameInstance.isResourcesSufficient(game.getDiceResult(), Knight.COST);
            }
        } else if (typeOfBuilding == 'S') {
            int location = Integer.parseInt(argument.substring(1,3));
            if (game.getBoard().canSettlementBuild(location, player)) {
                return GameInstance.isResourcesSufficient(game.getDiceResult(), Settlement.COST);
            }
        } else if (typeOfBuilding == 'T') {
            int location = Integer.parseInt(argument.substring(1,3));
            if (game.getBoard().canCityBuild(location, player)) {
                return GameInstance.isResourcesSufficient(game.getDiceResult(), City.COST);
            }
        }
        return false;
    }

    @Override
    public void apply(String argument) {
        char buildingType = argument.charAt(0) == 'K' ? 'J' : argument.charAt(0);
        BuilderFactory.of(game, player)
                .getBuilderById(buildingType)
                .build(argument.substring(1));
        if (game.getDiceCount() == 0) return;
        Player currentPlayer = game.getCurrentPlayer();
        if (buildingType == 'R') {
            game.useResources(Road.COST);
            game.checkAndUpdateLongestRoad();
        }
        else if (buildingType == 'C') {
            game.useResources(Castle.COST);
            currentPlayer.setScore(currentPlayer.getScore() + 2);

        }
        else if (buildingType == 'S') {
            game.useResources(Settlement.COST);
            currentPlayer.setScore(currentPlayer.getScore() + 1);
        }
        else if (buildingType == 'T') {
            game.useResources(City.COST);
            currentPlayer.setScore(currentPlayer.getScore() + 1);
        }
        else if (buildingType == 'J') {
            game.useResources(Knight.COST);
            game.checkAndUpdateLargestArmy();
        };

    }
}
