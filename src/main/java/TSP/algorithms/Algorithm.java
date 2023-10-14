package TSP.algorithms;

import TSP.data.Matrix;

public interface Algorithm {
    AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException;
}
