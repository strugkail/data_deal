package designpatterns.observer;

/**
 * created by strugkail on 2018/8/8 0008
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @author strugkail
 */
public interface Observerable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}
