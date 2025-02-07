package homeWork2;

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("Корень");
        Folder subFolder = new Folder("Подпапка");
        File file1 = new File("Документ.txt");
        File file2 = new File("Фото.jpg");

        root.add(file1);
        root.add(subFolder);
        subFolder.add(file2);

        root.show();
    }
}
