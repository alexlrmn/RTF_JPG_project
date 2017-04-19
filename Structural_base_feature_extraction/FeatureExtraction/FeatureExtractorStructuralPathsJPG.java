package FeatureExtraction;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureExtractorStructuralPathsJPG<T> extends AFeatureExtractor<T> {

    private boolean _extract_single_joint = false;
    private boolean _extract_top_down_sub = false;
    private boolean _extract_bot_up_sub = false;

    private final String _NAME = "JPG Structural Features Extractor";

    private Map<String, Integer> _feature_map;

    public FeatureExtractorStructuralPathsJPG() {
    }

    public FeatureExtractorStructuralPathsJPG(boolean top_down_sub, boolean bot_up_sub, boolean single_joint) {
        this._extract_single_joint = single_joint;
        this._extract_top_down_sub = top_down_sub;
        this._extract_bot_up_sub = bot_up_sub;
    }

    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {

        Parser parser = new Parser();

        //Parse file
        Data dt = parser.Parse((String) element);
        _feature_map = new HashMap<>();

        //Extract features
        ExtractHierarchicalFeaturesRec(dt, "");
        return _feature_map;
    }

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

    @Override
    public String GetName() {
        return this._NAME;
    }

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

            List<String> hexArray = readFile(path);
            String last_marker = "";
            StringBuilder buffer = new StringBuilder();

            Data root = new Data("");
            Data iterator = root;
            Data parent = null;

            for (int i = 0; i < hexArray.size() - 1; i++) {
                //Check if byte is a start of a marker (ff00 is not a marker)
                if (hexArray.get(i).equals("ff") && !hexArray.get(i + 1).equals("00")) {

                    iterator.setData(buffer.toString());
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
                                    parent = iterator.getParent();
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
                                    regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }
                                iterator = new Data("/DHT ", parent);

                                break;
                            case "DAC":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    regressToMarker(iterator, "SOF");
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
                        System.out.println(hexArray.get(i + 1));
                        e.printStackTrace();
                    }

                    i = i + 1;
                } else {

                    buffer.append(hexArray.get(i)).append(",");
                }
            }

            return root;
        }

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

        //</editor-fold>
        //<editor-fold desc="Used for reading an image data to hex array represented by strings">
        private List<String> readFile(String path) {

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
            this._parent = parent;

            if (parent != null) {
                this._depth = parent.getDepth() + 1;
                parent.addChild(this);
            } else {
                this._depth = 0;
            }
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
    }
}
