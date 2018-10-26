package designpatterns.facade;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class FacadeTest {

    public static void main(String[] args){
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
