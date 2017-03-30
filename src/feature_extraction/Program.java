package feature_extraction;

import feature_extraction.Metadata.IMetadata;
import feature_extraction.Parsers.IParser;
import feature_extraction.Parsers.RTFParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Alex on 3/23/2017.
 */
public class Program {
    public static void main(String[] args) {
//        String path = "D:\\Final Project\\Files\\JPG\\malicious\\0a0aefc88b85980d93c85af9ac0c429c2eb12bec1be06a028d28dbddcd55d2da";
//        RTFParser rfp = new RTFParser();
//        rfp.Parse(path);
//        FeatureTree ft = rfp.getTree();
//        ft.writeTree("D:\\temp\\tree.txt");
//        IParser jpp = new JPGParser();
//        List<String> fileNames = new ArrayList<>();
//        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
//            for (Path p : directoryStream) {
//                jpp.Parse(p.toString());
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//         jpp.Parse(path);
       testRTFParser("D:\\Final Project\\Files\\RTF\\benign\\test.rtf");
//        testJPGFeatureExtractor();

    }

    public static void testRTFParser(String path) {
        IParser p = new RTFParser();
        IMetadata m = p.Parse(path);
        System.out.println();
    }

    public static void testJPGFeatureExtractor() {
        IFeatureExtractor<String> fe = new JPGFeatureExtractor<>(true, true, true);

        String path = "D:\\Final Project\\Files\\JPG\\malicious\\";
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 1;
            for (Path p : directoryStream) {
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
                StringBuilder sb = new StringBuilder();
                List<String> map_list = new ArrayList(map.keySet());
                Collections.sort(map_list);
                for (String s : map_list){
                    sb.append(s + " : "  + map.get(s) + "\n");
                }

                try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\JPG\\features\\" + count + ".txt")){
                    out.println(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                count++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }
}
