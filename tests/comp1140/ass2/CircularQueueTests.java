package comp1140.ass2;

import comp1140.ass2.game.CircularQueue;
import comp1140.ass2.game.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircularQueueTests {

    @Test
    public void test_the_pop_function_doesnt_remove_elements_from_the_queue() {
        CircularQueue<Player> queue = new CircularQueue<>();
        queue.add(new Player("Player 1"));
        queue.add(new Player("Player 2"));
        Assertions.assertEquals(2, queue.size());

        queue.pop();
        queue.pop();
        Assertions.assertEquals(2, queue.size());
    }

}
