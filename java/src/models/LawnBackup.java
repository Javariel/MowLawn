package models;


import java.util.Arrays;


public class LawnBackup {
    /**
     * Binary matrix attribute lawnMatrix represents the cartesian coordinates system.
     * It is initiated as false as beginning signified that no mower occupies anywhere.
     * With update method, it can update mower occupied situation.
     */
    private boolean[][] lawnMatrix;

    public LawnBackup(int sizeX, int sizeY) {
        this.lawnMatrix = new boolean[sizeY + 1][sizeX + 1];
    }

    public boolean[][] getLawnMatrix() {
        return lawnMatrix;
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
    public boolean isInLawn(int positionX, int positionY) {
        return positionX < this.lawnMatrix[0].length && positionY < this.lawnMatrix.length;
    }

    /**
     * check if a given position is occupied by any mower
     * @param positionX
     * @param positionY
     * @return
     */
    public boolean isLawnPositionOccupied(int positionX, int positionY) {
        return this.lawnMatrix[positionY][positionX];
    }

    /**
     *  update mower located situation in lawn.
     * @param positionX
     * @param positionY
     * @param updateValue
     */
    public void update(int positionX, int positionY, boolean updateValue){

        this.lawnMatrix[positionY][positionX] = updateValue;
    }
}