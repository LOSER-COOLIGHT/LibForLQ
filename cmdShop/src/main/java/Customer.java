import java.text.DecimalFormat;

public class Customer {
    private String id;
    private String password;
    private String name;
    private String address;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        double d=Double.parseDouble(id);
        DecimalFormat df=new DecimalFormat("#");
        this.id = df.format(d);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        double d=Double.parseDouble(phone);
        DecimalFormat df=new DecimalFormat("#");
        this.phone = df.format(d);
    }

    public boolean login(){

        return false;
    }

    public void productView(){

    }
}
