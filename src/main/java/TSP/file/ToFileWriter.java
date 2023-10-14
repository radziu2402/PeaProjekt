package TSP.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ToFileWriter {
    private final String fileName;

    ToFileWriter(String fileName) {
        this.fileName = fileName;
    }

    void write(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);
        writer.close();
    }
}
