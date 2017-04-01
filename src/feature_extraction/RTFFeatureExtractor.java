package feature_extraction;

import feature_extraction.Metadata.DataTreeNode;
import feature_extraction.Metadata.IMetadata;
import feature_extraction.Parsers.IParser;
import feature_extraction.Parsers.RTFParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 3/29/2017.
 */
public class RTFFeatureExtractor<T> extends AFeatureExtractor<T>  {

    private boolean _extract_single_joint;
    private boolean _extract_sub_path;

    public RTFFeatureExtractor(boolean extract_single_joint, boolean extract_sub_path){
        this._extract_single_joint = extract_single_joint;
        this._extract_sub_path = extract_sub_path;
    }

    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {
        IParser rtf = new RTFParser();
        IMetadata metadata = rtf.Parse((String)element);

        Map<String, Integer> feature_map =  new HashMap<>();
        ExtractStructuralFeaturesRec(feature_map, metadata.getTree().getRoot(), "");
        return feature_map;
    }

    private void ExtractStructuralFeaturesRec(Map<String, Integer> feature_map, DataTreeNode iterator, String feature){

        String control_words_data = getControlWords(iterator.getData());

        if (control_words_data.isEmpty())
            return;

        String[] control_words = control_words_data.split("\\\\");

        List<String> available_controls = new ArrayList<>();
        try {
            available_controls.add("/" + control_words[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 2; i < control_words.length; i++) {
            available_controls.add("/" + control_words[1] + "/" + control_words[i]);
        }

        if (this._extract_single_joint) {
            for (int i = 1; i < control_words.length; i++) {
                addEntry(feature_map, "/" + control_words[i]);
            }
        }

        if (this._extract_sub_path && !iterator.isLeaf() && !iterator.isRoot()){
            for (int i = 0 ; i < available_controls.size(); i++) {
                addEntry(feature_map, feature + available_controls.get(i));
            }
        }

        if (iterator.isLeaf()){

            //Extract bottom up
            if (this._extract_sub_path){
                String bu_feature = available_controls.get(0);
                DataTreeNode bu_iterator = iterator.getParent();
                while (!bu_iterator.isRoot()){
                    String it_feature = "/" + getControlWords(bu_iterator.getData()).split("\\\\")[1];
                    bu_feature = it_feature + bu_feature;
                    addEntry(feature_map, bu_feature);
                    bu_iterator = bu_iterator.getParent();
                }
            }

            //Add all available full paths
            for (int i = 0 ; i < available_controls.size() ; i++) {
                addEntry(feature_map, feature + available_controls.get(i));
            }

        }

        else {
            feature = feature + available_controls.get(0);
            for (DataTreeNode child : iterator.getChildren()){
                ExtractStructuralFeaturesRec(feature_map, child, feature);
            }
        }
    }

    private void addEntry(Map<String, Integer> feature_map, String feature) {
        int count = feature_map.containsKey(feature) ? feature_map.get(feature) : 0;
        feature_map.put(feature, count+1);
    }
    
    private String getControlWords(String data){
        String control_words = "";
        try {
            String[] data_ray = data.split(" ");
            if (data_ray.length > 0)
                control_words = data.split(" ")[0];
        } catch ( Exception e ){
            System.out.println("An Error occurred while trying to extract control_words.");
        }

        return control_words;
    }

    @Override
    public String GetName() {
        return "RTF Structural Features";
    }
}
