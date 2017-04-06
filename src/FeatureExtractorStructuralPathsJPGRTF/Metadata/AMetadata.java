package FeatureExtractorStructuralPathsJPGRTF.Metadata;


public abstract class AMetadata implements IMetadata{

    protected DataTree _dt;
    protected int _eof_index;
    protected int _file_length;

    protected AMetadata(DataTree tree, int eof_index, int file_length) {
        this._dt = tree;
        this._eof_index = eof_index;
        this._file_length = file_length;
    }

    public DataTree getTree(){
        return _dt;
    }

    public abstract boolean isEOF();

}
