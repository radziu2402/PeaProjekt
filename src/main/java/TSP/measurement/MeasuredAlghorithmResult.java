package TSP.measurement;

import TSP.algorithms.AlgorithmResult;

public class MeasuredAlghorithmResult {
    /**
     * Basic result of an algorithm
     */
    private final AlgorithmResult result;

    /**
     * Execution time of an algohrithm
     */
    private final long executionTime;

    /**
     * Flag that checks wheater alghorithm succeded generating result
     */
    private final boolean executedCorrectly;

    public MeasuredAlghorithmResult(
            AlgorithmResult result,
            long executionTime,
            boolean algorithmSucceed
    ) {
        this.result = result;
        this.executionTime = executionTime;
        this.executedCorrectly = algorithmSucceed;
    }

    public AlgorithmResult getAlgorithmResult() {
        return result;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public boolean isExecutedCorrectly() {
        return executedCorrectly;
    }
}
