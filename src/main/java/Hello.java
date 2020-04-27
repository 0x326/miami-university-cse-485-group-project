import java.util.Map;
import java.util.HashMap;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.SelectResults;

public class Hello {

    public static void main(String[] args) throws Exception {

        ClientCache cache = new ClientCacheFactory()
            .addPoolLocator("localhost", 10334)
            .create();
        Region<UUID, Company> companyRegion = cache
            .<UUID, Company>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("companies");
        Region<UUID, Stock> stockRegion = cache
            .<UUID, Stock>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("stocks");

        // populate regions
        companyRegion.clear();
        companyRegion.putAll(createCompanyData());
        stockRegion.clear();
        stockRegion.putAll(createStockData());

        /*----------------------------/
         *  WRITE YOUR QUERY HERE:   /
         *-------------------------*/
        //String queryString = "SELECT c.name FROM /companies c where c.hqLocationState = 'NJ'";
        String queryString1 = "SELECT * FROM /stocks";
        String queryString2 = "SELECT s.name, s.\"date\" FROM /stocks s WHERE s.price > 1000";

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        // Execute query
        SelectResults<Stock> results1 =
            (SelectResults<Stock>) queryService.newQuery(queryString1).execute();

        // Print results
        System.out.println("  -- Records returned: " + results1.size() + " --");
        for (Stock result : results1) {
            // System.out.println(result.getPrice());
            // System.out.printf("Class: %s%n", result.getClass().getName());
            System.out.printf("Name %s at $%.2f (as of %s)%n",
                result.getName(), result.getPrice(), result.getDate());
        }

        // Execute query
        SelectResults<Struct> results2 =
            (SelectResults<Struct>) queryService.newQuery(queryString2).execute();

        // Print results
        System.out.println("  -- Records returned: " + results2.size() + " --");
        for (Struct result : results2) {
            // System.out.println(result.getPrice());
            // System.out.printf("Class: %s%n", result.getClass().getName());
            System.out.printf("Name %s at %s%n", result.get("name"), result.get("date"));
        }

        cache.close();
    }

    // get a map of company data
    public static Map<UUID, Company> createCompanyData() {
        String[] names = {
            "Alphabet Inc.",
            "Apple Inc.",
            "Amazon.com Inc.",
            "Facebook Inc.",
            "Microsoft Corporation",
            "Johnson & Johnson"
        };
        String[] stockAbr = {
            "GOOGL",
            "AAPL",
            "AMZN",
            "FB",
            "MSFT",
            "JNJ"
        };
        String[] hqLocSt = {
            "CA",
            "CA",
            "WA",
            "CA",
            "WA",
            "NJ"
        };
        Map<UUID, Company> companies = new HashMap<UUID, Company>();
        for (int i = 0; i < names.length; i++) {
            Company value = new Company(names[i], stockAbr[i], hqLocSt[i]);
            companies.put(UUID.randomUUID(), value);
        }
        return companies;
    }

    // TODO (John): create a map returning method for populating the Stock region
}
