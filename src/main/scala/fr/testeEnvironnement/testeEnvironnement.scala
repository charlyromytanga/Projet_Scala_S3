import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object testeEnvironnement {

  def main(args: Array[String]): Unit = {

    // SparkSession (en remplacement de spark-shell)
    val spark = SparkSession.builder
      .appName("SparkTest")
      .config("spark.master", "local")
      .getOrCreate()

    // Définir le niveau de log
    spark.sparkContext.setLogLevel("WARN")

    // Importation de librairies pour travailler avec des DataFrames
    import spark.implicits._

    // Un RDD
    val data = Seq(4, 5)
    val rdd = spark.sparkContext.parallelize(data)

    // Afficher le contenu de l'RDD
    rdd.collect().foreach(println)

    // Effectuer une opération sur l'RDD (élever les nombres au carré)
    val squared = rdd.map(x => x * x)
    squared.collect().foreach(println)

    // Définir explicitement le schéma (types des colonnes)
    val schema = StructType(Array(
      StructField("Profession", StringType, nullable = true),
      StructField("Expérience", StringType, nullable = true),
      StructField("Salaire Annuel Brut", IntegerType, nullable = true)
    ))

    // Crée un DataFrame (n'utiliser qu'une seule définition pour "df")
    val df = Seq(("Admin Système ","Débutant (0-2 ans)", 35000),
      ("Ingénieur Big Data", "Débutant (0-2 ans)", 50000),
      ("Ingénieur Logiciel", "Débutant (0-2 ans)", 38000)
    ).toDF("Profession", "Expérience", "Salaire Annuel Brut")

    // Afficher le DataFrame
    df.show()

    // Fermeture de SparkSession
    spark.stop()
  }
}
