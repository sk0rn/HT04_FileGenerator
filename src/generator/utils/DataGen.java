package generator.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class DataGen {

    private static Random random = new Random();


    public void getFiles(String path, int n, int min, int max, int diff, String[] words, int probability) {
        byte[] buffer;
        StringBuilder sb = new StringBuilder();
        SizeGen sizeGen = new SizeGen();
        int[] sizes = sizeGen.getSizes(n, min, max, diff);
        int sizeIndex = 0;
        for (int i = 1; i <= n; i++) {
            try (FileOutputStream fileStream = new FileOutputStream(path + "file" + i + ".dat");
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileStream)) {

                int sentences = (random.nextInt(20) + 1);
                for (int j = 1; j <= sizes[sizeIndex]; j++) {
                    sb.append(genSentence(words, probability));
                    if (j % sentences == 0) { // абзац
                        sb.append("\r\n");
                    }
                    buffer = sb.toString().getBytes();
                    bufferedOutputStream.write(buffer);
                    sb.delete(0, sb.length());
                }
                sizeIndex++;

            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String genSentence(String[] words, int probability) {
        StringBuilder sb = new StringBuilder();
        boolean wordAdded = false;
        int wordsNum = (random.nextInt(15) + 1);
        for (int i = 0; i < wordsNum; i++) {

            // вероятность добавления в предложение одного случайного слова из массива words
            int wordProb = random.nextInt(probability + 1);
            if (!wordAdded && wordProb == 1) {
                sb.append(words[random.nextInt(words.length)]);
                wordAdded = true;
            } else {
                sb.append(genWord());
            }
            // Случайная запятая после слова
            int virguleProb = random.nextInt(6);
            if (virguleProb == 3 && i != 0 && i != wordsNum - 1) {
                sb.append(',');
            }
            // В конце предложения нет пробела
            if (i != wordsNum - 1) {
                sb.append(" ");
            }

        }
        sb.replace(0, 1, String.valueOf(sb.charAt(0)).toUpperCase()); // заглавная буква
        sb.insert(sb.length(), genEnd()); // конец предложения
        return sb.toString();
    }

    private String genWord() {
        StringBuilder sb = new StringBuilder();
        int wordNum = (random.nextInt(15) + 1);
        for (int i = 0; i < wordNum; i++) {
            sb.append((char) (random.nextInt(123 - 97) + 97));
        }
        return sb.toString();
    }

    private String genEnd() {
        char[] chars = {'.', '!', '?'};
        int index = random.nextInt(3);
        return chars[index] + " ";

    }

    public String[] genArrayWords(int size) {
        String[] words = new String[size];
        for (int i = 0; i < size; i++) {
            words[i] = genWord();
        }
        return words;
    }


}
