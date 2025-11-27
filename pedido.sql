CREATE TABLE Produto (
  codigo INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  nome VARCHAR(20)  NOT NULL  ,
  quantidade INTEGER UNSIGNED  NOT NULL  ,
  valor DOUBLE  NOT NULL    ,
PRIMARY KEY(codigo));



CREATE TABLE Cliente (
  cpf VARCHAR(11)  NOT NULL  ,
  nome VARCHAR(45)  NOT NULL    ,
PRIMARY KEY(cpf));



CREATE TABLE Pedido (
  codigo INTEGER UNSIGNED  NOT NULL AUTO_INCREMENT  ,
  Cliente_cpf VARCHAR(11)  NOT NULL  ,
  dataPedido DATE  NULL  ,
  pagamento INTEGER UNSIGNED  NULL  ,
  valorTotal DOUBLE  NULL    ,
PRIMARY KEY(codigo)  ,
INDEX Pedido_FKIndex1(Cliente_cpf),
  FOREIGN KEY(Cliente_cpf)
    REFERENCES Cliente(cpf)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Pedido_has_Produto (
  Pedido_codigo INTEGER UNSIGNED  NOT NULL  ,
  Produto_codigo INTEGER UNSIGNED  NOT NULL  ,
  quantidade INTEGER UNSIGNED  NULL    ,
PRIMARY KEY(Pedido_codigo, Produto_codigo)  ,
INDEX Pedido_has_Produto_FKIndex1(Pedido_codigo)  ,
INDEX Pedido_has_Produto_FKIndex2(Produto_codigo),
  FOREIGN KEY(Pedido_codigo)
    REFERENCES Pedido(codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Produto_codigo)
    REFERENCES Produto(codigo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




