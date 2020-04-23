 <h1 align=center> DevOpsProject </h1>

[![Build Status](https://travis-ci.com/chouaibMo/DevOpsProject.svg?branch=master)](https://travis-ci.com/chouaibMo/DevOpsProject)
[![codecov](https://codecov.io/gh/chouaibMo/DevOpsProject/branch/master/graph/badge.svg)](https://codecov.io/gh/chouaibMo/DevOpsProject)



## Préembule 

Les Dataframes sont des tableaux en deux dimensions où chaque colonne est identifiée par un label et chaque ligne par un index. Chaque colonne stocke des données d’un seul type. Cependant deux colonnes différentes peuvent stocker des types différents.  
Pandas est une des bibliotèques les plus populaires (pour langage Python). Elle permet de traiter de grandes quantités de données simplement et efficacement (plus d'informations sur https://pandas.pydata.org).   
L’objectif de ce projet est d'implémenter une sous-partie des fonctionnalitées offertes par Pandas en Java.

## Documentation : Javadoc
La documentation java de cette bibliothèque peut facilement etre génerée en executant la commande suivante à la racine du projet : 
```sh
mvn javadoc:javadoc
```
Vous trouverez la documentation génerée par cette commande dans le repertoire `target/site/apidocs`.


## Description des fonctionnalitées
Toutes les fonctionnalitées présentées dans cette section ont été inspirée de la bibliothèque Pandas.

### 1- Création d'un Dataframe
Cette bibliotèque permet d'instancier un Dataframe de 3 manières différentes :

- Création d'un Dataframe vide qui pourra etre rempli par la suite en y ajoutant des colonnes.

- Création d'un Dataframe à partie d'une structure de données (HashMap).
Chaque entrée de cette structure est représentée par une clé (nom de colonne), et une valeur (liste des valeur de la colonne).   
**Attention** : Toutes les colonnes doivent etre de la meme tailles, c'est a dire contenir le meme nombre d'elements.

- Création d'un dataframe à partir d'un fichier csv. Le fichier csv doit contenir en première ligne le type de chaque colonne.
Quelques fichiers csv sont disponibles dans le répertoire `src/main/ressources/`.   
**Attention** : Toutes les colonnes doivent etre de la meme tailles, c'est a dire contenir le meme nombre d'elements.


### 2- Ajout/Suppression de données dans un Dataframe
La bibliothèque fournis différentes techniques pour modifier le contenu d'un Dataframe :
```
- Suppression d'une colonne suivant son index : dropColumn(int)
- Suppression d'une colonne suivant son label : dropColumn(String)
- Suppression de la dernière colonne du Dataframe : pop()
- Insertion d'un colonne : insertColumn(Column)
- Insertion d'une ligne  : insertRow(String[])
```

### 3- Affichage d'un Dataframe
Il existe 4 manières différentes pour afficher le contenu d'un Dataframe :
```
- Afficher le contenu intégral     : `fetchAll`
- Afficher le contenu d'un interval: `fetchFromTo(int,int)`
- Afficher les n premières lignes  : `head(int)`
- Afficher les n dernières lignes  : `tail(int)`
```
### 4- Sélections dans un Dataframe
Il existe 2 manières différentes pour réaliser une selection dans un Dataframe.
Le résultat de la sélection est un nouveau Dataframe : 

- 
- 

### 5- Fonctions statistiques sur un Dataframe
Il existe 4 fonctions statistiques, applicables sur les colonnes numériques d'un Dataframe (Integer, Double ou Float). Ces fonctions prennent en parametre le nom (label) d'une colonne : 
```
- Le minimum : `min(String)`
- Le maximum : `max(String)`
- La somme   : `sum(String)`
- La moyenne : `mean(String)`
```
La fonction printStats permet d'afficher le resultat de ces fonctions sous forme d'un tableau.

## Description des outils utilisés
-  **Maven 4.0.0**: Comme outils d'automatisation de developpement
-  **JUnit 4.13** : Pour les tests unitaires
-  **GitHub**     : Comme service de dépot git pour ce projet
-  **Travis CI**  : Pour l'integration continue
-  **JaCoCo**     : Pour évaluer la couverture du code
-  **CodeCov**    : Permet d'avoir un badge sur GitHub avec le pourcentage de couverture
-  **DockerHub**  : Comme service de dépot pour notre image Docker


De plus, 2 dépendances maven sont utilisées : 

- **OpenCSV 3.8**      : Pour simplifier la lecture des fichiers csv (plus d'info sur http://opencsv.sourceforge.net)
- **Asciitable 0.3.2** : Pour afficher proprement le dataframe sous forme de tableau (plus d'infos sur https://github.com/vdmeer/asciitable)

On utilise également le plugin maven `maven-shade-plugin` pour génerer un jar executable pour lancer le main de démontration (pour plus d'infos http://maven.apache.org/plugins/maven-shade-plugin/)

## Docker image - Docker Hub

-
-

## Mode d'emploi

Pour lancer les tests unitaire, executer la commande suivante à la racine du projet :
```sh
$ mvn test
```
Pour lancer les tests unitaire et génerer le jar, executer la commande suivante à la racine du projet :
```sh
$ mvn package
```
Pour lancer le main de demonstration, il faut choisir un fichier csv dans le répertoire `src/main/ressources/` (file.csv par exemple) et executer ensuite une des commandes suivantes à la racine du projet :
```sh
$ mvn exec:java -Dexec.args="src/main/ressources/file.csv"
```
```sh
$ java -jar target/DataframeMain.jar src/main/ressources/file.csv
```

Pour génerer la javadoc, executer la commande suivante à la racine du projet :

```sh
$ mvn javadoc:javadoc
```
## FeedBack

-
-
