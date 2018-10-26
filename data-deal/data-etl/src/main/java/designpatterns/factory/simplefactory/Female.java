package designpatterns.factory.simplefactory;

/**
 * created by strugkail on 2018/8/7 0007
 *
 * @author strugkail
 */
public class Female implements Human{
    @Override
    public void eat() {
        System.out.println("I (Female) want to eat!");
    }

    @Override
    public void drink() {
        System.out.println("I (Female) want to drink!");
    }

    @Override
    public void beat() {
        System.out.println("I (Female) want to beat!");
    }
}
