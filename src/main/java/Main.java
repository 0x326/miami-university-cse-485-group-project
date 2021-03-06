import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.sql.Date;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.*;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.Struct;

public class Main {

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

        // Get QueryService from Cache.
        QueryService queryService = cache.getQueryService();

        /*----------------------------/
         *  EXAMPLE QUERY            /
         *-------------------------*/
        // String exQueryString = "SELECT * FROM /stocks";
        //
        // // Execute query
        // SelectResults<Stock> resultsEx =
        //     (SelectResults<Stock>) queryService.newQuery(exQueryString).execute();
        //
        // // Print results
        // System.out.println("  -- Example: Records returned: " + resultsEx.size() + " --");
        // for (Stock result : resultsEx) {
        //     System.out.printf("Name %s at $%.2f (as of %s)%n",
        //         result.getName(), result.getPrice(), result.getLastUpdatedDate());
        // }

        /*----------------------------/
         *  WRITE QUERY #1 HERE      /
         *-------------------------*/
        String queryString1 = "";
        
        // Execute query
        SelectResults<Struct> results1 =
            (SelectResults<Struct>) queryService.newQuery(queryString1).execute();

        // Print results
        System.out.println("  -- Query 1: Records returned: " + results1.size() + " --");
        for (Struct result : results1) {
            System.out.printf("%s at $%s%n", result.get("name"), result.get("price"));
        }

        /*----------------------------/
         *  WRITE QUERY #2 HERE      /
         *-------------------------*/
        String queryString2 = "";

        // Execute query
        SelectResults<Struct> results2 =
            (SelectResults<Struct>) queryService.newQuery(queryString2).execute();

        // Print results
        System.out.println("\n\n  -- Query 2: Records returned: " + results2.size() + " --");
        for (Struct result : results2) {
            System.out.printf("%s at $%s%n", result.get("name"), result.get("price"));
        }

        /*----------------------------/
         *  WRITE QUERY #3 HERE      /
         *-------------------------*/
        String queryString3 = "";

        // Execute query
        SelectResults<Struct> results3 =
            (SelectResults<Struct>) queryService.newQuery(queryString3).execute();

        // Print results
        System.out.println("\n\n  -- Query 3: Records returned: " + results3.size() + " --");
        for (Struct result : results3) {
            System.out.printf("%s averaged at $%s%n", result.get("name"), result.get("avgPrice"));
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

    // get a map of company data
    public static Map<UUID, Stock> createStockData() {
        String[] names = {
            "GOOGL",
            "AAPL",
            "AMZN",
            "FB",
            "MSFT",
            "JNJ"
        };
        String[] dates = {
            "4/1",
            "4/2",
            "4/3",
            "4/4",
            "4/5",
            "4/6",
            "4/7",
            "4/8",
            "4/9",
            "4/10",
            "4/11",
            "4/12",
            "4/13",
            "4/14"
        };
        Map<UUID, Stock> stocks = new HashMap<UUID, Stock>();
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < dates.length; j++) {
                double price = (((double)(j * 100 - (j * 7))) - ((double) i * -1 * i + 3));
                Stock stock = new Stock(names[i], dates[j], price);
                stocks.put(UUID.randomUUID(), stock);
            }
        }
        return stocks;
    }
}
