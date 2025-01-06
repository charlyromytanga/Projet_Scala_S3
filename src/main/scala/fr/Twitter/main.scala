package fr.Twitter
import fr.Twitter.source_fictive
import fr.Twitter.Preprocessing
import fr.Twitter.SentimentAnalysis
import fr.Twitter.Aggregation
import fr.Twitter.CassandraStorage

import org.apache.spark.sql.SparkSession

object main {
  def main(args: Array[String]): Unit = {
    // Vérification des arguments d'entrée
    if (args.length < 1) {
      System.err.println("Usage: main <path_to_csv_directory>")
      System.exit(1)
    }

    val csvDirectory = args(0)

    println("Étape 1 : Simulation du flux depuis les CSVs")
    source_fictive.main(Array(csvDirectory))

    println("Étape 2 : Prétraitement des données")
    Preprocessing.main(args)

    println("Étape 3 : Analyse des sentiments")
    SentimentAnalysis.main(args)

    println("Étape 4 : Agrégation des résultats")
    Aggregation.main(args)

    println("Étape 5 : Stockage des résultats dans Cassandra")
    CassandraStorage.main(args)

    println("Pipeline complet exécuté avec succès.")
  }
}
