import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;

public class Demo05 {
    public static void main(String[] args) throws Exception {
        ClientCache cache = new ClientCacheFactory()
            .addPoolLocator("localhost", 10334)
            .create();
        Region<String, Company> region = cache
            .<String, Company>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("companyInfo");

        // Populate companyInfo
        region.put("AAPL", new Company("Apple, Inc.", "Cupertino, CA"));
        region.put("GOOGL", new Company("Google LLC", "Mountain View, CA"));
        region.put("MSFT", new Company("Microsoft Corp.", "Albuquerque, NM"));

        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery("SELECT * " +
            "FROM /stockValues stockValue, /companyInfo companyInfo " +
            "WHERE stockValue.name = companyInfo.name");

        // Execute Query locally. Returns results set.
        SelectResults<Object> results = (SelectResults<Object>) query.execute();
        for (Object result : results) {
            System.out.printf("Class: %s%n", result.getClass().getName());

            // String name = result.getName();
            // double value = result.getValue();
            // System.out.printf("%s: %.2f%n", name, value);
        }

        cache.close();
    }
}
