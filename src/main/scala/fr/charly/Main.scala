package fr.charly


import org.apache.spark.sql.{DataFrame, SparkSession}

object Main extends App {
  val spark = SparkSession.builder
    .appName("Simple DataFrame Example")
    .master("local[*]") // Utilise toutes les ressources locales disponibles
    .getOrCreate()

  // Configuration du niveau de log pour éviter les messages inutiles
  spark.sparkContext.setLogLevel("WARN")

  // Exemple de données : une liste de tuples
  val data = Seq(
    ("Alice", 28, "Engineer"),
    ("Bob", 34, "Doctor"),
    ("Catherine", 29, "Data Scientist")
  )

  // Création de la DataFrame à partir des données
  val df: DataFrame = spark.createDataFrame(data)
                            .toDF("Name", "Age", "Profession") // Nom des colonnes

  // Affichage du contenu de la DataFrame
  println("Affichage de la DataFrame :")
  df.show()

  // Affichage du schéma de la DataFrame
  println("Affichage du schéma de la DataFrame :")
  df.printSchema()



  // Arrêt de SparkSession
  spark.stop()
}
