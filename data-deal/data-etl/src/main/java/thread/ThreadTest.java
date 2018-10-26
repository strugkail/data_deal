package thread;

/**
 * created by strugkail on 2018/8/6 0006
 *
 * @author strugkail
 */
public class ThreadTest {

    public static void main(String[] args) {
//        MaYi maYi1 = new MaYi("一号");
//        maYi1.start();
//        MaYi maYi2 = new MaYi("二号");
//        maYi2.start();
//        thread.XiaoMaYi xiaoMaYi = new thread.XiaoMaYi("小号");
//        xiaoMaYi.start();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        System.out.println("要执行的方法1");
                        Thread.sleep(5000);
                        System.out.println("要执行的方法2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("线程"+i+"开始执行中");
        }
    }

    static class MaYi extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ":蚂蚁正在下载");
        }

        String name;

        public MaYi() {
        }

        public MaYi(String name) {
            this.name = name;
        }
    }
}
