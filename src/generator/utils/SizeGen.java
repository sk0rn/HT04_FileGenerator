package generator.utils;


import java.util.Arrays;
import java.util.Random;

public class SizeGen {

    private final static Random random = new Random();

    public int[] getSizes(int n, int min, int max, int diff) {
        switch (diff) {
            case 1 : return randomDistrib(n, min, max);
            case 2 : return mirrorParabola(n, min, max);
            case 3 : return normalParabola(n, min, max);
        }
        return null;
    }

    private int[] mirrorParabola(int n, int min, int max) {
        int bound1 = (min + max) / 2;
        int bound2 = bound1 + 1;
        int half = n / 2;

        int k;
        int r1;
        int r2;

        int[] array = new int[n];
        for (int i = 1, j = n; i <= half; i++, j--) {
            k = (int) (((double) i / (double) half) * (max - bound1));
            r1 = random.nextInt(bound2 - (bound1 - k)) + bound1 - k;
            r2 = random.nextInt(bound2 + k - bound1) + bound1;
            array[j - 1] = r2;
            array[i - 1] = r1;
        }
        if (n % 2 != 0) {
            array[half] = bound1;
        }
        return array;
    }

    private int[] normalParabola(int n, int min, int max) {
        int bound = (min + max) / 2;
        int half = n / 2;

        int k;
        int r1;
        int r2;

        int[] array = new int[n];
        for (int i = 1, j = n; i <= half; i++, j--) {
            k = (int) (((double) i / (double) half) * (max - bound)) + 1;
            r1 = random.nextInt(k) + min;
            r2 = random.nextInt(k) + max - (k-1);
            array[j - 1] = r2;
            array[i - 1] = r1;
        }
        if (n % 2 != 0) {
            array[half] = (random.nextInt(2) == 0) ? min : max;
        }
        return array;
    }

    private int[] randomDistrib(int n, int min, int max) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max + 1 - min) + min;
        }
        return array;
    }


}
