
import org.apache.spark.ml.classification.LogisticRegressionModel
import org.apache.spark.ml.feature.{StopWordsRemover, Tokenizer}
import org.apache.spark.sql.SparkSession


case class AnalyzedTweet(text: String, prediction: String)


object SentimentAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("SentimentAnalysis").getOrCreate()
    val tweets = spark.read.text("data/cleaned_tweets").toDF("text")

    val tokenizer = new Tokenizer().setInputCol("text").setOutputCol("words")
    val tokenized = tokenizer.transform(tweets)

    val remover = new StopWordsRemover().setInputCol("words").setOutputCol("filtered_words")
    val filtered = remover.transform(tokenized)

    val model = LogisticRegressionModel.load("data/sentiment_model")
    val predictions = model.transform(filtered)
    predictions.select("text", "prediction").as[AnalyzedTweet].write.csv("data/sentiment_results")

    spark.stop()
  }
}
