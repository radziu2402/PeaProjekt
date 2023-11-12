package TSP;

import TSP.algorithms.Algorithm;
import TSP.algorithms.BranchAndBound;
import TSP.algorithms.BruteForce;
import TSP.algorithms.DynamicProgramming;
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
                new AlgorithmMeasurement(60*10000000)
        );
        tsp.run();
        new ToFileWriter("report.log").write(reportGenerator.generateReport());
    }

    public static void doAutomated() throws IOException {
        ReportGenerator reportGenerator = new CSVReportGenerator();
        ArrayList<Algorithm> algorithms = new ArrayList<>();
        algorithms.add(new BranchAndBound());
        TSP tsp = new TSP(
                new RandomDataProvider(new int[]{
                        5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                        10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                        15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
                        20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
                        21, 21, 21, 21, 21, 21, 21, 21, 21, 21,
                        22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
                        23, 23, 23, 23, 23, 23, 23, 23, 23, 23,
                        24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
                        25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
                        27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
                        29, 29, 29, 29, 29, 29, 29, 29, 29, 29,
                        31, 31, 31, 31, 31, 31, 31, 31, 31, 31,
                        33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
                        35, 35, 35, 35, 35, 35, 35, 35, 35, 35,
                        37, 37, 37, 37, 37, 37, 37, 37, 37, 37,
                        39, 39, 39, 39, 39, 39, 39, 39, 39, 39,
                        41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
                        42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
                        43, 43, 43, 43, 43, 43, 43, 43, 43, 43,
                        44, 44, 44, 44, 44, 44, 44, 44, 44, 44,
                        45, 45, 45, 45, 45, 45, 45, 45, 45, 45
                }),
                new StubAlgorithmProvider(algorithms),
                reportGenerator,
                new AlgorithmMeasurement(4*60*1000)
        );
        tsp.run();
        new ToFileWriter("report.csv").write(reportGenerator.generateReport());
    }
}
