package models;

import static org.junit.jupiter.api.Assertions.*;

class MowerTest {

    @org.junit.jupiter.api.Test
    void updatePosition() {
        Mower mower = new Mower(4, 4, MoveOrientation.N);
        mower.updatePosition(1, 5);
        assertTrue(1 == mower.getX() && 5 == mower.getY() && MoveOrientation.N.equals(mower.getOrientation()));
    }

    @org.junit.jupiter.api.Test
    void move() {
        Mower mower = new Mower(1, 4, MoveOrientation.S);
        mower.move('F');
        assertTrue(1 == mower.getX() && 3 == mower.getY() && MoveOrientation.S.equals(mower.getOrientation()));
        mower.move('L');
        assertTrue(1 == mower.getX() && 3 == mower.getY() && MoveOrientation.E.equals(mower.getOrientation()));
        mower.move('R');
        assertTrue(1 == mower.getX() && 3 == mower.getY() && MoveOrientation.S.equals(mower.getOrientation()));
    }
}