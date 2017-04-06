package FeatureExtractorStructuralPathsJPGRTF.Parsers;
import FeatureExtractorStructuralPathsJPGRTF.Metadata.DataTree;
import FeatureExtractorStructuralPathsJPGRTF.Metadata.DataTreeNode;
import FeatureExtractorStructuralPathsJPGRTF.Metadata.IMetadata;
import FeatureExtractorStructuralPathsJPGRTF.Metadata.MetadataRTF;

import java.io.*;
import java.util.*;


public class ParserRTF implements IParser {

    //<editor-fold desc="Read file">
    /**
     * Read the content of a file into a string
     * @param path The full path to a file
     * @return
     * @throws IOException
     */
    public String readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Parsing">
    /**
     *
     * @param path
     * @return
     */
    public  IMetadata Parse(String path) {

        //Hash map, contains features as keys, and number of occurrences as a value
        Map<String, Integer> word_count = new HashMap<>();

        //Hash map, contains features as keys, and a list of parameter encountered for every feature
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

        //Check if there is a rtf group
        if (file_cont.charAt(0) == '{') {

            root = new DataTreeNode("\\root");
            DataTreeNode parent;
            DataTreeNode iterator = root;
            StringBuilder data = new StringBuilder();
            String group_data;

            for (int i = 0; i < file_cont.length() && !EOF; i++) {

                //Build group hierarchy
                switch (file_cont.charAt(i)) {
                    case '{':

                        //Start a new group, save the data encountered so far
                        if (!data.toString().isEmpty()) {
                            group_data = separate_data(word_count, word_params, data.toString());
                            iterator.setData(group_data);
                        }

                        parent = iterator;
                        iterator = new DataTreeNode(parent);
                        data = new StringBuilder();

                        break;
                    case '}':

                        //End last group, check if last group is the main rtf group and update relevant parameters
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

                        //Check if there's data after a closed group and create a separate group for that data
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
        IMetadata metadata = new MetadataRTF(dt, curr_index, file_cont.length(), word_count, word_params);
        return metadata;
    }

    private void addEntry(Map<String, List<Integer>> word_params, Map<String, Integer> word_count, String control_word){
        //Not a control word
        if (control_word.contains("\\'"))
            return;

        //Separate control word from its parameter
        String[] part = control_word.split("(?<=\\D)(?=\\d)");

        try{

            int count = word_count.containsKey(part[0]) ? word_count.get(part[0]) : 0;
            word_count.put(part[0], count+1);

            //check if control word has parameter
            if (part.length > 1){

                //Get parameter list of the specific control word
                List<Integer> list = word_params.containsKey(part[0]) ? word_params.get(part[0]) : new ArrayList<>();
                StringBuilder param = new StringBuilder();

                //Extract the parameter without any spare data that may occur after
                for (int i = 0; i < part[1].length(); i++){
                    if (tryParse(part[1].charAt(i) + "")){
                        param.append(part[1].charAt(i));
                    }
                    else break;
                }
                try {

                    //Add param, parameter may be mistaken with data that comes right after control word
                    list.add(new Integer(param.toString()));
                    word_params.put(part[0], list);
                } catch (Exception e){

//                    System.out.println("Could not parse number " + param.toString());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Separates control words from rest of the data
     * @param word_count
     * @param word_params
     * @param data
     * @return
     */
    private String separate_data(Map<String, Integer> word_count, Map<String, List<Integer>> word_params, String data) {

        StringBuilder group_control_words = new StringBuilder();
        StringBuilder group_data_builder = new StringBuilder();

        boolean build_control_word = false;
        StringBuilder control_word_builder = new StringBuilder();
        String control_word;

        for (int i = 0; i < data.length(); i++){
            char curr = data.charAt(i);

            //Check representation of the char
            if (curr == '\\'){

                //Start building a control word
                build_control_word = true;
                control_word = control_word_builder.toString();

                //Save previously encountered control words
                if (!control_word.isEmpty()){
                    addEntry(word_params, word_count, control_word);
                    group_control_words.append("\\" + control_word);
                }

                control_word_builder = new StringBuilder();
            }
            else if (curr == ' ' && build_control_word){
                //End of control word building, save control word.
                build_control_word = false;
                control_word = control_word_builder.toString();
                addEntry(word_params, word_count, control_word);

                group_control_words.append("\\" + control_word);
                control_word_builder = new StringBuilder();
            }
            else if (curr == '\''){
                //Not a control word
                build_control_word = false;
                control_word_builder = new StringBuilder();
            }
            else if (build_control_word){
                //Build control word, ignore ';'
                if (curr != ';')
                    control_word_builder.append(curr);
            }
            else {
                //Regular data
                group_data_builder.append(curr);
            }
        }

        //Save control word to Hash maps
        if (build_control_word && !control_word_builder.toString().isEmpty()){
            addEntry(word_params, word_count, control_word_builder.toString());
            group_control_words.append("\\" + control_word_builder.toString());
        }

        return group_control_words.append(" ").append(group_data_builder.toString()).toString();
    }

    /**
     * Checks if a specific string represents an Integer
     * @param num
     * @return
     */
    private boolean tryParse(String num) {
        try {
            int x = Integer.parseInt(num);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    //</editor-fold>

}
