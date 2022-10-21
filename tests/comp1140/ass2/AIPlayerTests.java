package comp1140.ass2;

import comp1140.ass2.ai.AIPlayer;
import comp1140.ass2.ai.GreedyAI;
import comp1140.ass2.ai.SmartAI;
import comp1140.ass2.gameobjects.GameInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.stream.Stream;

public class AIPlayerTests {

    @ParameterizedTest
    @ValueSource(strings = { "greedy", "smart" })
    public void test_that_ai_players_always_build_when_possible(String greedy) {
        for (String[][][] array : ExampleGames.FULL_GAME1_WITH_ALL_POSSIBLE_ACTION_SEQUENCES) {
            GameInstance game = new GameInstance(array[0][0][0]);
            if (Stream.of(array[1]).anyMatch(action -> action.length > 0 && action[0].startsWith("build"))) {
                AIPlayer ai = greedy.equals("greedy") ? new GreedyAI(game.getCurrentPlayer()) : new SmartAI(game.getCurrentPlayer());

                System.out.println("Original board eval: " + ai.evaluate(array[0][0][0]));
                Stream.of(CatanDiceExtra.generateAllPossibleActionSequences(array[0][0][0]))
                        .map(sequence -> new AbstractMap.SimpleEntry<>(Arrays.stream(sequence).toList(), ai.evaluate(CatanDiceExtra.applyActionSequence(array[0][0][0], sequence))))
                        .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

                Assertions.assertTrue(Arrays.stream(ai.selectActionSequence(array[0][0][0])).anyMatch(action -> action.startsWith("build")),
                        "The AI had 'build' options, but chose something else");
            }
        }
    }

}
