package comp1140.ass2;

import comp1140.ass2.game.GameInstance;
import comp1140.ass2.game.Board;
import comp1140.ass2.game.Player;
import comp1140.ass2.game.Knight;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gui.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class isKnightResourceAvailableTest {
    Resource[] resources = {Resource.WOOL, Resource.GRAIN, Resource.BRICK, Resource.GOLD, Resource.LUMBER, Resource.ORE};
    private void testIsKnightResourceAvailable(Board board,Resource neededResource, Player player, boolean expected) {
        boolean actual = board.isKnightResourceAvailable(neededResource, player);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testResourceNotAvailable1() {
        GameInstance emptyGameInstance = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        for (var resource : resources) {
            for (var player : emptyGameInstance.getPlayers())
                testIsKnightResourceAvailable(emptyGameInstance.getBoard(), resource, player, false );
        }
    }

    @Test
    public void testResourceNotAvailable2() {
        GameInstance resourceAvailable = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        Map<Integer, Knight> knightBoard = resourceAvailable.getBoard().getKnightBoard();
        Player player = resourceAvailable.getCurrentPlayer();
        knightBoard.get(1).setOwner(player);
        knightBoard.get(2).setOwner(player);
        testIsKnightResourceAvailable(resourceAvailable.getBoard(), Resource.WOOL, player, false);
    }

    @Test
    public void testResourceAlreadyUsed() {
        GameInstance resourceAvailable = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        Map<Integer, Knight> knightBoard = resourceAvailable.getBoard().getKnightBoard();
        Player player = resourceAvailable.getCurrentPlayer();
        Knight currentKnight = knightBoard.get(0);
        currentKnight.setOwner(player);
        currentKnight.setJoker(true);
        System.out.println(knightBoard.get(0));
        testIsKnightResourceAvailable(resourceAvailable.getBoard(), Resource.WOOL, player, false);
    }
    @Test
    public void testResourceAvailable1() {
        GameInstance resourceAvailable = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        Map<Integer, Knight> knightBoard = resourceAvailable.getBoard().getKnightBoard();
        Player player = resourceAvailable.getCurrentPlayer();
        for (int i = 0; i < knightBoard.size(); i++) {
            knightBoard.get(i).setOwner(player);
            testIsKnightResourceAvailable(resourceAvailable.getBoard(), knightBoard.get(i).getJokerResource(), player, true);
            knightBoard.get(i).setOwner(null);
        }
    }
    @Test
    public void testWildCardKnight() {
        GameInstance resourceAvailable = new GameInstance(new Player[]{new Player("p1"), new Player("p2")});
        Map<Integer, Knight> knightBoard = resourceAvailable.getBoard().getKnightBoard();
        Player player = resourceAvailable.getCurrentPlayer();
        knightBoard.get(9).setOwner(player);
        knightBoard.get(10).setOwner(player);
        for (var resource : resources) {
            testIsKnightResourceAvailable(resourceAvailable.getBoard(), resource, player, true);
        }
    }
}
