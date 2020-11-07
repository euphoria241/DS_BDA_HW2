//import bigdata.hw2.LogLevelCounter;
//import com.holdenkarau.spark.testing.JavaRDDComparisons;
//import com.holdenkarau.spark.testing.SharedJavaSparkContext;
//import org.apache.spark.SparkConf;
//import org.apache.spark.SparkContext;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import scala.Tuple2;
//import scala.reflect.ClassTag;
//import org.apache.spark.Logging;
//
//public class AppTest extends SharedJavaSparkContext {
//    SparkConf conf = new SparkConf().setAppName("spark.lab").setMaster("local");
//    JavaSparkContext jsc = new JavaSparkContext(conf);
//
//    @Test
//    public void verifyMapTest() {
//        // Create and run the test
//        List<String> input = Arrays.asList("Nov 2 12:22:22 3", "Nov 2 12:22:22 4", "Nov 2 12:22:22 3");
//        JavaRDD<String> inputRDD = jsc().parallelize(input);
//        JavaPairRDD<String, Integer> result = LogLevelCounter
//                .counLogLevelPerHour(inputRDD);
//
//        // Create the expected output
//        List<Tuple2<String, Integer>> expectedInput = Arrays.asList(
//                new Tuple2<String, Integer>("12,3", 2),
//                new Tuple2<String, Integer>("12,4", 1));
//        JavaPairRDD<String, Integer> expectedRDD = jsc()
//                .parallelizePairs(expectedInput);
//
//        ClassTag<Tuple2<String, Integer>> tag =
//                scala.reflect.ClassTag$.MODULE$
//                        .apply(Tuple2.class);
//
//        // Run the assertions on the result and expected
//        JavaRDDComparisons.assertRDDEquals(
//                JavaRDD.fromRDD(JavaPairRDD.toRDD(result), tag),
//                JavaRDD.fromRDD(JavaPairRDD.toRDD(expectedRDD), tag));
//    }
//}
