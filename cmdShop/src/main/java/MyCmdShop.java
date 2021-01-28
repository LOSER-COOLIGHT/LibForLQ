import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

public class MyCmdShop {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("欢迎使用本系统！");
        Scanner in = new Scanner(System.in);

        /*读取所有客户信息*/
        InputStream ins=Class.forName("MyCmdShop").getResourceAsStream("/Customer.xls");
        ExcelReader er=new ExcelReader();
        Customer[] cs=null;
        cs=er.getAllCustomer(ins);
        Customer user = new Customer();

        /*登录*/
        login:
        while (true) {
            System.out.println("\n请登录");
            System.out.println("请输入id:");
            user.setId(in.next());//获取id

            System.out.println("请输入密码:");
            user.setPassword(in.next());//获取密码

            /*登录验证*/
            int idf = cs.length;
            for (Customer o :
                    cs) {
                if (user.getId().equals(o.getId())) {
                    if (user.getPassword().equals(o.getPassword())) {
                        System.out.println("登录成功");
                        break login;
                    } else {
                        System.out.println("密码错误");
                    }
                } else {
                    if (--idf == 0) System.out.println("帐号不存在");
                }
            }
        }

        /*读取所有商品信息*/
        ins=Class.forName("MyCmdShop").getResourceAsStream("/Product.xls");
        Product[] products=null;
        products=er.getAllProduct(ins);

        int isPay=2;
        while (isPay>1) {
            /*显示商品*/
            System.out.println("\n商品信息:");
            System.out.println("商品id\t商品名称\t商品价格\t商品描述");
            for (Product o :
                    products) {
                System.out.println(o.getId() + "\t\t" + o.getName() + "\t" + o.getPrice() + "元\t" + o.getInfo());
            }

            /*添加购物车*/
            Product[] cart = new Product[1];//购物车
            Product product = null;
            int capacity = 0;//购物车容量
            while (true) {
                System.out.println("请输入要加入购物车的商品id，输入0退出");

                ins = Class.forName("MyCmdShop").getResourceAsStream("/Product.xls");
                String id = in.next();//读取输入的商品id
                if (id.equals("0")) {
                    break;//输入0退出
                } else {
                    product = er.getProductById(id, ins);
                    if (product == null) {
                        System.out.println("商品不存在");
                        continue;
                    }
                    if (capacity < cart.length) {
                        cart[capacity++] = product;//商品放入购物车
                        System.out.println("添加成功");
                    } else {
                        Product[] cartUp = new Product[cart.length + 1];//购物车扩容
                        for (int i = 0; i < cart.length; i++) {
                            cartUp[i] = cart[i];//扩容后记录原购物车内的物品
                        }
                        cartUp[capacity++] = product;//新商品放入扩容购物车
                        cart = cartUp;//购物车更新
                        System.out.println("添加成功");
                    }

                }
//            System.out.println(product.getId() + "\t\t" + product.getName() + "\t" + product.getPrice() + "元\t" + product.getInfo());
            }

            /*查看购物车*/
            /*System.out.println("\n购物车内容为：");
            System.out.println("商品id\t商品名称\t商品价格\t商品描述");
            for (Product o :
                cart) {
            System.out.println(o.getId() + "\t" + o.getName() + "\t" + o.getPrice() + "\t" + o.getInfo());
            }*/
            Order order = new Order();
            order.setOwner(user);
            order.setCart(cart);
            order.setTime(new Date());
            order.showCart();
            Order[] orders=new Order[1];
            orders[0]=order;
            order.record(orders);

            Pay:
            while (true) {
                System.out.println("付款请输入1，继续购物输入2，退出请按0");
                isPay = in.nextInt();
                switch (isPay) {
                    case 0:
                        ;
                        return;
                    case 1://付款;

                        break Pay;
                    case 2:
                        ;
                        break Pay;
                    default:
                        System.out.println("非法指令");

                }
            }

        }
    }
}
