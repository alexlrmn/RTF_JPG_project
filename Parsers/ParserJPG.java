package Parsers;

import FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Metadata.DataTree;
import FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Metadata.DataTreeNode;
import FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Metadata.IMetadata;
import FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Metadata.MetadataJPG;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParserJPG implements IParser {

    //<editor-fold desc="Markers">
    private  final Map<String, String> _markers = createMap();
    private  Map<String, String> createMap() {

        Map<String,String> markers = new HashMap<String,String>();
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
        for (int i = 0; i < 8; i++)
            markers.put("d" + hexSymbols[i], "RST" + hexSymbols[i]);
        for (int i = 0; i < hexSymbols.length; i++)
            markers.put("e" + hexSymbols[i], "APP" + hexSymbols[i]);
        for (int i = 0; i < hexSymbols.length - 4; i++)
            for (int j = 0; j < hexSymbols.length; j++){
                String mrkr = hexSymbols[i] + hexSymbols[j];
                markers.put(hexSymbols[i] + hexSymbols[j], "RES");
            }
        markers.remove("00");
        for (int i = 0; i < hexSymbols.length - 2; i++)
            markers.put("f" + hexSymbols[i], "JPG" + hexSymbols[i]);

        markers.put("01", "TEM");
        markers.put("ff", "UNKNOWN");
        return markers;
    }
    //</editor-fold>


    //<editor-fold desc="Parsing">
    public IMetadata Parse(String path) {
        Map<String, Integer> marker_count = new HashMap<>();

        List<String> hexArray = readFile(path);
        int curr_index = 0;
        String last_marker = "";
        StringBuilder buffer = new StringBuilder();

        DataTreeNode root = new DataTreeNode("");
        DataTreeNode iterator = root;
        DataTreeNode parent = null;

        for (int i = 0; i < hexArray.size() - 1; i++){
            //Check if byte is a start of a marker (ff00 is not a marker)
            if (hexArray.get(i).equals("ff") && !hexArray.get(i+1).equals("00")){

                iterator.setData(buffer.toString());
                buffer = new StringBuilder();
//                String marker = hexArray.get(i+1);
                try {
                    //Check for marker type
                    switch (this._markers.get(hexArray.get(i + 1))) {
                        case "SOI":

                            //Start of image
                            //Check if this is the first encounter
                            if (last_marker.equals(""))
                                parent = iterator;
                            else
                                parent = regressToMarker(iterator, "SOI");

                            iterator = new DataTreeNode("/SOI ", parent);
                            last_marker = "SOI";
                            parent = iterator;
                            addMarkerToDict(marker_count, "SOI", "-1");
                            break;
                        case "EOI":

                            //End of image
                            //Go up the tree until we reach the start of image node
                            parent = regressToMarker(iterator, "SOI").getParent();
                            iterator = new DataTreeNode("/EOI ", parent);
                            last_marker = "SOI";
                            curr_index = i;
                            addMarkerToDict(marker_count, "EOI", "-1");
                            break;
                        case "SOF":

                            //Start of scan
                            //Regress according to the last marker
                            if (!last_marker.equals("SOI"))
                                parent = regressToMarker(iterator, "SOF").getParent();
                            else
                                parent = iterator.getParent();
                            iterator = new DataTreeNode("/SOF" + hexArray.get(i + 1).charAt(1) + " ", parent);
                            last_marker = "SOF";
                            parent = iterator;

                            addMarkerToDict(marker_count, "SOF", hexArray.get(i + 1).charAt(1) + "");
                            break;
                        case "SOS":

                            //Start of scan
                            //Regress to the first SOF node encountered
                            parent = regressToMarker(iterator, "SOF");
                            iterator = new DataTreeNode("/SOS ", parent);
                            parent = iterator;

                            addMarkerToDict(marker_count, "SOS", "-1");
                            break;
                        case "DHT":

                            //Table
                            //Regress according to the last marker encountered
                            if (!last_marker.equals("SOI"))
                                regressToMarker(iterator, "SOF");
                            else parent = iterator;
                            iterator = new DataTreeNode("/DHT ", parent);

                            addMarkerToDict(marker_count, "DHT", "-1");
                            break;
                        case "DAC":

                            //Table
                            //Regress according to the last marker encountered
                            if (!last_marker.equals("SOI"))
                                regressToMarker(iterator, "SOF");
                            else
                                parent = iterator;

                            iterator = new DataTreeNode("/DAC ", parent);
                            addMarkerToDict(marker_count, "DAC", "-1");
                            break;

                        default:

                            iterator = new DataTreeNode("/" + this._markers.get(hexArray.get(i + 1)) + " ", parent);
                            addMarkerToDict(marker_count, this._markers.get(hexArray.get(i + 1)), "-1");
                            break;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                i = i + 1;
            }

            else {

                buffer.append(hexArray.get(i) + ",");
            }
        }

        DataTree dt = new DataTree(root);
        IMetadata metadata = new MetadataJPG(dt, curr_index, hexArray.size(), marker_count);

        return metadata;
    }

    /**
     * Iterates back to the nodes parent that has the specified marker.
     * @param iterator
     * @param marker
     * @return
     */
    private DataTreeNode regressToMarker(DataTreeNode iterator, String marker){
//        DataTreeNode temp = iterator;
        try {
            while (!iterator.getData().contains(marker) && iterator.getParent() != null)
                iterator = iterator.getParent();
        } catch (Exception e){
            e.printStackTrace();
        }

        return iterator;
    }

    private void addMarkerToDict(Map<String, Integer> marker_count, String marker, String unit){
        //Check if marker has a parameter
        if (!unit.equals("-1"))
            marker += unit;

        int count = marker_count.containsKey(marker) ? marker_count.get(marker) : 0;
        marker_count.put(marker, count + 1);
    }
    //</editor-fold>


    //<editor-fold desc="Used for reading an image data to hex array represented by strings">
    private List<String> readFile(String path) {

        List<String> file_cont = new ArrayList<>();
        try
        {
            FileInputStream fis = new FileInputStream(new File(path));

            byte[] bytes = new byte[1];
            int value = 0;
            do
            {
                value = fis.read(bytes);
                file_cont.add(toHexFromBytes(bytes));

            }while(value != -1);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return file_cont;
    }

    private final static String[] hexSymbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private final int BITS_PER_HEX_DIGIT = 4;

    private String toHexFromByte(final byte b) {
        byte leftSymbol = (byte)((b >>> BITS_PER_HEX_DIGIT) & 0x0f);
        byte rightSymbol = (byte)(b & 0x0f);

        return (hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
    }

    private String toHexFromBytes(final byte[] bytes){
        if(bytes == null || bytes.length == 0)
        {
            return ("");
        }

        // there are 2 hex digits per byte
        StringBuilder hexBuffer = new StringBuilder(bytes.length * 2);

        // for each byte, convert it to hex and append it to the buffer
        for(int i = 0; i < bytes.length; i++)
        {
            hexBuffer.append(toHexFromByte(bytes[i]));
        }

        return (hexBuffer.toString());
    }
    //</editor-fold>
}
