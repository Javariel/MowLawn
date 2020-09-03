package processors;

import models.Lawn;
import models.Mower;

import java.util.concurrent.Callable;

public class MowerMultiInstructionRunner implements Callable {
    private final char[] moveActions;
    private int mowerId;
    private Mower mower;
    private Lawn lawn;


    public MowerMultiInstructionRunner(int mowerId, char[] moveActions, Mower mower, Lawn lawn) {
        this.moveActions = moveActions;
        this.mowerId = mowerId;
        this.mower = mower;
        this.lawn = lawn;
    }

    @Override
    public String call() {
        for (char action: this.moveActions) {
            MowerRunner runner = new MowerRunner(mowerId, action, mower, lawn);
            runner.call();
        }
        return String.format("%d %d %s", mower.getX(), mower.getY(), mower.getOrientation().toString());
    }
}
