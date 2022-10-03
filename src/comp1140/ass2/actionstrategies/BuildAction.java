package comp1140.ass2.actionstrategies;

import comp1140.ass2.board.Board;
import comp1140.ass2.builderstrategies.BuilderFactory;
import comp1140.ass2.buildings.*;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;

public record BuildAction(GameInstance game, Player player) implements ActionStrategy {

    @Override
    public boolean isApplicable(String argument) {
        // TODO refactor this
        if (argument.length() < 3) return false;
        char typeOfBuilding = argument.charAt(0);
        int location = Integer.parseInt(argument.substring(1,3));
        if (typeOfBuilding == 'R') {
            int firstPos = Integer.parseInt(argument.substring(1,3));
            int secondPos = Integer.parseInt(argument.substring(3,5));
            boolean valid = false;
            if (game.getRollsDone() == 0 &&
                    game.getBoard().isCoastalAnd5RoadsAway(firstPos, secondPos, player) &&
                    game.getBoard().isRoadValid(firstPos, secondPos)) {
                valid = true;
            } else if (game.getBoard().isRoadBuildable(firstPos, secondPos, player) &&
                    GameInstance.isResourcesSufficient(game.getDiceResult(), Road.COST)) {
                valid = true;
            }
            return valid;
        } else if (typeOfBuilding == 'C') {
            location = Integer.parseInt(argument.substring(1,2));
            if (game.getBoard().canCastleBuild(location)) {
                return GameInstance.isResourcesSufficient(game.getDiceResult(), Castle.COST);
            }
        } else if (typeOfBuilding == 'K' || typeOfBuilding == 'J') {
            if (game.getBoard().canKnightBuild(location, player)) {
                return GameInstance.isResourcesSufficient(game.getDiceResult(), Knight.COST);
            }
        } else if (typeOfBuilding == 'S') {
            if (game.getBoard().canSettlementBuild(location, player)) {
                return GameInstance.isResourcesSufficient(game.getDiceResult(), Settlement.COST);
            }
        } else if (typeOfBuilding == 'T') {
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
