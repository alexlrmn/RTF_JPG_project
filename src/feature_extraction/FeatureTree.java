package feature_extraction;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Alex on 3/17/2017.
 */
public class FeatureTree {
    private FeatureTreeNode _root;

    private FeatureTreeNode _header;
    private FeatureTreeNode _document;

    public FeatureTree() {

    }

    public FeatureTree(FeatureTreeNode header, FeatureTreeNode document) {
        this._header = header;
        this._document = document;
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

    private StringBuilder sb;

    public void writeTree(String path) {
        this.sb = new StringBuilder();
        sb.append("<Header>\n");
        writeTreeRec(this._header);
        sb.append("</Header>\n");
        if (this._document != null){
            sb.append("<Document>\n");
            writeTreeRec(this._document);
            sb.append("</Document>\n");
        }


        try (PrintWriter out = new PrintWriter(path)){
            out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeTreeRec(FeatureTreeNode it) {
        this.sb.append(it.indented()).append(it.getData()).append("\n");
        if (it.getChildren().size() != 0) {
            this.sb.append(it.indented()).append("<Children>\n");

            for (FeatureTreeNode c : it.getChildren()) {
                writeTreeRec(c);
            }
            this.sb.append(it.indented()).append("</Children>\n");
        }
    }
}
