package designpatterns.proxy.staticproxy;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class BuyCarProxy implements IBuyCar {
    private Customer customer;//接收买车客户

    public BuyCarProxy(Customer customer){
        this.customer=customer;//接收买车客户
    }

    @Override
    public void buyCar() {//实现为客户买车
        int cash=customer.getCash();
        if(cash<100000){
            System.err.println("buyCar-->> 你的钱不够买一辆车");
            return;
        }
        customer.buyCar();
    }
}
