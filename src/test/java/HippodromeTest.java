import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void constructorNullAndMessageTest() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Hippodrome(null);
                });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorEmptyListAndMessageTest() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class,
                        () -> {
                            new Hippodrome(new ArrayList<Horse>());
                        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>(30);
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse("Horse no " + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertIterableEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        int horsesCnt = 50;
        List<Horse> horseList = new ArrayList<>(horsesCnt);
        for (int i = 0; i < horsesCnt; i++) {
            Horse h = new Horse(String.valueOf(i), i+1,i+1);
            horseList.add(Mockito.spy(h));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        List<Horse> horseListRes = hippodrome.getHorses();
        for (int i = 0; i < horseListRes.size(); i++) {
            Mockito.verify(horseListRes.get(i)).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>(3);
        Horse h1 = new Horse("Min", 3, 6);
        Horse hMax = new Horse("Max", 3, 16);
        Horse h3 = new Horse("Avg", 3, 2);
        horses.add(h1);
        horses.add(hMax);
        horses.add(h3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(hMax, hippodrome.getWinner());
    }

}