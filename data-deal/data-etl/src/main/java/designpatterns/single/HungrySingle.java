package designpatterns.single;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class HungrySingle {
    /**
     * 此处定义类变量实例并直接实例化，
     * 在类加载的时候就完成了实例化并保存在类中
     **/
    private static HungrySingle hungrySingle = new HungrySingle();

    /**
     * 定义无参构造器，用于单例实例
     */
    private HungrySingle(){}

    public static HungrySingle getInstance(){
        return hungrySingle;
    }
}
