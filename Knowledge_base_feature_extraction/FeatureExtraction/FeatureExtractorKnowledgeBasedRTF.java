import java.io.*;
import java.util.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alex on 4/22/2017.
 */
public class FeatureExtractorKnowledgeBasedRTF<T> extends AFeatureExtractor {

    private final String _NAME = "JPG Knowledge Based Feature Extractor";
    private Metadata _m;

    public FeatureExtractorKnowledgeBasedRTF() {

    }

    @Override
    public Map<String, String> ExtractFeaturesFrequencyFromSingleElement(Object element) {

        Parser p = new Parser();
        _m = p.Parse((String)element);

        //print tree for debugging
//        String[] splits = ((String)element).split("\\\\");
//        String el_name = splits[splits.length - 1];
//        String path = "";
//        for (int i =0 ; i < splits.length - 2  ; i++){
//            path += splits[i] + "\\";
//        }
//
//        path += "tree\\" + el_name + ".txt";
//        Data dt = _m.getTree();
//        dt.writeTree(path);

        //Extract features
        Map<String, String> features = new HashMap<>();
        features.put(Feature.File_size.toString(), get_File_size((String)element));
        features.put(Feature.File_extension.toString(), get_File_extension());
        features.put(Feature.Group_structure_incorrect_num.toString(), get_Group_structure_incorrect_num());
        features.put(Feature.Group_empty_num.toString(), get_Group_empty_num());
        features.put(Feature.CW_rtf_incorrect_val.toString(), get_CW_rtf_incorrect_val());
        features.put(Feature.CW_ole_content_url_num.toString(), get_CW_ole_content_url_num());
        features.put(Feature.CW_List_Incorrect_val_num.toString(), get_CW_List_Incorrect_val_num());
        features.put(Feature.CW_pict_content_url_num.toString(), get_CW_pict_content_url_num());
        features.put(Feature.CW_length_Incorrect_num.toString(), get_CW_length_Incorrect_num());
        features.put(Feature.CW_fromhtml_incorrect_val_num.toString(), get_CW_fromhtml_incorrect_val_num());
        features.put(Feature.CW_fprq_incorrect_val_num.toString(), get_CW_fprq_incorrect_val_num());
        features.put(Feature.CW_fbias_incorrect_val_num.toString(), get_CW_fbias_incorrect_val_num());
        features.put(Feature.CW_fcharset_incorrect_val_num.toString(), get_CW_fcharset_incorrect_val_num());
        features.put(Feature.CW_deflang_incorrect_val_num.toString(), get_CW_deflang_incorrect_val_num());
        features.put(Feature.CW_fid_incorrect_val_num.toString(), get_CW_fid_incorrect_val_num());
        features.put(Feature.CW_frealative_incorrect_val_num.toString(), get_CW_frealative_incorrect_val_num());
        features.put(Feature.CW_red_incorrect_val_num.toString(), get_CW_red_incorrect_val_num());
        features.put(Feature.CW_green_incorrect_val_num.toString(), get_CW_green_incorrect_val_num());
        features.put(Feature.CW_blue_incorrect_val_num.toString(), get_CW_blue_incorrect_val_num());
        features.put(Feature.CW_ctint_incorrect_val_num.toString(), get_CW_ctint_incorrect_val_num());
        features.put(Feature.CW_cshade_incorrect_val_num.toString(), get_CW_cshade_incorrect_val_num());
        features.put(Feature.CW_s_incorrect_val_num.toString(), get_CW_s_incorrect_val_num());
        features.put(Feature.CW_lsdlockeddef_incorrect_val_num.toString(), get_CW_lsdlockeddef_incorrect_val_num());
        features.put(Feature.CW_listid_incorrect_val_num.toString(), get_CW_listid_incorrect_val_num());
        features.put(Feature.CW_listsimple_incorrect_val_num.toString(), get_CW_listsimple_incorrect_val_num());
        features.put(Feature.CW_listrestarthdn_incorrect_val_num.toString(), get_CW_listrestarthdn_incorrect_val_num());
        features.put(Feature.CW_levelnfc_incorrect_val_num.toString(), get_CW_levelnfc_incorrect_val_num());
        features.put(Feature.CW_leveljcn_incorrect_val_num.toString(), get_CW_leveljcn_incorrect_val_num());
        features.put(Feature.CW_levelold_incorrect_val_num.toString(), get_CW_levelold_incorrect_val_num());
        features.put(Feature.CW_levelfollow_incorrect_val_num.toString(), get_CW_levelfollow_incorrect_val_num());
        features.put(Feature.CW_levellegal_incorrect_val_num.toString(), get_CW_levellegal_incorrect_val_num());
        features.put(Feature.CW_proptype_incorrect_val_num.toString(), get_CW_proptype_incorrect_val_num());
        features.put(Feature.CW_yr_incorrect_val_num.toString(), get_CW_yr_incorrect_val_num());
        features.put(Feature.CW_mo_incorrect_val_num.toString(), get_CW_mo_incorrect_val_num());
        features.put(Feature.CW_dy_incorrect_val_num.toString(), get_CW_dy_incorrect_val_num());
        features.put(Feature.CW_hr_incorrect_val_num.toString(), get_CW_hr_incorrect_val_num());
        features.put(Feature.CW_min_incorrect_val_num.toString(), get_CW_min_incorrect_val_num());
        features.put(Feature.CW_sec_incorrect_val_num.toString(), get_CW_sec_incorrect_val_num());
        features.put(Feature.CW_doctype_incorrect_val_num.toString(), get_CW_doctype_incorrect_val_num());
        features.put(Feature.CW_stylesortmethod_incorrect_val_num.toString(), get_CW_stylesortmethod_incorrect_val_num());
        features.put(Feature.CW_viewkind_incorrect_val_num.toString(), get_CW_viewkind_incorrect_val_num());
        features.put(Feature.CW_viewscale_incorrect_val_num.toString(), get_CW_viewscale_incorrect_val_num());
        features.put(Feature.CW_viewzk_incorrect_val_num.toString(), get_CW_viewzk_incorrect_val_num());
        features.put(Feature.CW_viewbksp_incorrect_val_num.toString(), get_CW_viewbksp_incorrect_val_num());
        features.put(Feature.CW_fet_incorrect_val_num.toString(), get_CW_fet_incorrect_val_num());
        features.put(Feature.CW_revprop_incorrect_val_num.toString(), get_CW_revprop_incorrect_val_num());
        features.put(Feature.CW_revbar_incorrect_val_num.toString(), get_CW_revbar_incorrect_val_num());
        features.put(Feature.CW_protlevel_incorrect_val_num.toString(), get_CW_protlevel_incorrect_val_num());
        features.put(Feature.CW_stexflow_incorrect_val_num.toString(), get_CW_stexflow_incorrect_val_num());
        features.put(Feature.CW_horzvert_incorrect_val_num.toString(), get_CW_horzvert_incorrect_val_num());
        features.put(Feature.CW_twoinone_incorrect_val_num.toString(), get_CW_twoinone_incorrect_val_num());
        features.put(Feature.CW_fftypetxt_incorrect_val_num.toString(), get_CW_fftypetxt_incorrect_val_num());
        features.put(Feature.CW_ffrecalc_incorrect_val_num.toString(), get_CW_ffrecalc_incorrect_val_num());
        features.put(Feature.CW_ffhaslistbox_incorrect_val_num.toString(), get_CW_ffhaslistbox_incorrect_val_num());
        features.put(Feature.CW_ffsize_incorrect_val_num.toString(), get_CW_ffsize_incorrect_val_num());
        features.put(Feature.CW_ffprot_incorrect_val_num.toString(), get_CW_ffprot_incorrect_val_num());
        features.put(Feature.CW_ffownstat_incorrect_val_num.toString(), get_CW_ffownstat_incorrect_val_num());
        features.put(Feature.CW_ffownhelp_incorrect_val_num.toString(), get_CW_ffownhelp_incorrect_val_num());
        features.put(Feature.CW_fftype_incorrect_val_num.toString(), get_CW_fftype_incorrect_val_num());
        features.put(Feature.CW_sbauto_incorrect_val_num.toString(), get_CW_sbauto_incorrect_val_num());
        features.put(Feature.CW_saauto_incorrect_val_num.toString(), get_CW_saauto_incorrect_val_num());
        features.put(Feature.CW_slmult_incorrect_val_num.toString(), get_CW_slmult_incorrect_val_num());
        features.put(Feature.CW_pnlvl_incorrect_val_num.toString(), get_CW_pnlvl_incorrect_val_num());
        features.put(Feature.CW_abslock_incorrect_val_num.toString(), get_CW_abslock_incorrect_val_num());
        features.put(Feature.CW_dropcapli_incorrect_val_num.toString(), get_CW_dropcapli_incorrect_val_num());
        features.put(Feature.CW_dropcapt_incorrect_val_num.toString(), get_CW_dropcapt_incorrect_val_num());
        features.put(Feature.CW_absnoovrlp_incorrect_val_num.toString(), get_CW_absnoovrlp_incorrect_val_num());
        features.put(Feature.CW_trautofit_incorrect_val_num.toString(), get_CW_trautofit_incorrect_val_num());
        features.put(Feature.CW_shpfblwtxt_incorrect_val_num.toString(), get_CW_shpfblwtxt_incorrect_val_num());
        features.put(Feature.CW_shpwr_incorrect_val_num.toString(), get_CW_shpwr_incorrect_val_num());
        features.put(Feature.CW_shpwrk_incorrect_val_num.toString(), get_CW_shpwrk_incorrect_val_num());
        features.put(Feature.CW_dpfillpat_incorrect_val_num.toString(), get_CW_dpfillpat_incorrect_val_num());
        features.put(Feature.CW_dpastartw_incorrect_val_num.toString(), get_CW_dpastartw_incorrect_val_num());
        features.put(Feature.CW_dpastartl_incorrect_val_num.toString(), get_CW_dpastartl_incorrect_val_num());
        features.put(Feature.CW_dpaendw_incorrect_val_num.toString(), get_CW_dpaendw_incorrect_val_num());
        features.put(Feature.CW_dpaendl_incorrect_val_num.toString(), get_CW_dpaendl_incorrect_val_num());
        features.put(Feature.CW_wbitmap_incorrect_val_num.toString(), get_CW_wbitmap_incorrect_val_num());
        features.put(Feature.CW_tblindtype_incorrect_val_num.toString(), get_CW_tblindtype_incorrect_val_num());
        features.put(Feature.CW_mcGpRule_incorrect_val_num.toString(), get_CW_mcGpRule_incorrect_val_num());
        features.put(Feature.CW_mscr_incorrect_val_num.toString(), get_CW_mscr_incorrect_val_num());
        features.put(Feature.CW_msty_incorrect_val_num.toString(), get_CW_msty_incorrect_val_num());
        features.put(Feature.CW_animtext_incorrect_val_num.toString(), get_CW_animtext_incorrect_val_num());
        features.put(Feature.CW_lbr_incorrect_val_num.toString(), get_CW_lbr_incorrect_val_num());
        features.put(Feature.CW_hres_incorrect_val_num.toString(), get_CW_hres_incorrect_val_num());
        features.put(Feature.CW_fbidis_num.toString(), get_CW_fbidis_num());
        features.put(Feature.CW_filetbl_num.toString(), get_CW_filetbl_num());
        features.put(Feature.CW_colortbl_num.toString(), get_CW_colortbl_num());
        features.put(Feature.CW_stylesheet_num.toString(), get_CW_stylesheet_num());
        features.put(Feature.CW_listtable_num.toString(), get_CW_listtable_num());
        features.put(Feature.CW_revtbl_num.toString(), get_CW_revtbl_num());
        features.put(Feature.CW_rsidtbl_num.toString(), get_CW_rsidtbl_num());
        features.put(Feature.CW_mmathpr_num.toString(), get_CW_mmathpr_num());
        features.put(Feature.CW_generator_num.toString(), get_CW_generator_num());
        features.put(Feature.CW_info_num.toString(), get_CW_info_num());
        features.put(Feature.CW_fonttbl_num.toString(), get_CW_fonttbl_num());
        features.put(Feature.CW_xmlnstbl_num.toString(), get_CW_xmlnstbl_num());
        features.put(Feature.CW_sect_num.toString(), get_CW_sect_num());
        features.put(Feature.CW_bin_num.toString(), get_CW_bin_num());

        return features;
    }

