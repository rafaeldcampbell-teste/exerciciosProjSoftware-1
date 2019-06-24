DROP DATABASE IF EXISTS trabalho2;
CREATE DATABASE trabalho2;

CREATE TABLE trabalho2.lojas (
  id              INT(11) NOT NULL AUTO_INCREMENT,
  endereco        VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE trabalho2.funcionarios (
  codigo           INT(11) NOT NULL AUTO_INCREMENT,
  nome             VARCHAR(30) NOT NULL,
  funcao     	   VARCHAR(30) NOT NULL,
  lojas_id         INT(11) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT LOJAS_FUNCIONARIOS_FK 
  FOREIGN KEY (lojas_id)
  REFERENCES trabalho2.lojas(id) 
  ON DELETE NO ACTION ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE trabalho2.mesas (
  id           	   INT(11) NOT NULL AUTO_INCREMENT,
  numero           INT(11) NOT NULL,
  funcionarios_id  INT(11) NOT NULL,
  lojas_id         INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FUNCIONARIOS_MESAS_FK 
  FOREIGN KEY (funcionarios_id)
  REFERENCES trabalho2.funcionarios(codigo) 
  ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT LOJAS_MESAS_FK 
  FOREIGN KEY (lojas_id)
  REFERENCES trabalho2.lojas(id) 
  ON DELETE NO ACTION ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE trabalho2.atendimentos (
  id           	   		INT(11) NOT NULL AUTO_INCREMENT,
  inicioDoAtendimento   DATE NOT NULL,
  fimDoAtendimento  	DATE NOT NULL,
  valorTotalConta       FLOAT NOT NULL,
  mesas_id				INT(11) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT MESAS_ATENDIMENTOS_FK 
  FOREIGN KEY (mesas_id)
  REFERENCES trabalho2.mesas(id) 
  ON DELETE NO ACTION ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE trabalho2.usuarios (
  conta            CHAR(20) NOT NULL,
  senha            VARCHAR(20) NOT NULL,
  CONSTRAINT USUARIOS_PK 
  PRIMARY KEY (conta)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE trabalho2.perfis (
  id           	   		INT(11) NOT NULL AUTO_INCREMENT,
  conta_id   			CHAR(20) NOT NULL,
  perfil  				VARCHAR(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT PERFIS_USUARIO_FK 
  FOREIGN KEY (conta_id)
  REFERENCES trabalho2.usuarios(conta) 
  ON DELETE NO ACTION ON UPDATE CASCADE
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;