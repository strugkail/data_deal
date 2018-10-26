package designpatterns.observer;

/**
 * created by strugkail on 2018/8/8 0008
 * 观察者
 * 实现了update方法
 * @author strugkail
 */
public class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }
}
