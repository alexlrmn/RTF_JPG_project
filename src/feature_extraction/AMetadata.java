package feature_extraction;

import javax.xml.crypto.Data;
import java.util.Dictionary;

/**
 * Created by Alex on 3/28/2017.
 */
public abstract class AMetadata implements IMetadata{

    protected DataTree _dt;
    protected int _eof_index;

    protected AMetadata(DataTree tree, int eof_index){
        this._dt = tree;
        this._eof_index = eof_index;
    }

    public abstract boolean isEOF();

}
