package FeatureExtractorStructuralPathsJPGRTF.Parsers;

import FeatureExtractorStructuralPathsJPGRTF.Metadata.IMetadata;

/**
 * Created by Alex on 3/17/2017.
 */
public interface IParser {

    IMetadata Parse(String path);

}
