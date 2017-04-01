package feature_extraction.Parsers;
import feature_extraction.Metadata.DataTree;
import feature_extraction.Metadata.DataTreeNode;
import feature_extraction.Metadata.IMetadata;
import feature_extraction.Metadata.RTFMetadata;

import java.io.*;
import java.util.*;

/**
 * Created by Alex on 3/18/2017.
 */
public class RTFParser implements IParser {

    public String readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
//                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

//    private String    readFile(String path) {
//        String file_cont;
//
//        File f = new File(path);
//        if(f.exists() && !f.isDirectory()) {
//            System.out.println("file exists");
//        }
//        else{
//            System.out.println("file doesn't exist");
//            return "";
//        }
//
//        try {
//            Scanner in = new Scanner(new File(path));
//            file_cont = in.useDelimiter("\\Z").next();
//            file_cont = file_cont.replace("\n", "").replace("\r", "");
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            file_cont = null;
//        }
//
//        return file_cont;
//    }

    public  IMetadata Parse(String path) {

        Map<String, Integer> word_count = new HashMap<>();
        Map<String, List<Integer>> word_params = new HashMap<>();
        String file_cont = "";
        try {
             file_cont = readFile(path).replace("\\*\\", "\\");
        } catch (Exception e){
            e.printStackTrace();
        }
        DataTreeNode root = null;
        int curr_index = 0;

        boolean EOF = false;

        if (file_cont.charAt(0) == '{') {

            root = new DataTreeNode("\\root");
            DataTreeNode parent;
            DataTreeNode iterator = root;
            StringBuilder data = new StringBuilder();
            String group_data;

            for (int i = 0; i < file_cont.length() && !EOF; i++) {
                switch (file_cont.charAt(i)) {
                    case '{':
                        if (!data.toString().isEmpty()) {
                            group_data = separate_data(word_count, word_params, data.toString());
                            iterator.setData(group_data);
                        }
                        parent = iterator;
                        iterator = new DataTreeNode(parent);
                        data = new StringBuilder();

                        break;
                    case '}':

                        if ( iterator.getParent() == null && iterator.getDepth() == 0 ) {
                            if (!EOF)
                                curr_index = i;

                            EOF = true;
                            break;
                        }

                        group_data = separate_data(word_count, word_params, data.toString());
                        iterator.setData(group_data);

                        iterator = iterator.getParent();
                        data = new StringBuilder();


                        break;
                    default:
                        if (file_cont.charAt(i-1) == '}'){
                            int j = i;
                            StringBuilder extra_cont = new StringBuilder();
                            while (j < file_cont.length() && file_cont.charAt(j) != '}' && file_cont.charAt(j) != '{'){
                                extra_cont.append(file_cont.charAt(j));
                                j++;
                            }

                            i = j - 1;
                            group_data = separate_data(word_count, word_params, extra_cont.toString());
                            parent = iterator;
                            iterator = new DataTreeNode(group_data, parent);
                            iterator = iterator.getParent();
                            break;
                        }

                        char curr = file_cont.charAt(i);
                        data.append(curr);

                        break;
                }
            }

        }

        DataTree dt = new DataTree(root);
//        dt.writeTree("D:\\temp\\rtf\\tree.txt");
        IMetadata metadata = new RTFMetadata(dt, curr_index, file_cont, word_count, word_params);
        return metadata;
    }

    private void      addEntry(Map<String, List<Integer>> word_params, Map<String, Integer> word_count, String control_word){
        if (control_word.contains("\\'"))
            return;
        String[] part = control_word.split("(?<=\\D)(?=\\d)");
        try{
            int count = word_count.containsKey(part[0]) ? word_count.get(part[0]) : 0;
            word_count.put(part[0], count+1);

            if (part.length > 1){
                List<Integer> list = word_params.containsKey(part[0]) ? word_params.get(part[0]) : new ArrayList<>();
                StringBuilder param = new StringBuilder();
                for (int i = 0; i < part[1].length(); i++){
                    if (tryParse(part[1].charAt(i) + "")){
                        param.append(part[1].charAt(i));
                    }
                    else break;
                }
                try {
                    list.add(new Integer(param.toString()));
                    word_params.put(part[0], list);
                } catch (Exception e){
                    System.out.println("Count not parse number " + param.toString());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String    separate_data(Map<String, Integer> word_count, Map<String, List<Integer>> word_params, String data) {

        StringBuilder group_control_words = new StringBuilder();
        StringBuilder group_data_builder = new StringBuilder();

        boolean build_control_word = false;
        StringBuilder control_word_builder = new StringBuilder();
        String control_word;

        for (int i = 0; i < data.length(); i++){
            char curr = data.charAt(i);

            if (curr == '\\'){
                build_control_word = true;
                control_word = control_word_builder.toString();
                if (!control_word.isEmpty()){
                    addEntry(word_params, word_count, control_word);
                    group_control_words.append("\\" + control_word);
                }

                control_word_builder = new StringBuilder();
            }
            else if (curr == ' ' && build_control_word){
                build_control_word = false;
                control_word = control_word_builder.toString();
                addEntry(word_params, word_count, control_word);

                group_control_words.append("\\" + control_word);
                control_word_builder = new StringBuilder();
            }
            else if (curr == '\''){
                build_control_word = false;
                control_word_builder = new StringBuilder();
            }
            else if (build_control_word){
                if (curr != ';')
                    control_word_builder.append(curr);
            }
            else {
                group_data_builder.append(curr);
            }
        }

        if (build_control_word && !control_word_builder.toString().isEmpty()){
            addEntry(word_params, word_count, control_word_builder.toString());
            group_control_words.append("\\" + control_word_builder.toString());
        }

        return group_control_words.append(" ").append(group_data_builder.toString()).toString();
    }

    private boolean   tryParse(String num) {
        try {
            int x = Integer.parseInt(num);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
