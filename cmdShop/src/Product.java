import java.text.DecimalFormat;

public class Product {
    private String id;
    private String name;
    private float price;
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        double d=Double.parseDouble(id);
        DecimalFormat df=new DecimalFormat("#");
        this.id = df.format(d);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
