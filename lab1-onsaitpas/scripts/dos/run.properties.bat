set NAMING_SERVICE_HOST=log720.logti.etsmtl.ca
set NAMING_SERVICE_PORT=31501
set SERVER_POSTE=ca.etsmtl.log720.lab1.Server_Poste
set CLIENT_POSTE=ca.etsmtl.log720.lab1.Client_Poste
set SERVER_VOITURE=ca.etsmtl.log720.lab1.Server_Voiture
set CLIENT_VOITURE=ca.etsmtl.log720.lab1.Client_Voiture
set MY_CLASSPATH=..\..\idl\target\classes;..\..\common\target\classes;%MY_CLASSPATH%
set MY_CLASSPATH=..\..\server_poste\target\classes;..\..\server_voiture\target\classes;%MY_CLASSPATH%
set MY_CLASSPATH=..\..\client_poste\target\classes;..\..\client_voiture\target\classes;%MY_CLASSPATH%
::build the persistance dir folder
if not exist "..\..\persistance" mkdir ..\..\persistance
