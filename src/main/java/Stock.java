import java.io.Serializable;
import java.util.HashMap;
import java.util.Date;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Date lastUpdatedDate;
    private double price;

    public Stock() {
        this("", new Date(0L), 0);
    }
    public Stock(String name, Date lastUpdatedDate, double price) {
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

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    public void setLastUpdatedDate(Date lastUpdatedDate) {
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
