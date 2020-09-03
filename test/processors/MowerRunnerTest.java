package processors;

import models.Lawn;
import models.MoveOrientation;
import models.Mower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MowerRunnerTest {


    @Test
    void call() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(1, 3, MoveOrientation.S);
        lawn.update(1, 3, true);

        new MowerRunner(1,'F', mower, lawn).call();
        assertTrue(
                true == lawn.isLawnPositionOccupied(1, 2) &&
                        false == lawn.isLawnPositionOccupied(1, 3) &&
                        mower.getX() == 1 && mower.getY() == 2 && mower.getOrientation().equals(MoveOrientation.S)
        );

        new MowerRunner(1,'L', mower, lawn).call();
        assertTrue(
                true == lawn.isLawnPositionOccupied(1, 2) &&
                        false == lawn.isLawnPositionOccupied(1, 3) &&
                        mower.getX() == 1 && mower.getY() == 2 && mower.getOrientation().equals(MoveOrientation.E)
        );

        new MowerRunner(1,'R', mower, lawn).call();
        assertTrue(
                true == lawn.isLawnPositionOccupied(1, 2) &&
                        false == lawn.isLawnPositionOccupied(1, 3) &&
                        mower.getX() == 1 && mower.getY() == 2 && mower.getOrientation().equals(MoveOrientation.S)
        );
    }

}