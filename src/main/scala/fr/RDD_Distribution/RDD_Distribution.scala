import org.apache.spark.{SparkConf, SparkContext}

object RDD_Distribution extends App {
  val conf = new SparkConf().setAppName("RDD Distribution").setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")

  // RDD partitionné en 3
  val rdd = sc.parallelize(Seq(1, 2, 3, 4, 5, 6), numSlices = 3)
  println(s"Nombre de partitions : ${rdd.getNumPartitions}")

  // Afficher les données par partition
  val partitionedData = rdd.mapPartitionsWithIndex((index, data) => {
    data.map(value => s"Partition: $index, Value: $value")
  })
  partitionedData.collect().foreach(println)

  sc.stop()
}

/*
Les RDD sont distribués, ce qui signifie que leurs données sont partitionnées et traitées parallèlement
sur plusieurs nœuds (ou cœurs locaux si exécuté sur une machine locale).
 */