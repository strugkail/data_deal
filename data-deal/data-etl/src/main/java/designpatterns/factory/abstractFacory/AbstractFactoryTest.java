package designpatterns.factory.abstractFacory;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        System.out.println("生产奔驰车C180");
        //父类但对象子类的实例
        BenzFactory benzFactory = new C180Factory();//创建一个C180工厂
        //调用父类的方法，这就是java多态的一种体现
        benzFactory.createCar();//C180工厂生产车辆C180
        benzFactory.createNacigator();//生产车辆C180对应的导航仪

        System.out.println("=========================================");
        System.out.println("生产奔驰车Z260");
        BenzFactory benzFactory2 = new E260Factory();
        benzFactory2.createCar();//E260工厂生产车辆E260
        benzFactory2.createNacigator();//生产车辆E260对应的导航仪

    }
}
