package comp1140.ass2;

import comp1140.ass2.gameobjects.GameInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetBoardStateTests {

    @Test
    public void test_getAsEncodedString_returns_the_same_string_passed_to_constructor() {
        for (String[][] strings : ExampleGames.FULL_GAME_WITH_ACTIONS1) {
            Assertions.assertEquals(strings[0][0], new GameInstance(strings[0][0]).getAsEncodedString());
        }
        for (String[][] strings : ExampleGames.FULL_GAME_WITH_ACTIONS2) {
            Assertions.assertEquals(strings[0][0], new GameInstance(strings[0][0]).getAsEncodedString());
        }
        for (String[][] strings : ExampleGames.FULL_GAME_WITH_ACTIONS3) {
            Assertions.assertEquals(strings[0][0], new GameInstance(strings[0][0]).getAsEncodedString());
        }
    }

}
