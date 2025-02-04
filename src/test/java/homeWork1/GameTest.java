package homeWork1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для игры Монти Холла
 */
public class GameTest {
    private Player playerStay;
    private Player playerSwitch;

    @BeforeEach
    void setup() {
        playerStay = new Player("Игрок (без смены)", false);
        playerSwitch = new Player("Игрок (со сменой)", true);
    }

    @Test
    void testStayStrategy() {
        Game game = new Game(playerStay);
        boolean result = game.play(0);
        assertNotNull(result); // Проверяем, что метод play() возвращает результат
    }

    @Test
    void testSwitchStrategy() {
        Game game = new Game(playerSwitch);
        boolean result = game.play(0);
        assertNotNull(result);
    }

    @Test
    void testPlayerName() {
        assertEquals("Игрок (без смены)", playerStay.getName());
        assertEquals("Игрок (со сменой)", playerSwitch.getName());
    }

    @Test
    void testDoorPrize() {
        Door door = new Door(true);
        assertTrue(door.hasPrize());

        Door emptyDoor = new Door(false);
        assertFalse(emptyDoor.hasPrize());
    }
}
