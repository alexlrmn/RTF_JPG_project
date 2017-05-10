import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Alex on 4/22/2017.
 */
public class FeatureExtractorKnowledgeBasedJPG<T> extends AFeatureExtractor
{

    private final String _NAME = "JPG Knowledge Based Feature Extractor";
    private Metadata _m;

    public FeatureExtractorKnowledgeBasedJPG() {

    }

    @Override
    public Map<String, String> ExtractFeaturesFrequencyFromSingleElement(Object element) {

        Parser p = new Parser();
        _m = p.Parse((String) element);

        //print tree for debugging
//        String[] splits = ((String)element).split("\\\\");
//        String el_name = splits[splits.length - 1];
//        String path = "";
//        for (int i =0 ; i < splits.length - 2  ; i++){
//            path += splits[i] + "\\";
//        }
//
//        path += "tree\\" + el_name + ".txt";
//        Data dt = _m.getTree();
//        dt.writeTree(path);

        //Extract features
        Map<String, String> features = new HashMap<>();

        /**************************File handle****************************************/
        features.put(Feature.File_size.toString(), get_File_size());
        features.put(Feature.File_extension.toString(), get_File_extention());
        features.put(Feature.File_marker_num.toString(), get_File_marker_num());

        /**************************Count of markers************************/
        features.put(Feature.Marker_APP0_num.toString(), get_Marker_APP0_num());
        features.put(Feature.Marker_APP1_num.toString(), get_Marker_APP1_num());
        features.put(Feature.Marker_APP2_num.toString(), get_Marker_APP2_num());
        features.put(Feature.Marker_APP3_num.toString(), get_Marker_APP3_num());
        features.put(Feature.Marker_APP4_num.toString(), get_Marker_APP4_num());
        features.put(Feature.Marker_APP5_num.toString(), get_Marker_APP5_num());
        features.put(Feature.Marker_APP6_num.toString(), get_Marker_APP6_num());
        features.put(Feature.Marker_APP7_num.toString(), get_Marker_APP7_num());
        features.put(Feature.Marker_APP8_num.toString(), get_Marker_APP8_num());
        features.put(Feature.Marker_APP9_num.toString(), get_Marker_APP9_num());
        features.put(Feature.Marker_APP10_num.toString(), get_Marker_APP10_num());
        features.put(Feature.Marker_APP11_num.toString(), get_Marker_APP11_num());
        features.put(Feature.Marker_APP12_num.toString(), get_Marker_APP12_num());
        features.put(Feature.Marker_APP13_num.toString(), get_Marker_APP13_num());
        features.put(Feature.Marker_APP14_num.toString(), get_Marker_APP14_num());
        features.put(Feature.Marker_APP15_num.toString(), get_Marker_APP15_num());
        features.put(Feature.Marker_COM_num.toString(), get_Marker_COM_num());
        features.put(Feature.Marker_DAC_num.toString(), get_Marker_DAC_num());
        features.put(Feature.Marker_DHP_num.toString(), get_Marker_DHP_num());
        features.put(Feature.Marker_DHT_num.toString(), get_Marker_DHT_num());
        features.put(Feature.Marker_DNL_num.toString(), get_Marker_DNL_num());
        features.put(Feature.Marker_DQT_num.toString(), get_Marker_DQT_num());
        features.put(Feature.Marker_DRI_num.toString(), get_Marker_DRI_num());
        features.put(Feature.Marker_EOI_num.toString(), get_Marker_EOI_num());
        features.put(Feature.Marker_EXP_num.toString(), get_Marker_EXP_num());
        features.put(Feature.Marker_JPG_num.toString(), get_Marker_JPG_num());
        features.put(Feature.Marker_JPG0_num.toString(), get_Marker_JPG0_num());
        features.put(Feature.Marker_JPG1_num.toString(), get_Marker_JPG1_num());
        features.put(Feature.Marker_JPG2_num.toString(), get_Marker_JPG2_num());
        features.put(Feature.Marker_JPG3_num.toString(), get_Marker_JPG3_num());
        features.put(Feature.Marker_JPG4_num.toString(), get_Marker_JPG4_num());
        features.put(Feature.Marker_JPG5_num.toString(), get_Marker_JPG5_num());
        features.put(Feature.Marker_JPG6_num.toString(), get_Marker_JPG6_num());
        features.put(Feature.Marker_JPG7_num.toString(), get_Marker_JPG7_num());
        features.put(Feature.Marker_JPG8_num.toString(), get_Marker_JPG8_num());
        features.put(Feature.Marker_JPG9_num.toString(), get_Marker_JPG9_num());
        features.put(Feature.Marker_JPG10_num.toString(), get_Marker_JPG10_num());
        features.put(Feature.Marker_JPG11_num.toString(), get_Marker_JPG11_num());
        features.put(Feature.Marker_JPG12_num.toString(), get_Marker_JPG12_num());
        features.put(Feature.Marker_JPG13_num.toString(), get_Marker_JPG13_num());
        features.put(Feature.Marker_RST0_num.toString(), get_Marker_RST0_num());
        features.put(Feature.Marker_RST1_num.toString(), get_Marker_RST1_num());
        features.put(Feature.Marker_RST2_num.toString(), get_Marker_RST2_num());
        features.put(Feature.Marker_RST3_num.toString(), get_Marker_RST3_num());
        features.put(Feature.Marker_RST4_num.toString(), get_Marker_RST4_num());
        features.put(Feature.Marker_RST5_num.toString(), get_Marker_RST5_num());
        features.put(Feature.Marker_RST6_num.toString(), get_Marker_RST6_num());
        features.put(Feature.Marker_RST7_num.toString(), get_Marker_RST7_num());
        features.put(Feature.Marker_SOF0_num.toString(), get_Marker_SOF0_num());
        features.put(Feature.Marker_SOF1_num.toString(), get_Marker_SOF1_num());
        features.put(Feature.Marker_SOF2_num.toString(), get_Marker_SOF2_num());
        features.put(Feature.Marker_SOF3_num.toString(), get_Marker_SOF3_num());
        features.put(Feature.Marker_SOF5_num.toString(), get_Marker_SOF5_num());
        features.put(Feature.Marker_SOF6_num.toString(), get_Marker_SOF6_num());
        features.put(Feature.Marker_SOF7_num.toString(), get_Marker_SOF7_num());
        features.put(Feature.Marker_SOF9_num.toString(), get_Marker_SOF9_num());
        features.put(Feature.Marker_SOF10_num.toString(), get_Marker_SOF10_num());
        features.put(Feature.Marker_SOF11_num.toString(), get_Marker_SOF11_num());
        features.put(Feature.Marker_SOF13_num.toString(), get_Marker_SOF13_num());
        features.put(Feature.Marker_SOF14_num.toString(), get_Marker_SOF14_num());
        features.put(Feature.Marker_SOF15_num.toString(), get_Marker_SOF15_num());
        features.put(Feature.Marker_SOI_num.toString(), get_Marker_SOI_num());
        features.put(Feature.Marker_SOS_num.toString(), get_Marker_SOS_num());
        features.put(Feature.Marker_TEM_num.toString(),get_Marker_TEM_num());
        /*****************************************************************/

        /***************************SOI and EOI handle********************/
        features.put(Feature.Marker_SOI_EOI_dif.toString(), get_Marker_SOI_EOI_dif());
        //features.put(Feature.Marker_SOI_EOI_nested_num.toString(),
        features.put(Feature.Marker_EOI_illegal_content_after_num.toString(), get_Marker_EOI_illegal_content_after_num());
        /*****************************************************************/

        /**************************features.put(Feature.Marker correctness**************************************/
        features.put(Feature.Marker_APP0_size_max.toString(), get_Marker_APP0_size_max());
        features.put(Feature.Marker_APP0_size_incorrect_num.toString(), get_Marker_APP0_size_incorrect_num());
        features.put(Feature.Marker_APP0_size_declared_incorrect_num.toString(), get_Marker_APP0_size_declared_incorrect_num());
        features.put(Feature.Marker_APP1_size_max.toString(), get_Marker_APP1_size_max());
        features.put(Feature.Marker_APP1_size_incorrect_num.toString(), get_Marker_APP1_size_incorrect_num());
        features.put(Feature.Marker_APP1_size_declared_incorrect_num.toString(), get_Marker_APP1_size_declared_incorrect_num());
        features.put(Feature.Marker_APP2_size_max.toString(), get_Marker_APP2_size_max());
        features.put(Feature.Marker_APP2_size_incorrect_num.toString(), get_Marker_APP2_size_incorrect_num());
        features.put(Feature.Marker_APP2_size_declared_incorrect_num.toString(), get_Marker_APP2_size_declared_incorrect_num());
        features.put(Feature.Marker_APP3_size_max.toString(), get_Marker_APP3_size_max());
        features.put(Feature.Marker_APP3_size_incorrect_num.toString(), get_Marker_APP3_size_incorrect_num());
        features.put(Feature.Marker_APP3_size_declared_incorrect_num.toString(), get_Marker_APP3_size_declared_incorrect_num());
        features.put(Feature.Marker_APP4_size_max.toString(), get_Marker_APP4_size_max());
        features.put(Feature.Marker_APP4_size_incorrect_num.toString(), get_Marker_APP4_size_incorrect_num());
        features.put(Feature.Marker_APP4_size_declared_incorrect_num.toString(), get_Marker_APP4_size_declared_incorrect_num());
        features.put(Feature.Marker_APP5_size_max.toString(), get_Marker_APP5_size_max());
        features.put(Feature.Marker_APP5_size_incorrect_num.toString(), get_Marker_APP5_size_incorrect_num());
        features.put(Feature.Marker_APP5_size_declared_incorrect_num.toString(), get_Marker_APP5_size_declared_incorrect_num());
        features.put(Feature.Marker_APP6_size_max.toString(), get_Marker_APP6_size_max());
        features.put(Feature.Marker_APP6_size_incorrect_num.toString(), get_Marker_APP6_size_incorrect_num());
        features.put(Feature.Marker_APP6_size_declared_incorrect_num.toString(), get_Marker_APP6_size_declared_incorrect_num());
        features.put(Feature.Marker_APP7_size_max.toString(), get_Marker_APP7_size_max());
        features.put(Feature.Marker_APP7_size_incorrect_num.toString(), get_Marker_APP7_size_incorrect_num());
        features.put(Feature.Marker_APP7_size_declared_incorrect_num.toString(), get_Marker_APP7_size_declared_incorrect_num());
        features.put(Feature.Marker_APP8_size_max.toString(), get_Marker_APP8_size_max());
        features.put(Feature.Marker_APP8_size_incorrect_num.toString(), get_Marker_APP8_size_incorrect_num());
        features.put(Feature.Marker_APP8_size_declared_incorrect_num.toString(), get_Marker_APP8_size_declared_incorrect_num());
        features.put(Feature.Marker_APP9_size_max.toString(), get_Marker_APP9_size_max());
        features.put(Feature.Marker_APP9_size_incorrect_num.toString(), get_Marker_APP9_size_incorrect_num());
        features.put(Feature.Marker_APP9_size_declared_incorrect_num.toString(), get_Marker_APP9_size_declared_incorrect_num());
        features.put(Feature.Marker_APP10_size_max.toString(), get_Marker_APP10_size_max());
        features.put(Feature.Marker_APP10_size_incorrect_num.toString(), get_Marker_APP10_size_incorrect_num());
        features.put(Feature.Marker_APP10_size_declared_incorrect_num.toString(), get_Marker_APP10_size_declared_incorrect_num());
        features.put(Feature.Marker_APP11_size_max.toString(),get_Marker_APP11_size_max());
        features.put(Feature.Marker_APP11_size_incorrect_num.toString(), get_Marker_APP11_size_incorrect_num());
        features.put(Feature.Marker_APP11_size_declared_incorrect_num.toString(), get_Marker_APP11_size_declared_incorrect_num());
        features.put(Feature.Marker_APP12_size_max.toString(), get_Marker_APP12_size_max());
        features.put(Feature.Marker_APP12_size_incorrect_num.toString(), get_Marker_APP12_size_incorrect_num());
        features.put(Feature.Marker_APP12_size_declared_incorrect_num.toString(), get_Marker_APP12_size_declared_incorrect_num());
        features.put(Feature.Marker_APP13_size_max.toString(), get_Marker_APP13_size_max());
        features.put(Feature.Marker_APP13_size_incorrect_num.toString(), get_Marker_APP13_size_incorrect_num());
        features.put(Feature.Marker_APP13_size_declared_incorrect_num.toString(), get_Marker_APP13_size_declared_incorrect_num());
        features.put(Feature.Marker_APP14_size_max.toString(), get_Marker_APP14_size_max());
        features.put(Feature.Marker_APP14_size_incorrect_num.toString(), get_Marker_APP14_size_incorrect_num());
        features.put(Feature.Marker_APP14_size_declared_incorrect_num.toString(), get_Marker_APP14_size_declared_incorrect_num());
        features.put(Feature.Marker_APP15_size_max.toString(), get_Marker_APP15_size_max());
        features.put(Feature.Marker_APP15_size_incorrect_num.toString(), get_Marker_APP15_size_incorrect_num());
        features.put(Feature.Marker_APP15_size_declared_incorrect_num.toString(), get_Marker_APP15_size_declared_incorrect_num());
        features.put(Feature.Marker_COM_size_max.toString(), get_Marker_COM_size_max());
        features.put(Feature.Marker_COM_size_incorrect_num.toString(), get_Marker_COM_size_incorrect_num());
        features.put(Feature.Marker_COM_size_declared_incorrect_num.toString(), get_Marker_COM_size_declared_incorrect_num());
        features.put(Feature.Marker_DAC_size_max.toString(), get_Marker_DAC_size_max());
        features.put(Feature.Marker_DAC_size_incorrect_num.toString(), get_Marker_DAC_size_incorrect_num());
        features.put(Feature.Marker_DAC_size_declared_incorrect_num.toString(), get_Marker_DAC_size_declared_incorrect_num());
        features.put(Feature.Marker_DHP_size_max.toString(), get_Marker_DHP_size_max());
        features.put(Feature.Marker_DHP_size_incorrect_num.toString(), get_Marker_DHP_size_incorrect_num());
        features.put(Feature.Marker_DHP_size_declared_incorrect_num.toString(),get_Marker_DHP_size_declared_incorrect_num());
        features.put(Feature.Marker_DHT_size_max.toString(), get_Marker_DHT_size_max());
        features.put(Feature.Marker_DHT_size_incorrect_num.toString(), get_Marker_DHT_size_incorrect_num());
        features.put(Feature.Marker_DHT_size_declared_incorrect_num.toString(), get_Marker_DHT_size_declared_incorrect_num());
        features.put(Feature.Marker_DNL_size_max.toString(), get_Marker_DNL_size_max());
        features.put(Feature.Marker_DNL_size_incorrect_num.toString(), get_Marker_DNL_size_incorrect_num());
        features.put(Feature.Marker_DNL_size_declared_incorrect_num.toString(), get_Marker_DNL_size_declared_incorrect_num());
        features.put(Feature.Marker_DQT_size_max.toString(), get_Marker_DQT_size_max());
        features.put(Feature.Marker_DQT_size_incorrect_num.toString(), get_Marker_DQT_size_incorrect_num());
        features.put(Feature.Marker_DQT_size_declared_incorrect_num.toString(), get_Marker_DQT_size_declared_incorrect_num());
        features.put(Feature.Marker_DRI_size_max.toString(), get_Marker_DRI_size_max());
        features.put(Feature.Marker_DRI_size_incorrect_num.toString(), get_Marker_DRI_size_incorrect_num());
        features.put(Feature.Marker_DRI_size_declared_incorrect_num.toString(), get_Marker_DRI_size_declared_incorrect_num());
        features.put(Feature.Marker_EXP_size_max.toString(), get_Marker_EXP_size_max());
        features.put(Feature.Marker_EXP_size_incorrect_num.toString(), get_Marker_EXP_size_incorrect_num());
        features.put(Feature.Marker_EXP_size_declared_incorrect_num.toString(), get_Marker_EXP_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG_size_max.toString(), get_Marker_JPG_size_max());
        features.put(Feature.Marker_JPG_size_incorrect_num.toString(), get_Marker_JPG_size_incorrect_num());
        features.put(Feature.Marker_JPG_size_declared_incorrect_num.toString(), get_Marker_JPG_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG0_size_max.toString(), get_Marker_JPG0_size_max());
        features.put(Feature.Marker_JPG0_size_incorrect_num.toString(), get_Marker_JPG0_size_incorrect_num());
        features.put(Feature.Marker_JPG0_size_declared_incorrect_num.toString(), get_Marker_JPG0_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG1_size_max.toString(), get_Marker_JPG1_size_max());
        features.put(Feature.Marker_JPG1_size_incorrect_num.toString(), get_Marker_JPG1_size_incorrect_num());
        features.put(Feature.Marker_JPG1_size_declared_incorrect_num.toString(), get_Marker_JPG1_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG2_size_max.toString(), get_Marker_JPG2_size_max());
        features.put(Feature.Marker_JPG2_size_incorrect_num.toString(), get_Marker_JPG2_size_incorrect_num());
        features.put(Feature.Marker_JPG2_size_declared_incorrect_num.toString(), get_Marker_JPG2_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG3_size_max.toString(), get_Marker_JPG3_size_max());
        features.put(Feature.Marker_JPG3_size_incorrect_num.toString(), get_Marker_JPG3_size_incorrect_num());
        features.put(Feature.Marker_JPG3_size_declared_incorrect_num.toString(), get_Marker_JPG3_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG4_size_max.toString(), get_Marker_JPG4_size_max());
        features.put(Feature.Marker_JPG4_size_incorrect_num.toString(), get_Marker_JPG4_size_incorrect_num());
        features.put(Feature.Marker_JPG4_size_declared_incorrect_num.toString(), get_Marker_JPG4_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG5_size_max.toString(), get_Marker_JPG5_size_max());
        features.put(Feature.Marker_JPG5_size_incorrect_num.toString(), get_Marker_JPG5_size_incorrect_num());
        features.put(Feature.Marker_JPG5_size_declared_incorrect_num.toString(), get_Marker_JPG5_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG6_size_max.toString(), get_Marker_JPG6_size_max());
        features.put(Feature.Marker_JPG6_size_incorrect_num.toString(), get_Marker_JPG6_size_incorrect_num());
        features.put(Feature.Marker_JPG6_size_declared_incorrect_num.toString(), get_Marker_JPG6_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG7_size_max.toString(), get_Marker_JPG7_size_max());
        features.put(Feature.Marker_JPG7_size_incorrect_num.toString(), get_Marker_JPG7_size_incorrect_num());
        features.put(Feature.Marker_JPG7_size_declared_incorrect_num.toString(), get_Marker_JPG7_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG8_size_max.toString(), get_Marker_JPG8_size_max());
        features.put(Feature.Marker_JPG8_size_incorrect_num.toString(), get_Marker_JPG8_size_incorrect_num());
        features.put(Feature.Marker_JPG8_size_declared_incorrect_num.toString(), get_Marker_JPG8_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG9_size_max.toString(), get_Marker_JPG9_size_max());
        features.put(Feature.Marker_JPG9_size_incorrect_num.toString(), get_Marker_JPG9_size_incorrect_num());
        features.put(Feature.Marker_JPG9_size_declared_incorrect_num.toString(), get_Marker_JPG9_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG10_size_max.toString(), get_Marker_JPG10_size_max());
        features.put(Feature.Marker_JPG10_size_incorrect_num.toString(), get_Marker_JPG10_size_incorrect_num());
        features.put(Feature.Marker_JPG10_size_declared_incorrect_num.toString(), get_Marker_JPG10_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG11_size_max.toString(), get_Marker_JPG11_size_max());
        features.put(Feature.Marker_JPG11_size_incorrect_num.toString(), get_Marker_JPG11_size_incorrect_num());
        features.put(Feature.Marker_JPG11_size_declared_incorrect_num.toString(), get_Marker_JPG11_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG12_size_max.toString(), get_Marker_JPG12_size_max());
        features.put(Feature.Marker_JPG12_size_incorrect_num.toString(), get_Marker_JPG12_size_incorrect_num());
        features.put(Feature.Marker_JPG12_size_declared_incorrect_num.toString(), get_Marker_JPG12_size_declared_incorrect_num());
        features.put(Feature.Marker_JPG13_size_max.toString(), get_Marker_JPG13_size_max());
        features.put(Feature.Marker_JPG13_size_incorrect_num.toString(), get_Marker_JPG13_size_incorrect_num());
        features.put(Feature.Marker_JPG13_size_declared_incorrect_num.toString(), get_Marker_JPG13_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF0_size_max.toString(), get_Marker_SOF0_size_max());
        features.put(Feature.Marker_SOF0_size_incorrect_num.toString(), get_Marker_SOF0_size_incorrect_num());
        features.put(Feature.Marker_SOF0_size_declared_incorrect_num.toString(), get_Marker_SOF0_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF1_size_max.toString(), get_Marker_SOF1_size_max());
        features.put(Feature.Marker_SOF1_size_incorrect_num.toString(), get_Marker_SOF1_size_incorrect_num());
        features.put(Feature.Marker_SOF1_size_declared_incorrect_num.toString(), get_Marker_SOF1_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF2_size_max.toString(), get_Marker_SOF2_size_max());
        features.put(Feature.Marker_SOF2_size_incorrect_num.toString(), get_Marker_SOF2_size_incorrect_num());
        features.put(Feature.Marker_SOF2_size_declared_incorrect_num.toString(), get_Marker_SOF2_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF3_size_max.toString(), get_Marker_SOF3_size_max());
        features.put(Feature.Marker_SOF3_size_incorrect_num.toString(), get_Marker_SOF3_size_incorrect_num());
        features.put(Feature.Marker_SOF3_size_declared_incorrect_num.toString(), get_Marker_SOF3_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF5_size_max.toString(), get_Marker_SOF5_size_max());
        features.put(Feature.Marker_SOF5_size_incorrect_num.toString(), get_Marker_SOF5_size_incorrect_num());
        features.put(Feature.Marker_SOF5_size_declared_incorrect_num.toString(), get_Marker_SOF5_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF6_size_max.toString(), get_Marker_SOF6_size_max());
        features.put(Feature.Marker_SOF6_size_incorrect_num.toString(), get_Marker_SOF6_size_incorrect_num());
        features.put(Feature.Marker_SOF6_size_declared_incorrect_num.toString(), get_Marker_SOF6_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF7_size_max.toString(), get_Marker_SOF7_size_max());
        features.put(Feature.Marker_SOF7_size_incorrect_num.toString(), get_Marker_SOF7_size_incorrect_num());
        features.put(Feature.Marker_SOF7_size_declared_incorrect_num.toString(), get_Marker_SOF7_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF9_size_max.toString(), get_Marker_SOF9_size_max());
        features.put(Feature.Marker_SOF9_size_incorrect_num.toString(), get_Marker_SOF9_size_incorrect_num());
        features.put(Feature.Marker_SOF9_size_declared_incorrect_num.toString(), get_Marker_SOF9_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF10_size_max.toString(), get_Marker_SOF10_size_max());
        features.put(Feature.Marker_SOF10_size_incorrect_num.toString(), get_Marker_SOF10_size_incorrect_num());
        features.put(Feature.Marker_SOF10_size_declared_incorrect_num.toString(), get_Marker_SOF10_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF11_size_max.toString(), get_Marker_SOF11_size_max());
        features.put(Feature.Marker_SOF11_size_incorrect_num.toString(),get_Marker_SOF11_size_incorrect_num());
        features.put(Feature.Marker_SOF11_size_declared_incorrect_num.toString(), get_Marker_SOF11_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF13_size_max.toString(), get_Marker_SOF13_size_max());
        features.put(Feature.Marker_SOF13_size_incorrect_num.toString(), get_Marker_SOF13_size_incorrect_num());
        features.put(Feature.Marker_SOF13_size_declared_incorrect_num.toString(), get_Marker_SOF13_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF14_size_max.toString(), get_Marker_SOF14_size_max());
        features.put(Feature.Marker_SOF14_size_incorrect_num.toString(), get_Marker_SOF14_size_incorrect_num());
        features.put(Feature.Marker_SOF14_size_declared_incorrect_num.toString(), get_Marker_SOF14_size_declared_incorrect_num());
        features.put(Feature.Marker_SOF15_size_max.toString(), get_Marker_SOF15_size_max());
        features.put(Feature.Marker_SOF15_size_incorrect_num.toString(), get_Marker_SOF15_size_incorrect_num());
        features.put(Feature.Marker_SOF15_size_declared_incorrect_num.toString(), get_Marker_SOF15_size_declared_incorrect_num());
        features.put(Feature.Marker_SOS_size_max.toString(), get_Marker_SOS_size_max());
        features.put(Feature.Marker_SOS_size_incorrect_num.toString(), get_Marker_SOS_size_incorrect_num());
        features.put(Feature.Marker_SOS_size_declared_incorrect_num.toString(), get_Marker_SOS_size_declared_incorrect_num());
        features.put(Feature.Marker_APP_major_revision_incorrect_num.toString(), get_Marker_APP_major_revision_incorrect_num());
        features.put(Feature.Marker_APP_minor_revision_incorrect_num.toString(), get_Marker_APP_minor_revision_incorrect_num());
        features.put(Feature.Marker_APP_unit_density_incorrect_num.toString(), get_Marker_APP_unit_density_incorrect_num());
        features.put(Feature.Marker_APP_x_density_incorrect_num.toString(), get_Marker_APP_x_density_incorrect_num());
        features.put(Feature.Marker_APP_y_density_incorrect_num.toString(), get_Marker_APP_y_density_incorrect_num());
        return features;
    }

