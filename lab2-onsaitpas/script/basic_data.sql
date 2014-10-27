--Insertion des Dossier
insert into "Dossier" ("nom","prenom","noPermis","noPlaque") values ('john','do','Pe0001','Pl001');
insert into "Dossier" ("nom","prenom","noPermis","noPlaque") values ('steven','do','Pe0002','Pl002');
--test UNQ_NoPermis
insert into "Dossier" ("nom","prenom","noPermis","noPlaque") values ('fail','permis','Pe0002','blablabala');
--verification table Dossier (2 entries)
select * from "Dossier";


--Insertion des infractions
insert into "Infraction" ("description","niveau") values ('pickpocket',1);
insert into "Infraction" ("description","niveau") values ('atteinte pudeur',2);
insert into "Infraction" ("description","niveau") values ('usurpation id',3);
insert into "Infraction" ("description","niveau") values ('raquettage',4);
insert into "Infraction" ("description","niveau") values ('proxenetisme',5);
insert into "Infraction" ("description","niveau") values ('vol arme',6);
insert into "Infraction" ("description","niveau") values ('meurtre',7);
--test chk_niveau
insert into "Infraction" ("description","niveau") values ('test_failbelow',0);
insert into "Infraction" ("description","niveau") values ('test_failup',11);
--verification table Infraction (7 entries)
select * from "Infraction";


--Insertion des DosInf
insert into "DosInfraction" ("Id_Dossier","Id_Infraction") values (2,2);
insert into "DosInfraction" ("Id_Dossier","Id_Infraction") values (2,5);
insert into "DosInfraction" ("Id_Dossier","Id_Infraction") values (1,7);
--test invalid FK
insert into "DosInfraction" ("Id_Dossier","Id_Infraction") values (0,7);
insert into "DosInfraction" ("Id_Dossier","Id_Infraction") values (1,15);
--verification table DosInfraction (3 entries)
select * from "DosInfraction";
