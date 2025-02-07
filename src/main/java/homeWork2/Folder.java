package homeWork2;

import java.util.List;
import java.util.ArrayList;

public class Folder extends Component {
    private List<Component> components = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void add(Component component) {
        if (component == null) {
            throw new IllegalArgumentException("Cannot add null component");
        }
        components.add(component);
    }


    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public void show() {
        show(0);  // Начинаем с нулевого уровня вложенности
    }

    private void show(int depth) {
        System.out.println("  ".repeat(depth) + "📂 Папка: " + name);
        for (Component component : components) {
            if (component instanceof Folder) {
                ((Folder) component).show(depth + 1);  // Увеличиваем отступ
            } else {
                System.out.println("  ".repeat(depth + 1) + "📄 Файл: " + component.getName());
            }
        }
    }

    public List<Component> getComponents() {
        return components;
    }
}
