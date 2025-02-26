-- Criar a tabela area_conhecimento
CREATE TABLE area_conhecimento (
  id_area_conhecimento SERIAL PRIMARY KEY,
  nome VARCHAR(45) NOT NULL UNIQUE
);

-- Inserir dados na tabela area_conhecimento
INSERT INTO area_conhecimento (id_area_conhecimento, nome) VALUES
  (41, 'Administração'), (36, 'Análises Clínicas'), (2, 'Arte'), (4, 'Biologia'),
  (11, 'Ciências'), (24, 'Contabilidade'), (37, 'Direito e Legislação'),
  (28, 'Edificações'), (14, 'Educação Especial'), (3, 'Educação Física'),
  (21, 'Eletrônica'), (38, 'Eletrotécnica'), (15, 'Enfermagem'), (42, 'Estatística'),
  (16, 'Estética'), (5, 'Física'), (25, 'Gastronomia'), (8, 'Geografia'),
  (7, 'História'), (27, 'Informática'), (9, 'Inglês'), (10, 'LIBRAS'),
  (1, 'Língua Portuguesa'), (39, 'Logística'), (6, 'Matemática'), (22, 'Mecânica'),
  (17, 'Meio Ambiente'), (40, 'Metalurgia'), (29, 'Moda'), (12, 'Pedagogia'),
  (19, 'Prótese Dentária'), (18, 'Psicologia'), (23, 'Química'),
  (43, 'Restauro e Conservação Predial'), (20, 'Segurança do Trabalho'),
  (13, 'Sociologia'), (26, 'Turismo');

  -- Tabela `unidade`
DROP TABLE IF EXISTS unidade;
CREATE TABLE unidade (
  id_unidade SERIAL PRIMARY KEY,
  nome VARCHAR(65) UNIQUE
);

-- Inserção de dados na tabela `unidade`
INSERT INTO unidade (id_unidade, nome) VALUES 
  (1, 'EEEF República'),
  (2, 'ETE Barra Mansa'),
  (3, 'ETE Adolpho Bloch'),
  (4, 'ETE Agrícola Antônio Sarlo'),
  (5, 'ETE Amaury Cesar Vieira'),
  (6, 'ETE Ferreira Viana'),
  (7, 'ETE Helber Vignoli Muniz'),
  (8, 'ETE Henrique Lage'),
  (9, 'ETE Imbariê'),
  (10, 'ETE João Barcelos Martins'),
  (11, 'ETE João Luiz do Nascimento'),
  (12, 'ETE Juscelino Kubitschek'),
  (13, 'ETE Maria Mercedes Mendes Teixeira'),
  (14, 'ETE Oscar Tenório'),
  (15, 'ETE República'),
  (16, 'ETE Santa Cruz'),
  (17, 'ETE Saúde Herbert José de Souza'),
  (18, 'ETE Teatro Martins Pena'),
  (19, 'ETE Transporte Engenheiro Silva Freire'),
  (20, 'ETE Visconde de Mauá');

-- Criar a tabela carga_horaria
CREATE TABLE carga_horaria (
  id_carga_horaria SERIAL PRIMARY KEY,
  carga_horaria VARCHAR(3) DEFAULT NULL
);

-- Inserir dados na tabela carga_horaria
INSERT INTO carga_horaria (id_carga_horaria, carga_horaria) VALUES
  (1, '20h'), (2, '30h'), (3, '40h');

  -- Tabela `cargo`
DROP TABLE IF EXISTS cargo;
CREATE TABLE cargo (
  id_cargo SERIAL PRIMARY KEY,
  nome VARCHAR(65) NOT NULL UNIQUE
);

-- Inserção de dados na tabela `cargo`
INSERT INTO cargo (id_cargo, nome) VALUES 
  (8, 'Agente Administrativo'),
  (9, 'Inspetor de Alunos'),
  (4, 'Instrutor para Disciplinas Profissionalizantes I'),
  (6, 'Orientador Educacional'),
  (2, 'Professor de Ensino Superior FAETEC'),
  (1, 'Professor FAETEC I'),
  (5, 'Supervisor Educacional'),
  (7, 'Técnico Superior');

