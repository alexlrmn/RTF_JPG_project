package feature_extraction;

/**
 * Created by Alex on 3/17/2017.
 */
public class FeatureTree {
    private FeatureTreeNode _root;

    public FeatureTree(FeatureTreeNode root){
        this._root = root;
    }

    public FeatureTreeNode getRoot() {
        return _root;
    }

}
