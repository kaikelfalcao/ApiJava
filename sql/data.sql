/**
 * Author:  Kaike
 * Created: 26 de nov. de 2023
 */

-- Criação do banco de dados
CREATE DATABASE hospital;

\c hospital;


CREATE TABLE usuario (
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE paciente (
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dtnascimento DATE NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    planosaude VARCHAR(255) NOT NULL,
    numcarteirinhaplanosaude VARCHAR(255) NOT NULL
);

CREATE TABLE atendimento (
    numeroatendimento INT NOT NULL PRIMARY KEY,
    cpfpaciente VARCHAR(11) NOT NULL,
    nomepaciente VARCHAR(255) NOT NULL,
    cpfusuario VARCHAR(11) NOT NULL,
    descricaoatendimento VARCHAR(255) NOT NULL,
    dataatendimento DATE NOT NULL,
    valorcobrado DOUBLE PRECISION NOT NULL,
    tipoatendimento VARCHAR(255) NOT NULL,
    FOREIGN KEY (cpfpaciente) REFERENCES paciente(cpf),
    FOREIGN KEY (cpfusuario) REFERENCES usuario(cpf)
);

ALTER TABLE atendimento
MODIFY COLUMN numeroatendimento INT NOT NULL AUTO_INCREMENT PRIMARY KEY;

INSERT INTO usuario (cpf, login, senha) VALUES
('12345678901', 'kaike', 'epic7'),
('98765432101', 'henrique', 'spCampeao'),
('11122233344', 'ana', 'senha123'),
('55566677788', 'carlos', 'senhasegura'),
('99988877766', 'lucas', 'minhasenha');


INSERT INTO paciente (cpf, nome, dtnascimento, endereco, email, planosaude, numcarteirinhaplanosaude) VALUES
('12345678901', 'Kaike Falcão', '2004-05-20', 'Rua das Flores, 123', 'kaike@email.com', 'Unimed', 'Particular'),
('98765432101', 'Maria Luisa', '2003-08-28', 'Rua das Rosas, 321', 'luisa@email.com', 'SUS', '9876543210'),
('11122233344', 'Ana Silva', '1990-03-15', 'Avenida Principal, 456', 'ana@email.com', 'Amil', 'A12345'),
('55566677788', 'Carlos Oliveira', '1985-11-22', 'Rua Central, 789', 'carlos@email.com', 'Unimed', 'U54321'),
('99988877766', 'Lucas Santos', '2000-07-10', 'Travessa dos Alamos, 789', 'lucas@email.com', 'Particular', 'P98765');


INSERT INTO atendimento (cpfpaciente, nomepaciente, cpfusuario, descricaoatendimento, dataatendimento, valorcobrado, tipoatendimento) VALUES
('12345678901', 'Kaike Falcão', '12345678901', 'Consulta médica', '2023-10-04', 100.00, 'consulta'),
('98765432101', 'Maria Luisa', '12345678901', 'Exame laboratorial', '2023-10-05', 50.00, 'exame'),
('11122233344', 'Ana Silva', '55566677788', 'Consulta pediátrica', '2023-10-06', 120.00, 'consulta'),
('55566677788', 'Carlos Oliveira', '99988877766', 'Tomografia', '2023-10-07', 300.00, 'exame'),
('99988877766', 'Lucas Santos', '11122233344', 'Acompanhamento clínico', '2023-10-08', 80.00, 'consulta');