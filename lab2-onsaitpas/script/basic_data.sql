--Insertion des dossier
insert into "dossier" ("nom","prenom","noPermis","noPlaque") values ('john','do','Pe0001','Pl001');
insert into "dossier" ("nom","prenom","noPermis","noPlaque") values ('steven','do','Pe0002','Pl002');
--test UNQ_NoPermis
insert into "dossier" ("nom","prenom","noPermis","noPlaque") values ('fail','permis','Pe0002','blablabala');
--verification table dossier (2 entries)
select * from "dossier";


--Insertion des infractions
insert into "infraction" ("description","niveau") values ('pickpocket',1);
insert into "infraction" ("description","niveau") values ('atteinte pudeur',2);
insert into "infraction" ("description","niveau") values ('usurpation id',3);
insert into "infraction" ("description","niveau") values ('raquettage',4);
insert into "infraction" ("description","niveau") values ('proxenetisme',5);
insert into "infraction" ("description","niveau") values ('vol arme',6);
insert into "infraction" ("description","niveau") values ('meurtre',7);
--test chk_niveau
insert into "infraction" ("description","niveau") values ('test_failbelow',0);
insert into "infraction" ("description","niveau") values ('test_failup',11);
--verification table infraction (7 entries)
select * from "infraction";


--Insertion des DosInf
insert into "dosinfraction" ("id_dossier","id_infraction") values (2,2);
insert into "dosinfraction" ("id_dossier","id_infraction") values (2,5);
insert into "dosinfraction" ("id_dossier","id_infraction") values (1,7);
--test invalid FK
insert into "dosinfraction" ("id_dossier","id_infraction") values (0,7);
insert into "dosinfraction" ("id_dossier","id_infraction") values (1,15);
--verification table dosinfraction (3 entries)
select * from "dosinfraction";
