package algorithms;

import data.Matrix;

public interface Algorithm {
    AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException;
}
