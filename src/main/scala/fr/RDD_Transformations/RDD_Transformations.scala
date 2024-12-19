import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}


object RDD_Transformations extends App {

    // Création de la configuration Spark
    val conf = new SparkConf().setAppName("RDD Resilience").setMaster("local[*]")

    // Création de la SparkSession en utilisant la configuration
    val spark: SparkSession = SparkSession.builder()
      .config(conf) // Passe la configuration SparkConf
      .getOrCreate()

    // Définir le niveau de log
    spark.sparkContext.setLogLevel("WARN")

    // Importation implicite pour la conversion vers DataFrame
    import spark.implicits._

    // Exemple de RDD
    val rdd = spark.sparkContext.parallelize(Seq(1, 2, 3, 4, 5))
    val squaredRDD = rdd.map(x => x * x)
    val evenRDD = rdd.filter(_ % 2 == 0)

    println(s"RDD original : ${rdd.collect().mkString(", ")}")
    println(s"RDD après map (carré des éléments) : ${squaredRDD.collect().mkString(", ")}")
    println(s"RDD après filter (éléments pairs) : ${evenRDD.collect().mkString(", ")}")
    // 3. Données pour les transformations
    val transformationsData = Seq(
      ("map", "Transformation", "Applique une fonction à chaque élément", "Oui"),
      ("flatMap", "Transformation", "Aplatie les résultats après transformation", "Oui"),
      ("filter", "Transformation", "Filtre les éléments selon une condition", "Oui"),
      ("union", "Transformation", "Fusionne deux RDD", "Oui"),
      ("distinct", "Transformation", "Supprime les doublons d'un RDD", "Oui"),
      ("groupByKey", "Transformation", "Regroupe les paires par clé", "Oui"),
      ("reduceByKey", "Transformation", "Agrège les valeurs par clé", "Oui"),
      ("sortBy", "Transformation", "Trie les éléments d'un RDD", "Oui"),
      ("coalesce", "Transformation", "Réduit le nombre de partitions", "Oui"),
      ("repartition", "Transformation", "Change le nombre de partitions", "Oui")
    )

    // Conversion en DataFrame pour les transformations
    val transformationsDF: DataFrame = transformationsData.toDF("Nom", "Type", "Description", "Paresseux")

    // 4. Affichage sous forme de DataFrame
    println("=== Transformations dans Spark RDD ===")
    transformationsDF.show(truncate = false)

    // Fermer la session Spark
    spark.stop()
  }



/*
Les transformations créent un nouveau RDD en appliquant une logique au RDD d'entrée. Ces transformations
sont paresseuses (lazy), c'est-à-dire qu'elles ne sont exécutées que lorsqu'une action est appelée
*/

