import org.apache.spark.sql.SparkSession

object Spark_Analyse_Data_Structure {
  def main(args: Array[String]): Unit = {
    // Création d'un SparkSession, équivalent de l'interface spark-shell
    val spark = SparkSession.builder
      .appName("IntroductionApacheSpark")
      .config("spark.master", "local") // Exécution locale, idéal pour les tests
      .getOrCreate()

    // Affichage des logs au niveau WARN pour éviter les messages de debug
    spark.sparkContext.setLogLevel("WARN")

    // Importation de librairies pour travailler avec des DataFrames
    import spark.implicits._

    // Création de données simples sous forme d'un DataFrame
    val data = Seq(
      ("Alice", 34, "Masseuse"),
      ("Bob", 45, "Ingénieur"),
      ("Charlie", 29, "Développeur")
    )

    // Convertir en DataFrame et donner des noms de colonnes
    val df = spark.createDataFrame(data).toDF("Name", "Age", "Job")

    // Affichage des données
    df.show()

    // Exemple de transformation : sélectionner les personnes de plus de 30 ans
    val filteredDF = df.filter($"Age" > 30)

    // Affichage du DataFrame filtré
    filteredDF.show()

    // Arrêt du SparkSession
    spark.stop()
  }
}
