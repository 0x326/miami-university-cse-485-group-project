import java.io.Serializable;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String lastUpdatedDate;
    private double price;

    public Stock() {
        this("", "", 0);
    }
    public Stock(String name, String lastUpdatedDate, double price) {
        this.name = name;
        this.lastUpdatedDate = lastUpdatedDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return name + " on " + lastUpdatedDate + ": $" + price;
    }
}
