package TSP;

import TSP.algorithms.AlgorithmResult;
import TSP.measurement.MeasuredAlghorithmResult;
import TSP.utils.ReportGenerator;

import java.util.List;

public class CSVReportGenerator implements ReportGenerator {
    @Override
    public String generateReport(List<MeasuredAlghorithmResult> results) {
        StringBuilder report = new StringBuilder();
        for (MeasuredAlghorithmResult result : results) {
            AlgorithmResult algorithmResult = result.getAlgorithmResult();
            report.append(algorithmResult.getNumberOfVertices());
            report.append(';');
            report.append(result.getExecutionTime());
            report.append('\n');
        }

        return report.toString();
    }
}
