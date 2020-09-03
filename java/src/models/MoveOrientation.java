package models;

public enum MoveOrientation {
    N, S, W, E;

    /**
     * The orientation is
     *
     * @param currentOrientation
     * @param action String
     * @return
     */
    public static MoveOrientation nextOrientation(MoveOrientation currentOrientation, char action) {

        if (action == 'R') {
            switch (currentOrientation) {
                case N: return E;
                case S: return W;
                case E: return S;
                case W: return N;
            }
        } else {
            switch (currentOrientation) {
                case N: return W;
                case S: return E;
                case E: return N;
                case W: return S;
            }
        }
        return null;
    }
}
