import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;

public class Demo04 {
    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory()
            .addPoolLocator("localhost", 10334)
            .create();
        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery("SELECT * FROM /stockValues");

        // Execute Query locally. Returns results set.
        SelectResults<Stock> results = (SelectResults<Stock>) query.execute();
        for (Stock result : results) {
            String name = result.getName();
            double value = result.getValue();
            System.out.printf("%s: %.2f%n", name, value);
        }

        cache.close();
    }
}
