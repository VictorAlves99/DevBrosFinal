CREATE DATABASE DB_LOJA_NOOBIES;

USE DB_LOJA_NOOBIES;

CREATE TABLE TB_CLIENTES(
	COD_CLIENTE INT NOT NULL AUTO_INCREMENT,
	NOME_CLIENTE VARCHAR(100) NOT NULL,
	CPF_CLIENTE VARCHAR(11) NOT NULL UNIQUE,    
    TELEFONE_CLIENTE VARCHAR(15) NOT NULL,
    EMAIL_CLIENTE VARCHAR(50) NOT NULL,
    
    CONSTRAINT PK_CLIENTE PRIMARY KEY(COD_CLIENTE)
);

CREATE TABLE TB_FUNCIONARIOS(
	COD_FUNCIONARIO INT NOT NULL AUTO_INCREMENT,
    LOGIN_SIST VARCHAR(20) NOT NULL UNIQUE,
    SENHA_SIST VARCHAR(20) NOT NULL,
	NOME_FUNCIONARIO VARCHAR(100) NOT NULL,
    CPF_FUNCIONARIO VARCHAR(14) NOT NULL,
	RG_FUNCIONARIO VARCHAR(12) NOT NULL,
	DATA_NASCIMENTO DATE,
    FILIAL_FUNCIONARIO VARCHAR (28),
    CARGO_FUNCIONARIO VARCHAR (20) NOT NULL,

	CONSTRAINT PK_FUNCIONARIOS PRIMARY KEY(COD_FUNCIONARIO)
);
    
CREATE TABLE TB_PRODUTOS(
	COD_PRODUTO INT NOT NULL AUTO_INCREMENT,
    NOME_PRODUTO VARCHAR(100) NOT NULL,
    DESCRICAO VARCHAR(100) NOT NULL,
    VALOR_COMPRA DECIMAL (10,2) NOT NULL,
    VALOR_VENDA DECIMAL (10,2) NOT NULL,
    QUANTIDADE INT NOT NULL,
    CATEGORIA VARCHAR(100) NOT NULL,
    
	CONSTRAINT PK_PRODUTOS PRIMARY KEY(COD_PRODUTO)
);
    
CREATE TABLE TB_VENDAS(
    VALOR_TOTAL DECIMAL (10,2) NOT NULL,
    DATA_COMPRA TIMESTAMP NOT NULL,
    FORMA_PGTO VARCHAR(45) NOT NULL
	);