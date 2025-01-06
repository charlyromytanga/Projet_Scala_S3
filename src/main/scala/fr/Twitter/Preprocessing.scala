
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.trim


case class CleanedTweet(text: String)

object Preprocessing {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Preprocessing").getOrCreate()
    val tweets = spark.read.text("data/tweets").toDF("text")
    val cleanedTweets = tweets.withColumn("cleaned_text", trim(regexp_replace(col("text"), "http\\S+|www\\S+|@\\w+|#\\w+", "")))
    cleanedTweets.as[CleanedTweet].write.text("data/cleaned_tweets")
    spark.stop()
  }
}
