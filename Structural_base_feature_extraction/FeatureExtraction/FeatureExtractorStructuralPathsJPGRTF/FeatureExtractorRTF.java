package FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF;

import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Metadata.DataTreeNode;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Metadata.IMetadata;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Parsers.IParser;
import FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Parsers.ParserRTF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 3/29/2017.
 */
public class FeatureExtractorRTF<T> extends AFeatureExtractor<T>  {

    private boolean _extract_single_joint;
    private boolean _extract_top_down_sub;
    private boolean _extract_bot_up_sub;

    private Map<String, Integer> _feature_map;

    public FeatureExtractorRTF(boolean top_down_sub_path, boolean bot_up_sub_path, boolean single_joint){
        this._extract_single_joint = single_joint;
        this._extract_top_down_sub = top_down_sub_path;
        this._extract_bot_up_sub = bot_up_sub_path;
    }

    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {

        //Parse file
        IParser rtf = new ParserRTF();
        IMetadata metadata = rtf.Parse((String)element);

        //Extract features
        _feature_map =  new HashMap<>();
        ExtractStructuralFeaturesRec(metadata.getTree().getRoot(), "");

        return _feature_map;
    }

    /**
     * Extract features while iterating over the Tree constructed while parsing the file
     * @param iterator Current tree node
     * @param feature Constructed full path feature
     */
    private void ExtractStructuralFeaturesRec(DataTreeNode iterator, String feature){

        //Split control words from other data in the node
        String control_words_data = getControlWords(iterator.getData());

        if (control_words_data.isEmpty())
            return;

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
//                    String bu_feature = available_controls.get(0);
                    DataTreeNode bu_iterator = iterator.getParent();

                    //Back track the path using each node's parent node until root is reached
                    try {

                        while (bu_iterator != null && !bu_iterator.isRoot()){ // && feature_map.size() < 350000) {

                            String it_feature = "/" + getControlWords(bu_iterator.getData()).split("\\\\")[1];

                            temp = new StringBuilder(it_feature).append(bu_feature_builder);
                            bu_feature_builder = temp;
                            addEntry(bu_feature_builder.toString());
                            bu_iterator = bu_iterator.getParent();

                        }
                    } catch(OutOfMemoryError e){
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
        for (DataTreeNode child : iterator.getChildren()){
            ExtractStructuralFeaturesRec(child, feature);
        }
    }



    private void addEntry(String feature) {
        int count = _feature_map.containsKey(feature) ? _feature_map.get(feature) : 0;
        _feature_map.put(feature, count+1);
    }


    /**
     * Separates the control word from the rest of the data
     * @param data
     * @return
     */
    private String getControlWords(String data){

        String control_words = "";
        try {
            //split control word and data
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
