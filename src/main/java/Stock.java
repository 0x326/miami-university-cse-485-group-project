import java.io.Serializable;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String date;
    private double price;

    public Stock() {
        this("", "", 0);
    }
    public Stock(String name, String date, double price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return name + " on " + date + ": $" + price;
    }
}
