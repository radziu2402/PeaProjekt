package TSP.menu;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmProvider;
import TSP.data.DataProvider;
import TSP.measurement.AlgorithmMeasurement;
import TSP.data.Matrix;
import TSP.measurement.MeasuredAlghorithmResult;
import TSP.utils.ReportGenerator;

public class TSP {
    private final DataProvider dataProvider;
    private final AlgorithmProvider algorithmProvider;
    private final ReportGenerator reportGenerator;

    public TSP(
            DataProvider dataProvider,
            AlgorithmProvider algorithmProvider,
            ReportGenerator reportGenerator
    ) {
        this.dataProvider = dataProvider;
        this.algorithmProvider = algorithmProvider;
        this.reportGenerator = reportGenerator;
    }

    public void run() {
        Matrix data;
        MeasuredAlghorithmResult result;
        AlgorithmMeasurement algorithmMeasurement = new AlgorithmMeasurement();
        Algorithm algorithm = algorithmProvider.getAlgorithm();
        while ((data = dataProvider.getData()) != null && algorithm != null) {
            try {
                result = algorithmMeasurement.measureAlgorithm(algorithm, data);
                reportGenerator.addToReport(result);
            } catch (Exception ignored) {}
        }
    }
}