    @Override
    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>();

        Feature[] features = Feature.values();
        for (Feature f : features){
            headers.add(f.toString());
        }

        Collections.sort(headers);

        return headers;

    }

    @Override
    public String GetName() {
        return _NAME;
    }


    private class Parser {

        private Map<String, Integer> _marker_count;

        //<editor-fold desc="Markers">
        private final String[] hexSymbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        private final Map<String, String> _markers = createMap();
        private Map<String, List<Data>> _marker_size;

        private Map<String, String> createMap() {

            Map<String, String> markers = new HashMap<>();
            markers.put("c4", "DHT");
            markers.put("c8", "JPG");
            markers.put("cc", "DAC");
            markers.put("d8", "SOI");
            markers.put("d9", "EOI");
            markers.put("da", "SOS");
            markers.put("db", "DQT");
            markers.put("dc", "DNL");
            markers.put("dd", "DRI");
            markers.put("de", "DHP");
            markers.put("df", "EXP");
            markers.put("fe", "COM");
            markers.put("c0", "SOF");
            markers.put("c1", "SOF");
            markers.put("c2", "SOF");
            markers.put("c3", "SOF");
            markers.put("c5", "SOF");
            markers.put("c6", "SOF");
            markers.put("c7", "SOF");
            markers.put("c9", "SOF");
            markers.put("ca", "SOF");
            markers.put("cb", "SOF");
            markers.put("cd", "SOF");
            markers.put("ce", "SOF");
            markers.put("cf", "SOF");
            for (int i = 0; i < 8; i++) {
                markers.put("d" + hexSymbols[i], "RST" + hexSymbols[i]);
            }
            for (int i = 0; i < hexSymbols.length; i++) {
                markers.put("e" + hexSymbols[i], "APP" + hexSymbols[i]);
            }
            for (int i = 0; i < hexSymbols.length - 4; i++) {
                for (int j = 0; j < hexSymbols.length; j++) {
                    markers.put(hexSymbols[i] + hexSymbols[j], "RES");
                }
            }
            markers.remove("00");
            for (int i = 0; i < hexSymbols.length - 2; i++) {
                markers.put("f" + hexSymbols[i], "JPG" + hexSymbols[i]);
            }

            markers.put("01", "TEM");
            markers.put("ff", "UNKNOWN");
            return markers;
        }
        //</editor-fold>

        //<editor-fold desc="Parsing">

        private Metadata Parse(String path) {

            _marker_count = new HashMap<>();
            _marker_size = new HashMap<>();

            char[] charhex = readFileToHex(path);

            String last_marker = "";

            Data root = new Data("/image");
            Data iterator = root;
            Data parent = root;

            boolean SOI = false;
            int soi_index = -1;
            int eoi_index = -1;

            StringBuilder buffer = new StringBuilder();

            String file_start = "";
            if (charhex.length < 3) {
                file_start = charhex[0] + charhex[1] + charhex[2] + charhex[3] + "";
            }

            String marker;
            List<Data> marker_list;

            for (int i = 0; i < charhex.length - 1; i=i+2) {
                //Check if byte is a start of a marker (ff00 is not a marker)
                if (charhex[i] == 'f'
                        && charhex[i+1] == 'f'
                        && i < charhex.length - 3
                        && (charhex[i+2] != '0' || charhex[i+3] != '0')){

                    iterator.setData(buffer.toString());
                    buffer = new StringBuilder();



                    try {
                        //Check marker
                        //create the byte as string ( char + char )
                        String markerbyte = String.valueOf(charhex[i+2]).concat(String.valueOf(charhex[i+3]));
                        switch (this._markers.get(markerbyte)){
                            case "SOI":
                                if (!SOI){
                                    SOI = true;
                                    soi_index = i;
                                }

                                if (last_marker.equals("")) {
                                    parent = iterator;
                                } else {
                                    parent = regressToMarker(iterator, "SOI");
                                }

                                iterator = new Data("/SOI ", parent);
                                last_marker = "SOI";
                                parent = iterator;
                                addEntry("SOI", "-1");

                                break;
                            case "EOI":
                                eoi_index = i + 3;

                                //End of image
                                //Go up the tree until we reach the start of image node
                                parent = regressToMarker(iterator, "SOI");
                                if (!parent.isRoot()){
                                    parent = parent.getParent();
                                }
                                iterator = new Data("/EOI ", parent);
                                last_marker = "SOI";

                                addEntry("EOI", "-1");

                                break;
                            case "SOF":

                                //Start of scan
                                //Regress according to the last marker
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                    if (!parent.isRoot()){
                                        parent = parent.getParent();
                                    }
                                } else {
                                    parent = regressToMarker(iterator, "SOI");
                                }

                                iterator = new Data("/SOF " + String.valueOf(charhex[i+3]), parent);

                                marker = "SOF".concat(String.valueOf(charhex[i+3]));
                                marker_list = _marker_size.containsKey(marker) ? _marker_size.get(marker) : new ArrayList<>();
                                marker_list.add(iterator);
                                _marker_size.put(marker, marker_list);

                                last_marker = "SOF";
                                parent = iterator;

                                addEntry("SOF", String.valueOf(charhex[i+3]));

                                break;
                            case "SOS":

                                //Start of scan
                                //Regress to the first SOF node encountered
                                parent = regressToMarker(iterator, "SOF");
                                iterator = new Data("/SOS ", parent);
                                parent = iterator;

                                addEntry("SOS", "-1");

                                marker = "SOS";
                                marker_list = _marker_size.containsKey(marker) ? _marker_size.get(marker) : new ArrayList<>();
                                marker_list.add(iterator);
                                _marker_size.put(marker, marker_list);

                                break;
                            case "DHT":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }

                                iterator = new Data("/DHT ", parent);

                                addEntry("DHT", "-1");

                                marker = "DHT";
                                marker_list = _marker_size.containsKey(marker) ? _marker_size.get(marker) : new ArrayList<>();
                                marker_list.add(iterator);
                                _marker_size.put(marker, marker_list);

                                break;
                            case "DAC":

                                //Table
                                //Regress according to the last marker encountered
                                if (!last_marker.equals("SOI")) {
                                    parent = regressToMarker(iterator, "SOF");
                                } else {
                                    parent = iterator;
                                }

                                iterator = new Data("/DAC ", parent);

                                addEntry("DAC", "-1");

                                marker = "DAC";
                                marker_list = _marker_size.containsKey(marker) ? _marker_size.get(marker) : new ArrayList<>();
                                marker_list.add(iterator);
                                _marker_size.put(marker, marker_list);

                                break;

                            default:

                                iterator = new Data("/".concat(this._markers.get(markerbyte)) + " ", parent);

                                addEntry(this._markers.get(markerbyte), "-1");

                                marker = _markers.get(markerbyte);
                                marker_list = _marker_size.containsKey(marker) ? _marker_size.get(marker) : new ArrayList<>();
                                marker_list.add(iterator);
                                _marker_size.put(marker, marker_list);

                                break;
                        }

                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    i = i + 2;
                }
                else {
                    buffer.append(String.valueOf(charhex[i])).append(String.valueOf(charhex[i+1]));
                }
            }

            return new Metadata(root, _marker_count, _marker_size, soi_index, eoi_index, charhex.length, file_start);
        }

        /**
         * Iterates back to the nodes parent that has the specified marker.
         *
         * @param iterator Current Data node
         * @param marker Marker to regress to
         * @return
         */
        private Data regressToMarker(Data iterator, String marker) {
            try {
                while (!iterator.getData().contains(marker) && iterator.getParent() != null) {
                    iterator = iterator.getParent();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return iterator;
        }

        private void addEntry(String marker, String unit){
            //Check if marker has a parameter
            if (!unit.equals("-1"))
                marker += unit;

            int count = _marker_count.containsKey(marker) ? _marker_count.get(marker) : 0;
            _marker_count.put(marker, count + 1);
        }

        private final char[] hexArray = "0123456789abcdef".toCharArray();

        private char[] readFileToHex(String path){

            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }

            char[] hexChars = new char[bytes.length * 2];
            for ( int j = 0; j < bytes.length; j++ ) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }

            return hexChars;
        }

    }

    private class Metadata {

        private Data _tree;
        private Map<String, Integer> _marker_count;
        private Map<String, List<Data>> _marker_size;
        private int _soi_index;
        private int _eoi_index;
        private int _file_len;
        private String _file_start;

        public Metadata(Data tree, Map<String, Integer> marker_count, Map<String, List<Data>> marker_size,
                        int soi_index, int eoi_index, int file_len, String file_start){
            _tree = tree;
            _marker_count = marker_count;
            _marker_size = marker_size;
            _eoi_index = eoi_index;
            _soi_index = soi_index;
            _file_len = file_len;
            _file_start = file_start;
        }

        public Data getTree() {
            return _tree;
        }

        public Map<String, Integer> getMarkerCountDict() {
            return _marker_count;
        }

        public Map<String, List<Data>> getMarkerSizeDict() { return _marker_size; }

        public int getFileLen() {
            return _file_len;
        }

        public int getSoiIndex() { return _soi_index; }

        public int getEoiIndex() { return _eoi_index; }

        public String getFileStart() { return _file_start; }
    }

    private class Data {

        private String _data;
        private List<Data> _children;
        private Data _parent;
        private int _depth;

        private Data(String data) {
            this._data = data;
            this._children = new ArrayList<>();
            this._parent = null;
            this._depth = 0;
        }

        private Data(String data, Data parent) {
            this._data = data;
            _children = new ArrayList<>();
            this._parent = parent;
            this._depth = parent.getDepth() + 1;
            parent.addChild(this);


        }

        private Data(String data, Data parent, String tmp) {
            this._data = data;
            _children = new ArrayList<>();
            this._parent = parent;
            this._depth = parent.getDepth() + 1;
        }

        private void addChild(Data child) {
            this._children.add(child);
        }

        //<editor-fold desc="Getters">
        private List<Data> getChildren() {
            return this._children;
        }

        private Data getParent() {
            return this._parent;
        }

        private int getDepth() {
            return _depth;
        }

        private String getData() {
            return this._data;
        }
        //</editor-fold>

        //<editor-fold desc="Setters">
        private void setData(String data) {
            StringBuilder sb = new StringBuilder();
            sb.append(this._data).append(data);
            this._data = sb.toString();
        }
        //</editor-fold>

        private boolean isRoot() {
            return this._depth == 0;
        }

        private boolean isLeaf() {
            return this._children.isEmpty();
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


        //<editor-fold desc="Debugging">
        private StringBuilder sb = new StringBuilder();

        /**
         * Writes the tree to a specific file for visualization.
         * @param path
         */
        public void writeTree(String path) {
            this.sb = new StringBuilder();
            writeTreeRec(this);

            try (PrintWriter out = new PrintWriter(path)){
                out.println(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void writeTreeRec(Data it) {
            this.sb.append(it.indented()).append(it.getData()).append("\n");

            if (it.getChildren().size() != 0) {

                for (Data c : it.getChildren()) {
                    writeTreeRec(c);
                }
            }


        }
    }

    //<editor-fold desc="File handle funtions">
    private String get_File_size() {

        return String.valueOf(_m.getFileLen());
    }

    private String get_File_extention() {

        if (_m.getFileStart().equals("ffd8"))
            return "0";
        return "1";

    }

    private String get_File_marker_num() {

        Map<String, Integer> markers = _m.getMarkerCountDict();
        int sum = 0;
        for (int value : markers.values()){
            sum = sum + value;
        }
        return String.valueOf(sum);
    }
    //</editor-fold>

    //<editor-fold desc="Marker number handle functions">
    private String get_Marker_APP0_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP0") ?
                this._m.getMarkerCountDict().get("APP0") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP1_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP1") ?
                this._m.getMarkerCountDict().get("APP1") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP2_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP2") ?
                this._m.getMarkerCountDict().get("APP2") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP3_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP3") ?
                this._m.getMarkerCountDict().get("APP3") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP4_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP4") ?
                this._m.getMarkerCountDict().get("APP4") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP5_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP5") ?
                this._m.getMarkerCountDict().get("APP5") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP6_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP6") ?
                this._m.getMarkerCountDict().get("APP6") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP7_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP7") ?
                this._m.getMarkerCountDict().get("APP7") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP8_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP8") ?
                this._m.getMarkerCountDict().get("APP8") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP9_num() {

        int count = this._m.getMarkerCountDict().containsKey("APP9") ?
                this._m.getMarkerCountDict().get("APP9") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP10_num() {

        int count = this._m.getMarkerCountDict().containsKey("APPa") ?
                this._m.getMarkerCountDict().get("APPa") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP11_num() {

        int count = this._m.getMarkerCountDict().containsKey("APPb") ?
                this._m.getMarkerCountDict().get("APPb") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP12_num() {

        int count = this._m.getMarkerCountDict().containsKey("APPc") ?
                this._m.getMarkerCountDict().get("APPc") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP13_num() {

        int count = this._m.getMarkerCountDict().containsKey("APPd") ?
                this._m.getMarkerCountDict().get("APPd") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP14_num() {

        int count = this._m.getMarkerCountDict().containsKey("APPe") ?
                this._m.getMarkerCountDict().get("APPe") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_APP15_num() {

        int count = this._m.getMarkerCountDict().containsKey("APPf") ?
                this._m.getMarkerCountDict().get("APPf") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_COM_num() {

        int count = this._m.getMarkerCountDict().containsKey("COM") ?
                this._m.getMarkerCountDict().get("COM") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_DAC_num() {

        int count = this._m.getMarkerCountDict().containsKey("DAC") ?
                this._m.getMarkerCountDict().get("DAC") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_DHP_num() {

        int count = this._m.getMarkerCountDict().containsKey("DHP") ?
                this._m.getMarkerCountDict().get("DHP") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_DHT_num() {

        int count = this._m.getMarkerCountDict().containsKey("DHT") ?
                this._m.getMarkerCountDict().get("DHT") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_DNL_num() {

        int count = this._m.getMarkerCountDict().containsKey("DNL") ?
                this._m.getMarkerCountDict().get("DNL") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_DQT_num() {

        int count = this._m.getMarkerCountDict().containsKey("DQT") ?
                this._m.getMarkerCountDict().get("DQT") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_DRI_num() {

        int count = this._m.getMarkerCountDict().containsKey("DRI") ?
                this._m.getMarkerCountDict().get("DRI") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_EOI_num() {

        int count = this._m.getMarkerCountDict().containsKey("EOI") ?
                this._m.getMarkerCountDict().get("EOI") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_EXP_num() {

        int count = this._m.getMarkerCountDict().containsKey("EXP") ?
                this._m.getMarkerCountDict().get("EXP") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG") ?
                this._m.getMarkerCountDict().get("JPG") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG0_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG0") ?
                this._m.getMarkerCountDict().get("JPG0") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG1_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG1") ?
                this._m.getMarkerCountDict().get("JPG1") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG2_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG2") ?
                this._m.getMarkerCountDict().get("JPG2") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG3_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG3") ?
                this._m.getMarkerCountDict().get("JPG3") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG4_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG4") ?
                this._m.getMarkerCountDict().get("JPG4") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG5_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG5") ?
                this._m.getMarkerCountDict().get("JPG5") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG6_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG6") ?
                this._m.getMarkerCountDict().get("JPG6") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG7_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG7") ?
                this._m.getMarkerCountDict().get("JPG7") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG8_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG8") ?
                this._m.getMarkerCountDict().get("JPG8") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG9_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPG9") ?
                this._m.getMarkerCountDict().get("JPG9") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG10_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPGa") ?
                this._m.getMarkerCountDict().get("JPGa") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG11_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPGb") ?
                this._m.getMarkerCountDict().get("JPGb") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG12_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPGc") ?
                this._m.getMarkerCountDict().get("JPGc") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_JPG13_num() {

        int count = this._m.getMarkerCountDict().containsKey("JPGd") ?
                this._m.getMarkerCountDict().get("JPGd") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST0_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST0") ?
                this._m.getMarkerCountDict().get("RST0") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST1_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST1") ?
                this._m.getMarkerCountDict().get("RST1") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST2_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST2") ?
                this._m.getMarkerCountDict().get("RST2") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST3_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST3") ?
                this._m.getMarkerCountDict().get("RST3") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST4_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST4") ?
                this._m.getMarkerCountDict().get("RST4") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST5_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST5") ?
                this._m.getMarkerCountDict().get("RST5") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST6_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST6") ?
                this._m.getMarkerCountDict().get("RST6") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_RST7_num() {

        int count = this._m.getMarkerCountDict().containsKey("RST7") ?
                this._m.getMarkerCountDict().get("RST7") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF0_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF0") ?
                this._m.getMarkerCountDict().get("SOF0") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF1_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF1") ?
                this._m.getMarkerCountDict().get("SOF1") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF2_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF2") ?
                this._m.getMarkerCountDict().get("SOF2") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF3_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF3") ?
                this._m.getMarkerCountDict().get("SOF3") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF5_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF5") ?
                this._m.getMarkerCountDict().get("SOF5") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF6_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF6") ?
                this._m.getMarkerCountDict().get("SOF6") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF7_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF7") ?
                this._m.getMarkerCountDict().get("SOF7") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF9_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOF9") ?
                this._m.getMarkerCountDict().get("SOF9") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF10_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOFa") ?
                this._m.getMarkerCountDict().get("SOFa") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF11_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOFb") ?
                this._m.getMarkerCountDict().get("SOFb") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF13_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOFd") ?
                this._m.getMarkerCountDict().get("SOFd") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF14_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOFe") ?
                this._m.getMarkerCountDict().get("SOFe") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOF15_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOFf") ?
                this._m.getMarkerCountDict().get("SOFf") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOI_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOI") ?
                this._m.getMarkerCountDict().get("SOI") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOS_num() {

        int count = this._m.getMarkerCountDict().containsKey("SOS") ?
                this._m.getMarkerCountDict().get("SOS") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_TEM_num() {

        int count = this._m.getMarkerCountDict().containsKey("TEM") ?
                this._m.getMarkerCountDict().get("TEM") : 0;
        return String.valueOf(count);
    }

    private String get_Marker_SOI_EOI_dif() {


        int soi_num = this._m.getMarkerCountDict().containsKey("SOI") ?
                this._m.getMarkerCountDict().get("SOI") : 0;

        int eoi_num = this._m.getMarkerCountDict().containsKey("EOI") ?
                this._m.getMarkerCountDict().get("EOI") : 0;

        int dif = soi_num - eoi_num;

        return String.valueOf(dif);
    }


    private String get_Marker_EOI_illegal_content_after_num() {

//        Data root = this._m.getTree();
//        int count_ill_bytes = 0;
//
//        String last_marker = "";
//
//        for (Data child : root.getChildren()) {
//
//            String[] splits = child.getData().split(" ");
//            if (splits.length > 0){
//                switch (splits[0]) {
//                    case "SOI":
//                        last_marker = "SOI";
//                        break;
//                    case "EOI":
//                        last_marker = "EOI";
//                        break;
//                    default:
//                        switch (last_marker) {
//                            case "SOI":
//                                break;
//
//                            default:
//                                count_ill_bytes += get_illigal_content_rec(child);
//                                break;
//
//                        }
//                        break;
//                }
//
//
//            }
//        }
//
//        return String.valueOf(count_ill_bytes);

        return String.valueOf(_m.getSoiIndex() + (_m.getFileLen() - _m.getEoiIndex()));
    }

    private int get_illigal_content_rec(Data node) {

        String[] splits = node.getData().split(" ");

        int count_ill_bytes = 0;
        for (int i = 0; i < splits.length; i++) {
            count_ill_bytes += splits[i].length();
        }

        for (Data child : node.getChildren()) {
            count_ill_bytes += get_illigal_content_rec(child);
        }

        return count_ill_bytes;
    }
    //</editor-fold>

    //<editor-fold desc="Marker size handle functions">
    private String get_Marker_APP0_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP0_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP0_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP1_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP1") ? marker_size.get("APP1") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP1_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP1") ? marker_size.get("APP1") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP1_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP1") ? marker_size.get("APP1") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP2_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP2") ? marker_size.get("APP2") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP2_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP2") ? marker_size.get("APP2") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP2_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP2") ? marker_size.get("APP2") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP3_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP3") ? marker_size.get("APP3") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP3_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP3") ? marker_size.get("APP3") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP3_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP3") ? marker_size.get("APP3") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP4_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP4") ? marker_size.get("APP4") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP4_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP4") ? marker_size.get("APP4") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP4_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP4") ? marker_size.get("APP4") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP5_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP5") ? marker_size.get("APP5") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP5_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP5") ? marker_size.get("APP5") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP5_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP5") ? marker_size.get("APP5") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP6_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP6") ? marker_size.get("APP6") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP6_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP6") ? marker_size.get("APP6") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP6_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP6") ? marker_size.get("APP6") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP7_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP7") ? marker_size.get("APP7") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP7_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP7") ? marker_size.get("APP7") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP7_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP7") ? marker_size.get("APP7") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP8_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP8") ? marker_size.get("APP8") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP8_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP8") ? marker_size.get("APP8") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP8_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP8") ? marker_size.get("APP8") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP9_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP9") ? marker_size.get("APP9") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP9_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP9") ? marker_size.get("APP9") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP9_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP9") ? marker_size.get("APP9") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP10_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPa") ? marker_size.get("APP0") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP10_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPa") ? marker_size.get("APPa") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP10_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPa") ? marker_size.get("APPa") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP11_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPb") ? marker_size.get("APPb") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP11_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPb") ? marker_size.get("APPb") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP11_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPb") ? marker_size.get("APPb") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP12_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPc") ? marker_size.get("APPc") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP12_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPc") ? marker_size.get("APPc") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP12_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPc") ? marker_size.get("APPc") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP13_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPd") ? marker_size.get("APPd") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP13_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPd") ? marker_size.get("APPd") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP13_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPd") ? marker_size.get("APPd") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP14_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPe") ? marker_size.get("APPe") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP14_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPe") ? marker_size.get("APPe") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP14_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPe") ? marker_size.get("APPe") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_APP15_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPf") ? marker_size.get("APPf") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_APP15_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPf") ? marker_size.get("APPf") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP15_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APPf") ? marker_size.get("APPf") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_COM_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("COM") ? marker_size.get("COM") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_COM_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("COM") ? marker_size.get("COM") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_COM_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("COM") ? marker_size.get("COM") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_DAC_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DAC") ? marker_size.get("DAC") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_DAC_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DAC") ? marker_size.get("DAC") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_DAC_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DAC") ? marker_size.get("DAC") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_DHP_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DHP") ? marker_size.get("DHP") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_DHP_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DHP") ? marker_size.get("DHP") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_DHP_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DHP") ? marker_size.get("DHP") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_DHT_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DHT") ? marker_size.get("DHT") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_DHT_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DHT") ? marker_size.get("DHT") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_DHT_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DHT") ? marker_size.get("DHT") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }
    private String get_Marker_DNL_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DNL") ? marker_size.get("DNL") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_DNL_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DNL") ? marker_size.get("DNL") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_DNL_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DNL") ? marker_size.get("DNL") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_DQT_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DQT") ? marker_size.get("DQT") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_DQT_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DQT") ? marker_size.get("DQT") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_DQT_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DQT") ? marker_size.get("DQT") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_DRI_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DRI") ? marker_size.get("DRI") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_DRI_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DRI") ? marker_size.get("DRI") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_DRI_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("DRI") ? marker_size.get("DRI") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_EXP_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("EXP") ? marker_size.get("EXP") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_EXP_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("EXP") ? marker_size.get("EXP") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_EXP_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("EXP") ? marker_size.get("EXP") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG") ? marker_size.get("JPG") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG") ? marker_size.get("JPG") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG") ? marker_size.get("JPG") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG0_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG0") ? marker_size.get("JPG0") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG0_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG0") ? marker_size.get("JPG0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG0_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG0") ? marker_size.get("JPG0") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG1_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG1") ? marker_size.get("JPG1") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG1_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG1") ? marker_size.get("JPG1") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG1_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG1") ? marker_size.get("JPG1") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG2_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG2") ? marker_size.get("JPG2") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG2_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG2") ? marker_size.get("JPG2") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG2_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG2") ? marker_size.get("JPG2") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG3_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG3") ? marker_size.get("JPG3") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG3_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG3") ? marker_size.get("JPG3") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG3_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG3") ? marker_size.get("JPG3") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG4_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG4") ? marker_size.get("JPG4") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG4_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG4") ? marker_size.get("JPG4") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG4_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG4") ? marker_size.get("JPG4") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG5_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG5") ? marker_size.get("JPG5") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG5_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG5") ? marker_size.get("JPG5") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG5_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG5") ? marker_size.get("JPG5") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG6_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG6") ? marker_size.get("JPG6") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG6_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG6") ? marker_size.get("JPG6") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG6_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG6") ? marker_size.get("JPG6") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG7_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG7") ? marker_size.get("JPG7") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG7_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG7") ? marker_size.get("JPG7") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG7_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG7") ? marker_size.get("JPG7") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG8_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG8") ? marker_size.get("JPG8") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG8_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG8") ? marker_size.get("JPG8") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG8_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG8") ? marker_size.get("JPG8") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG9_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG9") ? marker_size.get("JPG9") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG9_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG9") ? marker_size.get("JPG9") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG9_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPG9") ? marker_size.get("JPG9") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG10_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGa") ? marker_size.get("JPGa") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG10_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGa") ? marker_size.get("JPGa") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG10_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGa") ? marker_size.get("JPGa") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG11_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGb") ? marker_size.get("JPGb") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG11_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGb") ? marker_size.get("JPGb") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG11_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGb") ? marker_size.get("JPGb") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG12_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGc") ? marker_size.get("JPGc") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG12_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGc") ? marker_size.get("JPGc") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG12_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGc") ? marker_size.get("JPGc") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_JPG13_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGd") ? marker_size.get("JPGd") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_JPG13_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGd") ? marker_size.get("JPGd") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_JPG13_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("JPGd") ? marker_size.get("JPGd") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF0_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF0") ? marker_size.get("SOF0") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF0_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF0") ? marker_size.get("SOF0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF0_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF0") ? marker_size.get("SOF0") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF1_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF1") ? marker_size.get("SOF1") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF1_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF1") ? marker_size.get("SOF1") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF1_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF1") ? marker_size.get("SOF1") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF2_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF2") ? marker_size.get("SOF2") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF2_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF2") ? marker_size.get("SOF2") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF2_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF2") ? marker_size.get("SOF2") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF3_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF3") ? marker_size.get("SOF3") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF3_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF3") ? marker_size.get("SOF3") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF3_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF3") ? marker_size.get("SOF3") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF5_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF5") ? marker_size.get("SOF5") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF5_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF5") ? marker_size.get("SOF5") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF5_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF5") ? marker_size.get("SOF5") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF6_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF6") ? marker_size.get("SOF6") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF6_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF6") ? marker_size.get("SOF6") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF6_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF6") ? marker_size.get("SOF6") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF7_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF7") ? marker_size.get("SOF7") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF7_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF7") ? marker_size.get("SOF7") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF7_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF7") ? marker_size.get("SOF7") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF9_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF9") ? marker_size.get("SOF9") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF9_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF9") ? marker_size.get("SOF9") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF9_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOF9") ? marker_size.get("SOF9") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF10_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFa") ? marker_size.get("SOFa") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF10_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFa") ? marker_size.get("SOFa") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF10_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFa") ? marker_size.get("SOFa") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF11_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFb") ? marker_size.get("SOFb") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF11_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFb") ? marker_size.get("SOFb") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF11_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFb") ? marker_size.get("SOFb") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF13_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFd") ? marker_size.get("SOFd") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF13_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFd") ? marker_size.get("SOFd") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF13_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFd") ? marker_size.get("SOFd") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF14_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFe") ? marker_size.get("SOFe") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF14_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFe") ? marker_size.get("SOFe") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF14_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFe") ? marker_size.get("SOFe") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOF15_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFf") ? marker_size.get("SOFf") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOF15_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFf") ? marker_size.get("SOFf") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOF15_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOFf") ? marker_size.get("SOFf") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }

    private String get_Marker_SOS_size_max() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOS") ? marker_size.get("SOS") : null;

        int max_size = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr > max_size) {
                        max_size = curr;
                    }
                }

            }
        }

        return String.valueOf(max_size);

    }

    private String get_Marker_SOS_size_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOS") ? marker_size.get("SOS") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (curr < 2) {
                        incorrect_num += 1;
                    }
                } else {
                    incorrect_num += 1;
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_SOS_size_declared_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("SOS") ? marker_size.get("SOS") : null;

        int declared_incorrect_num = 0;

        if (data_list != null) {
            String splits[];
            
            int curr;
            for (Data data : data_list) {
                splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 3) {
                    curr = Integer.parseInt(splits[1].substring(0, 4), 16);
                    if (splits[1].length() / 2 != curr) {
                        declared_incorrect_num += 1;
                    }
                } else {
                    declared_incorrect_num += 1;
                }
            }
        }

        return String.valueOf(declared_incorrect_num);
    }
    //</editor-fold>

    //<editor-fold desc="Marker parameter handle function">
    private String get_Marker_APP_major_revision_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            for (Data data : data_list) {
                String[] splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 18) {
                    String major = splits[1].substring(13, 15);
                    if (Integer.parseInt(major, 16) != 1 ) {
                        incorrect_num += 1;
                    }
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP_minor_revision_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            for (Data data : data_list) {
                String[] splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 18) {
                    String minor = splits[1].substring(15, 17);
                    if (Integer.parseInt(minor, 16) > 2) {
                        incorrect_num += 1;
                    }
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP_unit_density_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            for (Data data : data_list) {
                String[] splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 20) {
                    String minor = splits[1].substring(17, 19);
                    if (Integer.parseInt(minor, 16) > 2) {
                        incorrect_num += 1;
                    }
                }
            }
        }

        return String.valueOf(incorrect_num);

    }

    private String get_Marker_APP_x_density_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            for (Data data : data_list) {
                String[] splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 23) {
                    String minor = splits[1].substring(19, 23);
                    if (Integer.parseInt(minor, 16) != 0) {
                        incorrect_num += 1;
                    }
                }
            }
        }

        return String.valueOf(incorrect_num);
    }

    private String get_Marker_APP_y_density_incorrect_num() {

        Map<String, List<Data>> marker_size = _m.getMarkerSizeDict();
        List<Data> data_list = marker_size.containsKey("APP0") ? marker_size.get("APP0") : null;

        int incorrect_num = 0;

        if (data_list != null) {
            for (Data data : data_list) {
                String[] splits = data.getData().split(" ");
                if (splits.length > 1 && splits[1].length() > 27) {
                    String minor = splits[1].substring(23, 27);
                    if (Integer.parseInt(minor, 16) != 0) {
                        incorrect_num += 1;
                    }
                }
            }
        }

        return String.valueOf(incorrect_num);
    }
    //</editor-fold>
