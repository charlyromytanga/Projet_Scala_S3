import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

// Définition des collections RDD

object RDD_Collections extends App {
  // Configuration Spark
  val conf = new SparkConf().setAppName("RDD Collections").setMaster("local[*]")
  val spark = SparkSession.builder().config(conf).getOrCreate()
  val sc = spark.sparkContext // Récupération de SparkContext
  sc.setLogLevel("WARN")

  println("=== Exemple ParallelCollectionRDD ===")
  val parallelRDD = sc.parallelize(Seq(1, 2, 3, 4, 5))
  println(s"ParallelCollectionRDD : ${parallelRDD.collect().mkString(", ")}")

  println("\n=== Exemple MapPartitionsRDD ===")
  val mapPartitionsRDD = parallelRDD.map(_ * 2)
  println(s"MapPartitionsRDD (map transformation) : ${mapPartitionsRDD.collect().mkString(", ")}")

  println("\n=== Exemple UnionRDD ===")
  val rdd1 = sc.parallelize(Seq(1, 2, 3))
  val rdd2 = sc.parallelize(Seq(4, 5, 6))
  val unionRDD = rdd1.union(rdd2)
  println(s"UnionRDD (union des RDDs) : ${unionRDD.collect().mkString(", ")}")

  println("\n=== Exemple CartesianRDD ===")
  val cartesianRDD = rdd1.cartesian(rdd2)
  println(s"CartesianRDD (produit cartésien) : ${cartesianRDD.collect().mkString(", ")}")

  println("\n=== Exemple PairRDD ===")
  val pairRDD = sc.parallelize(Seq(("a", 1), ("b", 2), ("a", 3)))
  val reducedPairRDD = pairRDD.reduceByKey(_ + _)
  println(s"PairRDD (reduceByKey) : ${reducedPairRDD.collect().mkString(", ")}")

  println("\n=== Exemple HadoopRDD ===")
  println("HadoopRDD (nécessite un fichier Hadoop) : Exemple non exécuté ici pour éviter des dépendances Hadoop.")

  println("\n=== Exemple WholeTextFileRDD ===")
  println("WholeTextFileRDD (lit des fichiers textes complets) : Exemple non exécuté ici sans système de fichiers.")

  println("\n=== Exemple CoGroupedRDD ===")
  val rdd3 = sc.parallelize(Seq(("a", 1), ("b", 2)))
  val rdd4 = sc.parallelize(Seq(("a", "x"), ("b", "y")))
  val cogroupedRDD = rdd3.cogroup(rdd4)
  println(s"CoGroupedRDD (groupement de deux RDDs) : ${cogroupedRDD.collect().mkString(", ")}")

  println("\n=== Exemple ShuffledRDD ===")
  val shuffledRDD = rdd1.map(x => (x, x)).repartition(2)
  println(s"ShuffledRDD (repartition avec shuffle) : ${shuffledRDD.collect().mkString(", ")}")

  // Import implicite pour utiliser les DataFrames
  import spark.implicits._

  // Définition des types de RDD et de leur description
  val rddCollectionsData = Seq(
    ("ParallelCollectionRDD", "RDD", "Représente un RDD créé à partir d'une collection parallèle"),
    ("MapPartitionsRDD", "RDD", "Représente un RDD obtenu après une transformation map"),
    ("UnionRDD", "RDD", "Représente un RDD résultant de l'union de plusieurs RDDs"),
    ("CartesianRDD", "RDD", "Représente le produit cartésien de deux RDDs"),
    ("PairRDD", "Key-Value RDD", "Représente des RDDs avec des paires (clé, valeur)"),
    ("HadoopRDD", "RDD", "Représente un RDD issu de la lecture de données Hadoop"),
    ("WholeTextFileRDD", "RDD", "Représente un RDD où chaque fichier est lu comme un texte unique"),
    ("CoGroupedRDD", "Key-Value RDD", "Représente un RDD obtenu après un groupement par clé"),
    ("ShuffledRDD", "RDD", "Représente un RDD où les données ont été redistribuées entre partitions")
  )

  // Conversion en DataFrame pour affichage
  val rddCollectionsDF: DataFrame = rddCollectionsData.toDF("Nom", "Type", "Description")

  // Affichage des collections RDD
  println("=== Collections RDD disponibles dans Spark ===")
  rddCollectionsDF.show(truncate = false)

  // Fermeture de SparkSession
  spark.stop()
}
