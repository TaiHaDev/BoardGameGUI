package comp1140.ass2;

import comp1140.ass2.game.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class decodeCharTest {
    char[] chars = {'m', 'b', 'l', 'g', 'w', 'o'};
    Resource[] resources = {Resource.GOLD, Resource.BRICK, Resource.LUMBER, Resource.GRAIN, Resource.WOOL, Resource.ORE};
    @Test
    public void test_decode_char() {
        for (int i = 0; i < chars.length; i++) {
            boolean actual = Resource.decodeChar(chars[i]) == resources[i];
            Assertions.assertTrue(actual);
        }
    }
}
