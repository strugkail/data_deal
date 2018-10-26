package designpatterns.observer;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class ObserverTest {
    public static void main(String[] args){
        WeChatServer weChatServer = new WeChatServer();

        Observer strugkail = new User("strugkail");
        Observer love = new User("love");
        Observer grass = new User("grass");
        Observer snoopy = new User("snoopy");

        weChatServer.registerObserver(strugkail);
        weChatServer.registerObserver(love);
        weChatServer.registerObserver(grass);

        weChatServer.setInformation("Php 是世界上最好的语言");

        System.out.println("==================================================");
//        weChatServer.removeObserver(love);
        weChatServer.registerObserver(snoopy);
        weChatServer.setInformation("Java 才是世界上最好的语言");

    }
}
