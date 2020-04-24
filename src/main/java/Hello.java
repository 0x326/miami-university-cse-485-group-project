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
            .addPoolLocator("localhost", 10334)
            .create();
        Region<String, String> region = cache
            .<String, String>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("hello");

        region.put("1", "Hello");
        region.put("2", "World");
        region.get("1");  // No network request
        region.put("2", "World");
        //region.put("3", new Person("John Doe"));
        //region.get("3");
        //region.put("3", "{\"name\": 5}");

        // Identify your query string.
        String queryString = "SELECT * FROM /hello";

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults results = (SelectResults)query.execute();
        for (Object result : results) {
            if (result instanceof String) {
                String result2 = (String) result;
            }
            System.out.printf("Class: %s%n", result.getClass().getName());
            System.out.printf(".toString(): %s%n", result.toString());
        }

        cache.close();



                /*
        for (Map.Entry<String, String>  entry : region.entrySet()) {
            System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
        }
        cache.close();*/
    }
}
