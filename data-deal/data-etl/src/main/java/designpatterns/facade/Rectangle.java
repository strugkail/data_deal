package designpatterns.facade;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
