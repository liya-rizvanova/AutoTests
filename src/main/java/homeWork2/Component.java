package homeWork2;

public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void show();

    public String getName() {
        return name;
    }
}