-- Tabela `permissao`
DROP TABLE IF EXISTS permissao;
CREATE TABLE permissao (
  id_permissao SERIAL PRIMARY KEY,
  nome VARCHAR(45) UNIQUE
);

-- Inserção de dados na tabela `permissao`
INSERT INTO permissao (id_permissao, nome) VALUES 
  (1, 'servidor'),
  (2, 'administrador');

 -- Tabela `vinculo`
DROP TABLE IF EXISTS vinculo;
CREATE TABLE vinculo (
  id_vinculo SERIAL PRIMARY KEY,
  nome VARCHAR(45) UNIQUE
);

-- Inserção de dados na tabela `vinculo`
INSERT INTO vinculo (id_vinculo, nome) VALUES 
  (1, 'Efetivo'),
  (2, 'Temporário');

-- Tabela `usuario`
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  id_usuario SERIAL PRIMARY KEY,
  email VARCHAR(45) NOT NULL UNIQUE,
  nome VARCHAR(45) NOT NULL,
  senha VARCHAR(100),
  id_area_conhecimento INT NOT NULL,
  id_carga_horaria INT NOT NULL,
  id_cargo INT NOT NULL,
  id_unidade INT NOT NULL,
  id_vinculo INT NOT NULL,
  FOREIGN KEY (id_area_conhecimento) REFERENCES area_conhecimento(id_area_conhecimento),
  FOREIGN KEY (id_carga_horaria) REFERENCES carga_horaria(id_carga_horaria),
  FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo),
  FOREIGN KEY (id_unidade) REFERENCES unidade(id_unidade),
  FOREIGN KEY (id_vinculo) REFERENCES vinculo(id_vinculo)
);


-- Inserção de dados na tabela `usuario`
INSERT INTO usuario (id_usuario, email, nome, senha, id_area_conhecimento, id_carga_horaria, id_cargo, id_unidade, id_vinculo) VALUES
  (1, 'bruno.torres@prof.etejk.faetec.rj.gov.br', 'BRUNO AUGUSTO TORRES', 
     '$2a$10$Ms8lG2OYLx9Up0ttO659TecjtoB6isUrkdh2aRnoGRSfDmWo3COxy', 27, 3, 1, 12, 1),
  (2, 'brunotorresprofessor@gmail.com', 'VIVIAN MARTINS', 
     '$2a$10$ok.c78XQ4B60BS7.Q/.gj.zghfZKrhu2VaOB4jTaRPInPsoclCtlS', 27, 3, 1, 1, 1);


	 -- Tabela `usuario_permissao`
DROP TABLE IF EXISTS usuario_permissao;
CREATE TABLE usuario_permissao (
  usuario_id INT NOT NULL,
  permissao_id INT NOT NULL,
  PRIMARY KEY (usuario_id, permissao_id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario),
  FOREIGN KEY (permissao_id) REFERENCES permissao(id_permissao)
);

-- Inserção de dados na tabela `usuario_permissao`
INSERT INTO usuario_permissao (usuario_id, permissao_id) VALUES 
  (1, 2),
  (2, 2);

-- Criar a tabela candidatura
CREATE TABLE candidatura (
  id_candidatura SERIAL PRIMARY KEY,
  id_usuario INT,
  id_unidade INT,
  data_cadastro TIMESTAMP DEFAULT NULL,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
  FOREIGN KEY (id_unidade) REFERENCES unidade(id_unidade)
);

-- Inserir dados na tabela candidatura
INSERT INTO candidatura (id_candidatura, id_usuario, id_unidade, data_cadastro) VALUES
  (1, 1, 1, '2025-02-09 11:09:07'), (2, 1, 3, '2025-02-09 14:27:52'),
  (3, 1, 2, '2025-02-10 17:39:13'), (4, 1, 11, '2025-02-10 17:40:59'),
  (5, 2, 12, '2025-02-10 17:40:59');














