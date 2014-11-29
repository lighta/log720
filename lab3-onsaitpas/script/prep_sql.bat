::preparation de la db sql

@echo off

SET server=log720.logti.etsmtl.ca
SET /P server="Server [%server%]: "

SET database=log720_20143_14
SET /P database="Database [%database%]: "

SET port=5432
SET /P port="Port [%port%]: "

SET username=aj47630
SET /P username="Username [%username%]: "

SET PGPASSWORD=Log7203
SET /P PGPASSWORD="Password [%PGPASSWORD%]: "


psql -h %server% -U %username% -d %database% -p %port% -f "./clean_db.sql"

psql -h %server% -U %username% -d %database% -p %port% -f "./create_db.sql"

::psql -h %server% -U %username% -d %database% -p %port% -f "./basic_data.sql"
