package homeWork2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class FileSystemTest {
    private Folder root;
    private Folder subFolder;
    private File file1;
    private File file2;

    @BeforeEach
    void setUp() {
        root = new Folder("Корень");
        subFolder = new Folder("Подпапка");
        file1 = new File("Документ.txt");
        file2 = new File("Фото.jpg");
    }

    @Test
    void testAddFileToFolder() {
        root.add(file1);
        assertTrue(root.getComponents().contains(file1));
    }

    @Test
    void testAddFolderToFolder() {
        root.add(subFolder);
        assertTrue(root.getComponents().contains(subFolder));
    }

    @Test
    void testRemoveFileFromFolder() {
        root.add(file1);
        root.remove(file1);
        assertFalse(root.getComponents().contains(file1));
    }

    @Test
    void testRemoveFolderFromFolder() {
        root.add(subFolder);
        root.remove(subFolder);
        assertFalse(root.getComponents().contains(subFolder));
    }

    @Test
    void testEmptyFolder() {
        assertTrue(root.getComponents().isEmpty());
    }

    // Негативные тесты

    @Test
    void testRemoveNonExistentFile() {
        // Попытка удалить файл, которого нет в папке
        root.remove(file1);  // file1 еще не добавлен
        assertFalse(root.getComponents().contains(file1));  // Убедимся, что ошибки не возникло, а удаление не прошло
    }

    @Test
    void testAddNullFile() {
        // Попытка добавить null в папку
        assertThrows(IllegalArgumentException.class, () -> root.add(null));
    }

    @Test
    void testShowNullFolder() {
        // Попытка вызвать show() для пустой папки
        Folder nullFolder = null;
        assertThrows(NullPointerException.class, () -> nullFolder.show());
    }

    @Test
    void testRemoveNonExistentFolder() {
        // Попытка удалить папку, которой нет в родительской папке
        root.remove(subFolder);
        assertFalse(root.getComponents().contains(subFolder));
    }
}
