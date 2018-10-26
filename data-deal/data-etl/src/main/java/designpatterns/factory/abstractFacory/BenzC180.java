package designpatterns.factory.abstractFacory;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class BenzC180 implements Benz {
    /**
     * 构造方法，创建的时候就设置基本属性
     */
    public BenzC180() {
        carColor();
        carPrice();
        carSpeed();
    }
    @Override
    public void carColor() {
        System.out.println("奔驰C180的颜色是银白色");

    }

    @Override
    public void carSpeed() {
        System.out.println("奔驰C180的速度是200公里每小时");
    }

    @Override
    public void carPrice() {
        System.out.println("奔驰C180的价格是100万");
    }
}
