package feature_extraction;

/**
 * Created by Alex on 3/23/2017.
 */
public class Program {
    public static void main(String[] args) {
        String path = "D:\\temp\\test.rtf";
        RTFParser rfp = new RTFParser();
        rfp.Parse(path);
        FeatureTree ft = rfp.getTree();
        ft.writeTree("D:\\temp\\tree.txt");

    }
}
