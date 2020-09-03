import processors.IOProcessor;
import processors.MultiMowersExecutor;
import java.io.*;
import java.util.List;

public class MowLawnLauncher {

    public static void main(String[] args) throws IOException, InterruptedException {
        // read file
        List<String> content = IOProcessor.loadFile("./data/input/example.txt");

        System.out.println("Start Process Multi Mowers...");
        // load an action at the same time for all mowers
        String result = new MultiMowersExecutor(content).execute();

        IOProcessor.writeResult("./data/output/result.txt", result);

    }
}
