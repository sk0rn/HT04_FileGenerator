package generator;

import generator.utils.DataGen;

public class GenerationDemo {

    public static void main(String[] args) {

        String path = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\src\\data\\";

        DataGen generator = new DataGen();
        String[] randomWords = generator.genArrayWords(20);

        long start = System.currentTimeMillis();
        generator.getFiles(path, 10,20, 80, 3, randomWords, 10);
        System.out.println(System.currentTimeMillis() - start);
    }
}
