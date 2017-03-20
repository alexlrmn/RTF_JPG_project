package feature_extraction;

import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Alex on 3/18/2017.
 */
public class RTFParser implements IParser {

    private String readFile(String path) {
        String file_cont;
        try{
//            Scanner in = new Scanner(new FileReader(path));
//            StringBuilder sb = new StringBuilder();

            Scanner in = new Scanner(new File(path));

            file_cont = in.useDelimiter("\\Z").next().replace("\n", "").replace("\r", "");

            /*
            while (in.hasNext()){
                sb.append(in.next());
            }

            file_cont = sb.toString();
            */
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            file_cont = null;
        }

        return file_cont;
    }

    public FeatureTree Parse(String path) {

        String file_cont = readFile(path);

        FeatureTreeNode header = null;
        FeatureTreeNode document = null;
        int curr_index = 0;

        boolean eof = false;

        if (file_cont.charAt(0) == '{'){
            header = new FeatureTreeNode(null);

            FeatureTreeNode parent = null;
            FeatureTreeNode iterator = header;
            StringBuilder data = new StringBuilder();
            for (int i = 0 ; i < file_cont.length() && !eof; i++){
                switch (file_cont.charAt(i)){
                    case '{':
                        iterator.setData(data.toString());
                        parent = iterator;
                        iterator = new FeatureTreeNode(parent);
                        parent.addChild(iterator);
                        data = new StringBuilder();
                        break;
                    case '}':
                        iterator.setData(data.toString());
                        if (iterator.getParent().equals(header)) {
                            document = new FeatureTreeNode(null);
                            iterator = document;
                        }
                        else if (document != null && iterator.getParent().equals(document)) {
                            eof = true;
                            curr_index = i;
                            break;
                        }
                        else {
                            iterator = iterator.getParent();
                        }

                        data = new StringBuilder();

                        break;
                    default:
                        data.append(file_cont.charAt(i));
                        break;
                }
            }

        }

        FeatureTree fTree = new FeatureTree(header, document);
        return fTree;
        //ft.writeTree("d:\\temp\\tree.txt");

    }

//    public static void main(String[] args) {
//        String path = "d:\\temp\\test1.rtf";
//        String cont = readFile(path);
//        Parse(cont);
//
//    }

}
