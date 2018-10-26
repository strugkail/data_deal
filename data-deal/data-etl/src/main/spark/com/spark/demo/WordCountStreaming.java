package com.spark.demo;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCountStreaming {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","D:\\software\\hadoop-2.6.1");

        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetWorkWordCount");

        JavaSparkContext jsc = new JavaSparkContext(conf);

//        jsc.setLogLevel("ERROR");

        JavaStreamingContext jssc = new JavaStreamingContext(jsc, Durations.seconds(1));


//        jssc.textFileStream("D:\\software\\spark\\test.log");

        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9999);

        JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(x.split(" ")).iterator());

        JavaPairDStream<String,Integer> pairs = words.mapToPair(s -> new Tuple2<>(s,1));

        JavaPairDStream<String,Integer> wordCounts = pairs.reduceByKey((i1,i2) -> i1+i2);

        wordCounts.print();

        jssc.start();
        try {
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
