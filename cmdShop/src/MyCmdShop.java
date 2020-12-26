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

    }
}
