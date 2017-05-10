package test;

import FeatureExtraction.AFeatureExtractor;
import FeatureExtraction.IFeatureExtractor;
import FeatureExtraction.FeatureExtractorStructuralPathsJPG;
import FeatureExtraction.FeatureExtractorStructuralPathsRTF;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



public class Program {

    static String jpg_path_b = "D:\\Final Project\\Files\\JPG\\benign\\";
    static String jpg_path_m = "D:\\Final Project\\Files\\JPG\\malicious\\";
    static String jpg_path_t = "D:\\Final Project\\Files\\JPG\\test\\";

    static String rtf_path_b = "D:\\Final Project\\files\\RTF\\benign\\";
    static String rtf_path_m = "D:\\Final Project\\files\\RTF\\malicious\\";
    static String rtf_path_t = "D:\\Final Project\\files\\RTF\\test\\";

    public static void main(String[] args) {


        String rtf_single_test = "D:\\Final Project\\Files\\RTF\\test\\de1ec18ac21543921322674d7e37ecb856cae8022ec7c56a0dedfcf28fe8650c1";
//        testFeatureExtractorRTF(rtf_path_b);
//        testFeatureExtractorRTF(rtf_path_m);
//        TestRTFExtractor();

        testFeatureExtractorJPG(jpg_path_b);
//        testFeatureExtractorJPG();

//        checkRunTime(jpg_path_b, "b");
//        checkRunTime(jpg_path_m, "m");
//        checknew(jpg_path_b);

//        checknewrtf(rtf_path_b);
//
//        checkRunTimeRTF(rtf_path_b, "b");
//        checkRunTimeRTF(rtf_path_m, "m");
    }

