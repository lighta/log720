#
# Script pour "epurer" les variables d'environnement PATH et CLASSPATH
# et demarrer une coquille BASH utilisable pour executer les exemples
# fournis avec le cours et realiser les laboratoires du cours.
#
# Destine aux etudiants de l'ETS. Conseil: copiez ce script a un endroit ou
# vous avez le plein controle et creez un raccourci vers celui-ci sur votre
# "bureau" Windows, ce sera utile durant les laboratoires tout au long de la
# session.
#
# Tous les repertoires utilises dans ce script refletent l'installation des
# logiciels dans les laboratoires du Departement.
#
# Le script est divise en sections car utilise dans plusieurs cours. Les
# sections non pertinentes pour un cours donne peuvent etre retirees ou mises
# en commentaire. Le script regle dans l'ordre les parametres lies aux
# logiciels suivants:
#
# 1 - le script lui-meme;
# 2 - le SDK java;
# 3 - une distribution de Apache Maven;
# 4 - une distribution de JacORB.
#
# Enfin, le script demarre une coquille (shell) DOS dans un repertoire
# specifique. Cette coquille peut alors etre utilisee pour compiler les projets
# java (via javac ou Maven) lies au cours. Cette coquille peut egalement etre
# utilisee pour demarrer des applications CORBA developpees en java avec JacORB.
#
# Par Roger Champagne, P.Eng., Ph.D.
#     2008-Sep-08
# MAJ 2014-Jul-31

# 1 - Parametres globaux ======================================================

# Racine du lecteur ou ce script est stocke. Pour utilisation dans les
# laboratoires de l'ETS, il est suggere d'utiliser votre lecteur reseau (J:) ou
# une barette de memoire USB (typiquement E:). Sur votre ordinateur personnel,
# C: est la valeur typique.
CONFIG_DRIVE=./

# Repertoire ou l'on souhaite stocker le present script
CONFIG_HOME=$CONFIG_DRIVE

# Ecrasement du path, seul le repertoire ci-haut est conserve. Il est
# fortement recommande d'ecraser le PATH par defaut sur les postes du lab
# a l'ETS, car cette variable est surchargee et contient parfois des entrees
# qui causent des problemes dans ce cours (conflits de versions, etc).
PATH=$CONFIG_HOME

# 2 - Reglages SDK java =======================================================

# Repertoire du SDK Java a utiliser
JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.65-2.5.2.5.fc20.x86_64

# path: ajout des repertoires pertinents pour le SDK java.
PATH=$PATH;$JAVA_HOME/bin

# Ecrasement du classpath, seul le repertoire courant est conserve. Il est
# fortement recommande d'ecraser le CLASSPATH par defaut sur les postes du lab
# a l'ETS, car cette variable est surchargee et contient parfois des entrees
# qui causent des problemes dans ce cours (conflits de versions, etc).
CLASSPATH=.

# 3 - Reglages Maven ==========================================================

# Repertoire de la distribution maven a utiliser
MVN_HOME=/bin/

# Repertoire ou les utilitaires Maven du cours sont stockes
MY_MVN_HOME=$CONFIG_HOME

# path: ajout des repertoires pertinents pour Maven.
PATH=$PATH:$MVN_HOME/bin:$MY_MVN_HOME

# 4 - Reglages JacORB =========================================================

# Repertoire d'intallation de JacORB
JACORB_HOME=/home/lighta/Documents/ETS/LOG720/jacorb-3.1

# Endroit ou vous stockez orb.properties et jacorb.properties. Est ajoute dans
# le classpath plus bas. j:\ est un endroit plausible et pratique dans les labs
# de l'ETS. 
JACORB_PROPERTIES_DIR=$CONFIG_HOME

# path: ajout de la distribution JacORB
PATH=$PATH:$JACORB_HOME/bin

# classpath: ajout du reprtoire ou sont stockes les fichiers de config JacORB 
CLASSPATH=$JACORB_PROPERTIES_DIR:$CLASSPATH

# definition de variable pour tomcat
CATALINA_HOME=/home/lighta/Documents/ETS/LOG720/tomcat
CATALINA_BASE=/home/lighta/Documents/ETS/LOG720/tomcat

# x - Demarrage d'une coquille (shell) a un endroit predetermine ==============
