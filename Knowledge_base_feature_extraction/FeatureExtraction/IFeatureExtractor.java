import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 3/17/2017.
 */
public interface IFeatureExtractor<T> {

    Map<String, String> ExtractFeaturesFrequencyFromSingleElement(T element);
    List<String> getHeaders();
    String GetName();
}
