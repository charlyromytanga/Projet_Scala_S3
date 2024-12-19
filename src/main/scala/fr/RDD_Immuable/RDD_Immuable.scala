import org.apache.spark.{SparkConf, SparkContext}

object RDD_Immuable extends App {
  val conf = new SparkConf().setAppName("RDD Immutability").setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")

  val rdd = sc.parallelize(Seq(1, 2, 3, 4, 5))
  println(s"RDD original : ${rdd.collect().mkString(", ")}")

  // Transformation : ajouter 1 à chaque élément
  val newRDD = rdd.map(_ + 1)
  println(s"RDD après transformation (nouveau RDD) : ${newRDD.collect().mkString(", ")}")

  // Le RDD original reste inchangé
  println(s"RDD original reste inchangé : ${rdd.collect().mkString(", ")}")

  sc.stop()
}

/*
Un RDD est immuable, ce qui signifie qu'une fois créé, son contenu ne peut pas être modifié.
Cependant, vous pouvez appliquer des transformations pour créer de nouveaux RDD à partir d'un RDD existant.
 */