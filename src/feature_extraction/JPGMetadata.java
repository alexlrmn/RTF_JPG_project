package feature_extraction;


import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 3/28/2017.
 */
public class JPGMetadata extends AMetadata {

    private List<String> _data;
    private HashMap<String, Integer> _markers;

    public JPGMetadata(DataTree tree, int eof_index, List<String> data){
        super(tree, eof_index);
        this._data = data;
        this._markers = new HashMap<String, Integer>();
    }

    @Override
    public boolean isEOF() {
        return this._data.size() == this._eof_index;
    }
}
