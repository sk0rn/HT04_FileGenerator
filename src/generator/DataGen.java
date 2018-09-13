package generator;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class DataGen {

    private static Random random = new Random();


    public void getFiles(String path, int n, int size, String[] words, int probability) {

        for (int i = 1; i <= n; i++) {
            String data = genText(size, words, probability);

            try(FileOutputStream fileStream = new FileOutputStream(path + "file" + i + ".dat");
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileStream)) {
                byte[] buffer = data.getBytes();
                bufferedOutputStream.write(buffer);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String genText(int size, String[] words, int probability) {
        StringBuilder sb = new StringBuilder();
        int sentences = (random.nextInt(20) + 1);
        for (int i = 1; i <= size; i++) {
            sb.append(genSentence(words, probability));
            if (i % sentences == 0) { // абзац
                sb.append("\r\n");
            }
        }
        return sb.toString();

    }

    private static String genSentence(String[] words, int probability) {
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
            if (virguleProb == 3 && i != 0 && i  != wordsNum -1 ) {
                sb.append(',');
            }
            // В конеце предложения нет пробела
            if (i != wordsNum -1 ) {
                sb.append(" ");
            }

        }
        sb.replace(0, 1, String.valueOf(sb.charAt(0)).toUpperCase()); // заглавная буква
        sb.insert(sb.length(), genEnd()); // конец предложения
        return sb.toString();
    }

    private static String genWord() {
        StringBuilder sb = new StringBuilder();
        int wordNum = (random.nextInt(15) + 1);
        for (int i = 0; i < wordNum; i++) {
            sb.append((char) (random.nextInt(123 - 97) + 97));
        }
        return sb.toString();
    }

    private static String genEnd() {
        char[] chars = {'.', '!', '?' };
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
