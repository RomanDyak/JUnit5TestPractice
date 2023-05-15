import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Disabled
    @Test
    void mainTimeoutLessThan22Seconds() {
        try {
            assertTimeout(Duration.ofSeconds(22), () -> Main.main(null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}