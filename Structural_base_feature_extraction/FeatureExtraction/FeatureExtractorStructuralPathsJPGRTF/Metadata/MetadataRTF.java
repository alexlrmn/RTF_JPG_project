package FeatureExtraction.FeatureExtractorStructuralPathsJPGRTF.Metadata;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class MetadataRTF extends AMetadata {

    private Map<String, Integer> _word_count;
    private Map<String, List<Integer>> _word_params;

    public MetadataRTF(DataTree tree, int eof_index, int file_length,
                       Map<String, Integer> word_count, Map<String, List<Integer>> word_params) {
        super(tree, eof_index, file_length);
        this._word_count = word_count;
        this._word_params = word_params;
    }

    @Override
    public boolean isEOF() {
        return _eof_index == super._file_length;
    }

    private void writeToFile(String path) {
        StringBuilder count = new StringBuilder();
        for (Map.Entry<String, Integer> entry : _word_count.entrySet()){
            count.append(entry.getKey() + " : " + entry.getValue()).append("\n");
        }

        try (PrintWriter out = new PrintWriter(path + "\\count.txt")){
            out.println(count.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : _word_params.entrySet()){
            params.append(entry);
            params.append("\n");
        }

        try (PrintWriter out = new PrintWriter(path + "\\params.txt")){
            out.println(params.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
