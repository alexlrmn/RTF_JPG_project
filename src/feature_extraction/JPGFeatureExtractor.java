package feature_extraction;

import feature_extraction.Metadata.DataTreeNode;
import feature_extraction.Metadata.IMetadata;
import feature_extraction.Parsers.IParser;
import feature_extraction.Parsers.JPGParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 3/29/2017.
 */
public class JPGFeatureExtractor<T> extends AFeatureExtractor<T> {

    private boolean _extract_single_joint;
    private boolean _extract_top_down_sub;
    private boolean _extract_bot_up_sub;

    public JPGFeatureExtractor(boolean top_down_sub, boolean bot_up_sub, boolean single_joint){
        this._extract_single_joint = single_joint;
        this._extract_top_down_sub = top_down_sub;
        this._extract_bot_up_sub = bot_up_sub;
    }

    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {
        IParser parser = new JPGParser();
        IMetadata metadata = parser.Parse((String)element);
        metadata.getTree().writeTree("D:\\temp\\tree.txt");
        Map<String, Integer> feature_map = new HashMap<>();
        ExtractHierarchicalFeaturesRec(feature_map, metadata.getTree().getRoot(), new String());
        return feature_map;
    }

    private void ExtractHierarchicalFeaturesRec(Map<String, Integer> feature_map, DataTreeNode iterator, String feature){

        String curr_marker = getMarker(iterator.getData());
        String curr_feature = feature + curr_marker;

        if (_extract_single_joint && !iterator.isRoot()){
            addEntry(feature_map, curr_marker);
        }

        if (_extract_top_down_sub && !iterator.isLeaf() && !iterator.isRoot()) {
            addEntry(feature_map, curr_feature);
        }

        if (iterator.isLeaf()){

            if (_extract_bot_up_sub){
                //Extract all the Bottom-up features
                String bu_feature = curr_marker;
                DataTreeNode bu_iterator = iterator.getParent();
                while (!bu_iterator.isRoot()){
                    try {
//                        System.out.println(bu_feature);
                        bu_feature = getMarker(bu_iterator.getData()) + bu_feature;
                        bu_iterator = bu_iterator.getParent();
                        addEntry(feature_map, bu_feature);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            addEntry(feature_map, curr_feature);

        }

        else {
            for (DataTreeNode child : iterator.getChildren()) {
                ExtractHierarchicalFeaturesRec(feature_map, child, curr_feature);
            }
        }
    }

    private String getMarker(String data) {
        String marker = "";
        try {
            marker = data.split(" ")[0];
        } catch ( Exception e ){
            System.out.println("An Error occurred while trying to extract marker. Array length is: " + data.split(" ")[0]);
        }

        return marker;
    }

    private void addEntry(Map<String, Integer> feature_map, String feature){
        int count = feature_map.containsKey(feature) ? feature_map.get(feature) : 0;
        feature_map.put(feature, count+1);
    }


    @Override
    public String GetName() {
        return "JPG Structural Features";
    }
}
