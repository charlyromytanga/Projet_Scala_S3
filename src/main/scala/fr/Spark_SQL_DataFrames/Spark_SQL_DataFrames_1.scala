import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object Spark_SQL_DataFrames_1 {

  def main(args: Array[String]): Unit = {
    // Création de la SparkSession
    val spark = SparkSession.builder()
      .appName("Spark SQL and DataFrames Example")
      .master("local[*]") // Utilisation du mode local
      .getOrCreate()

    // Définir le niveau de log
    spark.sparkContext.setLogLevel("WARN")

    // Lecture d'un fichier CSV dans un DataFrame
    val filePath = "Data/data.csv" // Le chemin des données
    val df = spark.read
      .option("header", "true") // Considère la première ligne comme des en-têtes
      .option("inferSchema", "true") // Infère automatiquement le type des colonnes
      .csv(filePath)

    // Afficher les 5 premières lignes
    println("=== Affichage des données ===")
    df.show(5)

    // Afficher le schéma du DataFrame
    println("=== Schéma des données ===")
    df.printSchema()

    // Enregistrer le DataFrame comme une table temporaire
    df.createOrReplaceTempView("table_auto")

    // Exécution d'une requête SQL
    println("=== Exécution d'une requête SQL ===")
    val sqlResult = spark.sql("SELECT Annee, COUNT(*) AS Nb_Auto_annee FROM table_auto GROUP BY Annee")
    sqlResult.show()

    // Application de transformations sur le DataFrame
    println("=== Transformations sur le DataFrame ===")
    val transformedDF = df
      .filter("Prix > 100000") // Filtre les lignes où le prix > 100000
      .select("Annee","Marque", "Modele", "Prix") // Sélectionne  l'année, la marque, le modèle et le prix
      .orderBy("Annee") // Trie par Marque

    transformedDF.show()

    // Fin de la SparkSession
    spark.stop()
  }
}
