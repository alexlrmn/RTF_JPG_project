package test;

import FeatureExtraction.AFeatureExtractor;
import FeatureExtraction.IFeatureExtractor;
import FeatureExtraction.FeatureExtractorStructuralPathsJPG;
import FeatureExtraction.FeatureExtractorStructuralPathsRTF;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Program {
    public static void main(String[] args) {
        String jpg_path_test = "D:\\Final Project\\Files\\JPG\\test\\";
        String rtf_path_test = "D:\\Final Project\\files\\RTF\\benign\\";
        String rtf_single_test = "D:\\Final Project\\Files\\RTF\\test\\0190d0b5d853fe9b3cb2de0db832d4aefde413edb12813d5da5ea3ab236f836e";
        testFeatureExtractorRTF(rtf_path_test);

//        testFeatureExtractorJPG(jpg_path_test);
//        TestRTFExtractor();
    }



    public static void testFeatureExtractorRTF(String path){
        IFeatureExtractor<String> fe = new FeatureExtractorStructuralPathsRTF<>(true, true, true);
        List<Integer> ss = new ArrayList<>();
        List<Map> ll = new ArrayList<>();
        Set<String> hashset = new HashSet<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 1;
            for (Path p : directoryStream) {
//                System.out.println(p.toString());
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                System.out.println(map.size());
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
                ll.add(map);
                count++;
                ss.add(map.size());
                hashset.addAll(map.keySet());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("size = " + hashset.size());
        Collections.sort(ss);
        System.out.println("done rtf");


    }

    public static void testFeatureExtractorJPG(String path) {

        IFeatureExtractor<String> fe = new FeatureExtractorStructuralPathsJPG<>(true, false,false);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 1;
            for (Path p : directoryStream) {
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
                System.out.println(map.size());
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

    private static void TestRTFExtractor() {
        System.out.println("Testing RTF Feature Extraction!");
        AFeatureExtractor fe = new FeatureExtractorStructuralPathsRTF(true, true, true);
        Map<String, Integer> features = fe.ExtractFeaturesFrequencyFromSingleElement("D:\\Final Project\\Files\\RTF\\test\\de1ec18ac21543921322674d7e37ecb856cae8022ec7c56a0dedfcf28fe8650c");

        TreeMap<String, Integer> faturesSorted = new TreeMap<>(features);
        for (Map.Entry<String, Integer> entry : faturesSorted.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }
        System.out.println(features.size());
    }
}
