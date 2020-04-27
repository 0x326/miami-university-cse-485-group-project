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
        Region<Integer, Company> region = cache
            .<Integer, Company>createClientRegionFactory(ClientRegionShortcut.PROXY)
            .create("companies");
        // TODO (John): create a region for Stock

        // populate regions
        Map<Integer, Company> companies = createCompanyData();
        region.putAll(companies);

        /*----------------------------/
         *  WRITE YOUR QUERY HERE:   /
         *-------------------------*/
        String queryString = "SELECT c.name FROM /companies c where c.hqLocationState = 'NJ'";

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        // Create the Query Object.
        Query query = queryService.newQuery(queryString);

        // Execute Query locally. Returns results set.
        SelectResults<Company> results = (SelectResults<Company>)query.execute();

        // get the count of the records
        System.out.println("  -- Records returned: " + results.size() + " --");
        // print the results
        for (Object result : results) {
            if (result instanceof String) {
                String result2 = (String) result;
            }
            // System.out.printf("Class: %s%n", result.getClass().getName());
            System.out.printf(".toString(): %s%n", result.toString());
        }

        cache.close();
    }

    // get a map of company data
    public static Map<Integer, Company> createCompanyData() {
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
        Map<Integer, Company> companies = new HashMap<Integer, Company>();
        for (int i = 0; i < names.length; i++) {
            Company value = new Company(names[i],stockAbr[i],hqLocSt[i]);
            companies.put(i, value);
        }
        return companies;
    }

    // TODO (John): create a map returning method for populating the Stock region
}
