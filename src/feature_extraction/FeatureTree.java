package feature_extraction;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Alex on 3/17/2017.
 */
public class FeatureTree {
    private FeatureTreeNode _root;

    public FeatureTree() { }

    public FeatureTree(FeatureTreeNode root){
        this._root = root;
    }

    public FeatureTreeNode getRoot() {
        return _root;
    }

    private StringBuilder sb;

    public void writeTree(String path) {
        this.sb = new StringBuilder();
        writeTreeRec(this._root);

        try (PrintWriter out = new PrintWriter(path)){
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeTreeRec(FeatureTreeNode it) {
        this.sb.append(it.indented()).append(it.getData()).append("\n");
        if (it.getChildren().size() != 0) {
//            this.sb.append(it.indented()).append("<Children>\n");

            for (FeatureTreeNode c : it.getChildren()) {
                writeTreeRec(c);
            }
//            this.sb.append(it.indented()).append("</Children>\n");
        }
    }
}
