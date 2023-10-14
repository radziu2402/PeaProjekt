package TSP.algorithms;

import TSP.data.Matrix;

public interface Algorithm {
    /**
     * Run algorithm on Matrix
     */
    AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException;

    /**
     * Get name of an algorithm
     *
     */
    String getName();
}
