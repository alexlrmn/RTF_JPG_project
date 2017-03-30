package feature_extraction.Metadata;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 3/28/2017.
 */
public class JPGMetadata extends AMetadata {

    private List<String> _data;
    private Map<String, Integer> _markers;

    public JPGMetadata(DataTree tree, int eof_index, List<String> data, Map<String, Integer> marker_count){
        super(tree, eof_index);
        this._data = data;
        this._markers = marker_count;
    }

    @Override
    public boolean isEOF() {
        return this._data.size() == this._eof_index;
    }



    public DataTree getTree(){
        return this._dt;
    }

}
