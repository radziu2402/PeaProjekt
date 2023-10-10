package atsp.algorithms;

import atsp.data.Matrix;

import java.util.Arrays;

public class BruteForce implements Algorithm {

    public void runAlgorithm(Matrix data) {
        if (data == null) {
            System.out.println("Najpierw wczytaj lub wygeneruj dane.");
        } else {
            int numCities = data.getDistanceMatrix().length;
            int[] bestTour = null;
            int minDistance = Integer.MAX_VALUE;

            int[] tour = new int[numCities];
            for (int i = 0; i < numCities; i++) {
                tour[i] = i;
            }

            do {
                int currentDistance = calculateTourDistance(tour, data);

                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    bestTour = Arrays.copyOf(tour, numCities);
                }
            } while (nextPermutation(tour));

            System.out.println("Najkrótsza trasa: " + Arrays.toString(bestTour));
            System.out.println("Długość trasy: " + minDistance);
        }
    }

    private int calculateTourDistance(int[] tour, Matrix data) {
        int distance = 0;
        int[][] matrix = data.getDistanceMatrix();
        for (int i = 0; i < tour.length - 1; i++) {
            distance += matrix[tour[i]][tour[i + 1]];
        }
        distance += matrix[tour[tour.length - 1]][tour[0]]; // Return to the starting city
        return distance;
    }

    private boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] > array[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false; // No more permutations
        }

        int j = array.length - 1;
        while (array[j] < array[i]) {
            j--;
        }

        swap(array, i, j);
        reverse(array, i + 1);
        return true;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void reverse(int[] array, int start) {
        int end = array.length - 1;
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}
