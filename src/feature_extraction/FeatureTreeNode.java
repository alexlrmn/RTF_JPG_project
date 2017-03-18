package feature_extraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 3/17/2017.
 */
public class FeatureTreeNode {

    private Feature _data;
    private List<FeatureTreeNode> _children;

    public FeatureTreeNode(Feature data){
        this._data = data;
        _children = new ArrayList<FeatureTreeNode>();
    }

    public void addChild(FeatureTreeNode child){
        this._children.add(child);
    }

    public List<FeatureTreeNode> getChildren() {
        return this._children;
    }
}
