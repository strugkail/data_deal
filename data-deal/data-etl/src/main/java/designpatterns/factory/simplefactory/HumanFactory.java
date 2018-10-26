package designpatterns.factory.simplefactory;

/**
 * created by strugkail on 2018/8/7 0007
 *
 * @author strugkail
 */
public class HumanFactory {
    public static Human createHuman(String human){
        if(human.equals("male")){
            return new Male();
        }else if(human.equals("female")){
            return new Female();
        }else{
            System.out.println("parameter is error!");
            return null;
        }
    }
}
