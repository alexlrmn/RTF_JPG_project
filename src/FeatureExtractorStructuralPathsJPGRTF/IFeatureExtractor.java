package FeatureExtractorStructuralPathsJPGRTF;

import java.util.Map;

/**
 * Created by Alex on 3/17/2017.
 */
public interface IFeatureExtractor<T> {

    Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element);
    String GetName();
}
