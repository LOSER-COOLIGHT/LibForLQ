import java.io.File;
import java.util.Scanner;

public class MyCmdShop {
    public static void main(String[] args) {
        System.out.println("欢迎使用本系统！\n");
        System.out.println("请登录");
        System.out.println("请输入用户名或id:");
        Scanner in=new Scanner(System.in);

//        cs.setName(in.nextLine());
//        cs.setPassword(in.nextLine());
        File fp=new File("res\\Customer.xls");
        ExcelReader er=new ExcelReader();
        Customer[] cs=null;
        cs=er.readExcel(fp);
        for (Customer o :
                cs) {
            System.out.println(o.getId());
            System.out.println(o.getPassword());
            System.out.println(o.getName());
            System.out.println(o.getAddress());
            System.out.println(o.getPhone());
        }
        Customer user=new Customer();
        user.setId(in.next());
        user.setPassword(in.next());
        for (Customer o :
                cs) {
            if (user.getId().equals(o.getId())){
                if(user.getPassword().equals(o.getPassword())){
                    System.out.println("登录成功");
                    break;
                }
                else {
                    System.out.println("密码错误");
                }
            }
            else {
                System.out.println("账号不存在");
            }
        }
        in.close();
    }
}
