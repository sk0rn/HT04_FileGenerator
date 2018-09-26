package generator.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DataGenTest {

    private static DataGen generator;


    @BeforeEach
    void setUp() {
        generator = new DataGen();
    }

    @Test
    void genArrayWordsLengthTest() {
        int expResult = 50;
        String[] array = generator.genArrayWords(50);
        assertEquals(expResult, array.length);
    }


    @Test
    void getFilesNotExceed30Millis() {
        String path = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\src\\main\\java\\data\\";
        String[] words = {"Hello", "JInit"};
        assertTimeout(Duration.ofMillis(30), () -> {
            // This method runs in 10 seconds
            generator.getFiles(path, 5,500, 500, 1, words, 10);
        } , "getFiles method take more than 30 milliseconds");
    }

    @Test
    void getFilesIllegalArgumentExceptionExpected() {
        String path = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\src\\main\\java\\data\\";
        String[] words = {"Hello", "JInit"};
        assertThrows(IllegalArgumentException.class,
                () -> generator.getFiles(
                        path, 5, 300, 200, 3, words, 1));
    }

    @Test
    void getFilesFileNotFoundExceptionExpected() {
        String wrongPath = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\" +
                "src\\main\\java\\data\\fake\\";
        String[] words = {"Hello", "JInit"};
        assertThrows(FileNotFoundException.class,
                () -> generator.getFiles(
                        wrongPath, 5, 200, 300, 3, words, 1));
    }

    @Test
    void getFilesNoExceptionExpected() {
        String correctPath = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\" +
                "src\\main\\java\\data\\";
        String[] words = {"Hello", "JInit"};
        assertDoesNotThrow(() -> generator.getFiles(
                correctPath, 5, 200, 300, 3, words, 1));
    }

}