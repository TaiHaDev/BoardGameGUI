package comp1140.ass2;

import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.helpers.CircularQueue;
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

    @Test
    public void test_the_queue_moves_elements_circularly() {
        CircularQueue<Player> queue = new CircularQueue<>();
        queue.add(new Player("Player 1"));
        queue.add(new Player("Player 2"));
        queue.add(new Player("Player 3"));

        Assertions.assertEquals("Player 1", queue.peek().getUniqueId());
        Assertions.assertEquals("Player 2", queue.pop().getUniqueId());
        Assertions.assertEquals("Player 3", queue.pop().getUniqueId());
        Assertions.assertEquals("Player 1", queue.pop().getUniqueId());
        Assertions.assertEquals("Player 2", queue.pop().getUniqueId());
        Assertions.assertEquals("Player 3", queue.pop().getUniqueId());
    }

}
