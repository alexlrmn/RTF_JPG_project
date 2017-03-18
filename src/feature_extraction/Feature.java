package feature_extraction;

/**
 * Created by Alex on 3/17/2017.
 */
public class Feature {
    private String feature_name;
    private String feature_value;

    public Feature() {

    }

    public Feature(String name, String value){
        this.feature_name = name;
        this.feature_value = value;
    }

    public String get_name(){
        return feature_name;
    }

    public String get_value() {
        return feature_value;
    }
}
