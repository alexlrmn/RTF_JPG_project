package test;

import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.IFeatureExtractor;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.FeatureExtractorJPG;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Metadata.IMetadata;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Parsers.IParser;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Parsers.ParserRTF;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.FeatureExtractorRTF;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Program {
    public static void main(String[] args) {
        String jpg_path_test = "D:\\Final Project\\Files\\JPG\\test\\";
        String rtf_path_test = "D:\\Final Project\\files\\RTF\\test\\";

        testFeatureExtractorRTF(rtf_path_test);
        testFeatureExtractorJPG(jpg_path_test);
    }

    public static void testRTFParser(String path) {
        IParser p = new ParserRTF();
        IMetadata m = p.Parse(path);
        System.out.println();
    }

    public static void testFeatureExtractorRTF(String path){
        IFeatureExtractor<String> fe = new FeatureExtractorRTF<>(true, true, true);
        List<Integer> ss = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 1;
            for (Path p : directoryStream) {
//                System.out.println(p.toString());
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                StringBuilder sb = new StringBuilder();
//                List<String> map_list = new ArrayList(map.keySet());
//                Collections.sort(map_list);
//                for (String s : map_list){
//                    sb.append(s + " : "  + map.get(s) + "\n");
//                }

//                try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\RTF\\features\\" + count + ".txt")){
//                    out.println(sb.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }

                count++;
                ss.add(map.size());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Collections.sort(ss);
        System.out.println("done rtf");


    }

    public static void testFeatureExtractorJPG(String path) {

        IFeatureExtractor<String> fe = new FeatureExtractorJPG<>(true, true,true);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 1;
            for (Path p : directoryStream) {
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                StringBuilder sb = new StringBuilder();
//                List<String> map_list = new ArrayList(map.keySet());
//                Collections.sort(map_list);
//                for (String s : map_list){
//                    sb.append(s + " : "  + map.get(s) + "\n");
//                }

//                try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\JPG\\features\\" + count + ".txt")){
//                    out.println(sb.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }

                count++;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("done jpg");


    }
}
