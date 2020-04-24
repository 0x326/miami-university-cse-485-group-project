import java.io.Serializable;

public class Company implements Serializable {
    private String name;
    private String hqLocation;

    public Company() {
        this("", "");
    }
    public Company(String name, String hqLocation) {
        this.name = name;
        this.hqLocation = hqLocation;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getHqLocation() {
        return hqLocation;
    }
    public void setHqLocation(String hqLocation) {
        this.hqLocation = hqLocation;
    }

    public String toString() {
        return name + " at " + hqLocation;
    }
}
