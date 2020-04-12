-- CREATES

create table status_troca (
	id integer not null auto_increment,
	descricao VARCHAR(50),
	CONSTRAINT pk_status_troca_id PRIMARY KEY (id)
);

create table status_venda (
	id integer not null auto_increment,
	descricao VARCHAR(50),
	CONSTRAINT pk_status_venda_id PRIMARY KEY (id)
);

CREATE TABLE elivraria.bandeira (
id INTEGER NOT NULL AUTO_INCREMENT,
descricao VARCHAR(20),
CONSTRAINT pk_bandeira_id PRIMARY KEY (id)
);

CREATE TABLE usuario
 (
	id integer not null AUTO_INCREMENT,
    genero VARCHAR(20),
	nome VARCHAR(50),
	sobrenome VARCHAR(50),
	dtnascimento VARCHAR(15),
	role VARCHAR(50),
	ativo BOOLEAN,
	senha VARCHAR(60),
	email VARCHAR(100),
	ddd_telefone VARCHAR(2),
    telefone VARCHAR(10),
	CONSTRAINT pk_usuario_id PRIMARY KEY(id)
);

CREATE TABLE elivraria.cartao (
id INTEGER not null AUTO_INCREMENT,
numero VARCHAR(50),
nome VARCHAR(100),
vencimento_mes INTEGER,
vencimento_ano INTEGER, 
ccv INTEGER,
bandeira_id int,
usuario_id int,
CONSTRAINT pk_cartao_id PRIMARY KEY (id),
CONSTRAINT fk_cartao_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario (id),
CONSTRAINT fk_cartao_bandeira__id FOREIGN KEY (bandeira_id) REFERENCES bandeira (id)
);


CREATE TABLE categoria (
	id integer not null AUTO_INCREMENT,
	nome VARCHAR(50),
	descricao VARCHAR(255),
	imagem_url VARCHAR(50),
	ativo BOOLEAN,
	CONSTRAINT pk_categoria_id PRIMARY KEY (id) 

);

create table grupo_precificacao (
	id integer not null auto_increment,
	descricao varchar(150),
	percentual_lucro decimal(10,2),
	CONSTRAINT pk_grupo_precificacao_id PRIMARY KEY (id)
);

create TABLE elivraria.livro (
	id integer not null AUTO_INCREMENT,
	isbn VARCHAR(20),
	titulo VARCHAR(50),
	editora VARCHAR(50),
	autor VARCHAR(255),
	edicao VARCHAR(20),
	ano INT,
	sinopse VARCHAR(10000),
	codbarras BIGINT,
	numpaginas INT,
	largura DECIMAL(5,2),
	altura DECIMAL(5,2),
	profundidade DECIMAL(5,2),
	peso DECIMAL(5,2),
	precounit DECIMAL(10,2),
	quantidade INT,
	ativo BOOLEAN,
	categoria_id INT,
	fornecedor_id INT,
	compras INT DEFAULT 0,
	visualizacoes INT DEFAULT 0,
	grupo_precificacao_id int,
	CONSTRAINT pk_livro_id PRIMARY KEY (id),
 	CONSTRAINT fk_livro_categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria (id),
 	CONSTRAINT fk_livro_grupo_preco_id FOREIGN KEY (grupo_precificacao_id) REFERENCES grupo_precificacao (id),
	CONSTRAINT fk_livro_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES usuario(id)
	);	
	

CREATE TABLE endereco (
	id integer not null AUTO_INCREMENT,
	usuario_id int,
    tiporesidencia VARCHAR(20),
    tipologradouro VARCHAR(20),
	logradouro VARCHAR(100),
    numero VARCHAR(20),
    complemento VARCHAR(20),
	bairro VARCHAR(100),
	cidade VARCHAR(50),
	estado VARCHAR(20),
	pais VARCHAR(20),
	cep VARCHAR(10),
	cobranca BOOLEAN,
	entrega BOOLEAN,
	CONSTRAINT fk_endereco_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario (id),
	CONSTRAINT pk_endereco_id PRIMARY KEY (id)
);

