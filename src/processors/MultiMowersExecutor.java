package processors;

import models.Lawn;
import models.MoveOrientation;
import models.Mower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultiMowersExecutor {
    private int maxRunNumber;
    private Lawn lawn;
    private List<Mower> mowers;
    private List<char[]> mowerActions;

    private ExecutorService threadPool;

    public MultiMowersExecutor(List<String> content) {
        // Load Lawn, Mowers etc
        List<String> matrixSize = Arrays.asList(content.get(0).split(" "));
        this.lawn = new Lawn(Integer.valueOf(matrixSize.get(0)), Integer.valueOf(matrixSize.get(1)));
        this.mowers = new ArrayList<>();
        this.mowerActions = new ArrayList<>();
        this.maxRunNumber = initContextObjects(content, mowers, mowerActions);
    }

    public MultiMowersExecutor(int maxRunNumber, Lawn lawn, List<Mower> mowers, List<char[]> mowerActions) {
        this.maxRunNumber = maxRunNumber;
        this.lawn = lawn;
        this.mowers = mowers;
        this.mowerActions = mowerActions;
    }

    /**
     * This method seperates the moving action to several rounds, and in each round it is to launch all mowers
     * which is a thread of one mower and one moving action.
     * And the process waits all mowers finish their moving action and launch a next round action.
     *
     * @throws InterruptedException
     */
    public String execute() throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        threadPool = Executors.newFixedThreadPool(cores);

        for (int j = 0; j < maxRunNumber; j++) {
            for (int i = 0; i < mowers.size(); i++) {
                List<Callable<Boolean>> mowerRunThreads = new ArrayList<>();

                if (mowerActions.get(i).length > j) {
                    mowerRunThreads.add(new MowerRunner(i, mowerActions.get(i)[j], mowers.get(i), lawn));
                }
                List<Future<Boolean>> futures = threadPool.invokeAll(mowerRunThreads);
            }
        }
        threadPool.shutdown();

        return buildOutput();
    }

    /**
     * This method is aim to launch each mower as an individual thread with all corresponding moving instructions
     */
    public String executeMultiActions() {
        List<Callable<Boolean>> mowerRunThreads = new ArrayList<>();
        for (int i = 0; i < mowers.size(); i++) {
            mowerRunThreads.add(new MowerMultiInstructionRunner(i, mowerActions.get(i), mowers.get(i), lawn));
        }

        try {
            List<Future<Boolean>> futures = threadPool.invokeAll(mowerRunThreads);
        } catch (InterruptedException e) {
            // TODO : error handling
            e.printStackTrace();
        }
        return buildOutput();
    }

    private String buildOutput() {
        StringBuilder sb = new StringBuilder();
        for (Mower m : mowers) {
            sb.append(String.format("%d %d %s" + System.lineSeparator(), m.getX(), m.getY(), m.getOrientation().toString()));
        }
        return sb.toString();
    }

    /**
     * Initiate Mower Lawn executor context objects with parsing the input content:
     * From second element, all the even line number is corresponding to a mower position (X Y Orientation);
     * all the odd line number is a sequence of above line mower movement actions;
     *
     * The method receives the string content list to parsing, a empty mowers list and a empty mowerAction char[] list
     * it returns a Max mower actions sequence size
     *
     * @param content
     * @param mowers
     * @param mowerActions
     * @return
     */
    private static int initContextObjects(List<String> content, List<Mower> mowers, List<char[]> mowerActions) {
        int maxRunNumber = 0;
        for (int i = 1; i < content.size(); i++) {
            if (i % 2 == 1) {
                // initiate mowers
                String[] mowerPosition = content.get(i).split(" ");
                mowers.add(
                        new Mower(
                                Integer.valueOf(mowerPosition[0]),
                                Integer.valueOf(mowerPosition[1]),
                                MoveOrientation.valueOf(mowerPosition[2]))
                );
            } else {
                char[] actions = content.get(i).toCharArray();
                maxRunNumber = actions.length > maxRunNumber ? actions.length : maxRunNumber;
                mowerActions.add(content.get(i).toCharArray());
            }
        }
        return maxRunNumber;
    }
}
