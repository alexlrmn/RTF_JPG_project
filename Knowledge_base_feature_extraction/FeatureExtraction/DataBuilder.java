import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Alex on 5/3/2017.
 */
public class DataBuilder {

    public DataBuilder() {

    }

    public static StringBuilder CreateDataset(String path, AFeatureExtractor fe, Classification classification) {

        StringBuilder sb = new StringBuilder();

        String file_rep;
        Map<String, String> features;
        int count = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            for (Path file : directoryStream) {
                count++;
                file_rep = file.toString().concat(",");
                features = fe.ExtractFeaturesFrequencyFromSingleElement(file.toString());
                file_rep = file_rep.concat(concat_dict(features));
                file_rep = file_rep.concat(classification.toString()).concat("\n");

                sb.append(file_rep);
                System.out.println(count);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return sb;
    }

    private static String concat_dict(Map<String, String> features) {

        String file_rep = "";
        List<String> feature_names = new ArrayList<>(features.keySet());
        Collections.sort(feature_names);

        for (String name : feature_names) {
            file_rep = file_rep.concat(features.get(name)).concat(",");
        }

        return file_rep;
    }
}
