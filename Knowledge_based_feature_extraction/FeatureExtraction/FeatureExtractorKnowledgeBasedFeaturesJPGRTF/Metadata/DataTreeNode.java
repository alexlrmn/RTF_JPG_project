package FeatureExtraction.FeatureExtractorKnowledgeBasedFeaturesJPGRTF.Metadata;

import java.util.ArrayList;
import java.util.List;


public class DataTreeNode {

    private String _data;
    private List<DataTreeNode> _children;
    private DataTreeNode _parent;
    private int _depth;

    public DataTreeNode(String data) {
        this._data = data;
        this._children = new ArrayList<>();
        this._parent = null;
        this._depth = 0;
    }

    public DataTreeNode(DataTreeNode parent){
        this._data = "";
        this._children = new ArrayList<>();
        this._parent = parent;

        if (parent != null){
            this._depth = parent.getDepth() + 1;
            parent.addChild(this);
        }

        else
            this._depth = 0;
    }

    public DataTreeNode(String data, DataTreeNode parent){
        this._data = data;
        _children = new ArrayList<>();
        this._parent = parent;

        if (parent != null){
            this._depth = parent.getDepth() + 1;
            parent.addChild(this);
        }
        else
            this._depth = 0;
    }

    public void addChild(DataTreeNode child){
        this._children.add(child);
    }

    public List<DataTreeNode> getChildren() { return this._children; }

    public DataTreeNode getParent() {
        return this._parent;
    }

    public int getDepth(){
        return _depth;
    }

//    public boolean equals(DataTreeNode comp){
//        return (this._depth == comp.getDepth() && this._data == comp._data);
//    }

    public String getData() { return this._data; }

    public void setData(String data){
        StringBuilder sb = new StringBuilder();
        sb.append(this._data).append(data);
        this._data = sb.toString();
    }

    /**
     * For printing purpose.
     */
    public String indented() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < this._depth ; i++){
            sb.append("\t");
        }

        return sb.toString();
    }

    public boolean isRoot() { return this._depth == 0; }

    public boolean isLeaf() { return this._children.size() == 0; }
}
