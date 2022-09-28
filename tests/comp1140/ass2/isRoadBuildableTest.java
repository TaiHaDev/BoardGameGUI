package comp1140.ass2;

import comp1140.ass2.game.Board;
import comp1140.ass2.game.Player;
import comp1140.ass2.game.Road;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class isRoadBuildableTest {
    GameInstance gameInstance;
    Road[] roadList;
    Player currentPlayer;
    Player nextPlayer;

    @BeforeEach
    void init() {
        gameInstance = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        roadList = gameInstance.getBoard().getRoads();
        currentPlayer = gameInstance.nextPlayer();
        nextPlayer = gameInstance.nextPlayer();
    }
    private void testRoadBuildable(Board board, int firstPos, int secondPos, Player player, boolean expected) {
        boolean actual = board.isRoadBuildable(firstPos, secondPos, player);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void test_connected_road() {
        roadList[0].setOwner(currentPlayer);
        testRoadBuildable(gameInstance.getBoard(), 3, 7, currentPlayer, true);
    }

    @Test
    public void test_not_connected_road() {
        roadList[0].setOwner(currentPlayer);
        testRoadBuildable(gameInstance.getBoard(), 7, 12, currentPlayer, false);
    }
    @Test
    public void test_road_is_connected_but_wrong_player() {
        roadList[0].setOwner(currentPlayer);
        testRoadBuildable(gameInstance.getBoard(), 3, 7, nextPlayer, false);
    }
    @Test
    public void test_road_is_already_owned() {
        roadList[0].setOwner(currentPlayer);
        //roadList[0] is road 03
        roadList[6].setOwner(nextPlayer);
        //roadList[6] is road 37
        testRoadBuildable(gameInstance.getBoard(), 3, 7, currentPlayer, false);
    }
}
