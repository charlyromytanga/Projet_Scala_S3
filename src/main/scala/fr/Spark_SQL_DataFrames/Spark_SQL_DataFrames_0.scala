import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.spark.sql.DataFrame

object Spark_SQL_DataFrames_0 {

  def main(args: Array[String]): Unit = {

    // 1. Création de la session Spark
    val spark = SparkSession.builder
      .appName("Spark SQL Example")
      .config("spark.master", "local")
      .getOrCreate()

    // Définir le niveau de log
    spark.sparkContext.setLogLevel("WARN")

    // 2. Définir un DataFrame à partir de données structurées
    val data = Seq(
      ("Alice", 29, "F", 3000),
      ("Bob", 35, "M", 4000),
      ("Charlie", 30, "M", 3500),
      ("David", 40, "M", 4500),
      ("Eva", 25, "F", 3200)
    )

    // Création du DataFrame à partir de la séquence
    val df = spark.createDataFrame(data).toDF("Name", "Age", "Gender", "Salary")

    // 4. Enregistrer le DataFrame comme une vue temporaire pour exécuter des requêtes SQL
    df.createOrReplaceTempView("people")

    // 5. Requête SQL sur le DataFrame via Spark SQL
    val resultSQL: DataFrame = spark.sql("SELECT Name, Age, Salary FROM people WHERE Age > 30")

    // 7. Importer les implicites pour utiliser l'opérateur `$`
    import spark.implicits._

    // 8. Utilisation de DataFrame API pour des opérations similaires
    val resultDF = df.filter($"Age" > 30).select("Name", "Age", "Salary")

    // 10. Effectuer une agrégation pour obtenir la moyenne des salaires
    val avgSalary = df.groupBy("Gender").agg(F.avg("Salary").alias("AverageSalary"))


    // 3- Affichage 1 : le DataFrame
    println("DataFrame Original :")
    df.show()

    // 6- Affichage 2 : résultats de la requête SQL
    println("Résultats de la requête SQL (âge > 30) :")
    resultSQL.show()

    // 9- Affichage 3 : résultats de l'opération avec DataFrame API
    println("Résultats avec DataFrame API (âge > 30) :")
    resultDF.show()

    // 11- Affichage 4 : résultat de l'agrégation
    println("Moyenne des salaires par genre :")
    avgSalary.show()

    // 12. Fermeture de la session Spark
    spark.stop()
  }
}
