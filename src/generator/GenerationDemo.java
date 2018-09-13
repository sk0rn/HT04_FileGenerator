package generator;

public class GenerationDemo {

    public static void main(String[] args) {

        String path = "D:\\dev_edu\\STC13_HT\\HT04_TrashGenerator\\src\\data\\";




        DataGen generator = new DataGen();
        String[] randomWords = generator.genArrayWords(20);
        String[] words = {"qqq", "www", "eee"};

        generator.getFiles(path, 5, 50, randomWords, 10);
    }
}
