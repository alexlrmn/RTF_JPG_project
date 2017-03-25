package feature_extraction;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Created by Alex on 3/18/2017.
 */
public class RTFParser implements IParser {

    private FeatureTree _ft;

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

    public void Parse(String path) {

        String file_cont = readFile(path);

        FeatureTreeNode root = null;
        int curr_index = 0;

        boolean EOF = false;

        if (file_cont.charAt(0) == '{'){
            int index = file_cont.substring(1).indexOf('{');

            root = new FeatureTreeNode(file_cont.substring(1, index));

            FeatureTreeNode parent;
            FeatureTreeNode iterator = root;
            StringBuilder data = new StringBuilder();

            for (int i = index ; i < file_cont.length() && !EOF; i++){
                switch (file_cont.charAt(i)){
                    case '{':
                        iterator.setData(data.toString());
                        parent = iterator;
                        iterator = new FeatureTreeNode(parent);
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

                        if (iterator.getParent() == null && iterator.getDepth() == 0){
                            iterator.addChild(new FeatureTreeNode(data.toString(), iterator));
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

        this._ft = new FeatureTree(root);
    }

    public FeatureTree getTree(){
        return _ft;
    }


}
