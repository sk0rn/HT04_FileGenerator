package generator.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SizeGenTest {

    private static SizeGen generator;

    @BeforeEach
    void setUp() {
        generator = new SizeGen();
    }

    @Test
    void returnNullTest() {
        assertNull(generator.getSizes(100, 20, 50, 4));
    }

    @Test
    void randomDistribCorrectRangeTest() {
        int min = 50;
        int max = 100;
        int[] array = generator.getSizes(200, min, max, 1);
        Arrays.sort(array);
        assertTrue(array[0] >= min && array[array.length - 1] <= max);
    }

    @Test
    void mirrorParabolaCorrectRangeTest() {
        int min = 50;
        int max = 100;
        int[] array = generator.getSizes(200, min, max, 2);
        Arrays.sort(array);
        assertTrue(array[0] >= min && array[array.length - 1] <= max);
    }

    @Test
    void normalParabolaCorrectRangeTest() {
        int min = 40;
        int max = 120;
        int[] array = generator.getSizes(200, min, max, 3);
        Arrays.sort(array);
        assertTrue(array[0] >= min && array[array.length - 1] <= max);
    }

    @Test
    void IllegalArgumentExceptionExpected() {
        assertThrows(IllegalArgumentException.class,
                () -> generator.getSizes(100, 100, 50, 3));
    }
}