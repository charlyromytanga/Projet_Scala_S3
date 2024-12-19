package fr.cours

object Main {
  def main(args: Array[String]): Unit = {
    println("Scala ok")
  }
  var l=List(1,2,3,4)
  println(l)

  // L'unique méthode qui ne retourne rien Unit et affiche les éléments
  l.foreach(x=>println("Next "+x))

  var s= Set(1,2,3,4) // Set élimine les éléments redondant
  println(s)

  // Map : dictionnaire clé -> valeur. La Map n'est pas modifiable (non mutable)
  var m = Map(1->"One", 2->"tow", 3->"three") // Clé : Int, valeur:String
  println(m(1))

  // Rajout d'élément dans la collection et on cré une nouvelle collection
  var m1 = m+(5->"five")
  println(m1)

  // Prise de note :

  // si on change de clé alors une nouvelle collection est crée avec un écrassement de la précédante

  // On peut aussi acccéder avec la méthode fet
  // m1.get(3) : c'est  mieux que l'accès direct à une Map
  // en cas d'exception alors c'est une None qui est retourné
  // Rq: get donne : Some de ce qu'on lui demande

  // Autre façon d'accéder qui est mieu: m1.getOrElse
  // SI elle trouve la clé alors elle donne la valeur de la clé
  // Si elle ne trouve pas alors elle donne une valeur de subsitution : "Not Found"

  // Autre collection : var l=(1 to 10)
  // Qui retourne un range : un intervalle
  // Range est non mutable et on début les indices par zéro

  // Autre collection Array : est mutable

  // En scala
  // ON peut changer une liste et lui donner une autre statut
  // Ex  var r = Range(1,2,3,4)
  // On lui enchange en liste
  // var lBis = r.toList

  // Fonction qui imprime dans l'ordre décroissant à partir de 10 à 1
  //var r1 = (1 to 10)
  // var r1list = r1.toListe.reverse.foreach(println))


  // La map distribut une fonction sur chaque élément dans la collection
  // l.map(x=>fonction appliquer sur chaque élément de la collection))


  /*
    def values(f:Int=>Int, low:Int, high:Int)={
      (low to high).toList.map(x=>(x, f(x))
    }
     */

  /* application
  val square:Int=>Int=x=>x*x // f(x) = le carré
  val inc:Int=>Int=x=>x+1 // f(x) = x+1
  values(square, -2, 5)
   */

  /*
  Révision

  var l=List(5,6,7,-1)
  l.map(x=>1.0/x) chaque x sera remplacé par 1/x
  // donc map est une fonction d'odre supérieur parce qu'elle prend une fonction
  // comme paramètre ex l.map(x=>x*x)

  // Question : peut-on passer somm_2 à map ? Non car somm_2 accpete deux paramètre
  // Or map n'accpete qu'un seul paramètre
 // cad si on aps List(1,2,3) et l.map(x=>f(x)) alors List(f(1), f2), f(3))
   */

  /*
  Traduction Fr à  l'Ang
  val dic=Map("le"->"the")
  var phrase = "Le chat mange"
  phrase.toLowerCase // conversion en miniscule
  phrase.split(" ") // éclate la chaîne de caract à chaque fois qu'il trouve un espace
  // Ansi il retourne : Array(le, chat, mange)

  // phrase.map(mot=>dic.getOrElse(mot, "xxxx")) // je cherche le mot et si le trouve alors
  je le remplace par sa valeur ici traduction en Ang et s'il ne trouve pas alors xxxx

  // L'inverse de split pour concatener les versions traduites Ang
  phrase.mkString(" ")

  // Rq : Un dictionnaire c'est un accès directe pour donner la valeur donc il n y a pas de for

   */



  // Séance 22/11/2024 :
  /* Révision */
  /*
  object Main {

      def translatebis(dic.Map[String, String]
      def main(args: Array[String]): Unit = {
        println("Scala ok")
      }

  }


  import scala.collection.immutable.IntMap.Nil.foldLeft

  object Main {
    def translate(dic: Map[String, String], phrase: String)  = {    //    miniscule    liste de mot  traduction dans dic                      fusion de la liste pour en faire un string
      phrase.toLowerCase().split(" ").map(word => dic.getOrElse(word, "NotFound")).mkString(" ")  }

    def translateBis(dic: Map[String, String], phrase: String) = { //    miniscule    liste de mot  traduction dans dic                      fusion de la liste pour en faire un string
      //phrase.toLowerCase().split(" ").map(word => dic.getOrElse(word, "NotFound")).mkString(" ")

    }

    def main(args: Array[String]): Unit = {
      var dic = Map("le" -> "the",      "chat" -> "cat",
        "mange" -> "eats",      "une" -> "a",
        "viande" -> "meat",      "poisson" -> "fish")
      var phrase = "Le chat mange du poisson"
      var sentence = translate(dic, phrase)
      println(sentence)
    }
  }
   */


