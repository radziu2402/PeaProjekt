package algorithms;

import data.Matrix;

public interface Algorithm {
    AlghorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException;
}
