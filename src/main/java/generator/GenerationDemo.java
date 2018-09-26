package generator;

import generator.utils.DataGen;

import java.io.FileNotFoundException;

public class GenerationDemo {

    public static void main(String[] args) {

        String path = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\src\\main\\java\\data\\";

        DataGen generator = new DataGen();
        String[] randomWords = generator.genArrayWords(20);

        long start = System.currentTimeMillis();
        try {
            generator.getFiles(path, 5,500, 500, 1, randomWords, 10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