  /*
  // Les classes sont indépendantes et donc à l'extérieures de Mainabstract class User (lastName:String, name:String, age:Int)
  case class Student (lastName:String, name:String, age:Int, schoolYear:Int) extends User(lastName, name, age)case class Teacher (lastName:String, name:String, age:Int, seniority:Int) extends User(lastName, name, age)
  object Main {
    // Elle filtre une liste d'étudiants par rapport à une année donnée  def studentByYear(students:List[Student], year:Int) = {
      students.filter(student => student.schoolYear == year)  }
    // Trie (croissant) des étudiants selon leurs âge
    def sortStudentByYear(students:List[Student]) = {    students.sortBy(student => student.age)
    }
    // Moyenne d'âge des étudiants  def meanAgeStudent(students:List[Student]) = {
      //students.foldLeft(0)((acc, student) => acc + student.age) / students.size    students.map(student => student.age).sum / students.size
    }
    def main(args: Array[String]): Unit = {    val s1 = new Student("Paul", "Charles", 20, 1)
      val s2 = new Student("Julie", "sdfgihjo", 17, 3)    val s3 = new Student("Rémi", "gvhj", 19, 1)
      val students = List(s1, s2, s3)
      println(students)    println(studentByYear(students, year = 1)) // = Paul et Rémi
      println(meanAgeStudent(students)) // = 18    println(sortStudentByYear(students)) // Julie < Rémi < Paul
    }}
   */


  // Import des bibliothèques nécessaires


  /*
  package com.charly

  import scala.collection.immutable.IntMap.Nil.foldLeft
  abstract class User(lastName:String,firstName:String,age:Int)

  case class Teacher(lastName:String,firstName:String,age:Int,seniority:Int) extends User(lastName,firstName,age)
  case class Student(lastName:String,firstName:String,age:Int,schoolYear:Int) extends User(lastName,firstName,age)

  object Main {
    def meanAgeStudent(students : List[Student])={students.foldLeft(0)((acc, age) => students.get)}

    def main(args: Array[String]): Unit = {

      val Paul = new Teacher("Paul", "Paul", 22, 4)
      val Anne = new Teacher("Anne", "Anne", 30, 10)
      val Ali = new Teacher("Ali", "Ali", 35, 15)
      val teachers = List(Paul, Anne, Ali)

      val Mari = Student("Mari", "Mari", 17, 1)
      val Mathieu = Student("Mathieu", "Mathieu", 16, 1)
      val Amina = Student("Amina", "Amina", 37, 1)
      val students = List(Mari, Mathieu, Amina)
    }
  }

  def translate_bis(dic: Map[String, String], phrase: String) = {
    phrase.toLowerCase().split(" ").foldRight("")((mot, acc) => dic.getOrElse(mot, "") + " " + acc)
  }
   */

  /*
  import scala.collection.parallel.immutable.ParSeq
  import org.apache.spark.sql.{SparkSession, DataFrame}


  object Main {
    def main(args: Array[String]): Unit = {

      def isPrime(x: Int): Boolean = {
        (2 to Math.sqrt(x).toInt).forall(p => x % p != 0)
      }

      isPrime(17)
      var l = (1 to 1000).toList.par

      def countPrimePure(l:ParSeq[Int])={
        l.filter(x=>isPrime(x)).size
      }

    }
  }

   */
}


/*

import org.apache.spark.sql.{SparkSession, DataFrame}

object Main extends App{
  def main(args: Array[String]): Unit = {
    // Initialisation de la SparkSession
    val spark = SparkSession.builder
      .appName("Simple DataFrame Example")
      .master("local[*]") // Utilise toutes les ressources locales disponibles
      .getOrCreate()

    // Configuration du niveau de log pour éviter les messages inutiles
    spark.sparkContext.setLogLevel("WARN")

    // Exemple de données : une liste de tuples
    val data = Seq(
      ("Alice", 28, "Engineer"),
      ("Bob", 34, "Doctor"),
      ("Catherine", 29, "Data Scientist")
    )

    // Création de la DataFrame à partir des données
    val df: DataFrame = spark.createDataFrame(data)
      .toDF("Name", "Age", "Profession") // Nom des colonnes

    // Affichage du contenu de la DataFrame
    println("Affichage de la DataFrame :")
    df.show()

    // Affichage du schéma de la DataFrame
    println("Affichage du schéma de la DataFrame :")
    df.printSchema()

    // Arrêt de SparkSession
    spark.stop()
  }
}

 */