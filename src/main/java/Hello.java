import java.net.InetSocketAddress;
import java.util.Set;
import java.util.Map;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;

public class Hello {

    public static void main(String[] args) throws Exception {

        ClientCache cache = new ClientCacheFactory()
            //.addPoolLocator("localhost", 10334)
            .addPoolServer("localhost", 40404)
            .create();
        Region<String, String> region = cache
            .<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
            .create("region1");

        region.put("1", "Hello");
        region.put("2", "World");

        for (Map.Entry<String, String>  entry : region.entrySet()) {
            System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
        }
        cache.close();
    }
}
