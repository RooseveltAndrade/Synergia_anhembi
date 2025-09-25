-- ==============================================
-- 📌 Script de Criação do Banco Synergia
-- ==============================================

-- Criar banco de dados
CREATE DATABASE IF NOT EXISTS synergia;
USE synergia;

-- ==============================
-- Tabela: Usuarios
-- ==============================
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL,
    perfil ENUM('ADMIN', 'GERENTE', 'COLABORADOR') NOT NULL
);

-- ==============================
-- Tabela: Equipes
-- ==============================
CREATE TABLE IF NOT EXISTS equipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- ==============================
-- Tabela: Projetos
-- ==============================
CREATE TABLE IF NOT EXISTS projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    equipe_id INT,
    data_inicio DATE NOT NULL,
    data_fim_prevista DATE NOT NULL,
    data_fim DATE,
    status ENUM('PLANEJADO', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO') NOT NULL,
    gerente_id INT,
    FOREIGN KEY (equipe_id) REFERENCES equipes(id) ON DELETE SET NULL,
    FOREIGN KEY (gerente_id) REFERENCES usuarios(id) ON DELETE SET NULL
);

-- ==============================
-- Tabela: Tarefas
-- ==============================
CREATE TABLE IF NOT EXISTS tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    projeto_id INT NOT NULL,
    responsavel_id INT NOT NULL,
    status ENUM('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDA', 'CANCELADA') NOT NULL,
    data_inicio_prevista DATE NOT NULL,
    data_fim_prevista DATE NOT NULL,
    data_conclusao DATE,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id) ON DELETE CASCADE,
    FOREIGN KEY (responsavel_id) REFERENCES usuarios(id) ON DELETE SET NULL
);

-- ==============================
-- Dados iniciais
-- ==============================

-- Usuários de exemplo
INSERT INTO usuarios (nome, email, senha, perfil) VALUES
('Administrador', 'admin@synergia.com', 'admin123', 'ADMIN'),
('Gerente João', 'joao@synergia.com', '12345', 'GERENTE'),
('Colaborador Maria', 'maria@synergia.com', '12345', 'COLABORADOR');

-- Equipes de exemplo
INSERT INTO equipes (nome, descricao) VALUES
('Equipe Alpha', 'Equipe focada em inovação'),
('Equipe Beta', 'Equipe focada em manutenção');

-- Projetos de exemplo
INSERT INTO projetos (nome, descricao, equipe_id, data_inicio, data_fim_prevista, status, gerente_id) VALUES
('Projeto Synergia', 'Sistema de gestão de equipes e tarefas', 1, '2025-01-01', '2025-12-31', 'EM_ANDAMENTO', 2);

-- Tarefas de exemplo
INSERT INTO tarefas (titulo, descricao, projeto_id, responsavel_id, status, data_inicio_prevista, data_fim_prevista)
VALUES
('Criar Login', 'Implementar autenticação de usuários', 1, 3, 'CONCLUIDA', '2025-01-05', '2025-01-10'),
('Montar Dashboard', 'Tela principal com navegação', 1, 3, 'EM_ANDAMENTO', '2025-01-15', '2025-02-01');
