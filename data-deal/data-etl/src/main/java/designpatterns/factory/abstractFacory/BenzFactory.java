package designpatterns.factory.abstractFacory;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 * 奔驰工厂的抽象接口
 */
public interface BenzFactory {
    /**
     * 创建奔驰的方法
     */
    Benz createCar();

    /**
     * 创建导航仪的方法
     */
    CarNavigator createNacigator();
}
