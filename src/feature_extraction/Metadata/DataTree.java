package feature_extraction.Metadata;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Alex on 3/17/2017.
 */
public class DataTree {
    private DataTreeNode _root;

    public DataTree() { }

    public DataTree(DataTreeNode root){
        this._root = root;
    }

    public DataTreeNode getRoot() {
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

    public void writeTreeRec(DataTreeNode it) {
        this.sb.append(it.indented()).append(it.getData()).append("\n");
        if (it.getChildren().size() != 0) {

            for (DataTreeNode c : it.getChildren()) {
                writeTreeRec(c);
            }
        }
    }
}
