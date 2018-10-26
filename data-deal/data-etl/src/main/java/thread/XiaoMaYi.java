package thread;

/**
 * created by strugkail on 2018/8/6 0006
 *
 * @author strugkail
 */
public class XiaoMaYi extends Thread {
    String name;

    public XiaoMaYi(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ":小蚂蚁正在下载");
    }
}
