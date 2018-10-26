package designpatterns.factory.abstractFacory;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 * 生产某一个型号奔驰车辆的实际工厂
 */
public class C180Factory implements BenzFactory {
    @Override
    public Benz createCar() {
        return new BenzC180();
    }

    @Override
    public CarNavigator createNacigator() {
        return new C180Navigator();
    }
}
