import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelReader {

    /*
    * 获取所有用户信息
    * @inputStream 顾客信息输入流
    * @return 用户信息数组
    * */
    public Customer[] getAllCustomer(InputStream inputStream) {
        Customer users[] = null;

        try {
            HSSFWorkbook xw = new HSSFWorkbook(inputStream);
            HSSFSheet xs = xw.getSheetAt(0);
            users = new Customer[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                HSSFRow row = xs.getRow(j);
                Customer user = new Customer();

                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    HSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        user.setId(this.getValue(cell));
                    } else if (k == 1) {
                        user.setPassword(this.getValue(cell));
                    } else if(k==2){
                        user.setName(this.getValue(cell));
                    } else if (k == 3) {
                        user.setAddress(this.getValue(cell));
                    } else if (k == 4) {
                        user.setPhone(this.getValue(cell));
                    }
                    users[j-1]=user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /*
    * 获取所有商品信息
    * @inputStream 商品信息输入流
    * @return 商品信息数组
    * */
    public Product[] getAllProduct(InputStream inputStream){
        Product products[] = null;

        try {
            HSSFWorkbook xw = new HSSFWorkbook(inputStream);
            HSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                HSSFRow row = xs.getRow(j);
                Product product = new Product();

                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    HSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));
                    } else if(k==2){
                        product.setPrice(Float.parseFloat(this.getValue(cell)));
                    } else if (k==3){
                        product.setInfo(this.getValue(cell));
                    }
                    products[j-1]=product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    /*
    * 通过商品id获取商品信息
    * @id 商品id
    * @inputStream 商品信息输入流
    * @return 商品信息
    * */
    public Product getProductById(String id,InputStream inputStream){
        Product product=new Product();
        try {
            HSSFWorkbook xw = new HSSFWorkbook(inputStream);
            HSSFSheet xs = xw.getSheetAt(0);

            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                HSSFRow row = xs.getRow(j);
                HSSFCell cell = row.getCell(0);

                /*id格式标准化*/
                double d=Double.parseDouble(this.getValue(cell));
                DecimalFormat df=new DecimalFormat("#");

                if (df.format(d).equals(id)){
                    for (int k = 0; k <= row.getLastCellNum(); k++) {
                        cell = row.getCell(k);
                        if (k == 0) {
                            product.setId(this.getValue(cell));
                        } else if (k == 1) {
                            product.setName(this.getValue(cell));
                        } else if(k==2){
                            product.setPrice(Float.parseFloat(this.getValue(cell)));
                        } else if (k==3){
                            product.setInfo(this.getValue(cell));
                        }
                    }
                    return product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    * 查找所有订单的信息
    * @inputStream 订单信息输入流
    * @return 订单信息
    * */
    public Order[] getAllOrder(InputStream inputStream){
        Order[] orders=null;

        try {
            HSSFWorkbook xw = new HSSFWorkbook(inputStream);
            HSSFSheet xs = xw.getSheetAt(0);
            orders = new Order[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                HSSFRow row = xs.getRow(j);
                Order order=new Order();

                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    HSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        order.setId(this.getValue(cell));
                    } else if (k == 1) {
                        Customer customer=new Customer();
                        customer.setId(this.getValue(cell));
                        order.setOwner(customer);
                    } else if(k==2){
                        Product[] product=new Product[1];
                        Product cart=new Product();
                        cart.setId(this.getValue(cell));
                        product[0]=cart;
                        order.setCart(product);
                    } else if (k==3){
                        int[] num=new int[1];
                        double d=Double.parseDouble(this.getValue(cell));
                        DecimalFormat df=new DecimalFormat("#");
                        num[0]=Integer.parseInt(df.format(d));
                        order.setProductNum(num);
                    } else if (k==4){
                        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            order.setTime(df.parse(df.format(new Date())));
                        }catch (ParseException e){
                            e.printStackTrace();
                        }

                    } else if (k==5){
                        order.setNeedPay(Double.parseDouble(this.getValue(cell)));
                    } else if (k==6){
                        order.setRealPat(Double.parseDouble(this.getValue(cell)));
                    }

                }orders[j-1]=order;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private String getValue(HSSFCell cell) {
        String value;
        CellType type = cell.getCellType();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = cell.getNumericCellValue() + "";
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}