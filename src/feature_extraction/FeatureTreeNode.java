package feature_extraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 3/17/2017.
 */
public class FeatureTreeNode {

    private String _data;
    private List<FeatureTreeNode> _children;
    private FeatureTreeNode _parent;
    private int _depth;

    public FeatureTreeNode(FeatureTreeNode parent){
        this._data = "";
        this._children = new ArrayList<FeatureTreeNode>();
        this._parent = parent;

        if (parent != null)
            this._depth = parent.getDepth() + 1;
        else
            this._depth = 0;
    }

    public FeatureTreeNode(String data, FeatureTreeNode parent){
        this._data = data;
        _children = new ArrayList<FeatureTreeNode>();
        this._parent = parent;

        if (parent != null)
            this._depth = parent.getDepth() + 1;
        else
            this._depth = 0;
    }

    public void addChild(FeatureTreeNode child){
        this._children.add(child);
    }

    public List<FeatureTreeNode> getChildren() {
        return this._children;
    }

    public FeatureTreeNode getParent() {
        return this._parent;
    }

    public int getDepth(){
        return _depth;
    }

    public boolean equals(FeatureTreeNode comp){
        return (this._depth == comp.getDepth() && this._data == comp._data);
    }

    public String getData() {
        return this._data;
    }

    public void setData(String data){
        StringBuilder sb = new StringBuilder();
        sb.append(this._data).append(data);
        this._data = sb.toString();
    }

    public String indented() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < this._depth ; i++){
            sb.append("\t");
        }

        return sb.toString();
    }
}
