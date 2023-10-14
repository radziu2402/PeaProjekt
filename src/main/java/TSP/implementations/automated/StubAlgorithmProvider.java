package TSP.implementations.automated;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmProvider;

public class StubAlgorithmProvider implements AlgorithmProvider {
    private final Algorithm algorithm;

    public StubAlgorithmProvider(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
