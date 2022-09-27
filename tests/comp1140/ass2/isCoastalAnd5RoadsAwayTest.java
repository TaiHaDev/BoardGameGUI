package comp1140.ass2;

import comp1140.ass2.game.Board;
import comp1140.ass2.game.GameInstance;
import comp1140.ass2.game.Player;
import comp1140.ass2.gui.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class isCoastalAnd5RoadsAwayTest {
    private void testIsCoastalAnd5RoadsAway(Board board, int firstPos, int secondPos, Player player, boolean expected) {
        boolean actual =board.isCoastalAnd5RoadsAway(firstPos, secondPos, player);
        Assertions.assertEquals(actual, expected);
    }
    @Test
    public void test_new_road_for_empty_game_state() {
        GameInstance gameInstance = new GameInstance(new Player[]{new Player("p1")});
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 0, 3, gameInstance.getCurrentPlayer(), true);
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 15, 20, gameInstance.getCurrentPlayer(), true);
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 49, 53, gameInstance.getCurrentPlayer(), true);
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 33, 38, gameInstance.getCurrentPlayer(), true);
    }
    @Test
    public void test_exactly_5_roads_away() {
        GameInstance gameInstance = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        Player currentPlayer = gameInstance.getCurrentPlayer();
        Player nextPlayer = gameInstance.nextPlayer();
        var roadBoard = gameInstance.getBoard().getRoads();
        roadBoard[9].setOwner(nextPlayer);
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 0, 3, currentPlayer, true);
    }
    @Test
    public void test_exactly_4_roads_away() {
        GameInstance gameInstance = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        Player currentPlayer = gameInstance.getCurrentPlayer();
        Player nextPlayer = gameInstance.nextPlayer();
        var roadBoard = gameInstance.getBoard().getRoads();
        roadBoard[9].setOwner(nextPlayer);
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 0, 4, currentPlayer, false);
    }
    @Test
    public void test_road_is_not_coastal() {
        GameInstance gameInstance = new GameInstance(new Player[]{new Player("p1")});
        testIsCoastalAnd5RoadsAway(gameInstance.getBoard(), 12, 17, gameInstance.getCurrentPlayer(), false);
    }
}
