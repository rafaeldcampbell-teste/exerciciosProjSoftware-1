DROP DATABASE IF EXISTS projetoSoftware;
CREATE DATABASE projetoSoftware;

CREATE TABLE projetoSoftware.lojas (
  id              INT(11) NOT NULL AUTO_INCREMENT,
  endereco        VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE projetoSoftware.funcionarios (
  codigo           INT(11) NOT NULL AUTO_INCREMENT,
  nome             VARCHAR(30) NOT NULL,
  funcao     	   VARCHAR(30) NOT NULL,
  lojas_id         INT(11) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT LOJAS_FUNCIONARIOS_FK 
  FOREIGN KEY (lojas_id)
  REFERENCES projetoSoftware.lojas(id) 
  ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE projetoSoftware.mesas (
  id           	   INT(11) NOT NULL AUTO_INCREMENT,
  numero           INT(11) NOT NULL,
  funcionarios_id  INT(11) NOT NULL,
  lojas_id         INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FUNCIONARIOS_MESAS_FK 
  FOREIGN KEY (funcionarios_id)
  REFERENCES projetoSoftware.funcionarios(codigo) 
  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT LOJAS_MESAS_FK 
  FOREIGN KEY (lojas_id)
  REFERENCES projetoSoftware.lojas(id) 
  ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE projetoSoftware.atendimentos (
  id           	   		INT(11) NOT NULL AUTO_INCREMENT,
  inicioDoAtendimento   DATE NOT NULL,
  fimDoAtendimento  	DATE NOT NULL,
  valorTotalConta       FLOAT NOT NULL,
  mesas_id				INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT MESAS_ATENDIMENTOS_FK 
  FOREIGN KEY (mesas_id)
  REFERENCES projetoSoftware.mesas(id) 
  ON DELETE RESTRICT ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE projetoSoftware.usuarios (
  conta            CHAR(20) NOT NULL,
  senha            VARCHAR(20) NOT NULL,
  CONSTRAINT USUARIOS_PK 
  PRIMARY KEY (conta)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE projetoSoftware.perfis (
  id           	   		INT(11) NOT NULL AUTO_INCREMENT,
  conta_id   			CHAR(20) NOT NULL,
  perfil  				VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT PERFIS_USUARIO_FK 
  FOREIGN KEY (conta_id)
  REFERENCES projetoSoftware.usuarios(conta) 
  ON DELETE NO ACTION ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

insert into projetoSoftware.usuarios values ("dba", "123"), ("adm", "123");
insert into projetoSoftware.perfis values (1, "dba", "dba"), (2, "adm", "adm"), (3, "dba", "adm");