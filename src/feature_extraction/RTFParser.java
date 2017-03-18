package feature_extraction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Alex on 3/18/2017.
 */
public class RTFParser implements IParser {

    public enum featureType {

    }

    public String readFile(String path) {
        String file_cont;
        try{
            Scanner in = new Scanner(new FileReader(path));
            StringBuilder sb = new StringBuilder();

            while (in.hasNext()){
                sb.append(in.next());
            }

            file_cont = sb.toString();

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            file_cont = null;
        }

        return file_cont;
    }

    public void parse(String file_cont) {
        FeatureTree ft = new FeatureTree();
        FeatureTreeNode header;
        FeatureTreeNode document;
        int curr_index = 0;


        for (int i = 0 ; i < file_cont.length(); i++){
            if (file_cont.charAt(i) == '{'){

            }
        }
    }

    public static void main(String[] args) {
        String path = "d:\\temp\\test.rtf";




    }

}
