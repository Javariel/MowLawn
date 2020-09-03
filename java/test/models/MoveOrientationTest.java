package models;

import org.junit.jupiter.api.Test;

import static models.MoveOrientation.*;
import static org.junit.jupiter.api.Assertions.*;

class MoveOrientationTest {

    @Test
    void nextOrientation() {
        assertEquals(N, MoveOrientation.nextOrientation(W, 'R'));
        assertEquals(N, MoveOrientation.nextOrientation(E, 'L'));

        assertEquals(S, MoveOrientation.nextOrientation(E, 'R'));
        assertEquals(S, MoveOrientation.nextOrientation(W, 'L'));

        assertEquals(W, MoveOrientation.nextOrientation(S, 'R'));
        assertEquals(W, MoveOrientation.nextOrientation(N, 'L'));

        assertEquals(E, MoveOrientation.nextOrientation(N, 'R'));
        assertEquals(E, MoveOrientation.nextOrientation(S, 'L'));

    }
}