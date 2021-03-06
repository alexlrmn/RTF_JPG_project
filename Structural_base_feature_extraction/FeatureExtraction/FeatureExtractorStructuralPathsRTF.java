package FeatureExtraction;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractorStructuralPathsRTF<T> extends AFeatureExtractor<T> {

    private boolean _extract_top_down_sub = false;
    private boolean _extract_bot_up_sub = false;
    private boolean _extract_single_joint = false;

    private static final String _NAME = "RTF Structural Features extractor";

    private Map<String, Integer> _feature_map;

    public FeatureExtractorStructuralPathsRTF() {
    }

    public FeatureExtractorStructuralPathsRTF(boolean top_down_sub_path, boolean bot_up_sub_path, boolean single_joint) {
        this._extract_top_down_sub = top_down_sub_path;
        this._extract_bot_up_sub = bot_up_sub_path;
        this._extract_single_joint = single_joint;
    }

    public void setExtract_top_down_sub(boolean _extract_top_down_sub) {
        this._extract_top_down_sub = _extract_top_down_sub;
    }

    public void setExtract_bot_up_sub(boolean _extract_bot_up_sub) {
        this._extract_bot_up_sub = _extract_bot_up_sub;
    }

    public void setExtract_single_joint(boolean _extract_single_joint) {
        this._extract_single_joint = _extract_single_joint;
    }

    @Override
    public String GetName() {
        //return this._NAME;
        String etd = _extract_top_down_sub == true ? "T" : "F";
        String ebu = _extract_bot_up_sub == true ? "T" : "F";
        String esj = _extract_single_joint == true ? "T" : "F";
        return String.format("%s - %s%s%s", _NAME, etd, ebu, esj);
    }

//    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {
//
//        //Parse file
//        Parser rtf = new Parser();
//        Data data = rtf.Parse((String) element);
//
//        //print tree for debugging
//        String[] splits = ((String)element).split("\\\\");
//        String el_name = splits[splits.length - 1];
//        String path = "";
//        for (int i =0 ; i < splits.length - 2  ; i++){
//            path += splits[i] + "\\";
//        }
//
//        path += "tree\\" + el_name + ".txt";
//        data.writeTree(path);
//
//        //Extract features
//        _feature_map = new HashMap<>();
//        ExtractStructuralFeaturesRec(data, "");
//
//        return _feature_map;
//    }
//    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {

        //Parse file
        Parser rtf = new Parser();
        Data data = rtf.Parse((String) element);

        //print tree for debugging
//        String[] splits = ((String)element).split("\\\\");
//        String el_name = splits[splits.length - 1];
//        String path = "";
//        for (int i =0 ; i < splits.length - 2  ; i++){
//            path += splits[i] + "\\";
//        }
//
//        path += "tree\\" + el_name + ".txt";
//        data.writeTree(path);
        //Extract features
        _feature_map = new HashMap<>();
        ExtractStructuralFeaturesRec(data, "");

        return _feature_map;
    }

    /**
     * Extract features while iterating over the Tree constructed while parsing
     * the file
     *
     * @param iterator Current tree node
     * @param feature Constructed full path feature
     */
    private void ExtractStructuralFeaturesRec(Data iterator, String feature) {

        //Split control words from other data in the node
        String control_words_data = getControlWords(iterator.getData());

        //Containers for the control words
        String[] control_words = control_words_data.split("\\\\");
        List<String> available_controls = new ArrayList<>();

        if (control_words.length > 1) {

            //Assemble an array of the control words
            //by taking the first control word and hierarchically concatenating the following control words to it
            try {
                available_controls.add("/" + control_words[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 2; i < control_words.length; i++) {
                available_controls.add("/" + control_words[1] + "/" + control_words[i]);
            }

            //Save single control words in case the parameter is set to true
            if (this._extract_single_joint) {
                for (int i = 1; i < control_words.length; i++) {
                    addEntry("/" + control_words[i]);
                }
            }

            //Save the path which is a sub of the full path up to this node
            if (this._extract_top_down_sub && !iterator.isLeaf() && !iterator.isRoot()) {
                for (int i = 0; i < available_controls.size(); i++) {
                    addEntry(feature + available_controls.get(i));
                }
            }

            if (iterator.isLeaf()) {

                //Extract all the paths bottom up
                if (this._extract_bot_up_sub) {
                    StringBuilder bu_feature_builder = new StringBuilder(available_controls.get(0));
                    StringBuilder temp;
                    Data bu_iterator = iterator.getParent();

                    //Back track the path using each node's parent node until root is reached
                    try {

                        while (bu_iterator != null && !bu_iterator.isRoot()) {

                            String[] temp_control_words = getControlWords(bu_iterator.getData()).split("\\\\");
                            String it_feature = "";
                            if (temp_control_words.length > 1) {
                                it_feature = "/" + temp_control_words[1];
                            }

                            temp = new StringBuilder(it_feature).append(bu_feature_builder);
                            bu_feature_builder = temp;
                            addEntry(bu_feature_builder.toString());
                            bu_iterator = bu_iterator.getParent();

                        }
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        throw (new OutOfMemoryError());
                    }
                }

                //Add all available full paths
                for (int i = 0; i < available_controls.size(); i++) {
                    addEntry(feature + available_controls.get(i));
                }

            }

            feature = feature + available_controls.get(0);
        }

        //Continue the recursive next iteration
        for (Data child : iterator.getChildren()) {
            ExtractStructuralFeaturesRec(child, feature);
        }
    }

    /**
     * Adds a observed feature to the HashMap
     *
     * @param feature Observed feature to add
     */
    private void addEntry(String feature) {
        int count = _feature_map.containsKey(feature) ? _feature_map.get(feature) : 0;
        _feature_map.put(feature, count + 1);
    }

    /**
     * Separates the control word from the rest of the data
     *
     * @param data A string containing control words and data
     * @return A string with concatenated words
     */
    private String getControlWords(String data) {

        String control_words = "";
        StringBuilder sb = new StringBuilder();
        try {
            //split control word and data
            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) != ' ') {
                    sb.append(data.charAt(i));
                    //control_words.concat(data.charAt(i) + "");
                } else {
                    break;
                }

            }
            control_words = sb.toString();
            /*
            String[] data_ray = data.split(" ");
            if (data_ray.length > 0) {
                control_words = data.split(" ")[0];
            }
             */
        } catch (Exception e) {
            System.out.println("An Error occurred while trying to extract control_words.");
        }

        return control_words;
    }

    private class Parser {

        //<editor-fold desc="Read file">
        /**
         * Read the content of a file into a string
         *
         * @param path The full path to a file
         * @return
         * @throws IOException
         */
        private char[] readFile(String path) throws FileNotFoundException {
            BufferedReader br = new BufferedReader(new FileReader(path));
            try {
                String str;
                StringBuilder sb = new StringBuilder();
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                return sb.toString().toCharArray();//replace("\\*\\", "\\").replace("*", "").
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

//        private String readFile1(String path) throws IOException {
//            BufferedReader br = new BufferedReader(new FileReader(path));
//            try {
//                StringBuilder sb = new StringBuilder();
//                String line = br.readLine();
//
//                while (line != null) {
//                    sb.append(line);
//                    line = br.readLine();
//                }
//                return sb.toString();
//            } finally {
//                br.close();
//            }
//        }
        //</editor-fold>
        //<editor-fold desc="Parsing">
        /**
         * Parse a file of type RTF using its parentheses which represent a
         * beginning and an end of a group
         *
         * @param path Path to the file
         * @return The content of the file in a tree like form
         */
        private Data Parse(String path) {

            char[] file_cont = new char[0];

            try {
                file_cont = readFile(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Data root = new Data();
            Data parent;
            Data iterator = root;
            StringBuilder data = new StringBuilder();
            String group_data;

            for (int i = 0; i < file_cont.length; i++) {
                switch (file_cont[i]) {
                    case '{':

                        //Test

                        if (!data.toString().isEmpty() &&  !data.toString().replaceAll("[^a-zA-Z0-9]+", "").isEmpty()) {
                            parent = iterator;
                            group_data = separate_data(data.toString());
                            iterator = new Data(group_data, parent);
                            data = new StringBuilder();
                        }

                        break;
                    case '}':

                        if (!data.toString().isEmpty() && !((data.toString()).replaceAll("[^a-zA-Z0-9]+", "")).isEmpty()) {
                            group_data = separate_data(data.toString());
                            Data node = new Data(group_data, iterator);
                            data = new StringBuilder();
                        } else {
                            data = new StringBuilder();
                            if (!iterator.isRoot()) {
                                iterator = iterator.getParent();
                            }
                        }

                        break;
                    default:

                        //Check if there's data after a closed group and create a separate group for that data
                        if (i != 0 && file_cont[i - 1] == '}') {
                            int j = i;
                            StringBuilder extra_cont = new StringBuilder();

                            while (j < file_cont.length && file_cont[j] != '}' && file_cont[j] != '{') {
                                extra_cont.append(file_cont[j]);
                                j++;
                            }

                            i = j - 1;
                            if (extra_cont.toString().isEmpty() && (!((extra_cont.toString().replaceAll("[^a-zA-Z0-9]+", "")).isEmpty()))) {
                                group_data = separate_data(extra_cont.toString());
                                parent = iterator;
                                iterator = new Data(group_data, parent);
                                iterator = iterator.getParent();
                            }

                            break;
                        }

                        char curr = file_cont[i];
                        data.append(curr);

                        break;
                }
            }

            return root;
        }

//        private Data Parse(String path) {
//
//            String file_cont = "";
//
//            try {
//                file_cont = readFile1(path).replace("\\*\\", "\\");
//                file_cont = file_cont.replace("*", "");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            Data root;
//
//            boolean EOF = false;
//
//            root = new Data("");
//            Data parent;
//            Data iterator = root;
//            StringBuilder data = new StringBuilder();
//            String group_data;
//
//            for (int i = 0; i < file_cont.length() ; i++) { //&& !EOF
//
//                //Build group hierarchy
//                switch (file_cont.charAt(i)) {
//                    case '{':
//
//                        //Test
//                        if (!data.toString().isEmpty() && !data.toString().replaceAll("\\s+","").isEmpty()){
//                            parent = iterator;
//                            group_data = separate_data(data.toString());
//                            iterator = new Data(group_data, parent);
//                            data = new StringBuilder();
//                        }
//
//
//                        break;
//                    case '}':
//
//                        if (!data.toString().isEmpty() && !((data.toString()).replaceAll("\\s+","")).isEmpty()) {
//                            group_data = separate_data(data.toString());
//                            Data node = new Data(group_data, iterator);
//                            data = new StringBuilder();
//                        }
//
//                        else {
//                            data = new StringBuilder();
//                            if (!iterator.isRoot())
//                                iterator = iterator.getParent();
//                        }
//
//                        break;
//                    default:
//
//                        //Check if there's data after a closed group and create a separate group for that data
//                        if (i != 0 && file_cont.charAt(i - 1) == '}') {
//                            int j = i;
//                            StringBuilder extra_cont = new StringBuilder();
//
//                            while (j < file_cont.length() && file_cont.charAt(j) != '}' && file_cont.charAt(j) != '{') {
//                                extra_cont.append(file_cont.charAt(j));
//                                j++;
//                            }
//
//                            i = j - 1;
//                            group_data = separate_data(extra_cont.toString());
//                            parent = iterator;
//                            iterator = new Data(group_data, parent);
//                            iterator = iterator.getParent();
//                            break;
//                        }
//
//                        char curr = file_cont.charAt(i);
//                        data.append(curr);
//
//                        break;
//                }
//            }
//
//            return root;
//        }
        /**
         * Separates control words from rest of the data
         *
         * @param data Data to separate
         * @return String containing control words and the rest of the data
         * separated by a space
         */
        private String separate_data(String data) {

            StringBuilder group_control_words = new StringBuilder();
//            StringBuilder group_data_builder = new StringBuilder();

            boolean build_control_word = false;
            StringBuilder control_word_builder = new StringBuilder();
            String control_word;

            for (int i = 0; i < data.length(); i++) {
                char curr = data.charAt(i);

                //Check representation of the char
                if (curr == '\\') {

                    //Start building a control word
                    build_control_word = true;
                    control_word = control_word_builder.toString();

                    //Save previously encountered control words
                    if (!control_word.isEmpty() && Character.isLetter(control_word.charAt(0))) {
                        group_control_words.append("\\" + control_word);
                    }

                    control_word_builder = new StringBuilder();
                } else if (curr == ' ' && build_control_word) {
                    //End of control word building, save control word.
                    build_control_word = false;
                    control_word = control_word_builder.toString();

                    if (!control_word.isEmpty() && Character.isLetter(control_word.charAt(0))) {
                        group_control_words.append("\\" + control_word);
                    }

                    control_word_builder = new StringBuilder();
                } else if (curr == '\''){ // || curr == '*') {
                    //Not a control word
                    build_control_word = false;
                    control_word_builder = new StringBuilder();
                } else if (build_control_word) {
                    //Build control word, ignore ';'
                    if (curr != ';') {
                        control_word_builder.append(curr);
                    }
                }// else {
//                    //Regular data
//                    group_data_builder.append(curr);
//                }
            }

            //Save control word to Hash maps
            if (build_control_word && !control_word_builder.toString().isEmpty()) {
                control_word = control_word_builder.toString();
                if (!control_word.isEmpty() && Character.isLetter(control_word.charAt(0))) {
                    group_control_words.append("\\" + control_word);
                }
//                group_control_words.append("\\" + control_word_builder.toString());
            }

            //Correct control words with appended data
            /*String cws = group_control_words.toString();
            if (cws.contains("objdata")) {
                String[] splits = cws.split("\\\\");
                StringBuilder sb = new StringBuilder();
                for (String word : splits) {
                    if (word.contains("objdata")) {
                        sb.append("\\objdata");
                    } else {
                        sb.append("\\" + word);
                    }
                }
                group_control_words = sb;
//                System.out.println(cws);
//                System.out.println(group_control_words.toString());
//                try {
//                    System.in.read();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }*/

            return group_control_words.toString();//.append(" ").append(group_data_builder.toString()).toString();
        }

        //</editor-fold>
    }

    private class Data {

        //<editor-fold desc="Variables">
        private String _data;
        private List<Data> _children;
        private Data _parent;
        private int _depth;
        //</editor-fold>

        public Data() {
            this._data = "";
            this._children = new ArrayList<>();
            this._parent = null;
            this._depth = 0;
        }

        public Data(String data) {
            this._data = data;
            this._children = new ArrayList<>();
            this._parent = null;
            this._depth = 0;
        }

        public Data(Data parent) {
            this._data = "";
            this._children = new ArrayList<>();
            this._parent = parent;

            if (parent != null) {
                this._depth = parent.getDepth() + 1;
                parent.addChild(this);
            } else {
                this._depth = 0;
            }
        }

        public Data(String data, Data parent) {
            this._data = data;
            _children = new ArrayList<>();
            this._parent = parent;

            if (parent != null) {
                this._depth = parent.getDepth() + 1;
                parent.addChild(this);
            } else {
                this._depth = 0;
            }
        }

        public void addChild(Data child) {
            this._children.add(child);
        }

        //<editor-fold desc="Getters">
        public List<Data> getChildren() {
            return this._children;
        }

        public Data getParent() {
            return this._parent;
        }

        public int getDepth() {
            return _depth;
        }

        public String getData() {
            return this._data;
        }
        //</editor-fold>

        //<editor-fold desc="Setters">
        public void setData(String data) {
            StringBuilder sb = new StringBuilder();
            sb.append(this._data).append(data);
            this._data = sb.toString();
        }
        //</editor-fold>

        public boolean isRoot() {
            return this._depth == 0;
        }

        public boolean isLeaf() {
            return this._children.isEmpty();
        }

        /**
         * For printing purpose.
         */
        public String indented() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this._depth; i++) {
                sb.append("\t");
            }

            return sb.toString();
        }

        //<editor-fold desc="Debugging">
        private StringBuilder sb = new StringBuilder();

        /**
         * Writes the tree to a specific file for visualization.
         *
         * @param path
         */
        public void writeTree(String path) {
            this.sb = new StringBuilder();
            writeTreeRec(this);

            try (PrintWriter out = new PrintWriter(path)) {
                out.println(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void writeTreeRec(Data it) {
            String data = it.getData();
            this.sb.append(it.indented()).append(it.getData()).append("\n");

            if (it.getChildren().size() != 0) {

                for (Data c : it.getChildren()) {
                    writeTreeRec(c);
                }
            }

        }
        //</editor-fold>
    }

}
