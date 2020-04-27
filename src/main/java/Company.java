import java.io.Serializable;

public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String stockAbr;
    private String hqLocationState;

    public Company() {
        this("", "", "");
    }
    public Company(String name, String stockAbr, String hqLocationState) {
        this.name = name;
        this.stockAbr = stockAbr;
        this.hqLocationState = hqLocationState;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getHqLocationState() {
        return hqLocationState;
    }
    public void setHqLocationState(String hqLocation) {
        this.hqLocationState = hqLocation;
    }

    public String getStockAbr() { return stockAbr; }
    public void setStockAbr(String stockAbr) { this.stockAbr = stockAbr; }

    public String toString() {
        return name + " (" + stockAbr + ")" + " in " + hqLocationState;
    }
}
