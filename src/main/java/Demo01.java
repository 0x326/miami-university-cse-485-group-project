import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory()
            .addPoolLocator("localhost", 10334)
            .create();
        Region<Object, Object> region = cache
            .<Object, Object>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("stockValues");
    }
}
