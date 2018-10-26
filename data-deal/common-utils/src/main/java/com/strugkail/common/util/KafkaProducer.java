package com.strugkail.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * created by strugkail on 2018/6/19 0019
 * @author strugkail
 */
public class KafkaProducer {
    private static final Properties properties = new Properties();
    static {
        /**
         * 指定主机名和端口号
         */
        properties.put("bootstrap.servers", "SRV-Test-Centos68-2-67.oigbuy.com:9092");
        properties.put("request.required.acks", "0");
        properties.put("producer.type", "sync");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        /**
         * 指定消息key序列化方式
         */
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /**
         * 指定消息本身的序列化方式
         */
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    /**
     * kafka主题名称
     */
    private static final String topicName = "test";

    /**
     * 开启多线程,发送自定义数据
     * @param threadNum 线程数
     */
    private static void startThreadsProducer(Integer threadNum) {
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
        final char[] chars = "qazwsxedcrfvtgbyhnujmiklop".toCharArray();
        final int charLendth = chars.length;
        final Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int event = random.nextInt(1000) + 100;
                    String threadName = Thread.currentThread().getName();
                    for (int j = 0; j < event; j++) {
                        if (j != 0 && j % 100 == 0) {
                            System.out.println("线程【" + threadName + "】已经发送" + j + "条数据！");
                        }
//                        String key = "key_" + random.nextInt(100);
                        StringBuilder sb = new StringBuilder();
                        int wordNums = random.nextInt(10) + 1;
                        for (int k = 0; k < wordNums; k++) {
                            StringBuilder sb2 = new StringBuilder();
                            int charNums = random.nextInt(8) + 2;
                            for (int l = 0; l < charNums; l++) {
                                sb2.append(chars[random.nextInt(charLendth)]);
                            }
                            sb.append(sb2.toString()).append(" ");
                        }
                        String value = sb.toString().trim();
                        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topicName,
                                value);
                        producer.send(producerRecord);
                        try {
                            Thread.sleep(random.nextInt(50) + 10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("线程" + threadName + "共发送了" + event + "条数据");
                }
            }, "thread-" + i).start();
        }
        closeProducer(producer);
    }
    /**
     * 创建生产者连接
      */
    private static ProducerRecord<String, String> getRecord(String value){
        return new ProducerRecord<String, String>(topicName, value);
    }

    /**
     * 关闭生产者连接
     * @param producer
     */
    private static void closeProducer(Producer<String, String> producer){
        // 要求数据发送完成后完成关闭操作 一般可以考虑添加jvm钩子，jvm推出进行该操作
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.close();
            }
        }));
    }

    /**
     * 定时任务，每次发送单条数据
     * @param value
     */
    private static void sendMultipleWithTimer(String value) {
        // 创建连接
        ProducerRecord<String, String> producerRecord = getRecord(value);
        // 创建生产者对象
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
        // 启动定时任务，发送数据
        startTimer(producerRecord,producer);
        // 关闭生产者连接
        closeProducer(producer);
    }
    /**
     * 发送单条数据
     * @param value
     */
    public static void sendSingleMessage(String value) {
        // 创建连接
        ProducerRecord<String, String> producerRecord = getRecord(value);
        // 创建生产者对象
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
        // 发送数据
        producer.send(producerRecord);
        // 关闭生产者连接
        closeProducer(producer);
    }

    /**
     * 读取地址文件，并发送数据
     * @param path
     */
    private static void startLocalFileProducer(String path) throws IOException {
        // 创建生产者对象
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
            if(StringUtils.isBlank(path)){
                path = "E:\\data\\t_ebay_listing.csv";
            }
            // 读取路径地址，获取文件
            File filename = new File(path);
            // 获取输入流对象
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            // 转化为高级流
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("要发送的数据************>>" + line);
                // 得到每行内容并创建发送对象（不可取，会创建很多对象，消耗内存）
                ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topicName,line);
                // 发送数据
                producer.send(producerRecord);
            }
        // 关闭生产者连接
        closeProducer(producer);
    }

    /**
     * 开启定时任务，并随机发送数据
     * @param producerRecord
     * @param producer
     */
    private static void startTimer(ProducerRecord<String, String> producerRecord,Producer<String, String> producer) {
        new Timer().schedule(new TimerTask() {
            int num = 0;
            @Override
            public void run() {
                if ((System.currentTimeMillis()) % 3 == 2) {
                    System.out.println("============生产者开始发送第" + num + "条数据==============");
                    producer.send(producerRecord);
                    num += 1;
                }
            }
        }, 100, 150);
    }

//    public static void main(String[] args) {

        //    KafkaProducer.startThreadsProducer(5);
//        String value = "108790007,105910058,101140035,18,18,,46 also admission fees,,Very disgusted and a," +
//                "D520180607152334DB37$(C*ET5M5iNG2OQ6,ITCESHWCJP05,,0701160640838,1,,,,,GTC,0.00,None,NoHitCounter," +
//                "BoldTitle,2,FixedPriceItem,2,1,PayPal,,gengjinkui@zugelao.com,0,,0,2018/6/7 15:23:59,,27666368," +
//                "318653,318737,,318600,,444.00,,,1,0.00,0\n" +
//                "&amp;amp;middot; Intelligent Energy-saving###,7.99,4LED需要定制 年后有货  买样买了8LED的相似款大小外形都一样 ,263738314499," +
//                "0,20.78,0";
//        KafkaProducer.startSingleProducer(value);
//        startLocalFileProducer();
//    }
}
