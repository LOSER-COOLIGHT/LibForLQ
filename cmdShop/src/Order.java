import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String id;
    private Customer owner;
    private Product[] cart;
    private int[] productNum;
    private double needPay;
    private double realPat;
    private Date time;
/*用户，商品，数量，总付款，实际付款，下单时间*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Product[] getCart() {
        return cart;
    }

    public void setCart(Product[] cart) {
        this.cart = cart;
    }

    public int[] getProductNum() {
        return productNum;
    }

    public void setProductNum(int[] productNum) {
        this.productNum = productNum;
    }

    public double getNeedPay() {
        return needPay;
    }

    public void setNeedPay(double needPay) {
        this.needPay = needPay;
    }

    public double getRealPat() {
        return realPat;
    }

    public void setRealPat(double realPat) {
        this.realPat = realPat;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(String time){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.time=df.parse(df.format(time));
        }catch (ParseException e){
            e.printStackTrace();
        }

    }
    /*
    * 查看购物车内的商品
    * */
    public void showCart(){
        if (cart==null){
            System.out.println("购物车为空");
            return;
        }
        for (int i=0;i<cart.length;i++){
            int num=1;//当前商品数量
            int[] numUp;//扩容数组
            if (productNum==null)
                numUp=new int[1];
            else
                numUp=new int[productNum.length+1];
            for (int j=i+1;j<cart.length;j++){

                /*商品id重复，删除重复项*/
                if (cart[i].getId().equals(cart[j].getId())){
                    num++;//数量+1
                    for (int k=j;k<cart.length;k++){
                        if(k!=cart.length-1){
                            cart[k]=cart[k+1];//清除重复项
                        }
                    }
                    /*购物车缩小*/
                    Product[] cartUp=new Product[cart.length-1];
                    for(int m=0;m<cartUp.length;m++){
                        cartUp[m]=cart[m];
                    }
                    cart=cartUp;
                    numUp[i]=num;//记录商品数量
                    j--;
                }
            }
            /*数量数组扩容*/
            if (productNum==null){
                numUp[0]=num;
                productNum=numUp;
                continue;
            }
            for (int j=0;j<productNum.length;j++){
                numUp[j]=productNum[j];
            }
            numUp[productNum.length]=num;
            productNum=numUp;
        }
        System.out.println("\n购物车内容为：");
        System.out.println("商品id\t商品名称\t商品价格\t购买数量\t商品描述");
        for (int i=0;i<cart.length;i++) {
            System.out.println(cart[i].getId() + "\t" + cart[i].getName() + "\t" + cart[i].getPrice() + "\t"+productNum[i]+"\t" + cart[i].getInfo());
        }
    }

    /*
    * 记录订单信息
    * @orders 所有的订单
    * */
    public void record(Order[] orders){
        String path="F:\\LibForLQ\\LibForLQ\\cmdShop\\res\\order.xls";
        //定义二维数组



        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            HSSFSheet sheet = workbook.createSheet("订单");
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short)0);
            //在索引0的位置创建单元格（左上端）
            HSSFCell cell01 = row.createCell((short)0);

            HSSFCell cell02 = row.createCell((short)1);

            HSSFCell cell03 = row.createCell((short)2);

            HSSFCell cell04 = row.createCell((short)3);

            HSSFCell cell05 = row.createCell((short)4);

            HSSFCell cell06 = row.createCell((short)5);

            HSSFCell cell07 = row.createCell((short)6);

            HSSFCell cell08 = row.createCell((short)7);

            HSSFCell cell09 = row.createCell((short)8);

            HSSFCell cell10 = row.createCell((short)9);

            HSSFCell cell11 = row.createCell((short)10);

            // 在单元格中输入一些内容
            cell01.setCellValue("订单编号");
            cell02.setCellValue("用户id");
            cell03.setCellValue("商品id");
            cell04.setCellValue("购买数量");
            cell05.setCellValue("下单时间");
            cell06.setCellValue("订单总金额");
            cell07.setCellValue("实际付款金额");


            HSSFRow[] myrow = new HSSFRow[orders[0].cart.length];
            for(int i=0;i<myrow.length;i++){
                myrow[i]=sheet.createRow((short)i+1);
            }

            HSSFCell[][] cell=new HSSFCell[orders[0].cart.length][7];
            for(int i=0;i<cell.length;i++){
                for(int j=0;j<cell[i].length;j++){
                    cell[i][j]=myrow[i].createCell((short)j);

                    switch (j){
                        case 0:cell[i][j].setCellValue(orders[i].getId());
                        break;
                        case 1:cell[i][j].setCellValue(orders[i].owner.getId());
                        break;
                        case 2:cell[i][j].setCellValue(orders[i].cart[i].getId());
                        break;
                        case 3:cell[i][j].setCellValue(orders[i].productNum[i]);
                        break;
                        case 4:cell[i][j].setCellValue(orders[i].time);
                        break;
                        case 5:cell[i][j].setCellValue(orders[i].needPay);
                        break;
                        case 6:cell[i][j].setCellValue(orders[i].realPat);
                        break;
                        default:
                            System.out.println("错误");
                    }


                }
            }

            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(path);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成...");
        } catch (Exception e) {
            System.out.println("已运行 xlCreate() : " + e);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Order order=new Order();
        /*order.setId("1");
        Customer customer=new Customer();
        customer.setId("1");
        order.setOwner(customer);*/
        ExcelReader er=new ExcelReader();
        InputStream inputStream=Class.forName("Order").getResourceAsStream("/order.xls");

        order.record(er.getAllOrder(inputStream));
    }
}
