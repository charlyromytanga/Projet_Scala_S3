
import org.apache.spark.sql.SparkSession

 object Aggregation {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Aggregation").getOrCreate()
    val results = spark.read.option("header", true).csv("data/sentiment_results")
    val stats = results.groupBy("prediction").count()
    stats.write.csv("data/sentiment_stats")
    spark.stop()
  }
}
