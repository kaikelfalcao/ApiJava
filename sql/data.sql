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

INSERT INTO usuario (cpf, login, senha) VALUES
('12345678901', 'usuario1', 'senha1234'),
('98765432101', 'usuario2', 'senha5678'),
('01234567891', 'usuario3', 'senha9012');


INSERT INTO paciente (cpf, nome, dtnascimento, endereco, email, planosaude, numcarteirinhaplanosaude) VALUES
('12345678901', 'Paciente 1', '1980-01-01', 'Rua das Flores, 123', 'paciente1@email.com', 'Unimed', '1234567890'),
('98765432101', 'Paciente 2', '1985-02-02', 'Rua das Rosas, 321', 'paciente2@email.com', 'Bradesco Saúde', '9876543210'),
('01234567891', 'Paciente 3', '1990-03-03', 'Rua dos Cravos, 432', 'paciente3@email.com', 'Amil', '0123456789');

INSERT INTO atendimento (numeroatendimento, cpfpaciente, nomepaciente, cpfusuario, descricaoatendimento, dataatendimento, valorcobrado, tipoatendimento) VALUES
(1, '12345678901', 'Paciente 1', '12345678901', 'Consulta médica', '2023-10-04', 100.00, 'consulta'),
(2, '98765432101', 'Paciente 2', '12345678901', 'Exame laboratorial', '2023-10-05', 50.00, 'exame'),
(3, '01234567891', 'Paciente 3', '12345678901', 'Fisioterapia', '2023-10-06', 80.00, 'sessão');
