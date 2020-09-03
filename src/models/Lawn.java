package models;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;


public class Lawn {
    /**
     * Binary matrix attribute lawnMatrix represents the cartesian coordinates system.
     * It is initiated as false as beginning signified that no mower occupies anywhere.
     * With update method, it can update mower occupied situation.
     */
    private AtomicBoolean[][] lawnMatrix;

    public Lawn(int sizeX, int sizeY) {
        this.lawnMatrix = new AtomicBoolean[sizeY + 1][sizeX + 1];
        for (int i = 0; i < this.lawnMatrix.length; i++) {
            for (int j = 0; j < this.lawnMatrix[i].length; j++) {
                this.lawnMatrix[i][j] = new AtomicBoolean();
            }
        }
    }

    public AtomicBoolean getLawnPositionStatus(int x, int y) {
        return this.lawnMatrix[y][x];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lawnMatrix.length; i++) {
            sb.append("\n" + Arrays.toString(lawnMatrix[i]) );
        }
        sb.append("]");
        return "Lawn{" +
                "lawnMatrix=" + sb.toString() +
                '}';
    }

    /**
     * check if a given position is inside lawn
     * @param positionX
     * @param positionY
     * @return
     */
    public Boolean isInLawn(int positionX, int positionY) {
        return positionX < this.lawnMatrix[0].length && positionY < this.lawnMatrix.length;
    }

    /**
     * check if a given position is occupied by any mower
     * @param positionX
     * @param positionY
     * @return
     */
    public Boolean isLawnPositionOccupied(int positionX, int positionY) {
        return this.lawnMatrix[positionY][positionX].get();
    }

    /**
     *  update mower located situation in lawn.
     * @param positionX
     * @param positionY
     * @param updateValue
     */
    public void update(int positionX, int positionY, boolean updateValue){

        this.lawnMatrix[positionY][positionX].set(updateValue);
    }
}