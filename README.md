# ITF_WEB
PROVA-Sistema de Lançamentos de Receitas/Despesas(Create/Delete/Read)

- Projeto criado para avalição.


Tela de Cadastro
<img src="https://github.com/VicenteLamberti/ITF_WEB/blob/main/src/main/webapp/resources/images/Tela_cadastro.png" alt="cadastro"/>


Tela de Lançamentos
<img src="https://github.com/VicenteLamberti/ITF_WEB/blob/main/src/main/webapp/resources/images/Tela_lista_lancamentos.png" alt="lancamentos"/>

-Projeto JAVA MAVEN de uma aplicação WEB que tem a função de cadastrar, deletar e listar lançamentos de despesas e receitas. Foi utilizado JavaServerFaces para se construir as 
interfaces juntamente com o server-side. Foi utilizado componentes do JSF para criar o formulario de cadastro, assim como para fazer a listagem dos itens cadastrados.
Esses componentes facilitam os eventos entre o formulário e o back-end. O projeto foi separado em pacotes e diretórios para se manter uma organização e facilitar a manutenção do 
código. Como por exemplo pacote DAO, que possui as classes referente a conexão com o banco de dados e persistência de dados, pacote Models, que possui as entidades
do sistema e o diretorio css onde é guardado os arquivos de estilização.
A estilização em css, foi utilizado com classes no componentes necessários.
A persistência e consulta de dados foi feita em JDBC. Essa foi minha escolha ao invés de utilizar a JPA com hibernate, pois assim é possível demonstrar as querys executadas.
O banco de dados utilizado para testes foi o MySQL.

As dependências do projeto estão no arquivo pom.xml.
Para subir a aplicação foi utilizado o TOMCAT 9



-------------SCRIPTS SQL-------------
```
CREATE SCHEMA `itf_web` ;

CREATE TABLE `itf_web`.`lancamento` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(20) NOT NULL,
  `codigo_empresa` INT NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `pago` BIT(1) NOT NULL,
  `data_pagamento` DATE NULL DEFAULT NULL,
  `data_vencimento` DATE NOT NULL,
  PRIMARY KEY (`codigo`));



CREATE TABLE `itf_web`.`empresa` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`codigo`));



INSERT INTO `itf_web`.`empresa` (`nome`) VALUES ('CHIQUINHO');
INSERT INTO `itf_web`.`empresa` (`nome`) VALUES ('IFOOD');
INSERT INTO `itf_web`.`empresa` (`nome`) VALUES ('MERCADO LIVRE');

```

**Caso o banco de dados tenha usuário e senha, é necessário fazer a alteracao da string de conexão no arquivo  AcessBD.java.
**Caso foi utilizar outro banco de dados é necessário acrescentar a dependência de conector de tal banco de dados.
