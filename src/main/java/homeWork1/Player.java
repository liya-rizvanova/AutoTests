
package homeWork1;

/**
 * Класс игрока
 */
public class Player {
    private String name;
    private boolean switchDoor; // true - меняет выбор, false - остается

    public Player(String name, boolean switchDoor) {
        this.name = name;
        this.switchDoor = switchDoor;
    }

    public boolean shouldSwitch() {
        return switchDoor;
    }

    public String getName() {
        return name;
    }
}

