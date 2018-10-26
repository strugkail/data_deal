package designpatterns.facade;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
