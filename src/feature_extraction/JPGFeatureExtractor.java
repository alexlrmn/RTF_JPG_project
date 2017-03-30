package feature_extraction;

import feature_extraction.Metadata.DataTree;
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

//    public enum feature_names{
//        feature1("feature1"),
//        feature2("feature2");
//
//        private final String text;
//        feature_names(String text){
//            this.text = text;
//        }
//
//        public Map<String, Integer> extract() {
//            Map<String, Integer> feature_occurence = new HashMap<String, Integer>();
//            for (feature_names fn : feature_names.values()){
//                Feature f = fn.
//            }
//        }
//    }
//
//
//
//    private static Feature extractFeature1() {
//        // Do something
//        // return feature
//        return null;
//    }
//
//    private static Feature extractFeature2() {
//        // Do something
//        // return feature
//        return null;
//    }
//
//    private Map<String, Integer> ExtractExpertFeatures(){
//        for (feature_names fn : feature_names.values()){
//            Feature f = fn.extract();
//        }
//
//        return null;
//    }


    private boolean _extract_single_joint;
    private boolean _extract_full_path;
    private boolean _extract_sub_path;

    private Map<String, Integer> _feature_occurence;

    public JPGFeatureExtractor(boolean extract_single_joint, boolean extract_full_path, boolean extract_sub_path){
        this._extract_single_joint = extract_single_joint;
        this._extract_full_path = extract_full_path;
        this._extract_sub_path = extract_sub_path;
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
/*
    private void ExtractHierarchicalFeatures(Map<String, Integer> feature_map, IMetadata metadata){
        DataTreeNode iterator = metadata.getTree().getRoot();
        ExtractHierarchicalFeaturesRec(feature_map, iterator, new String());
    }
*/
    private void ExtractHierarchicalFeaturesRec(Map<String, Integer> feature_map, DataTreeNode iterator, String feature){

        String curr_marker = getMarker(iterator.getData());
        String curr_feature = feature + curr_marker;

        if (_extract_single_joint && !iterator.isRoot()){
            addEntry(feature_map, curr_marker);
        }

        if (_extract_sub_path && !iterator.isLeaf() && !iterator.isRoot()) {
            addEntry(feature_map, curr_feature);
        }

        if (iterator.isLeaf()){

            if (_extract_sub_path){
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

            if (_extract_full_path){
                addEntry(feature_map, curr_feature);
            }

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
            System.out.println("An Error occured while trying to extract marker. Array length is: " + data.split(" ")[0]);
        }

        return marker;
    }

    private void addEntry(Map<String, Integer> feature_occurence, String feature){
        int count = feature_occurence.containsKey(feature) ? feature_occurence.get(feature) : 0;
        feature_occurence.put(feature, count+1);
    }


    @Override
    public String GetName() {
        return null;
    }
}
