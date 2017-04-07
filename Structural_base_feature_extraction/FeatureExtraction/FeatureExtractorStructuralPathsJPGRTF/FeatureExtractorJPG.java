package FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF;

import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Metadata.DataTreeNode;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Metadata.IMetadata;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Parsers.IParser;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Parsers.ParserJPG;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 3/29/2017.
 */
public class FeatureExtractorJPG<T> extends AFeatureExtractor<T> {

    private boolean _extract_single_joint;
    private boolean _extract_top_down_sub;
    private boolean _extract_bot_up_sub;

    private Map<String, Integer> _feature_map;

    public FeatureExtractorJPG(boolean top_down_sub, boolean bot_up_sub, boolean single_joint){
        this._extract_single_joint = single_joint;
        this._extract_top_down_sub = top_down_sub;
        this._extract_bot_up_sub = bot_up_sub;
    }

    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {

        IParser parser = new ParserJPG();

        //Parse file
        IMetadata metadata = parser.Parse((String)element);
        _feature_map = new HashMap<>();

        //Extract features
        ExtractHierarchicalFeaturesRec(metadata.getTree().getRoot(), new String());
        return _feature_map;
    }

    /**
     * Iterates over the Tree constructed by the parser and extract features.
     * @param iterator Current node
     * @param feature Current feature constructed up until now
     */
    private void ExtractHierarchicalFeaturesRec(DataTreeNode iterator, String feature){

        String curr_marker = getMarker(iterator.getData());
        String curr_feature = feature + curr_marker;

        //Add single marker to map
        if (_extract_single_joint && !iterator.isRoot()){
            addEntry(curr_marker);
        }

        //Add sub path observed this far
        if (_extract_top_down_sub && !iterator.isLeaf() && !iterator.isRoot()) {
            addEntry(curr_feature);
        }

        if (iterator.isLeaf()){

            if (_extract_bot_up_sub){
                //Extract all the Bottom-up features
                String bu_feature = curr_marker;
                DataTreeNode bu_iterator = iterator.getParent();
                while (bu_iterator != null && !bu_iterator.isRoot()){
                    try {
                        bu_feature = getMarker(bu_iterator.getData()) + bu_feature;
                        bu_iterator = bu_iterator.getParent();
                        addEntry(bu_feature);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            //Add full path to map
            addEntry(curr_feature);

        }

        else {
            //Continue recursive iteration
            for (DataTreeNode child : iterator.getChildren()) {
                ExtractHierarchicalFeaturesRec(child, curr_feature);
            }
        }
    }

    /**
     * Separate marker from rest of the data
     * @param data
     * @return
     */
    private String getMarker(String data) {
        String marker = "";
        try {
            marker = data.split(" ")[0];
        } catch ( Exception e ){
            System.out.println("An Error occurred while trying to extract marker. Array length is: " + data.split(" ")[0]);
        }

        return marker;
    }

    private void addEntry(String feature){
        int count = _feature_map.containsKey(feature) ? _feature_map.get(feature) : 0;
        _feature_map.put(feature, count+1);
    }


    @Override
    public String GetName() {
        return "JPG Structural Features";
    }



}
