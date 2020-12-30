public class Order {
    private String id;
    private Customer owner;
    private Product[] cart;
    private int[] productNum;

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
}
