package TSP.measurement;

import TSP.algorithms.AlgorithmResult;

public class MeasuredAlghorithmResult {
    private final AlgorithmResult result;

    /**
     * Execution time of an algohrithm
     */
    private final long executionTime;

    public MeasuredAlghorithmResult(AlgorithmResult result, long executionTime) {
        this.result = result;
        this.executionTime = executionTime;
    }

    public AlgorithmResult getAlgorithmResult() {
        return result;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
