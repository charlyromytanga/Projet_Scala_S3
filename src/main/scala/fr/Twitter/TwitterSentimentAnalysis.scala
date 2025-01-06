//
//import org.apache.spark._
//import org.apache.spark.streaming._
//
//object TwitterSentimentAnalysis {
//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setAppName("TwitterSentimentAnalysis").setMaster("local[2]")
//    val ssc = new StreamingContext(conf, Seconds(5))
//
//    System.setProperty("twitter4j.oauth.consumerKey", "YOUR_CONSUMER_KEY")
//    System.setProperty("twitter4j.oauth.consumerSecret", "YOUR_CONSUMER_SECRET")
//    System.setProperty("twitter4j.oauth.accessToken", "YOUR_ACCESS_TOKEN")
//    System.setProperty("twitter4j.oauth.accessTokenSecret", "YOUR_ACCESS_SECRET")
//
//    val filters = Array("keyword1", "keyword2")
//    val stream = TwitterUtils.createStream(ssc, None, filters)
//    stream.map(status => status.getText).print()
//    stream.map(status => status.getText).saveAsTextFiles("data/tweets", "txt")
//
//    ssc.start()
//    ssc.awaitTermination()
//  }
//}
