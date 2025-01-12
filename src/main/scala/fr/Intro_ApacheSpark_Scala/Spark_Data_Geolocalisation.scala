import org.apache.spark.sql.{SparkSession, functions => F}
import org.apache.spark.sql.types._

object Spark_Data_Geolocalisation {
  def main(args: Array[String]): Unit = {
    // Initialiser SparkSession
    val spark = SparkSession.builder
      .appName("GeoTemporalAnalysis")
      .config("spark.master", "local")
      .getOrCreate()

    // Affichage des logs au niveau WARN pour éviter les messages de debug
    spark.sparkContext.setLogLevel("WARN")

    import spark.implicits._

    // Schéma des données géospatiales et temporelles
    val schema = StructType(Array(
      StructField("VehicleID", StringType, nullable = false),
      StructField("Timestamp", StringType, nullable = false), // Format : "YYYY-MM-DD HH:MM:SS"
      StructField("Latitude", DoubleType, nullable = false),
      StructField("Longitude", DoubleType, nullable = false)
    ))

    // Données simulées
    val data = Seq(
      ("V1", "2025-01-10 08:00:00", 48.8566, 2.3522), // Paris
      ("V1", "2025-01-10 09:00:00", 48.8584, 2.2945), // Tour Eiffel
      ("V2", "2025-01-10 10:00:00", 51.5074, -0.1278), // Londres
      ("V2", "2025-01-10 11:00:00", 51.5094, -0.1182)  // Londres - Charing Cross
    )

    // Création du DataFrame
    val df = spark.createDataFrame(data).toDF("VehicleID", "Timestamp", "Latitude", "Longitude")

    // Conversion de l'horodatage en type Timestamp
    val dfWithTimestamp = df.withColumn("Timestamp", F.to_timestamp($"Timestamp", "yyyy-MM-dd HH:mm:ss"))

    // Filtrer les trajets après 9h00
    val filteredDF = dfWithTimestamp.filter(F.hour($"Timestamp") > 9)

    // Afficher les données filtrées
    println("Trajets après 9h00 :")
    filteredDF.show()

    // Calcul simple de distance (approximation)
    // Formule Haversine pour calculer la distance en km entre deux points géographiques
    def haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double = {
      val R = 6371 // Rayon de la Terre en km
      val dLat = Math.toRadians(lat2 - lat1)
      val dLon = Math.toRadians(lon2 - lon1)
      val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
          Math.sin(dLon / 2) * Math.sin(dLon / 2)
      val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
      R * c
    }

    // UDF pour utiliser la formule Haversine
    val haversineUDF = F.udf(haversine _)

    // Ajouter une colonne calculant la distance entre chaque point
    val withDistance = dfWithTimestamp.withColumn("Distance",
      haversineUDF(
        $"Latitude",
        $"Longitude",
        F.lag($"Latitude", 1).over(org.apache.spark.sql.expressions.Window.partitionBy($"VehicleID").orderBy($"Timestamp")),
        F.lag($"Longitude", 1).over(org.apache.spark.sql.expressions.Window.partitionBy($"VehicleID").orderBy($"Timestamp"))
      )
    )

    // Afficher les distances
    println("Données avec distance calculée :")
    withDistance.show()

    // Fermeture de la session Spark
    spark.stop()
  }
}
