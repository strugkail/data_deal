package designpatterns.single;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 * 单重加锁，每次执行会拖慢速度
 * 所以采用 双重加锁机制
 *
 */
public class DoubleSynLazySingle {
    private static volatile DoubleSynLazySingle dss = null;
    private DoubleSynLazySingle(){}
    public static DoubleSynLazySingle getInstance(){
        if(dss==null){
            synchronized (DoubleSynLazySingle.class){
                if(dss==null){
                    dss = new DoubleSynLazySingle();
                }
            }
        }
        return dss;
    }
}
