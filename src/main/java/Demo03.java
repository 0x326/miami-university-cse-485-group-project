import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;

public class Demo03 {
    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory()
            .addPoolLocator("localhost", 10334)
            .create();
        Region<String, Stock> region = cache
            .<String, Stock>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("stockValues");

        // Put values into region
        region.put("AAPL", new Stock("Apple, Inc.", 274.44));
        region.put("GOOGL", new Stock("Google LLC", 1245.40));
        region.put("MSFT", new Stock("Microsoft Corp.", 170.53));

        // Get values from region
        Stock appleStock = region.get("AAPL");
        System.out.printf("AAPL: %s%n", appleStock);

        // Get a non-existent value (will be null)
        Stock facebookStock = region.get("FB");
        System.out.printf("FB: %s%n", facebookStock);

        cache.close();
    }
}