CREATE TABLE carrinho (
	id integer not null AUTO_INCREMENT,
	usuario_id int,
	total DECIMAL(10,2),
	itens int,
	CONSTRAINT fk_carrinho_usuario_id FOREIGN KEY (usuario_id ) REFERENCES usuario(id),
	CONSTRAINT pk_carrinho_id PRIMARY KEY (id)
);

CREATE TABLE itemCarrinho (
	id integer not null AUTO_INCREMENT,
	carrinho_id int,
	total DECIMAL(10,2),
	livro_id int,
	livro_qtd int,
	preco_compra DECIMAL(10,2),
	disponivel boolean,
	CONSTRAINT fk_itemcarrinho_livro_id FOREIGN KEY (livro_id ) REFERENCES livro (id),
	CONSTRAINT pk_itemcarrinho_id PRIMARY KEY (id)
);

CREATE TABLE venda_detalhe (
	id integer not null AUTO_INCREMENT,
	usuario_id int,
	venda_total DECIMAL(10,2),
	venda_qtd int,
	enderecoCobranca_id int,
	enderecoEntrega_id int,
	venda_data datetime,
	status_venda_id int,
	cartao_id int,
	CONSTRAINT fk_venda_detalhe_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario (id),
	CONSTRAINT fk_venda_detalhe_endereco_entrega_id FOREIGN KEY (enderecoEntrega_id) REFERENCES endereco (id),
	CONSTRAINT fk_venda_detalhe_endereco_cobranca_id FOREIGN KEY (enderecoCobranca_id) REFERENCES endereco (id),
	CONSTRAINT fk_venda_detalhe_status_venda_id FOREIGN KEY (status_venda_id) REFERENCES status_venda (id),
	constraint fk_venda_detalhe_cartao_id foreign key (cartao_id) references cartao (id),
	CONSTRAINT pk_venda_detalhe_id PRIMARY KEY (id)
);

CREATE TABLE venda_item (
	id integer not null AUTO_INCREMENT,
	venda_detalhe_id int,
	total DECIMAL(10,2),
	livro_id int,
	livro_qtd int,
	preco_compra DECIMAL(10,2),
	CONSTRAINT fk_venda_item_livro_id FOREIGN KEY (livro_id) REFERENCES livro (id),
	CONSTRAINT fk_venda_item_venda_detalhe_id FOREIGN KEY (venda_detalhe_id) REFERENCES venda_detalhe (id),
	CONSTRAINT pk_venda_item_id PRIMARY KEY (id)
);

create table fornecedor (
	id integer not null auto_increment,
	nome varchar(100),
	CONSTRAINT pk_fornecedor_id PRIMARY KEY (id)
);

create table estoque (
	id integer not null auto_increment,
	livro_id int,
	quantidade integer,
	valor_custo decimal(10,2),
	data_entrada varchar(20),
	tipo_operacao VARCHAR(10),
	flg_zerado BOOLEAN,
	fornecedor_id int,
	CONSTRAINT fk_estoque_livro_id FOREIGN KEY (livro_id) REFERENCES livro (id),	
	CONSTRAINT fk_estoque_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id),
	CONSTRAINT pk_estoque_id PRIMARY KEY (id)
);


select * from estoque
create table troca (
	id integer not null auto_increment,
	motivo varchar(1000),
	venda_item_id int,
	usuario_id int,
	status_troca_id int,
	CONSTRAINT fk_venda_item_id FOREIGN KEY (venda_item_id) REFERENCES venda_item (id),
	CONSTRAINT fk_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario (id),
	CONSTRAINT fk_status_troca_id FOREIGN KEY (status_troca_id) REFERENCES status_troca (id),
	CONSTRAINT pk_troca_id PRIMARY KEY (id)
);

CREATE TABLE elivraria.cartao_validador (
id INTEGER not null AUTO_INCREMENT,
numero VARCHAR(50),
nome VARCHAR(100),
vencimento_mes INTEGER,
vencimento_ano INTEGER, 
ccv INTEGER,
bandeira_id int,
limite decimal(10,2),
CONSTRAINT pk_cartao_validador_id PRIMARY KEY (id)
);