    private static void checknewrtf(String path) {
        FeatureExtractorStructuralPathsRTF fe = new FeatureExtractorStructuralPathsRTF(false, false, false);
        Set<String> hashset = new HashSet<>();
        int count = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            for (Path p : directoryStream) {

                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                System.out.println(map.size());
                hashset.addAll(map.keySet());
                count++;
                System.out.println(count);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (String key : hashset) {
            sb.append(String.format("%s \n", key));
        }

        try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\RTF\\features\\features_new.txt")){
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void checknew(String path){
        FeatureExtractorStructuralPathsJPG fe = new FeatureExtractorStructuralPathsJPG(false, false, false);
        Set<String> hashset = new HashSet<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            for (Path p : directoryStream) {

                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                System.out.println(map.size());
                hashset.addAll(map.keySet());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (String key : hashset) {
            sb.append(String.format("%s \n", key));
        }

        try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\JPG\\features\\features_new.txt")){
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

//    static void checkRunTimeRTF(String path, String c) {
//        FeatureExtractorStructuralPathsRTF fe = new FeatureExtractorStructuralPathsRTF();
//        Set<String> hashset = new HashSet<>();
//        Set<String> hashset1 = new HashSet<>();
//
//        long totaltime = 0;
//        long totaltime1 = 0;
//
//        StringBuilder sb = new StringBuilder();
//
//        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
//            int count = 0;
//            for (Path p : directoryStream) {
//                long time = 0;
//                long time1 = 0;
//
//                long startTime = System.nanoTime();
//
//
//
//                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                long endTime = System.nanoTime();
//                time = (endTime - startTime);
//
//                startTime = System.nanoTime();
//                Map<String, Integer> map1 = fe.ExtractFeaturesFrequencyFromSingleElement1(p.toString());
//                endTime = System.nanoTime();
//                time1 = (endTime - startTime);
//
//                hashset.addAll(map.keySet());
//                hashset1.addAll(map1.keySet());
//
//                sb.append(p.toString() + " Number old: " + map.size() + " Number new: " + map1.size() + " time old: " + time + " time new" + time1 + "\n");
//                totaltime += time;
//                totaltime1 += time1;
//
//                count++;
//                System.out.println(count);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        sb.append("Total features old: " + hashset.size() + "\n");
//        sb.append("Total features new: " + hashset1.size() + "\n");
//        sb.append("Total time took for old version: " + totaltime + "\n");
//        sb.append("Total time took for new version: " + totaltime1 + "\n");
//
//        try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\RTF\\logs\\compare" + c + ".txt")){
//            out.println(sb.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Done");
//    }
    /*
    private static void checkRunTime(String path, String c) {
        FeatureExtractorStructuralPathsJPG fe = new FeatureExtractorStructuralPathsJPG(true, true, true);
        Set<String> hashset = new HashSet<>();
        Set<String> hashset1 = new HashSet<>();

        long totaltime = 0;
        long totaltime1 = 0;

        StringBuilder sb = new StringBuilder();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 0;
            for (Path p : directoryStream) {
                long time = 0;
                long time1 = 0;

                long startTime = System.nanoTime();



                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
                long endTime = System.nanoTime();
                time = (endTime - startTime);

                startTime = System.nanoTime();
                Map<String, Integer> map1 = fe.ExtractFeaturesFrequencyFromSingleElement1(p.toString());
                endTime = System.nanoTime();
                time1 = (endTime - startTime);

                //
//                try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\RTF\\features\\old_method\\" + name + ".txt")){
//                    out.println(sb.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                hashset.addAll(map.keySet());
                hashset1.addAll(map1.keySet());

                sb.append(p.toString() + " Number old: " + map.size() + " Number new: " + map1.size() + " time old: " + time + " time new" + time1 + "\n");
                totaltime += time;
                totaltime1 += time1;

                count++;
                System.out.println(count);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sb.append("Total features old: " + hashset.size() + "\n");
        sb.append("Total features new: " + hashset1.size() + "\n");
        sb.append("Total time took for old version: " + totaltime + "\n");
        sb.append("Total time took for new version: " + totaltime1 + "\n");

        try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\JPG\\logs\\compare" + c + ".txt")){
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }
    */

    public static void testFeatureExtractorRTF(String path){
        IFeatureExtractor<String> fe = new FeatureExtractorStructuralPathsRTF<>(false, false, false);
        List<Integer> ss = new ArrayList<>();
        List<Map> ll = new ArrayList<>();
        Set<String> hashset = new HashSet<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 0;
            for (Path p : directoryStream) {
//                System.out.println(p.toString());
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
//                System.out.println(map.size());
//                StringBuilder sb = new StringBuilder();
//                List<String> map_list = new ArrayList(map.keySet());
////                Collections.sort(map_list);
//                for (String s : map_list){
////                    sb.append(s + " : "  + map.get(s) + "\n");
//                    if (s.contains("*")) System.out.println(s);
//                }
//
//                String[] splits = p.toString().split("\\\\");
//                String name = splits[splits.length - 1];
//
//                try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\RTF\\features\\old_method\\" + name + ".txt")){
//                    out.println(sb.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                ll.add(map);
                count++;
                System.out.println(count);
                ss.add(map.size());
                hashset.addAll(map.keySet());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("size = " + hashset.size());
        Collections.sort(ss);
        System.out.println("done rtf");

        TreeMap<String, Integer> sorted_map = new TreeMap<>();
        for (String key : hashset){
            sorted_map.put(key, key.length());
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> m : sorted_map.entrySet()){
            sb.append(String.format("%s > %s\n", m.getKey(), m.getValue().toString()));
        }

        try (PrintWriter out = new PrintWriter("D:\\Final Project\\Files\\RTF\\features\\feature_len.txt")){
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void testFeatureExtractorJPG(String path) {

        IFeatureExtractor<String> fe = new FeatureExtractorStructuralPathsJPG<>(true, false,false);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(path))) {
            int count = 1;
            for (Path p : directoryStream) {

                long startTime = System.nanoTime();
                Map<String, Integer> map = fe.ExtractFeaturesFrequencyFromSingleElement(p.toString());
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);
                System.out.println(duration);
//                System.out.println(map.size());
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
        AFeatureExtractor fe = new FeatureExtractorStructuralPathsRTF(false, false, false);
        Map<String, Integer> features = fe.ExtractFeaturesFrequencyFromSingleElement("D:\\Final Project\\Files\\RTF\\test\\1bade16bad51dcdc611c5f3401154c8b81e1984915900e07741ad640ccf2d8db");

        TreeMap<String, Integer> faturesSorted = new TreeMap<>(features);
        for (Map.Entry<String, Integer> entry : faturesSorted.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }
        System.out.println(features.size());
    }

    private static void testFeatureExtractorJPG() {
        System.out.println("Testing JPG Feature Extraction!");
        FeatureExtractorStructuralPathsJPG fe = new FeatureExtractorStructuralPathsJPG(false, false, false);

        long start = System.currentTimeMillis();
        Map<String, Integer> features = fe.ExtractFeaturesFrequencyFromSingleElement("D:\\Final Project\\Files\\JPG\\benign\\ff086da20be2fd048690fc4b15c2ed6397ba8db37f8e1182803ba692bcdeab21");
        long end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;
        System.out.println(time);

        TreeMap<String, Integer> faturesSorted = new TreeMap<>(features);
        for (Map.Entry<String, Integer> entry : faturesSorted.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }
        System.out.println(features.size());
    }
}
