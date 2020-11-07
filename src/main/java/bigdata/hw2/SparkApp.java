package bigdata.hw2;

import java.util.Arrays;
import lombok.extern.log4j.Log4j;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

@Log4j
public class SparkApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("spark.lab").setMaster("local");
        try(final JavaSparkContext context = new JavaSparkContext(conf)) {
            JavaRDD<String> textFile = context.textFile("hdfs://localhost:9000/user/root/input");
            JavaPairRDD<String, Integer> resultRDD = LogLevelCounter.counLogLevelPerHour(textFile);
            resultRDD.saveAsTextFile("hdfs://localhost:9000/user/root/output/");

        }
    }
}