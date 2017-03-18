package feature_extraction;

/**
 * Created by Alex on 3/17/2017.
 */
public class FeatureTree {
    private FeatureTreeNode _root;

    private FeatureTreeNode _header;
    private FeatureTreeNode _document;

    public FeatureTree() {

    }

    public FeatureTree(FeatureTreeNode root){
        this._root = root;
    }

    public FeatureTreeNode getRoot() {
        return _root;
    }

    public FeatureTreeNode getHeader(){
        return _header;
    }

    public FeatureTreeNode getDocument() {
        return _document;
    }
}
