package homeWork2;

public class File extends Component {
    public File(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("Файл: " + name);
    }
}