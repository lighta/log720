::retrait de tous les fichier dans le dossier de persistance
IF EXIST ..\..\persistance\* del ..\..\persistance\*
IF EXIST _nsdb_root del _nsdb_root
echo "Clean executed sucessfully"
