package TSP;

import TSP.algorithms.Algorithm;
import TSP.algorithms.BruteForce;
import TSP.file.ToFileWriter;
import TSP.implementations.automated.CSVReportGenerator;
import TSP.implementations.automated.RandomDataProvider;
import TSP.implementations.automated.StubAlgorithmProvider;
import TSP.implementations.forhuman.HumanAlgorithmProvider;
import TSP.implementations.forhuman.HumanDataProvider;
import TSP.implementations.forhuman.HumanReadableReportGenerator;
import TSP.measurement.AlgorithmMeasurement;
import TSP.menu.TSP;
import TSP.utils.ReportGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Main.doHuman();
    }

    public static void doHuman() throws IOException {
        ReportGenerator reportGenerator = new HumanReadableReportGenerator();
        TSP tsp = new TSP(
                new HumanDataProvider(),
                new HumanAlgorithmProvider(),
                reportGenerator,
                new AlgorithmMeasurement(10000)
        );
        tsp.run();
        new ToFileWriter("report.log").write(reportGenerator.generateReport());
    }

    public static void doAutomated() throws IOException {
        ReportGenerator reportGenerator = new CSVReportGenerator();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new BruteForce());
        TSP tsp = new TSP(
                new RandomDataProvider(new int[]{
                        8,8,8,8,8,8,8,8,8,8,
                        9,9,9,9,9,9,9,9,9,9,
                        10,10,10,10,10,10,10,10,10,10,
                        11,11,11,11,11,11,11,11,11,11,
                        12,12,12,12,12,12,12,12,12,12
                }),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurement(10000)
        );
        tsp.run();
        new ToFileWriter("report.csv").write(reportGenerator.generateReport());
    }
}
