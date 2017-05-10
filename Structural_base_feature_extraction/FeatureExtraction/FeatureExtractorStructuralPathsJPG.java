package FeatureExtraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractorStructuralPathsJPG<T> extends AFeatureExtractor<T> {

    private boolean _extract_top_down_sub = false;
    private boolean _extract_bot_up_sub = false;
    private boolean _extract_single_joint = false;

    private final String _NAME = "JPG Structural Features Extractor";

    private Map<String, Integer> _feature_map;

    public FeatureExtractorStructuralPathsJPG() {
    }

    public FeatureExtractorStructuralPathsJPG(boolean top_down_sub, boolean bot_up_sub, boolean single_joint) {
        this._extract_top_down_sub = top_down_sub;
        this._extract_bot_up_sub = bot_up_sub;
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

    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {
        Parser parser = new Parser();

        //Parse file
        Data dt = parser.Parse((String) element);

        //Debugging
        String[] splits = ((String)element).split("\\\\");
        String el_name = splits[splits.length - 1];
        String path = "";
        for (int i =0 ; i < splits.length - 2  ; i++){
            path += splits[i] + "\\";
        }

        path += "tree\\" + el_name + ".txt";
        dt.writeTree(path);
        //

        _feature_map = new HashMap<>();

        //Extract features
        ExtractHierarchicalFeaturesRec(dt, "");
        return _feature_map;
    }

    /*
    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {

        Parser parser = new Parser();

        //Parse file
        Data dt = parser.Parse((String) element);

        //print tree for debugging
//        String[] splits = ((String)element).split("\\\\");
//        String el_name = splits[splits.length - 1];
//        String path = "";
//        for (int i =0 ; i < splits.length - 2  ; i++){
//            path += splits[i] + "\\";
//        }
//
//        path += "tree\\" + el_name + ".txt";
//        dt.writeTree(path);

        _feature_map = new HashMap<>();

        //Extract features
        ExtractHierarchicalFeaturesRec(dt, "");
        return _feature_map;
    }
     */
    /**
     * Iterates over the Tree constructed by the parser and extract features.
     *
     * @param iterator Current node
     * @param feature Current feature constructed up until now
     */
    private void ExtractHierarchicalFeaturesRec(Data iterator, String feature) {

        String curr_marker = getMarker(iterator.getData());
        String curr_feature = feature + curr_marker;

        //Add single marker to map
        if (_extract_single_joint && !iterator.isRoot()) {
            addEntry(curr_marker);
        }

        //Add sub path observed this far
        if (_extract_top_down_sub && !iterator.isLeaf() && !iterator.isRoot()) {
            addEntry(curr_feature);
        }

        if (iterator.isLeaf()) {

            if (_extract_bot_up_sub) {
                //Extract all the Bottom-up features
                String bu_feature = curr_marker;
                Data bu_iterator = iterator.getParent();
                while (bu_iterator != null && !bu_iterator.isRoot()) {
                    try {
                        bu_feature = getMarker(bu_iterator.getData()) + bu_feature;
                        bu_iterator = bu_iterator.getParent();
                        addEntry(bu_feature);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            //Add full path to map
            addEntry(curr_feature);

        } else {
            //Continue recursive iteration
            for (Data child : iterator.getChildren()) {
                ExtractHierarchicalFeaturesRec(child, curr_feature);
            }
        }
    }

    /**
     * Separate marker from rest of the data
     *
     * @param data Content of an iterator
     * @return A new String with markers and rest of the data separated by a
     * space
     */
    private String getMarker(String data) {
        String marker = "";
        try {
            marker = data.split(" ")[0];
        } catch (Exception e) {
            System.out.println("An Error occurred while trying to extract marker. Array length is: " + data.split(" ")[0]);
        }

        return marker;
    }

    private void addEntry(String feature) {
        int count = _feature_map.containsKey(feature) ? _feature_map.get(feature) : 0;
        _feature_map.put(feature, count + 1);
    }

//    public void test(String path){
//        Parser p = new Parser();
//
//        p.testing(path);
//        p.readFile(path);
//    }
    private class Parser {

        //<editor-fold desc="Markers">
        private final String[] hexSymbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        private final Map<String, String> _markers = createMap();

        private Map<String, String> createMap() {

            Map<String, String> markers = new HashMap<>();
            markers.put("c4", "DHT");
            markers.put("c8", "JPG");
            markers.put("cc", "DAC");
            markers.put("d8", "SOI");
            markers.put("d9", "EOI");
            markers.put("da", "SOS");
            markers.put("db", "DQT");
            markers.put("dc", "DNL");
            markers.put("dd", "DRI");
            markers.put("de", "DHP");
            markers.put("df", "EXP");
            markers.put("fe", "COM");
            markers.put("c0", "SOF");
            markers.put("c1", "SOF");
            markers.put("c2", "SOF");
            markers.put("c3", "SOF");
            markers.put("c5", "SOF");
            markers.put("c6", "SOF");
            markers.put("c7", "SOF");
            markers.put("c9", "SOF");
            markers.put("ca", "SOF");
            markers.put("cb", "SOF");
            markers.put("cd", "SOF");
            markers.put("ce", "SOF");
            markers.put("cf", "SOF");
            for (int i = 0; i < 8; i++) {
                markers.put("d" + hexSymbols[i], "RST" + hexSymbols[i]);
            }
            for (int i = 0; i < hexSymbols.length; i++) {
                markers.put("e" + hexSymbols[i], "APP" + hexSymbols[i]);
            }
            for (int i = 0; i < hexSymbols.length - 4; i++) {
                for (int j = 0; j < hexSymbols.length; j++) {
                    markers.put(hexSymbols[i] + hexSymbols[j], "RES");
                }
            }
            markers.remove("00");
            for (int i = 0; i < hexSymbols.length - 2; i++) {
                markers.put("f" + hexSymbols[i], "JPG" + hexSymbols[i]);
            }

            markers.put("01", "TEM");
            markers.put("ff", "UNKNOWN");
            return markers;
        }
        //</editor-fold>

        //<editor-fold desc="Parsing">
        private Data Parse(String path) {

            char[] charhex = readFileToHex(path);

            String last_marker = "";

            Data root = new Data("/image");
            Data iterator = root;
            Data parent = root;

            for (int i = 0; i < charhex.length - 1; i = i + 2) {
                //Check if byte is a start of a marker (ff00 is not a marker)
                if (charhex[i] == 'f'
                        && charhex[i + 1] == 'f'
                        && i < charhex.length - 3
                        && (charhex[i + 2] != '0' || charhex[i + 3] != '0')) {

                    try {
                        //Check marker
                        //create the byte as string ( char + char )
                        String markerbyte = String.valueOf(charhex[i + 2]).concat(String.valueOf(charhex[i + 3]));
                        switch (this._markers.get(markerbyte)) {
                            case "SOI":

                                if (last_marker.equals("")) {
                                    parent = iterator;
                                } else {
                                    parent = regressToMarker(iterator, "SOI");
                                }

                                iterator = new Data("/SOI ", parent);
                                last_marker = "SOI";
                                parent = iterator;

                                break;
                            case "EOI":

                                //End of image
                                //Go up the tree until we reach the start of image node
                                parent = regressToMarker(iterator, "SOI");
                                if (!parent.isRoot()) {
                                    parent = parent.getParent();
                                }
                                iterator = new Data("/EOI ", parent);
                                last_marker = "SOI";

                                break;
                            case "SOF":

                                //Start of scan
                                //Regress according to the last marker
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                    if (!parent.isRoot()) {
                                        parent = parent.getParent();
                                    }
                                } else {
                                    parent = regressToMarker(iterator, "SOI");
                                }

                                iterator = new Data("/SOF" + String.valueOf(charhex[i + 3]), parent);
                                last_marker = "SOF";
                                parent = iterator;

                                break;
                            case "SOS":

                                //Start of scan
                                //Regress to the first SOF node encountered
                                parent = regressToMarker(iterator, "SOF");
                                iterator = new Data("/SOS ", parent);
                                parent = iterator;

                                break;
                            case "DHT":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }

                                parent.addChild(new Data("/DHT", parent, ""));
                                break;
                            case "DAC":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }

                                parent.addChild(new Data("/DAC", parent, ""));

                                break;

                            default:

                                parent.addChild(new Data("/".concat(this._markers.get(markerbyte)), parent, ""));

                                break;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    i = i + 2;
                }
            }
            return root;
        }

        /*
        private Data Parse(String path) {

            List<String> hexArray = readFile(path);

            String last_marker = "";
            StringBuilder buffer = new StringBuilder();

            Data root = new Data("");
            Data iterator = root;
            Data parent = root;

            for (int i = 0; i < hexArray.size() - 1; i++) {
                //Check if byte is a start of a marker (ff00 is not a marker)
                String tmp = hexArray.get(i);
                tmp += hexArray.get(i+1);
                //Test
//                if (i == 0 && hexArray.size() > 2){
//                    while (i + 1 < hexArray.size() && (!hexArray.get(i).equals("ff") || !hexArray.get(i+1).equals("d8")))
//                        i++;
//                }

                if (hexArray.get(i).equals("ff") && !hexArray.get(i + 1).equals("00")) {

                    //iterator.setData(buffer.toString());
                    buffer = new StringBuilder();
//                String marker = hexArray.get(i+1);
                    try {
                        //Check for marker type
                        switch (this._markers.get(hexArray.get(i + 1))) {
                            case "SOI":

                                //Start of image
                                //Check if this is the first encounter
                                if (last_marker.equals("")) {
                                    parent = iterator;
                                } else {
                                    parent = regressToMarker(iterator, "SOI");
                                }

                                iterator = new Data("/SOI ", parent);
                                last_marker = "SOI";
                                parent = iterator;
                                break;
                            case "EOI":

                                //End of image
                                //Go up the tree until we reach the start of image node
                                parent = regressToMarker(iterator, "SOI").getParent();
                                iterator = new Data("/EOI ", parent);
                                last_marker = "SOI";
                                break;
                            case "SOF":

                                //Start of scan
                                //Regress according to the last marker
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF").getParent();
                                } else {
                                    parent = regressToMarker(iterator, "SOI");//iterator.getParent();
                                }
                                iterator = new Data("/SOF" + hexArray.get(i + 1).charAt(1) + " ", parent);
                                last_marker = "SOF";
                                parent = iterator;

                                break;
                            case "SOS":

                                //Start of scan
                                //Regress to the first SOF node encountered
                                parent = regressToMarker(iterator, "SOF");
                                iterator = new Data("/SOS ", parent);
                                parent = iterator;

                                break;
                            case "DHT":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }
                                iterator = new Data("/DHT ", parent);

                                break;
                            case "DAC":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }

                                iterator = new Data("/DAC ", parent);
                                break;

                            default:
                                    iterator = new Data("/" + this._markers.get(hexArray.get(i + 1)) + " ", parent);

                                break;
                        }
                    } catch (Exception e) {
//                        System.out.println(hexArray.get(i + 1));
                        e.printStackTrace();
                    }

                    i = i + 1;
                } else {
                    //iugduighiu
                    //buffer.append(hexArray.get(i));//.append(",");
//                    buffer.append(hexArray.get(i+1));
//                    i = i + 1;
                }
            }

            return root;
        }
         */
        /**
         * Iterates back to the nodes parent that has the specified marker.
         *
         * @param iterator Current Data node
         * @param marker Marker to regress to
         * @return
         */
        private Data regressToMarker(Data iterator, String marker) {
//        Data temp = iterator;
            try {
                while (!iterator.getData().contains(marker) && iterator.getParent() != null) {
                    iterator = iterator.getParent();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return iterator;
        }

        private final char[] hexArray = "0123456789abcdef".toCharArray();

        private char[] readFileToHex(String path) {

            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            char[] hexChars = new char[bytes.length * 2];
            for (int j = 0; j < bytes.length; j++) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }

            return hexChars;
        }

        //</editor-fold>
        /*
        //<editor-fold desc="Used for reading an image data to hex array represented by strings">
        private List<String> readFile(String path) {
//            long startTime = System.nanoTime();

            List<String> file_cont = new ArrayList<>();
            try {
                FileInputStream fis = new FileInputStream(new File(path));

                byte[] bytes = new byte[1];
                int value = 0;
                do {
                    value = fis.read(bytes);
                    file_cont.add(toHexFromBytes(bytes));

                } while (value != -1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0 ; i < file_cont.size() - 1; i++){
                if (file_cont.get(i).equals("ff") && !file_cont.get(i+1).equals("00")){
                    int x = 1;
                    i = i + 1;
                }
            }

//            long endTime = System.nanoTime();
//
//            long duration = (endTime - startTime);
//            System.out.println(duration);

            return file_cont;
        }



        private final int BITS_PER_HEX_DIGIT = 4;

        private String toHexFromByte(final byte b) {
            byte leftSymbol = (byte) ((b >>> BITS_PER_HEX_DIGIT) & 0x0f);
            byte rightSymbol = (byte) (b & 0x0f);

            return (hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
        }

        private String toHexFromBytes(final byte[] bytes) {
            if (bytes == null || bytes.length == 0) {
                return ("");
            }

            // there are 2 hex digits per byte
            StringBuilder hexBuffer = new StringBuilder(bytes.length * 2);

            // for each byte, convert it to hex and append it to the buffer
            for (int i = 0; i < bytes.length; i++) {
                hexBuffer.append(toHexFromByte(bytes[i]));
            }

            return (hexBuffer.toString());
        }
        //</editor-fold>
         */
    }

    private class Data {

        private String _data;
        private List<Data> _children;
        private Data _parent;
        private int _depth;

        private Data(String data) {
            this._data = data;
            this._children = new ArrayList<>();
            this._parent = null;
            this._depth = 0;
        }

        private Data(String data, Data parent) {
            this._data = data;
            _children = new ArrayList<>();
//            if (parent != null){
            this._parent = parent;
            this._depth = parent.getDepth() + 1;
            parent.addChild(this);
//            } else {
//                this._parent = null;
//                this._depth = 0;
//            }

        }

        private Data(String data, Data parent, String tmp) {
            this._data = data;
            _children = new ArrayList<>();
            this._parent = parent;

            this._depth = parent.getDepth() + 1;
//            parent.addChild(this);
        }

        private void addChild(Data child) {
            this._children.add(child);
        }

        //<editor-fold desc="Getters">
        private List<Data> getChildren() {
            return this._children;
        }

        private Data getParent() {
            return this._parent;
        }

        private int getDepth() {
            return _depth;
        }

        private String getData() {
            return this._data;
        }
        //</editor-fold>

        //<editor-fold desc="Setters">
        private void setData(String data) {
            StringBuilder sb = new StringBuilder();
            sb.append(this._data).append(data);
            this._data = sb.toString();
        }
        //</editor-fold>

        private boolean isRoot() {
            return this._depth == 0;
        }

        private boolean isLeaf() {
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
            this.sb.append(it.indented()).append(it.getData()).append("\n");

            if (it.getChildren().size() != 0) {

                for (Data c : it.getChildren()) {
                    writeTreeRec(c);
                }
            }

        }
    }
}
