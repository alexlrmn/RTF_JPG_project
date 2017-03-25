package feature_extraction;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 3/23/2017.
 */
public class JPGParser implements IParser{

    private static final Map<String, String> _markers = createMap();
    private static Map<String, String> createMap()
    {
        Map<String,String> markers = new HashMap<String,String>();
        markers.put("C4", "DHT");
//        markers.put("C8", "JPG");
        markers.put("CC", "DAC");
        markers.put("D8", "SOI");
        markers.put("D9", "EOI");
        markers.put("DA", "SOS");
        markers.put("DB", "DQT");
        markers.put("DC", "DNL");
        markers.put("DD", "DRI");
        markers.put("DE", "DHP");
        markers.put("DF", "EXP");
        markers.put("FE", "COM");
        markers.put("C",  "SOF");
        for (int i = 0; i < 8; i++)
            markers.put("D" + hexArray[i], "RST" + hexArray[i]);
//        markers.put("D",  "RST");
        for (int i = 0; i < hexArray.length; i++)
            markers.put("E" + hexArray[i], "APP" + hexArray[i]);
//        markers.put("E",  "APP");
//        markers.put("F",  "JPG");

        markers.put("01", "TEM");
        return markers;
    }

//    enum Marker {
//        E,
//        E0, E1, E2, E3, E4, E5, E6, E7, E8, E9, EA, EB, EC, ED, EE, EF,                                //APP_n
//        FE,                                                                                            //COM
//        CC,                                                                                            //DAC
//        DE,                                                                                            //DHP
//        C4,                                                                                            //DHT
//        DC,                                                                                            //DNL
//        DB,                                                                                            //DQT
//        DD,                                                                                            //DRI
//        D9,                                                                                            //EOI
//        DF,                                                                                            //EXP
//        C8,                                                                                            //JPG
//        F,
//        F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, FA, FB, FC, FD,                                        //JPG_n
//        D,
//        DO, D1, D2, D3, D4, D5, D6, D7,                                                                //RST_m
//        C,
//        C0, C1, C2, C3, C5, C6, C7, C9, CA, CB, CD, CE, CF,                                            //SOF_n
//        D8,                                                                                            //SOI
//        DA,                                                                                            //SOS
//        FF01                                                                                           //TEM
//    }

    private String readFile(String path) {
        String cont;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedImage bi = ImageIO.read(new File(path));
            WritableRaster wr = bi.getRaster();
            DataBufferByte data = (DataBufferByte) wr.getDataBuffer();
            byte[][] bData = data.getBankData();
            for (int i = 0; i < bData.length; i++) {
                sb.append(bytesToHex(bData[i]));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        cont = sb.toString();
//        System.out.println(cont);
        return cont;
    }

    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private  String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String[] hexToArray(String hex) {
        List<String> hexArray = new ArrayList<String>();
        for (int i = 0; i < hex.length() - 2; i = i + 2){
            hexArray.add(hex.charAt(i) + "" + hex.charAt(i + 1));
        }

        return (String[]) hexArray.toArray();
    }

    public void Parse(String path) {

        String file_cont = readFile(path);
        String[] hexArray = hexToArray(file_cont);
        String last_marker = "";
        int curr_index = 0;
        boolean EOF = false;
        StringBuilder buffer = new StringBuilder();

        FeatureTreeNode root = new FeatureTreeNode("/image");
        FeatureTreeNode iterator = root;
        FeatureTreeNode parent = null;
        for (int i = 0; i < hexArray.length - 1 && !EOF; i++){
            if (hexArray[i] == "FF"){

                iterator.setData(buffer.toString());
                buffer = new StringBuilder();

                switch (hexArray[i+1]){
                    case "D8":
                        parent = iterator;
                        iterator = new FeatureTreeNode("/" + this._markers.get("D8"), parent);
                        parent.addChild(iterator);
                        break;
                    case "D9":
                        iterator.setData(buffer.toString());
                        curr_index = i;
                        EOF = true;
                        break;
                    case "DA":

                        break;


                    default:
                        break;
                }
            }

            else {

                buffer.append(hexArray[i]);
            }
        }

    }

//    public static void main(String[] args) {
//        String path = "D:\\temp\\jpg.jpg";
//        readFile(path);
//    }
}
