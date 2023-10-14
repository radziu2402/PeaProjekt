package TSP.utils;

import TSP.measurement.MeasuredAlghorithmResult;

import java.util.List;

public interface ReportGenerator {
    /**
     * Generates report about the ran TSP.algorithms
     *
     * @return String version of a report f.e. in HTML or CSV
     */
    String generateReport();

    /**
     * @param result Results of the run algorithms
     */
    void addToReport(MeasuredAlghorithmResult result);
}