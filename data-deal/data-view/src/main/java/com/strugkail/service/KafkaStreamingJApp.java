package com.strugkail.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.stereotype.Service;
import scala.Serializable;
import scala.Tuple2;

import java.util.*;

/**
 * created by strugkail on 2018/6/19 0019
 * @author strugkail
 */
@Service
public class KafkaStreamingJApp implements Serializable {
    /**
     * kafka 组id
     */
    private String groupId;
    /**
     * kafka 所部署的主机名与端口号
     */
    private String servers;
    /**
     * spark streaming 启动模式
     *  如：local[2] 本地模式，两个线程
     */
    private String master;
    /**
     * spark streaming 应用名称
     * 在UI界面显示的名称
     */
    private String appName;
    /**
     * kafka 主题名称
     * 可以有多个主题
     */
    private String topicName;
    /**
     * 批次处理时间
     * 1 即是1秒
     */
    private Integer batchTime;
    /**
     * spark 配置信息对象
     */
    private static SparkConf conf;
    /**
     * streamingContext 上下文对象
     */
    private static JavaStreamingContext jssc;
    /**
     * kafka 主题集合
     */
    private static Collection<String> topics;
    /**
     * InputDStream 全局上下文对象
     */
    private static JavaInputDStream<ConsumerRecord<String, String>> stream;

    /**
     * 初始化设置
     * @param kafkaStreamingJApp
     */
    public KafkaStreamingJApp initContent(KafkaStreamingJApp kafkaStreamingJApp){
        Map<String, Object> kafkaParams = new HashMap<>();
        if(null== kafkaStreamingJApp){
            kafkaStreamingJApp = new KafkaStreamingJApp();
            kafkaStreamingJApp.setAppName("WordCount");
            kafkaStreamingJApp.setGroupId("consumer-group");
//            kafkaStreamingJApp.setServers("SRV-Test-Centos68-2-67.oigbuy.com:9092");
            kafkaStreamingJApp.setServers("strugkail001.apply.com:9092");
            kafkaStreamingJApp.setTopicName("test");
            kafkaStreamingJApp.setMaster("local[2]");
        }
        conf = new SparkConf().setMaster(kafkaStreamingJApp.getMaster()).setAppName(kafkaStreamingJApp.getAppName());
        jssc = new JavaStreamingContext(conf, Durations.seconds(1));
        topics = Arrays.asList(kafkaStreamingJApp.getTopicName());
        kafkaParams.put("bootstrap.servers", kafkaStreamingJApp.getServers());
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", kafkaStreamingJApp.getGroupId());
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
        stream = KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent(), ConsumerStrategies.<String,String>Subscribe(topics,kafkaParams));
        return kafkaStreamingJApp;
    }
    /**
     * 获取 InputDStream 上下文
     * @return
     */
    public JavaInputDStream<ConsumerRecord<String, String>> getInputDStream(){
        return stream;
    }
    /**
     * 获取 DStream 上下文
     * @param regex 分隔符
     * @return
     */
    public JavaDStream<String> getDStream(String regex) {
        return stream.flatMap(new FlatMapFunction<ConsumerRecord<String, String>, String>() {
            @Override
            public Iterator<String> call(ConsumerRecord<String, String> stringStringConsumerRecord) throws Exception {
                return Arrays.asList(stringStringConsumerRecord.value().split(regex)).iterator();
            }
        });
    }
    /**
     * 词频统计 滑动窗口
     * @param regex 分隔符
     * @param windowDurationSecond 窗口大小
     * @param slideDurationSecond 滑动间隔 （多久滑动一次）
     */
    public void wordCountByWindow(String regex, Integer windowDurationSecond, Integer slideDurationSecond){
        JavaDStream<String> wordStream = getDStream(regex);
        JavaPairDStream<String, Integer> pairs = wordStream.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairDStream<String, Integer> windowedWordCounts =
                pairs.reduceByKeyAndWindow((i1, i2) -> i1 + i2, Durations.seconds(windowDurationSecond), Durations.seconds(slideDurationSecond));
        windowedWordCounts.print(10);
    }
    /**
     * 词频统计
     * @param regex 分隔符
     */
    public void wordCount(String regex) {
        JavaDStream<String> wordStream = getDStream(regex);
        // Count each word in each batch
        JavaPairDStream<String, Integer> pairs = wordStream.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey((i1, i2) -> i1 + i2);
        wordCounts.print(5);
    }
    /**
     * 启动服务
     *
     * @throws InterruptedException
     */
    public void dealStart() throws InterruptedException {
        jssc.start();
        jssc.awaitTermination();
    }

    /**
     * 关闭服务 (有问题，待解决，停止不了)
     */
    public static void dealStop(JavaStreamingContext jssc) {
        jssc.stop(true, true);
    }

    /**
     * 获取DStream上下文
     * @return JavaStreamingContext
     */
    public JavaStreamingContext getJssc() {
        return jssc;
    }

    public KafkaStreamingJApp() {
    }

    public KafkaStreamingJApp(String groupId, String servers, String master,
                              String appName, String topicName) {
        this.groupId = groupId;
        this.servers = servers;
        this.master = master;
        this.appName = appName;
        this.topicName = topicName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public static Collection<String> getTopics() {
        return topics;
    }

    public static void setTopics(Collection<String> topics) {
        KafkaStreamingJApp.topics = topics;
    }

    public Integer getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(Integer batchTime) {
        this.batchTime = batchTime;
    }

    public static void main(String[] args) throws InterruptedException {
//        initContent(null);
//        wordCount("\t");
//        dealStart();
    }
}
