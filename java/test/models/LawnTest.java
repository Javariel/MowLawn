package models;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LawnTest {

    @Test
    void isInLawn() {
        Lawn lawn = new Lawn(5, 5);
        assertEquals(true,
                lawn.isInLawn(new Random().nextInt(5), new Random().nextInt(5)));
        assertEquals(false,
                lawn.isInLawn(6, 5));
        assertEquals(false,
                lawn.isInLawn(0, 10));
    }

    @Test
    void isLawnPositionOccupied() {
        Lawn lawn = new Lawn(5, 5);
        assertEquals(false,
                lawn.isLawnPositionOccupied(new Random().nextInt(5), new Random().nextInt(5)));
    }

    @Test
    void update() {
        Lawn lawn = new Lawn(5, 5);
        lawn.update(1, 2, true);
        assertEquals(true, lawn.isLawnPositionOccupied(1, 2));
        lawn.update(1, 2, false);
        assertEquals(false, lawn.isLawnPositionOccupied(1, 2));
    }

    @Test
    void updateMultiThreadsConcurrentRun() {
        // Init lawn
        Lawn lawn = new Lawn(5, 5);

        for (int i = 0; i < 6; i++) {
            System.out.println(i);
            Thread t = new Thread(new LawnUpdator(lawn, 0, 1, true));
            t.start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lawn);
    }

    @Test
    void updateMultiThreadsNonConcurrentRun() {
        // Init lawn
        Lawn lawn = new Lawn(5, 5);

        for (int i = 0; i < 6; i++) {
            System.out.println("Mower thread - " + i);
            Thread t = new Thread(new LawnUpdator(lawn, i, 1, true));
            t.start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lawn);
    }


    class LawnUpdator implements Runnable {
        /**
         *  This class is created to test Multi-threads concurrent/non-concurrent run
         */
        private Lawn lawn;
        private int x, y;
        private boolean value;

        LawnUpdator(Lawn lawn, int x, int y, boolean value) {
            this.lawn = lawn;
            this.x = x;
            this.y = y;
            this.value = value;
        }
        @Override
        public void run() {
            //System.out.println("Start: " +  this.lawn);
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(Thread.currentThread().getName() + "Check: " +  this.lawn);

            // Block the given x, y in the matrix, as the matrix element type is a reference type
            // if concurrent run -> several threads want to access the same matrix(x, y)
            // they need to access one by one, means they will wait
            // if non-concurrent run -> different threads could read their required location
            synchronized (this.lawn.getLawnPositionStatus(x, y)) {
                boolean check = this.lawn.isLawnPositionOccupied(x, y);

                try {
                    Thread.sleep(new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() +
                        ": \tbegining check: " + check + ", new check: " + this.lawn.isLawnPositionOccupied(x, y));
                if (check) {
                    //System.out.println("Update : " + x + ", " + y + " to " + false);
                    this.lawn.update(x, y, false);
                } else {
                    //System.out.println("Update : " + x + ", " + y + " to " + true);
                    this.lawn.update(x, y, true);
                }
            }
            //System.out.println("End: "+  this.lawn);
        }
    }
}