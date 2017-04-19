package FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Parsers;


import FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Metadata.IMetadata;

/**
 * Created by Alex on 3/17/2017.
 */
public interface IParser {

    IMetadata Parse(String path);

}
