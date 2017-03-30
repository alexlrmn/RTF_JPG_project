package feature_extraction.Parsers;
import feature_extraction.Metadata.DataTree;
import feature_extraction.Metadata.DataTreeNode;
import feature_extraction.Metadata.IMetadata;
import feature_extraction.Metadata.RTFMetadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Alex on 3/18/2017.
 */
public class RTFParser implements IParser {

    private String readFile(String path) {
        String file_cont;
        try {
            Scanner in = new Scanner(new File(path));
            file_cont = in.useDelimiter("\\Z").next().replace("\n", "").replace("\r", "");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            file_cont = null;
        }

        return file_cont;
    }

    public IMetadata Parse(String path) {

        Map<String, Integer> word_count = new HashMap<>();
        Map<String, List<Integer>> word_params = new HashMap<>();
        String file_cont = readFile(path).replace("\\*\\", "\\");

        DataTreeNode root = null;
        int curr_index = 0;

        boolean EOF = false;

        if (file_cont.charAt(0) == '{') {

            root = new DataTreeNode("\\root", null);
            DataTreeNode parent;
            DataTreeNode iterator = root;
            StringBuilder data = new StringBuilder();
            StringBuilder word_builder = new StringBuilder();
//            StringBuilder
            boolean build_word = false;
            String word;

            for (int i = 0; i < file_cont.length(); i++) {
                switch (file_cont.charAt(i)) {
                    case '{':
                        iterator.setData(data.toString());
                        parent = iterator;
                        iterator = new DataTreeNode(parent);
                        data = new StringBuilder();

                        if (build_word){
                            word = word_builder.toString();
                            if (!word.isEmpty()){
                                addEntry(word_params, word_count, word);
                            }
                            build_word = false;
                            word_builder = new StringBuilder();
                        }

                        break;
                    case '}':

                        if ( iterator.getParent() == null && iterator.getDepth() == 0 ) {
                            iterator.addChild(new DataTreeNode(data.toString(), iterator));
                            if (!EOF)
                                curr_index = i;

                            EOF = true;
                            break;
                        }

                        iterator.setData(data.toString());
                        iterator = iterator.getParent();
                        data = new StringBuilder();

                        if (build_word){
                            word = word_builder.toString();
                            addEntry(word_params, word_count, word);
                            build_word = false;
                            word_builder = new StringBuilder();
                        }

                        break;
                    default:
                        if (i != 0 && file_cont.charAt(i-1) == '}'){
                            int j = i;
                            StringBuilder tmp = new StringBuilder();
                            while (file_cont.charAt(j) != '}'){
                                tmp.append(file_cont.charAt(j));
                                j++;
                            }

                            System.out.println(tmp.toString());
                        }

                        char curr = file_cont.charAt(i);
                        data.append(curr);
                        if (curr == '\\'){
                            build_word = true;
                            word = word_builder.toString();
                            if (!word.isEmpty()){
                                addEntry(word_params, word_count, word);
                            }
                            word_builder = new StringBuilder();
                        }
                        else if (curr == ' ' && build_word){
                            build_word = false;
                            word = word_builder.toString();
                            addEntry(word_params, word_count, word);
                            word_builder = new StringBuilder();
                        }
                        else if (curr == '\''){
                            build_word = false;
                            word_builder = new StringBuilder();
                        }
                        else if (build_word){
                            if (curr != ';')
                                word_builder.append(curr);
                        }

                        break;
                }
            }

        }

        DataTree dt = new DataTree(root);
//        dt.writeTree("D:\\temp\\rtf\\tree.txt");
        IMetadata metadata = new RTFMetadata(dt, curr_index, file_cont, word_count, word_params);
        return metadata;
    }

    private void addEntry(Map<String, List<Integer>> word_params, Map<String, Integer> word_count, String control_word){
        if (control_word.contains("\\'"))
            return;
        String[] part = control_word.split("(?<=\\D)(?=\\d)");
        try{
            int count = word_count.containsKey(part[0]) ? word_count.get(part[0]) : 0;
            word_count.put(part[0], count+1);

            if (part.length > 1){
                List<Integer> list = word_params.containsKey(part[0]) ? word_params.get(part[0]) : new ArrayList<Integer>();
                StringBuilder param = new StringBuilder();
                for (int i = 0; i < part[1].length(); i++){
                    if (tryParse(part[1].charAt(i) + "")){
                        param.append(part[1].charAt(i));
                    }
                    else break;
                }

                list.add(new Integer(param.toString()));
                word_params.put(part[0], list);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean tryParse(String num) {
        try {
            int x = Integer.parseInt(num);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
