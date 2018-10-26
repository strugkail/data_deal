package designpatterns.factory.abstractFacory;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 * 生产某一个型号奔驰车E260辆的实际工厂
 */
public class E260Factory implements BenzFactory {
    @Override
    public Benz createCar() {
        return new BenzE260();
    }

    @Override
    public CarNavigator createNacigator() {
        return new E260Navigator();
    }
}
