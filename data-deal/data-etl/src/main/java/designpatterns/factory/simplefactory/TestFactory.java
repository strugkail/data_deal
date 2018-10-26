package designpatterns.factory.simplefactory;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class TestFactory {
    public static void main(String[] args){
        Human male = HumanFactory.createHuman("male");
        male.beat();
        Human female = HumanFactory.createHuman("female");
        female.eat();
    }
}
