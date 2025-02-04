package homeWork1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс игры
 */
public class Game {
    private Player player;
    private List<Door> doors;

    public Game(Player player) {
        this.player = player;
        this.doors = generateDoors();
    }

    private List<Door> generateDoors() {
        List<Door> doors = new ArrayList<>();
        doors.add(new Door(true));  // Одна дверь с призом
        doors.add(new Door(false));
        doors.add(new Door(false));
        Collections.shuffle(doors);
        return doors;
    }

    public boolean play(int chosenDoorIndex) {
        int revealedDoorIndex = revealDoor(chosenDoorIndex);
        if (player.shouldSwitch()) {
            // Меняем выбор на оставшуюся дверь
            for (int i = 0; i < 3; i++) {
                if (i != chosenDoorIndex && i != revealedDoorIndex) {
                    chosenDoorIndex = i;
                    break;
                }
            }
        }
        return doors.get(chosenDoorIndex).hasPrize();
    }

    private int revealDoor(int chosenDoorIndex) {
        for (int i = 0; i < 3; i++) {
            if (i != chosenDoorIndex && !doors.get(i).hasPrize()) {
                return i; // Открываем пустую дверь
            }
        }
        return -1; // Такого быть не должно
    }
}
