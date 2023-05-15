
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    static Stream<String> argsForEmptyOrSpaceBarTestProviderFactory() {
        return Stream.of("", " ", "        ");
    }

    @Test
    void constructorNullAndMessageTest() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Horse(null, 1, 1);
                });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argsForEmptyOrSpaceBarTestProviderFactory")
    void emptyOrSpaceBarTest(String str) {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Horse(str, 1, 1);
                });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void secondParamLessTenZero() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Horse("some", -5, 1);
                });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void thirdParamLessTenZero() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class, () -> {
                    new Horse("some", 1, -5);
                });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        assertEquals("Star", (new Horse("Star", 1, 5)).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(6, (new Horse("Star", 6, 5)).getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(5, (new Horse("Star", 2, 5)).getDistance());
        assertEquals(0, (new Horse("Star", 2)).getDistance());
    }

    @Test
    void moveGetRandomDoubleAndCheckDistance() {
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {
            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(1d);
            Horse horseTest = new Horse("Test",1.0, 2.0);
            horseTest.move();
            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(3d, horseTest.getDistance());
            horseTest.move();
            assertEquals(4d, horseTest.getDistance());


        }
    }


}