import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Alex on 4/22/2017.
 */
public class Program {

    static String jpg_path_b = "D:\\Final Project\\Files\\JPG\\benign\\";
    static String jpg_path_m = "D:\\Final Project\\Files\\JPG\\malicious\\";
    static String jpg_output = "D:\\Final Project\\Files\\JPG\\dataset\\DatasetJPG.csv";

    static String rtf_path_b = "D:\\Final Project\\files\\RTF\\benign\\";
    static String rtf_path_m = "D:\\Final Project\\files\\RTF\\malicious\\";
    static String rtf_output = "D:\\Final Project\\Files\\RTF\\dataset\\DatasetRTF.csv";

    public static void main(String[] args) {

        DatsetBuilderRTF();
        DatasetBuilderJPG();

    }

    private static void DatsetBuilderRTF() {
        AFeatureExtractor<String> fe = new FeatureExtractorKnowledgeBasedRTF<>();

        StringBuilder benign_data = DataBuilder.CreateDataset(rtf_path_b, fe, Classification.Benign);
        StringBuilder malicious_data = DataBuilder.CreateDataset(rtf_path_m, fe, Classification.Malicious);

        int result = CSVBuilder.CreateDataset(rtf_output, fe.getHeaders(), benign_data, malicious_data);
        System.out.println(result);
    }

    public static void DatasetBuilderJPG() {

        AFeatureExtractor<String> fe = new FeatureExtractorKnowledgeBasedJPG<>();

        StringBuilder benign_data = DataBuilder.CreateDataset(jpg_path_b, fe, Classification.Benign);
        StringBuilder malicious_data = DataBuilder.CreateDataset(jpg_path_m, fe, Classification.Malicious);

        int result = CSVBuilder.CreateDataset(jpg_output, fe.getHeaders(), benign_data, malicious_data);
        System.out.println(result);
    }

}
