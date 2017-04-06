package FeatureExtractorStructuralPathsJPGRTF.Metadata;



import java.util.List;
import java.util.Map;


public class MetadataJPG extends AMetadata {

    private Map<String, Integer> _markers;

    public MetadataJPG(DataTree tree, int eof_index, int file_length, Map<String, Integer> marker_count){
        super(tree, eof_index, file_length);
        this._markers = marker_count;
    }

    @Override
    public boolean isEOF() {
        return super._file_length == this._eof_index;
    }



    public DataTree getTree(){
        return this._dt;
    }

}
