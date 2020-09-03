package models;


public class Mower {
    private int x;
    private int y;
    private MoveOrientation orientation;

    public Mower(int x, int y, MoveOrientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MoveOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(MoveOrientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "models.Mower{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                '}';
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(char action) {
        int x = this.x;
        int y = this.y;

        if (action == 'F') {
            switch (this.orientation) {
                case N: y += 1; break;
                case E: x += 1; break;
                case S: y -= 1; break;
                case W: x -= 1; break;
            }
        } else {
            this.orientation = MoveOrientation.nextOrientation(this.orientation, action);
        }

        this.x = x;
        this.y = y;

    }
}
