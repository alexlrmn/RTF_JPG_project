import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 5/3/2017.
 */
public class CSVBuilder {

    public CSVBuilder() {

    }

    public static int CreateDataset(String path,
                                     List<String> headers,
                                     StringBuilder benign_files,
                                     StringBuilder malicious_files) {

        Collections.sort(headers);

        StringBuilder dataset = new StringBuilder();

        dataset.append("FilePath,".concat(String.join(",", headers)).concat(",class\n"));
        dataset.append(malicious_files.append(benign_files));

        try (PrintWriter out = new PrintWriter(path)) {
            out.write(dataset.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }


}
