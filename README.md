# Projet Scala S3 - proj_sbt_scala_cmd

## Description

Ce projet est un projet Scala utilisant SBT pour la gestion des dépendances et de la configuration. Il intègre Apache Spark pour l'analyse de données distribuées, avec une configuration de logging spécifique et un environnement de développement sous IntelliJ IDEA.

## Structure du Projet                                           
# Fichier README pour la documentation du projet

    PROJET : proj_sbt_scala_cmd 

# Projet Scala utilisant SBT pour la gestion des dépendances et de la configuration
    ├── SBT                     # Gestion des dépendances et de la configuration
    │   ├── build.sbt           # Version de Scala 2.13.15 et Spark 3.3.0 pour l'analyse de données distribuées 
    ├── LOGGING                 # Configuration des logs pour Spark et Hadoop 
    │   ├── log4j.properties    # Niveau des logs configuré pour afficher les messages d'avertissement et plus graves 
    ├── ENVIRONMENT             # Environnement de développement sous IntelliJ IDEA 
    │ ├── .bsp                  # Configuration de build tools pour l'IDE 
    │ ├── .idea                 # Configuration spécifique à l'IDE IntelliJ
    │ ├── project               # Fichiers de configuration liés à SBT
    ├── src                     # Code source du projet
    ├── target                  # Fichiers générés par le processus de compilation
    ├── .gitattributes          # Fichier de configuration Git
    ├── .gitignore              # Liste des fichiers et répertoires à ignorer par Git
    └── README.md               # Fichier README pour la documentation du projet
