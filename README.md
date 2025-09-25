# 📌 Synergia - Sistema de Gestão de Projetos e Equipes

O **Synergia** é uma aplicação desktop desenvolvida em **Java (Swing + JDBC + MySQL)**, com objetivo de gerenciar **usuários, equipes, projetos e tarefas** em um ambiente integrado.  
Foi criado como parte do projeto acadêmico da **Universidade Anhembi Morumbi (ADS - Análise e Desenvolvimento de Sistemas)**.

---

## 🚀 Funcionalidades

- **Login seguro** com autenticação de usuário
- **Dashboard** com navegação intuitiva
- **CRUD de Usuários**: adicionar, listar, atualizar e excluir
- **CRUD de Equipes**: gerenciar equipes e descrições
- **CRUD de Projetos**: criar projetos com status, gerente e datas
- **CRUD de Tarefas**: atribuir tarefas a projetos e responsáveis
- **Relatórios**: resumo de desempenho e atrasos

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Swing (GUI Desktop)**
- **JDBC (Conexão com banco de dados)**
- **MySQL 8**
- **Maven (gerenciamento de dependências)**
- **IntelliJ IDEA (IDE principal)**

---

## 📂 Estrutura do Projeto

Synergia_anhembi/
│── src/main/java/br/com/synergia/
│   ├── dao/        # DAOs para manipulação do banco
│   ├── model/      # Entidades (Usuario, Projeto, Equipe, Tarefa)
│   ├── view/       # Telas Swing (LoginView, DashboardView, etc.)
│   └── Main.java   # Ponto de entrada da aplicação
│
│── pom.xml         # Configuração Maven
│── run.sh          # Script de execução
│── README.md       # Documentação do projeto

---

## ⚙️ Requisitos

- **Java 17** ou superior
- **Maven 3.6+**
- **MySQL 8**
- IDE recomendada: **IntelliJ IDEA**

---

## 🔧 Instalação e Configuração

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/RooseveltAndrade/Synergia_anhembi.git
   cd Synergia_anhembi
   
2. **Configurar o banco MySQL**

   ```sql
   CREATE DATABASE synergia

 3. **Configurar conexão**
    •	No arquivo conexao.java, atualizar usuário e senha:
4. ```java
    private static final String URL = "jdbc:mysql://localhost:3306/synergia";
    private static final String USER = "root";
    private static final String PASSWORD = "sua_senha";

5. **Compiler o projeto**
6. ```bash
    mvn clean install


7. **Executar**
8. ```bash
    ./run.sh

## 📊 Relatórios

O módulo de relatórios permite:

Listar projetos em andamento e concluídos

Consultar tarefas em atraso

Resumo do desempenho das equipes

## 👨‍💻 Desenvolvedor

Roosevelt Andrade Dev-FullStack


## 📌 Status do Projeto

✅ Concluído para entrega acadêmica
🔜 Futuras melhorias:

Exportação de relatórios em PDF/Excel

Sistema de permissões avançadas

Integração com APIs externas

## 📜 Créditos

Projeto desenvolvido como parte da graduação em Análise e Desenvolvimento de Sistemas pela Anhembi Morumbi.







