package processors;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOProcessor {
    public static List<String> loadFile(String inputFilePath) throws IOException {
        File file = new File(inputFilePath);
        Scanner scanner = new Scanner(file);


        List<String> content = new ArrayList<>();
        while (scanner.hasNextLine()) {
            content.add(scanner.nextLine());
        }

        return content;
    }

    public static void writeResult(String outputFilePath, String outputContent) throws IOException {
        File file = new File(outputFilePath);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

        fw.write(outputContent);
        fw.close();
    }
}
