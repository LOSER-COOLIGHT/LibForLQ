import java.io.InputStream;
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

        /*登录*/
        login:
        while (true) {
            System.out.println("\n请登录");
            System.out.println("请输入id:");
            Customer user = new Customer();
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

        /*显示商品*/
        System.out.println("\n商品信息:");
        System.out.println("商品id\t商品名称\t商品价格\t商品描述");
        for (Product o :
                products) {
            System.out.println(o.getId() + "\t\t" + o.getName() + "\t" + o.getPrice() + "元\t" + o.getInfo());
        }

        /*添加购物车*/
        Product[] cart=new Product[4];//购物车
        Product product=null;
        int capacity=0;//购物车容量
        while (true){
            System.out.println("请输入要加入购物车的商品id，输入0退出");

            ins=Class.forName("MyCmdShop").getResourceAsStream("/Product.xls");
            String id=in.next();
            if(id.equals("0")){
                break;//输入0退出
            }
            else {
                product=er.getProductById(id,ins);
                if (product == null) {
                    System.out.println("商品不存在");
                    continue;
                }
                if(capacity<cart.length){
                    cart[capacity++]=product;
                    System.out.println("添加成功");
                } else{
                    Product[] cartUp=new Product[cart.length+1];
                    for(int i=0;i<cart.length;i++){
                        cartUp[i]=cart[i];
                    }
                    cartUp[capacity++]=product;
                    cart=cartUp;
                    System.out.println("添加成功");
                }

            }
//            System.out.println(product.getId() + "\t\t" + product.getName() + "\t" + product.getPrice() + "元\t" + product.getInfo());
        }



    }
}
