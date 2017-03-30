package feature_extraction;

import feature_extraction.Metadata.IMetadata;
import feature_extraction.Parsers.IParser;
import feature_extraction.Parsers.RTFParser;

import java.util.Map;

/**
 * Created by Alex on 3/29/2017.
 */
public class RTFFeatureExtractor<T> extends AFeatureExtractor<T>  {

    public RTFFeatureExtractor(){

    }

    @Override
    public Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element) {
        IParser rtf = new RTFParser();
        IMetadata metadata = rtf.Parse((String)element);
        return null;
    }

    @Override
    public String GetName() {
        return null;
    }
}
