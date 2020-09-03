package processors;

import models.Lawn;
import models.MoveOrientation;
import models.Mower;

import java.util.concurrent.Callable;


public class MowerRunner implements Callable {
    private int mowerId;
    private final char action;
    private Mower mower;
    private Lawn lawn;

    public MowerRunner(int mowerId, char action, Mower mower, Lawn lawn) {
        this.mowerId = mowerId;
        this.action = action;
        this.mower = mower;
        this.lawn = lawn;
    }

    /***
     * Make move mower in lawn with the given lawn
     * @return
     */
    @Override
    public Boolean call() {
        int x = this.mower.getX();
        int y = this.mower.getY();

        if (action == 'F') {
            switch (this.mower.getOrientation()) {
                case N: y += 1; break;
                case E: x += 1; break;
                case S: y -= 1; break;
                case W: x -= 1; break;
            }
        } else {
            this.mower.setOrientation(
                    MoveOrientation.nextOrientation(this.mower.getOrientation(), action));
        }
        if (this.lawn.isInLawn(x, y))
            synchronized (this.lawn.getLawnPositionStatus(x, y)) {
                 if(!this.lawn.isLawnPositionOccupied(x, y)) {
                    /*System.out.println(
                            "MowerId: " + this.mowerId +
                                    ", X: " + this.mower.getX() +
                                    ", Y: " + this.mower.getY() +
                                    ", Orientation: " + this.mower.getOrientation()
                    );*/
                    this.lawn.update(this.mower.getX(), this.mower.getY(), false);
                    this.mower.updatePosition(x, y);
                    this.lawn.update(x, y, true);
                    return true;
                }
            }
        return false;
    }


}
