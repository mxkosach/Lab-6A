package by.bsu.io.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader("src/main/resources/config.ini"));
        final String INPUT_FILE = prop.getProperty("INPUT_FILE");
        final String OUTPUT_FILE = prop.getProperty("OUTPUT_FILE");
        final String PATH_TO_DIR = prop.getProperty("PATH_TO_DIR");

        File inFile = new File(PATH_TO_DIR + INPUT_FILE);
        File outFile = new File(PATH_TO_DIR + OUTPUT_FILE);

        // bufferedReader;
        String line;

        List<String> stringList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), "UTF8"));
            while ((line = bufferedReader.readLine()) != null) {
                line = upperWordInLine(line);
                stringList.add(line);
                System.out.println(line);
            }

            try (BufferedWriter writter = new BufferedWriter(new FileWriter(outFile))) {
                for (String ln : stringList) {
                    writter.write(ln + "\n");
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String upperWordInLine(String s1) {
        String s2 = "";
        s2 = s2 + s1.substring(0, 1).toUpperCase(); //первый символ делаем заглавным
        for (int i = 1; i < s1.length(); i++) {
            // смотрим, был ли слева пробел:
            if (" ".equals(s1.substring(i - 1, i)))
                s2 = s2 + s1.substring(i, i + 1).toUpperCase();
            else
                s2 = s2 + s1.substring(i, i + 1);
        }
        return s2;
    }
}

