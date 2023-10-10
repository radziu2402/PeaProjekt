package data;

public class Matrix {
    private final int[][] distanceMatrix;

    public Matrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

}