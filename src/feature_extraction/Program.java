package feature_extraction;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 3/23/2017.
 */
public class Program {
    public static void main(String[] args) {
//        String path = "D:\\temp\\JPEG_collection";
//        RTFParser rfp = new RTFParser();
//        rfp.Parse(path);
//        FeatureTree ft = rfp.getTree();
//        ft.writeTree("D:\\temp\\tree.txt");
        IParser jpp = new JPGParser();
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(args[0]))) {
            for (Path p : directoryStream) {
                jpp.Parse(p.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//      jpp.Parse(path);
    }
}
