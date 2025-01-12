// Définition de la version du projet et de la version de Scala utilisée
ThisBuild / version := "0.1.0-SNAPSHOT"            // Version du projet
ThisBuild / scalaVersion := "2.13.15"              // Version de Scala

// Définition du projet racine
lazy val root = (project in file("."))              // Le projet racine situé dans le répertoire actuel
  .settings(
    name := "proj_sbt_scala_cmd"                    // Nom du projet
  )

// Ajout des dépendances nécessaires pour Spark avec Scala 2.13
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.0",    // Core Spark : fournit les fonctionnalités principales de Spark
  "org.apache.spark" %% "spark-sql" % "3.3.0",     // Spark SQL : pour travailler avec des DataFrames et exécuter des requêtes SQL
  "org.apache.spark" %% "spark-mllib" % "3.3.0",   // Spark MLlib : pour les algorithmes d'apprentissage automatique
  "org.apache.spark" %% "spark-streaming" % "3.3.0" // Spark Streaming : pour le traitement des données en temps réel
)