    @Override
    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();

        Feature[] features = Feature.values();
        for (Feature f : features){
            headers.add(f.toString());
        }

        Collections.sort(headers);

        return headers;
    }

    @Override
    public String GetName() {
        return _NAME;
    }

    private class Parser {

        //Hash map, contains features as keys, and number of occurrences as a value
        Map<String, Integer> word_count;

        //Hash map, contains features as keys, and a list of parameter encountered for every feature
        Map<String, List<Integer>> word_params;

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

        //</editor-fold>
        //<editor-fold desc="Parsing">
        /**
         * Parse a file of type RTF using its parentheses which represent a
         * beginning and an end of a group
         *
         * @param path Path to the file
         * @return The content of the file in a tree like form
         */
        private Metadata Parse(String path) {

            word_count = new HashMap<>();
            word_params = new HashMap<>();

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

            String start_of_file = "";
            if (file_cont.length > 4) {
                for (int i = 0; i < 4; i++) {
                    start_of_file += file_cont[i];
                }
            }


            int empty_group_counter = 0;
            int open_bracket_counter = 0;
            int close_bracket_counter = 0;


            for (int i = 0; i < file_cont.length; i++) {
                switch (file_cont[i]) {
                    case '{':

                        open_bracket_counter += 1;

                        if (!data.toString().isEmpty() &&  !data.toString().replaceAll("[^a-zA-Z0-9]+", "").isEmpty()) {
                            parent = iterator;
                            group_data = separate_data(data.toString());
                            iterator = new Data(group_data, parent);
                            data = new StringBuilder();
                        } else {
                            empty_group_counter += 1;
                        }

                        break;
                    case '}':

                        close_bracket_counter += 1;

                        if (!data.toString().isEmpty() && !((data.toString()).replaceAll("[^a-zA-Z0-9]+", "")).isEmpty()) {
                            group_data = separate_data(data.toString());
                            Data node = new Data(group_data, iterator);
                            data = new StringBuilder();
                        } else {

                            data = new StringBuilder();
                            if (!iterator.isRoot()) {
                                iterator = iterator.getParent();
                            }

                            empty_group_counter += 1;
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

                            if (j < file_cont.length){
                                if (file_cont[j] == '{')
                                    open_bracket_counter += 1;
                                else
                                    close_bracket_counter += 1;
                            }

                            i = j - 1;

                            if (!extra_cont.toString().isEmpty() && (!((extra_cont.toString().replaceAll("[^a-zA-Z0-9]+", "")).isEmpty()))) {
                                group_data = separate_data(extra_cont.toString());
                                parent = iterator;
                                iterator = new Data(group_data, parent);
                                iterator = iterator.getParent();
                            } else {
                                empty_group_counter += 1;
                            }

                            break;
                        }

                        char curr = file_cont[i];
                        data.append(curr);

                        break;
                }
            }

            Metadata m = new Metadata(root, word_count, word_params, file_cont.length, empty_group_counter,
                    start_of_file, Math.abs(open_bracket_counter - close_bracket_counter));

            return m;
        }

        /**
         * Separates control words from rest of the data
         *
         * @param data Data to separate
         * @return String containing control words and the rest of the data
         * separated by a space
         */
        private String separate_data(String data) {

            StringBuilder group_control_words = new StringBuilder();
            StringBuilder group_data_builder = new StringBuilder();

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
                        addEntry(control_word);
                        group_control_words.append("\\" + control_word);
                    }

                    control_word_builder = new StringBuilder();
                } else if (curr == ' ' && build_control_word) {
                    //End of control word building, save control word.
                    build_control_word = false;
                    control_word = control_word_builder.toString();

                    if (!control_word.isEmpty() && Character.isLetter(control_word.charAt(0))) {
                        addEntry(control_word);
                        group_control_words.append("\\" + control_word);
                    }

                    control_word_builder = new StringBuilder();

                } else if (curr == '\''){
                    //Not a control word
                    build_control_word = false;
                    control_word_builder = new StringBuilder();

                } else if (build_control_word) {
                    //Build control word, ignore ';'
                    if (curr != ';') {
                        control_word_builder.append(curr);
                    }
                } else {
                    //Regular data
                    group_data_builder.append(curr);
                }
            }

            //Save control word to Hash maps
            if (build_control_word && !control_word_builder.toString().isEmpty()) {
                control_word = control_word_builder.toString();
                if (!control_word.isEmpty() && Character.isLetter(control_word.charAt(0))) {
                    addEntry(control_word);
                    group_control_words.append("\\" + control_word);
                }
//                group_control_words.append("\\" + control_word_builder.toString());
            }


            return group_control_words.append(" ").append(group_data_builder.toString()).toString();
        }

        private void addEntry(String control_word){
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
//                        e.printStackTrace();
//                    System.out.println("Could not parse number " + param.toString());
                    }
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

        //</editor-fold>
    }

    //</editor-fold>

    private class Metadata {

        private Map<String, Integer> _word_count;
        private Map<String, List<Integer>> _word_params;
        private Data _tree;
        private int _file_len;
        private int _empty_group_num;
        private String _start_of_file;
        private int _bracket_diff;

        public Metadata(Data tree, Map<String, Integer> word_count, Map<String, List<Integer>> word_params,
                        int file_len, int empty_group_num, String start_of_file, int bracket_difference){
            _tree = tree;
            _word_count = word_count;
            _word_params = word_params;
            _file_len = file_len;
            _empty_group_num = empty_group_num;
            _start_of_file = start_of_file;
            _bracket_diff = bracket_difference;
        }

        public Data getTree() {
            return _tree;
        }

        public Map<String, Integer> get_word_count() { return _word_count; }

        public Map<String, List<Integer>> get_word_params() { return _word_params; }

        public int get_file_len() { return _file_len; }

        public int get_empty_group_num() { return _empty_group_num; }

        public String get_start_of_file() { return _start_of_file; }

        public int get_bracket_diff() { return _bracket_diff; }


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

    private String get_File_size(String filePath) {

        File f = new File(filePath);
        return String.valueOf(f.length());
    }

    private String get_File_extension() {
        if (_m.get_start_of_file().equals("{rtf"))
            return "0";
        return "1";
    }

    private String get_Group_structure_incorrect_num() {
        return String.valueOf(_m.get_bracket_diff());
    }

    private String get_Group_empty_num() {
        return String.valueOf(_m.get_empty_group_num());
    }

    private String get_CW_rtf_incorrect_val() {
        if (_m.get_word_count().containsKey("rtf1"))
            return "0";
        return "1";
    }

    private static String regex = "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
            "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
            "|mil|biz|info|mobi|name|aero|jobs|museum" +
            "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
            "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
            "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
            "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
            "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
            "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
            "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b";

    private String get_CW_ole_content_url_num() {

        Data iterator = _m.getTree();
        Pattern p = Pattern.compile(regex);

        int url_counter = look_for_url_ole(iterator, p);

        return String.valueOf(url_counter);

    }

    private int look_for_url_ole(Data iterator, Pattern p) {

        int counter = 0;

        String data = iterator.getData();
        if (data.contains("\\objdata")) {
            Matcher m = p.matcher(iterator.getData());

            if (m.find())
                counter = 1;
        }


        for (Data child : iterator.getChildren()) {
            counter += look_for_url_ole(child, p);
        }

        return counter;
    }

    private String get_CW_List_Incorrect_val_num() {
        return "";
    }

    private String get_CW_pict_content_url_num() {

        Data iterator = _m.getTree();
        Pattern p = Pattern.compile(regex);

        int url_count = look_for_url_pict(iterator, p);

        return String.valueOf(url_count);
    }

    private int look_for_url_pict(Data iterator, Pattern p) {
        int counter = 0;

        String data = iterator.getData();
        if (data.contains("\\pict")) {
            int size = iterator.getChildren().size();
            if (size != 0) {
                String pict_data = iterator.getChildren().get(size - 1).getData();

                Matcher m = p.matcher(pict_data);

                if (m.find())
                    counter = 1;

            }

        }


        for (Data child : iterator.getChildren()) {
            counter += look_for_url_ole(child, p);
        }

        return counter;
    }

    private String get_CW_length_Incorrect_num() {

        Map<String, List<Integer>> words = _m.get_word_params();

        int incorrect_len_count = 0;
        for (String key : words.keySet()) {
            if (key.length() > 32)
                incorrect_len_count += 1;
        }

        return String.valueOf(incorrect_len_count);
    }

    private String get_CW_fromhtml_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fromhtml") ? _m.get_word_params().get("fromhtml") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fprq_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fprq") ? _m.get_word_params().get("fprq") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fbias_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fbias") ? _m.get_word_params().get("fbias") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fcharset_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fcharset") ? _m.get_word_params().get("fcharset") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || String.valueOf(param).length() > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_deflang_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("deflang") ? _m.get_word_params().get("deflang") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || String.valueOf(param).length() > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fid_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fid") ? _m.get_word_params().get("fid") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_frealative_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("freelative") ? _m.get_word_params().get("freelative") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 )
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_red_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("red") ? _m.get_word_params().get("deflang") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_green_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("green") ? _m.get_word_params().get("green") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_blue_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("blue") ? _m.get_word_params().get("blue") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ctint_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ctint") ? _m.get_word_params().get("ctint") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_cshade_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("cshade") ? _m.get_word_params().get("cshade") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_s_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("s") ? _m.get_word_params().get("s") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 65535)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_lsdlockeddef_incorrect_val_num() {

        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("lsdlockkeddef") ? _m.get_word_params().get("lsdlockeddef") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_listid_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("listid") ? _m.get_word_params().get("listid") : null;

        if (params != null) {
            for (int param : params) {
                if (param < -1 || param > -5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_listsimple_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("listsimple") ? _m.get_word_params().get("listsimple") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_listrestarthdn_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("listrestarthdn") ? _m.get_word_params().get("listrestarthdn") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_levelnfc_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("levelnfc") ? _m.get_word_params().get("levelnfc") : null;

        if (params != null) {
            for (int param : params) {
                if (!((param >= 0 && param <= 7) || (param >= 10 && param <= 68) | param == 255))
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_leveljcn_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("leveljcn") ? _m.get_word_params().get("leveljcn") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_levelold_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("levelold") ? _m.get_word_params().get("levelold") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_levelfollow_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("levelfollow") ? _m.get_word_params().get("levelfollow") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_levellegal_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("levellegal") ? _m.get_word_params().get("levellegal") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_proptype_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("proptype") ? _m.get_word_params().get("proptype") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 3 && param != 5 && param != 11 && param != 30 && param != 64)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_yr_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("yr") ? _m.get_word_params().get("yr") : null;



        if (params != null) {
            for (int param : params) {
                if (param < 1900 || param > Calendar.getInstance().get(Calendar.YEAR))
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_mo_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("mo") ? _m.get_word_params().get("mo") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 1 || param > 12)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dy_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dy") ? _m.get_word_params().get("dy") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 1 || param > 31)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_hr_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("hr") ? _m.get_word_params().get("hr") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 23)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_min_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("min") ? _m.get_word_params().get("min") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 59)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_sec_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("sec") ? _m.get_word_params().get("sec") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 59)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_doctype_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("doctype") ? _m.get_word_params().get("doctype") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_stylesortmethod_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("stylesortmethod") ? _m.get_word_params().get("stylesortmethod") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 4)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_viewkind_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("viewkind") ? _m.get_word_params().get("viewkind") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_viewscale_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("viewscale") ? _m.get_word_params().get("viewscale") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 100)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_viewzk_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("viewzk") ? _m.get_word_params().get("viewzk") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_viewbksp_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("viewbksp") ? _m.get_word_params().get("viewbksp") : null;

        if (params != null) {
            for (int param : params) {
                if (param != 0 && param != 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fet_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fet") ? _m.get_word_params().get("fet") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_revprop_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("revprop") ? _m.get_word_params().get("revprop") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 4)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_revbar_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("revbar") ? _m.get_word_params().get("revbar") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_protlevel_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("protlevel") ? _m.get_word_params().get("protlvel") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_stexflow_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("stexflow") ? _m.get_word_params().get("stexflow") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_horzvert_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("horzvert") ? _m.get_word_params().get("horzvert") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_twoinone_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("twoinone") ? _m.get_word_params().get("twoinone") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 4)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fftypetxt_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fftypetxt") ? _m.get_word_params().get("fftypetxt") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ffrecalc_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ffrecalc") ? _m.get_word_params().get("frecalc") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ffhaslistbox_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ffhaslistbox") ? _m.get_word_params().get("ffhaslistbox") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ffsize_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ffsize") ? _m.get_word_params().get("ffsize") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ffprot_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ffprot") ? _m.get_word_params().get("ffprot") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ffownstat_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ffownstat") ? _m.get_word_params().get("ffownstat") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_ffownhelp_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("ffownhelp") ? _m.get_word_params().get("ffownhelp") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fftype_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("fftype") ? _m.get_word_params().get("fftype") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_sbauto_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("sbauto") ? _m.get_word_params().get("sbauto") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_saauto_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("saauto") ? _m.get_word_params().get("saauto") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }
    private String get_CW_slmult_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("slmult") ? _m.get_word_params().get("slmult") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_pnlvl_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("pnlvl") ? _m.get_word_params().get("pnlvl") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 1 || param > 9)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_abslock_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("absblock") ? _m.get_word_params().get("absblock") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dropcapli_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dropcapli") ? _m.get_word_params().get("dropcapli") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 1 || param > 10)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dropcapt_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dropcapt") ? _m.get_word_params().get("dropcapt") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 1 || param > 2)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_absnoovrlp_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("absnoovrlp") ? _m.get_word_params().get("absnoovrlp") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_trautofit_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("trautofit") ? _m.get_word_params().get("trautofit") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 255)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_shpfblwtxt_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("shpfblwtxt") ? _m.get_word_params().get("shpfblwtxt") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 1)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_shpwr_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("shpwr") ? _m.get_word_params().get("shpwr") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_shpwrk_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("shpwrk") ? _m.get_word_params().get("shpwrk") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dpfillpat_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dpfillpat") ? _m.get_word_params().get("dpfillpat") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 25)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dpastartw_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dpastartx") ? _m.get_word_params().get("dpastartw") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dpastartl_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dpastartl") ? _m.get_word_params().get("dpastartl") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dpaendw_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dpaendw") ? _m.get_word_params().get("dpaendw") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_dpaendl_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("dpaendl") ? _m.get_word_params().get("dpaendl") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_wbitmap_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("wbitmap") ? _m.get_word_params().get("wbitmap") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 1 || param > 8)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_tblindtype_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("tblindtype") ? _m.get_word_params().get("tblindtyoe") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_mcGpRule_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("mcGpRule") ? _m.get_word_params().get("mcGpRule") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 4)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_mscr_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("mscr") ? _m.get_word_params().get("mscr") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 5)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_msty_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("msty") ? _m.get_word_params().get("msty") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_animtext_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("animtext") ? _m.get_word_params().get("animtext") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 8)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_lbr_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("lbr") ? _m.get_word_params().get("lbr") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 3)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_hres_incorrect_val_num() {
        int count_incorrect = 0;
        List<Integer> params = _m.get_word_params().containsKey("hres") ? _m.get_word_params().get("hres") : null;

        if (params != null) {
            for (int param : params) {
                if (param < 0 || param > 6)
                    count_incorrect += 1;
            }
        }

        return String.valueOf(count_incorrect);
    }

    private String get_CW_fbidis_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("fbidis"))
            num = words.get("fbidis");
        return String.valueOf(num);
    }

    private String get_CW_filetbl_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("filetbl"))
            num = words.get("filetbl");
        return String.valueOf(num);
    }

    private String get_CW_colortbl_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("colortbl"))
            num = words.get("colortbl");
        return String.valueOf(num);
    }

    private String get_CW_stylesheet_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("stylesheet"))
            num = words.get("stylesheet");
        return String.valueOf(num);
    }

    private String get_CW_listtable_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("listtable"))
            num = words.get("listtable");
        return String.valueOf(num);
    }

    private String get_CW_revtbl_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("revtbl"))
            num = words.get("revtbl");
        return String.valueOf(num);
    }

    private String get_CW_rsidtbl_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("rsidbl"))
            num = words.get("rsidbl");
        return String.valueOf(num);
    }

    private String get_CW_mmathpr_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("mmathpr"))
            num = words.get("mmathpr");
        return String.valueOf(num);
    }

    private String get_CW_generator_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("generator"))
            num = words.get("generator");
        return String.valueOf(num);
    }

    private String get_CW_info_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("info"))
            num = words.get("info");
        return String.valueOf(num);
    }

    private String get_CW_fonttbl_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("fonttbl"))
            num = words.get("fonttbl");
        return String.valueOf(num);
    }

    private String get_CW_xmlnstbl_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("xmlnstbl"))
            num = words.get("xmlnstbl");
        return String.valueOf(num);
    }

    private String get_CW_sect_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("sect"))
            num = words.get("sect");
        return String.valueOf(num);
    }

    private String get_CW_bin_num() {
        Map<String, Integer> words = _m.get_word_count();

        int num = 0;
        if (words.containsKey("bin"))
            num = words.get("bin");
        return String.valueOf(num);
    }



    private enum Feature {
        /******************File handle************************/
        File_size,
        File_extension,
        Group_structure_incorrect_num,
        Group_empty_num,
        CW_rtf_incorrect_val,
        CW_ole_content_url_num,
        CW_List_Incorrect_val_num,
        CW_pict_content_url_num,
        CW_length_Incorrect_num,
        CW_fromhtml_incorrect_val_num,
        CW_fprq_incorrect_val_num,
        CW_fbias_incorrect_val_num,
        CW_fcharset_incorrect_val_num,
        CW_deflang_incorrect_val_num,
        CW_fid_incorrect_val_num,
        CW_frealative_incorrect_val_num,
        CW_red_incorrect_val_num,
        CW_green_incorrect_val_num,
        CW_blue_incorrect_val_num,
        CW_ctint_incorrect_val_num,
        CW_cshade_incorrect_val_num,
        CW_s_incorrect_val_num,
        CW_lsdlockeddef_incorrect_val_num,
        CW_listid_incorrect_val_num,
        CW_listsimple_incorrect_val_num,
        CW_listrestarthdn_incorrect_val_num,
        CW_levelnfc_incorrect_val_num,
        CW_leveljcn_incorrect_val_num,
        CW_levelold_incorrect_val_num,
        CW_levelfollow_incorrect_val_num,
        CW_levellegal_incorrect_val_num,
        CW_proptype_incorrect_val_num,
        CW_yr_incorrect_val_num,
        CW_mo_incorrect_val_num,
        CW_dy_incorrect_val_num,
        CW_hr_incorrect_val_num,
        CW_min_incorrect_val_num,
        CW_sec_incorrect_val_num,
        CW_doctype_incorrect_val_num,
        CW_stylesortmethod_incorrect_val_num,
        CW_viewkind_incorrect_val_num,
        CW_viewscale_incorrect_val_num,
        CW_viewzk_incorrect_val_num,
        CW_viewbksp_incorrect_val_num,
        CW_fet_incorrect_val_num,
        CW_revprop_incorrect_val_num,
        CW_revbar_incorrect_val_num,
        CW_protlevel_incorrect_val_num,
        CW_stexflow_incorrect_val_num,
        CW_horzvert_incorrect_val_num,
        CW_twoinone_incorrect_val_num,
        CW_fftypetxt_incorrect_val_num,
        CW_ffrecalc_incorrect_val_num,
        CW_ffhaslistbox_incorrect_val_num,
        CW_ffsize_incorrect_val_num,
        CW_ffprot_incorrect_val_num,
        CW_ffownstat_incorrect_val_num,
        CW_ffownhelp_incorrect_val_num,
        CW_fftype_incorrect_val_num,
        CW_sbauto_incorrect_val_num,
        CW_saauto_incorrect_val_num,
        CW_slmult_incorrect_val_num,
        CW_pnlvl_incorrect_val_num,
        CW_abslock_incorrect_val_num,
        CW_dropcapli_incorrect_val_num,
        CW_dropcapt_incorrect_val_num,
        CW_absnoovrlp_incorrect_val_num,
        CW_trautofit_incorrect_val_num,
        CW_shpfblwtxt_incorrect_val_num,
        CW_shpwr_incorrect_val_num,
        CW_shpwrk_incorrect_val_num,
        CW_dpfillpat_incorrect_val_num,
        CW_dpastartw_incorrect_val_num,
        CW_dpastartl_incorrect_val_num,
        CW_dpaendw_incorrect_val_num,
        CW_dpaendl_incorrect_val_num,
        CW_wbitmap_incorrect_val_num,
        CW_tblindtype_incorrect_val_num,
        CW_mcGpRule_incorrect_val_num,
        CW_mscr_incorrect_val_num,
        CW_msty_incorrect_val_num,
        CW_animtext_incorrect_val_num,
        CW_lbr_incorrect_val_num,
        CW_hres_incorrect_val_num,
        CW_fbidis_num,
        CW_filetbl_num,
        CW_colortbl_num,
        CW_stylesheet_num,
        CW_listtable_num,
        CW_revtbl_num,
        CW_rsidtbl_num,
        CW_mmathpr_num,
        CW_generator_num,
        CW_info_num,
        CW_fonttbl_num,
        CW_xmlnstbl_num,
        CW_sect_num,
        CW_bin_num

    }


}
