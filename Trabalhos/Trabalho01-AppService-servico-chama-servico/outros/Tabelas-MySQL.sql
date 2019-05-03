DROP TABLE banco.conta;

CREATE TABLE banco.conta (
  id INT(11) NOT NULL AUTO_INCREMENT,
  saldo DECIMAL(8, 2) NOT NULL,
  data_cadastro DATE NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO conta(SALDO, DATA_CADASTRO)
VALUES(3000, curdate());

INSERT INTO conta(SALDO, DATA_CADASTRO)
VALUES(5000, curdate());