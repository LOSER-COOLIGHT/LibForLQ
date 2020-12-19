import java.util.List;

public class Customer {
    private String name;
    private String phone;
    private List<Order> orderList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone.length()!=11){
            System.out.println("手机号长度为11位");
            return;
        }
        for (int i = 0; i < phone.length(); i++) {
            if(phone.charAt(i)<'0'||phone.charAt(i)>'9'){
                System.out.println("手机号中含有非法字符");
                return;
            }
        }
        this.phone = phone;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