/*
    private String get_Marker_DAC_tc_incorrect_num
    private String get_Marker_DAC_ta_incorrect_num
    private String get_Marker_DHT_tc_incorrect_num
    private String get_Marker_DHT_th_incorrect_num
    private String get_Marker_DQT_pq_incorrect_num
    private String get_Marker_DQT_tq_incorrect_num
    private String get_Marker_DQT_qk_DHT_tc_incorrect_bond_num
    private String get_Marker_SOF_p_diff_num
    private String get_Marker_SOF_nf_incorrect_num
    private String get_Marker_SOF_hi_incorrect_num
    private String get_Marker_SOF_vi_incorrect_num
    private String get_Marker_SOS_ns_incorrect_num
    private String get_Marker_SOS_td_incorrect_num
    private String get_Marker_SOS_ta_incorrect_num
    private String get_Marker_SOS_ss_se_incorrect_bond_num
    */


    private enum Feature {

        /**************************File handle****************************************/
        File_size,
        File_extension,
        File_marker_num,

        /**************************Count of markers************************/
        Marker_APP0_num,
        Marker_APP1_num,
        Marker_APP2_num,
        Marker_APP3_num,
        Marker_APP4_num,
        Marker_APP5_num,
        Marker_APP6_num,
        Marker_APP7_num,
        Marker_APP8_num,
        Marker_APP9_num,
        Marker_APP10_num,
        Marker_APP11_num,
        Marker_APP12_num,
        Marker_APP13_num,
        Marker_APP14_num,
        Marker_APP15_num,
        Marker_COM_num,
        Marker_DAC_num,
        Marker_DHP_num,
        Marker_DHT_num,
        Marker_DNL_num,
        Marker_DQT_num,
        Marker_DRI_num,
        Marker_EOI_num,
        Marker_EXP_num,
        Marker_JPG_num,
        Marker_JPG0_num,
        Marker_JPG1_num,
        Marker_JPG2_num,
        Marker_JPG3_num,
        Marker_JPG4_num,
        Marker_JPG5_num,
        Marker_JPG6_num,
        Marker_JPG7_num,
        Marker_JPG8_num,
        Marker_JPG9_num,
        Marker_JPG10_num,
        Marker_JPG11_num,
        Marker_JPG12_num,
        Marker_JPG13_num,
        Marker_RST0_num,
        Marker_RST1_num,
        Marker_RST2_num,
        Marker_RST3_num,
        Marker_RST4_num,
        Marker_RST5_num,
        Marker_RST6_num,
        Marker_RST7_num,
        Marker_SOF0_num,
        Marker_SOF1_num,
        Marker_SOF2_num,
        Marker_SOF3_num,
        Marker_SOF5_num,
        Marker_SOF6_num,
        Marker_SOF7_num,
        Marker_SOF9_num,
        Marker_SOF10_num,
        Marker_SOF11_num,
        Marker_SOF13_num,
        Marker_SOF14_num,
        Marker_SOF15_num,
        Marker_SOI_num,
        Marker_SOS_num,
        Marker_TEM_num,
        /*****************************************************************/


        /***************************SOI and EOI handle********************/
        Marker_SOI_EOI_dif,
        //Marker_SOI_EOI_nested_num,
        Marker_EOI_illegal_content_after_num,
        /*****************************************************************/


        /**************************Marker correctness**************************************/
        Marker_APP0_size_max,
        Marker_APP0_size_incorrect_num,
        Marker_APP0_size_declared_incorrect_num,
        Marker_APP1_size_max,
        Marker_APP1_size_incorrect_num,
        Marker_APP1_size_declared_incorrect_num,
        Marker_APP2_size_max,
        Marker_APP2_size_incorrect_num,
        Marker_APP2_size_declared_incorrect_num,
        Marker_APP3_size_max,
        Marker_APP3_size_incorrect_num,
        Marker_APP3_size_declared_incorrect_num,
        Marker_APP4_size_max,
        Marker_APP4_size_incorrect_num,
        Marker_APP4_size_declared_incorrect_num,
        Marker_APP5_size_max,
        Marker_APP5_size_incorrect_num,
        Marker_APP5_size_declared_incorrect_num,
        Marker_APP6_size_max,
        Marker_APP6_size_incorrect_num,
        Marker_APP6_size_declared_incorrect_num,
        Marker_APP7_size_max,
        Marker_APP7_size_incorrect_num,
        Marker_APP7_size_declared_incorrect_num,
        Marker_APP8_size_max,
        Marker_APP8_size_incorrect_num,
        Marker_APP8_size_declared_incorrect_num,
        Marker_APP9_size_max,
        Marker_APP9_size_incorrect_num,
        Marker_APP9_size_declared_incorrect_num,
        Marker_APP10_size_max,
        Marker_APP10_size_incorrect_num,
        Marker_APP10_size_declared_incorrect_num,
        Marker_APP11_size_max,
        Marker_APP11_size_incorrect_num,
        Marker_APP11_size_declared_incorrect_num,
        Marker_APP12_size_max,
        Marker_APP12_size_incorrect_num,
        Marker_APP12_size_declared_incorrect_num,
        Marker_APP13_size_max,
        Marker_APP13_size_incorrect_num,
        Marker_APP13_size_declared_incorrect_num,
        Marker_APP14_size_max,
        Marker_APP14_size_incorrect_num,
        Marker_APP14_size_declared_incorrect_num,
        Marker_APP15_size_max,
        Marker_APP15_size_incorrect_num,
        Marker_APP15_size_declared_incorrect_num,
        Marker_COM_size_max,
        Marker_COM_size_incorrect_num,
        Marker_COM_size_declared_incorrect_num,
        Marker_DAC_size_max,
        Marker_DAC_size_incorrect_num,
        Marker_DAC_size_declared_incorrect_num,
        Marker_DHP_size_max,
        Marker_DHP_size_incorrect_num,
        Marker_DHP_size_declared_incorrect_num,
        Marker_DHT_size_max,
        Marker_DHT_size_incorrect_num,
        Marker_DHT_size_declared_incorrect_num,
        Marker_DNL_size_max,
        Marker_DNL_size_incorrect_num,
        Marker_DNL_size_declared_incorrect_num,
        Marker_DQT_size_max,
        Marker_DQT_size_incorrect_num,
        Marker_DQT_size_declared_incorrect_num,
        Marker_DRI_size_max,
        Marker_DRI_size_incorrect_num,
        Marker_DRI_size_declared_incorrect_num,
        Marker_EXP_size_max,
        Marker_EXP_size_incorrect_num,
        Marker_EXP_size_declared_incorrect_num,
        Marker_JPG_size_max,
        Marker_JPG_size_incorrect_num,
        Marker_JPG_size_declared_incorrect_num,
        Marker_JPG0_size_max,
        Marker_JPG0_size_incorrect_num,
        Marker_JPG0_size_declared_incorrect_num,
        Marker_JPG1_size_max,
        Marker_JPG1_size_incorrect_num,
        Marker_JPG1_size_declared_incorrect_num,
        Marker_JPG2_size_max,
        Marker_JPG2_size_incorrect_num,
        Marker_JPG2_size_declared_incorrect_num,
        Marker_JPG3_size_max,
        Marker_JPG3_size_incorrect_num,
        Marker_JPG3_size_declared_incorrect_num,
        Marker_JPG4_size_max,
        Marker_JPG4_size_incorrect_num,
        Marker_JPG4_size_declared_incorrect_num,
        Marker_JPG5_size_max,
        Marker_JPG5_size_incorrect_num,
        Marker_JPG5_size_declared_incorrect_num,
        Marker_JPG6_size_max,
        Marker_JPG6_size_incorrect_num,
        Marker_JPG6_size_declared_incorrect_num,
        Marker_JPG7_size_max,
        Marker_JPG7_size_incorrect_num,
        Marker_JPG7_size_declared_incorrect_num,
        Marker_JPG8_size_max,
        Marker_JPG8_size_incorrect_num,
        Marker_JPG8_size_declared_incorrect_num,
        Marker_JPG9_size_max,
        Marker_JPG9_size_incorrect_num,
        Marker_JPG9_size_declared_incorrect_num,
        Marker_JPG10_size_max,
        Marker_JPG10_size_incorrect_num,
        Marker_JPG10_size_declared_incorrect_num,
        Marker_JPG11_size_max,
        Marker_JPG11_size_incorrect_num,
        Marker_JPG11_size_declared_incorrect_num,
        Marker_JPG12_size_max,
        Marker_JPG12_size_incorrect_num,
        Marker_JPG12_size_declared_incorrect_num,
        Marker_JPG13_size_max,
        Marker_JPG13_size_incorrect_num,
        Marker_JPG13_size_declared_incorrect_num,
        Marker_SOF0_size_max,
        Marker_SOF0_size_incorrect_num,
        Marker_SOF0_size_declared_incorrect_num,
        Marker_SOF1_size_max,
        Marker_SOF1_size_incorrect_num,
        Marker_SOF1_size_declared_incorrect_num,
        Marker_SOF2_size_max,
        Marker_SOF2_size_incorrect_num,
        Marker_SOF2_size_declared_incorrect_num,
        Marker_SOF3_size_max,
        Marker_SOF3_size_incorrect_num,
        Marker_SOF3_size_declared_incorrect_num,
        Marker_SOF5_size_max,
        Marker_SOF5_size_incorrect_num,
        Marker_SOF5_size_declared_incorrect_num,
        Marker_SOF6_size_max,
        Marker_SOF6_size_incorrect_num,
        Marker_SOF6_size_declared_incorrect_num,
        Marker_SOF7_size_max,
        Marker_SOF7_size_incorrect_num,
        Marker_SOF7_size_declared_incorrect_num,
        Marker_SOF9_size_max,
        Marker_SOF9_size_incorrect_num,
        Marker_SOF9_size_declared_incorrect_num,
        Marker_SOF10_size_max,
        Marker_SOF10_size_incorrect_num,
        Marker_SOF10_size_declared_incorrect_num,
        Marker_SOF11_size_max,
        Marker_SOF11_size_incorrect_num,
        Marker_SOF11_size_declared_incorrect_num,
        Marker_SOF13_size_max,
        Marker_SOF13_size_incorrect_num,
        Marker_SOF13_size_declared_incorrect_num,
        Marker_SOF14_size_max,
        Marker_SOF14_size_incorrect_num,
        Marker_SOF14_size_declared_incorrect_num,
        Marker_SOF15_size_max,
        Marker_SOF15_size_incorrect_num,
        Marker_SOF15_size_declared_incorrect_num,
        Marker_SOS_size_max,
        Marker_SOS_size_incorrect_num,
        Marker_SOS_size_declared_incorrect_num,
        Marker_APP_major_revision_incorrect_num,
        Marker_APP_minor_revision_incorrect_num,
        Marker_APP_unit_density_incorrect_num,
        Marker_APP_x_density_incorrect_num,
        Marker_APP_y_density_incorrect_num,
        /*Marker_DAC_tc_incorrect_num,
        Marker_DAC_ta_incorrect_num,
        Marker_DHT_tc_incorrect_num,
        Marker_DHT_th_incorrect_num,
        Marker_DQT_pq_incorrect_num,
        Marker_DQT_tq_incorrect_num,
        Marker_DQT_qk_DHT_tc_incorrect_bond_num,
        Marker_SOF_p_diff_num,
        Marker_SOF_nf_incorrect_num,
        Marker_SOF_hi_incorrect_num,
        Marker_SOF_vi_incorrect_num,
        Marker_SOS_ns_incorrect_num,
        Marker_SOS_td_incorrect_num,
        Marker_SOS_ta_incorrect_num,
        Marker_SOS_ss_se_incorrect_bond_num,*/
        /**************************************************************/
    }

}
