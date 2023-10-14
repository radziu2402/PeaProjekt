package TSP.implementations.automated;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmProvider;

import java.util.List;

public class StubAlgorithmProvider implements AlgorithmProvider {
    private final List<Algorithm> algorithms;

    public StubAlgorithmProvider(List<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

    @Override
    public Algorithm getAlgorithm() {
        try {
            return algorithms.remove(0);
        } catch (Exception ignored) {
            return null;
        }
    }
}
