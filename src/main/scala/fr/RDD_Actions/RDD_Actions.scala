import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object RDD_Actions extends App {
  // Configuration de Spark
  val conf = new SparkConf().setAppName("RDD Actions").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  spark.sparkContext.setLogLevel("WARN")

  // Import implicite pour les DataFrames
  import spark.implicits._

  // Exemple de RDD
  val rdd = spark.sparkContext.parallelize(Seq(1, 2, 3, 4, 5))

  // Exemples d'actions
  println(s"Collect : ${rdd.collect().mkString(", ")}") // Action collect
  println(s"Count : ${rdd.count()}")                   // Action count
  val sum = rdd.reduce(_ + _)                          // Action reduce
  println(s"Reduce (somme) : $sum")
  println(s"First : ${rdd.first()}")                   // Action first

  // Données pour le tableau des actions
  val actionsData = Seq(
    ("collect", "Action", "Retourne tous les éléments du RDD sous forme de tableau local.", "Déclencheur"),
    ("count", "Action", "Compte le nombre d'éléments dans le RDD.", "Déclencheur"),
    ("reduce", "Action", "Applique une fonction d'agrégation sur les éléments.", "Déclencheur"),
    ("first", "Action", "Retourne le premier élément du RDD.", "Déclencheur"),
    ("take", "Action", "Retourne les n premiers éléments.", "Déclencheur"),
    ("foreach", "Action", "Applique une fonction à chaque élément du RDD.", "Déclencheur")
  )

  // Conversion en DataFrame
  val actionsDF: DataFrame = actionsData.toDF("Nom", "Type", "Description", "Déclencheur")

  // Affichage du DataFrame des actions
  println("=== Actions dans Spark RDD ===")
  actionsDF.show(truncate = false)

  // Fermeture de la session Spark
  spark.stop()
}


/*
Les actions calculent une valeur ou effectuent une opération finale sur un RDD.
Elles déclenchent l'exécution des transformations précédentes.
 */