package feature_extraction;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Created by Alex on 3/18/2017.
 */
public class RTFParser implements IParser {

    private String readFile(String path) {
        String file_cont;
        try {
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            file_cont = null;
        }

        return file_cont;
    }

    public IMetadata Parse(String path) {

        Map<String, List<Integer>> word_count = new HashMap<>();

        String file_cont = readFile(path);

        DataTreeNode root = null;
        int curr_index = 0;

        boolean EOF = false;

        if (file_cont.charAt(0) == '{') {
            int index = file_cont.substring(1).indexOf('{');

            root = new DataTreeNode(file_cont.substring(1, index));

            DataTreeNode parent;
            DataTreeNode iterator = root;
            StringBuilder data = new StringBuilder();

            for (int i = index; i < file_cont.length(); i++) {
                switch (file_cont.charAt(i)) {
                    case '{':
                        iterator.setData(data.toString());
                        parent = iterator;
                        iterator = new DataTreeNode(parent);
                        parent.addChild(iterator);
                        data = new StringBuilder();
                        break;
                    case '}':
//                        iterator.setData(data.toString());
//
//                        if (iterator.getParent().equals(root)) {
//                            eof = true;
//                            curr_index = i;
//                            break;
//                        }

                        if ( iterator.getParent() == null && iterator.getDepth() == 0) {
                            iterator.addChild(new DataTreeNode(data.toString(), iterator));
                            EOF = true;
                            curr_index = i;
                            break;
                        }

                        iterator.setData(data.toString());
                        iterator = iterator.getParent();
                        data = new StringBuilder();

                        break;
                    default:
                        data.append(file_cont.charAt(i));
                        break;
                }
            }

        }

        DataTree dt = new DataTree(root);

    }

    private void addEntry(Map<String, List<Integer>> word_count, String control_word){

    }

}
