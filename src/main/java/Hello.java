import java.util.Map;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.management.configuration.Pdx;
import org.apache.geode.pdx.*;

public class Hello {

    public static void main(String[] args) throws Exception {

        ClientCache cache = new ClientCacheFactory()
            .addPoolServer("localhost", 40404)
            .create();
        Region<String, String> region = cache
            .<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
            .create("hello");

        region.put("1", "Hello");
        region.put("2", "World");

        // Identify your query string.
        String queryString = "SELECT * FROM /hello";

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults results = (SelectResults)query.execute();
        for (Object o : results) { System.out.println(o.toString()); }

        cache.close();



                /*
        for (Map.Entry<String, String>  entry : region.entrySet()) {
            System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
        }
        cache.close();*/
    }
}
