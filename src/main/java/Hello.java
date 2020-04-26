import java.util.Map;
import java.util.HashMap;
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
        Region<Integer, Company> region = cache
            .<Integer, Company>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("companies");

        /*
        region.put("1", "Hello");
        region.put("2", "World");
        region.get("1");  // No network request
        region.put("2", "World");
        */

        //region.put("3", new Person("John Doe"));
        //region.get("3");
        //region.put("3", "{\"name\": 5}");

        Map<Integer, Company> companies = createCompanyData();
        region.putAll(companies);

        // Identify your query string.
        String queryString = "SELECT * FROM /companies c where c.name";

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults<Company> results = (SelectResults<Company>)query.execute();
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

    public static Map<Integer, Company> createCompanyData() {
        String[] names = ("Alphabet Inc.,Apple Inc.,Amazon.com Inc.,Facebook Inc.,Microsoft Corporation," +
            "Johnson & Johnson").split(",");
        String[] stockAbr = "GOOGL, AAPL, AMZN, FB, MSFT, JNJ".split(",");
        String[] hqLocSt = "CA,CA,WA,CA,WA,NJ".split(",");
        Map<Integer, Company> companies = new HashMap<Integer, Company>();
        for (int i = 0; i < names.length; i++) {
            Company value = new Company(names[i],stockAbr[i],hqLocSt[i]);
            companies.put(i, value);
        }
        return companies;
    }
}
