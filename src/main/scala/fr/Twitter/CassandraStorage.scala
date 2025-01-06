
import org.apache.spark.sql.SparkSession

case class SentimentStat(prediction: String, count: Long)

object CassandraStorage {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("CassandraStorage")
      .config("spark.cassandra.connection.host", "localhost")
      .getOrCreate()

    val stats = spark.read.csv("data/sentiment_stats").toDF("prediction", "count").as[SentimentStat]
    stats.write
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> "sentiment_stats", "keyspace" -> "twitter_analysis"))
      .save()

    spark.stop()
  }
}

