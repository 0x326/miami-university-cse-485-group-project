import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;

public class Demo02 {
    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory()
            .addPoolLocator("localhost", 10334)
            .create();
        Region<String, Double> region = cache
            .<String, Double>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("stockValues");

        // Put values into region
        region.put("AAPL", 274.44);
        region.put("GOOGL", 1245.40);
        region.put("MSFT", 170.53);

        // Get values from region
        Double appleStock = region.get("AAPL");
        System.out.printf("AAPL: %s%n", appleStock);

        // Get a non-existent value (will be null)
        Double facebookStock = region.get("FB");
        System.out.printf("FB: %s%n", facebookStock);

        cache.close();
    }
}
