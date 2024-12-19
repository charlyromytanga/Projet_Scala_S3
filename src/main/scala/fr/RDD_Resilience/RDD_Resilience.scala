import org.apache.spark.{SparkConf, SparkContext}

object RDD_Resilience extends App {
  val conf = new SparkConf().setAppName("RDD Resilience").setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")

  val rdd = sc.parallelize(Seq(1, 2, 3, 4, 5), numSlices = 2)
  println(s"RDD initial : ${rdd.collect().mkString(", ")}")

  // Simulation d'une transformation
  val transformedRDD = rdd.map(x => if (x == 3) throw new RuntimeException("Erreur simulée") else x * 2)

  try {
    println(s"Résultat après transformation : ${transformedRDD.collect().mkString(", ")}")
  } catch {
    case e: Exception =>
      println(s"Une erreur s'est produite : ${e.getMessage}")
  }

  // La résilience permet de recréer le RDD depuis son origine
  println("Le RDD peut être recréé à partir de son ligneage.")
  sc.stop()
}


/*
Les RDD sont résilients en cas de défaillance grâce à leur ligneage, c'est-à-dire leur capacité à reconstruire
les partitions perdues en utilisant l'historique des transformations appliquées.
 */

/*
la méthode toDebugString sur le RDD. Cette méthode affiche l'historique des transformations appliquées sur un RDD
pour permettre sa reconstruction en cas de panne ou d'erreur.
 */

/*

import org.apache.spark.{SparkConf, SparkContext}

object RDD_Resilience extends App {
  val conf = new SparkConf().setAppName("RDD Resilience").setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")

  val rdd = sc.parallelize(Seq(1, 2, 3, 4, 5), numSlices = 2)
  println(s"RDD initial : ${rdd.collect().mkString(", ")}")

  // Simulation d'une transformation
  val transformedRDD = rdd.map(x => if (x == 3) throw new RuntimeException("Erreur simulée") else x * 2)

  try {
    println(s"Résultat après transformation : ${transformedRDD.collect().mkString(", ")}")
  } catch {
    case e: Exception =>
      println(s"Une erreur s'est produite : ${e.getMessage}")
  }

  // Afficher le lineage du RDD
  println("Lineage du RDD transformé :")
  println(transformedRDD.toDebugString)

  // La résilience permet de recréer le RDD depuis son origine
  println("Le RDD peut être recréé à partir de son lineage.")
  sc.stop()
}
*/
