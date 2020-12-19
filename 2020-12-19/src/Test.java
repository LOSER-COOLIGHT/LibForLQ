public class Test {
    public static void main(String[] args) {
        Customer customer=new Customer();
        customer.setName("张三");
        customer.setPhone("15177138436");
        customer.setPhone("1a111111111");
        customer.setPhone("111111111111");

        Order order=new Order();
        order.setCustomer(customer);
        System.out.println(customer.getName());
        System.out.println(customer.getPhone());
        System.out.println(order.getCustomer());
    }
}
