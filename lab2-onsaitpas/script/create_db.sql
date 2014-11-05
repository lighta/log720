--create table testdata (id serial primary key, foo varchar(25), bar int);
--insert into testdata (foo, bar) values('hello', 12345);

-- Table: dossier
-- DROP TABLE dossier;
CREATE SEQUENCE dossier_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE dossier_id_seq
  OWNER TO aj47630;
CREATE TABLE dossier
(
  id integer NOT NULL DEFAULT nextval('dossier_id_seq'::regclass), -- Id du dossier
  nom character varying(50), -- Nom du client du dossier
  prenom character varying(50), -- Prenom du client du dossier
  nopermis character varying(10) NOT NULL,
  noplaque character varying(10) NOT NULL,
  niveau integer DEFAULT 0,
  CONSTRAINT "PK_Dossier" PRIMARY KEY (id),
  CONSTRAINT "UNQ_NoPermis" UNIQUE (nopermis)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dossier
  OWNER TO equipe_14;
COMMENT ON TABLE dossier
  IS 'Liste des dossiers';
COMMENT ON COLUMN dossier.id IS 'Id du dossier';
COMMENT ON COLUMN dossier.nom IS 'Nom du client du dossier';
COMMENT ON COLUMN dossier.prenom IS 'Prenom du client du dossier';


-- Table: infraction
-- DROP TABLE infraction;
CREATE SEQUENCE infraction_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE infraction_id_seq
  OWNER TO aj47630;
CREATE TABLE infraction
(
  id integer NOT NULL DEFAULT nextval('infraction_id_seq'::regclass), -- Identifieur de l'infractions
  description character varying(150) NOT NULL, -- Description de l'infraction
  niveau integer,
  CONSTRAINT "PK_Infraction" PRIMARY KEY (id),
  CONSTRAINT "CHK_niveau" CHECK (niveau >= 1 AND niveau <= 10)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE infraction
  OWNER TO equipe_14;
COMMENT ON TABLE infraction
  IS 'Liste d''infractions';
COMMENT ON COLUMN infraction.id IS 'Identifieur de l''infractions';
COMMENT ON COLUMN infraction.description IS 'Description de l''infraction';


-- Table: dosinfraction
-- DROP TABLE dosinfraction;
CREATE TABLE dosinfraction
(
  iddossier integer NOT NULL DEFAULT 0,
  idinfraction integer NOT NULL DEFAULT 0,
  date timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT "FK_IDdos" FOREIGN KEY (iddossier)
      REFERENCES dossier (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_IDinf" FOREIGN KEY (idinfraction)
      REFERENCES infraction (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dosinfraction
  OWNER TO equipe_14;
COMMENT ON TABLE dosinfraction
  IS 'Liste d''infractions pour les dossiers';

