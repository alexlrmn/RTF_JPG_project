package FeatureExtraction;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Alex on 3/18/2017.
 */

public abstract class AFeatureExtractor<T> implements IFeatureExtractor<T>, Serializable {
    /**
     * Return list of features (and occurrences) extracted from the given source
     *
     * @param element the type of object represent the element that the features
     * should be extracted from
     * @return list of features (and occurrences) extracted from the given
     * source
     */
    @Override
    public abstract Map<String, Integer> ExtractFeaturesFrequencyFromSingleElement(T element);

    /**
     *
     * @return the features extractor name
     */
    @Override
    public abstract String GetName();
}