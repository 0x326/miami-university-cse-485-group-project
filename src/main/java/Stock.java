import java.io.Serializable;

public class Stock implements Serializable {
    private String name;
    private double value;

    public Stock() {
        this("", 0);
    }
    public Stock(String name) {
        this(name, 0);
    }
    public Stock(double value) {
        this("", value);
    }
    public Stock(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        return name + " (" + value + ")";
    }
}
