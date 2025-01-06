package fr.Twitter

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.sql.SparkSession

case class Tweet(text: String) // Définition correcte de la case class

object source_fictive {
  def main(args: Array[String]): Unit = {
    // Vérifier les arguments d'entrée
    if (args.length < 1) {
      System.err.println("Usage: SimulatedTweetStreamFromDirectory <path_to_directory>")
      System.exit(1)
    }

    val directoryPath = args(0)

    // Configuration Spark Streaming
    val conf = new SparkConf().setAppName("SimulatedTweetStreamFromDirectory").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    val spark = SparkSession.builder.config(conf).getOrCreate()

    // Lire tous les fichiers CSV du dossier spécifié
    val tweets = spark.read.option("header", "true").csv(directoryPath + "/*.csv").as[Tweet]
    tweets.createOrReplaceTempView("tweets")

    // Simulation du flux de données
    println("Simulation des tweets depuis les CSVs dans le dossier :")
    tweets.select("text").rdd.foreach(println)

    ssc.start()
    ssc.awaitTermination()
  }
}
