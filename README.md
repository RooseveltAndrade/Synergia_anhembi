📌 Synergia Anhembi – Sistema de Gestão de Projetos e Equipes

📖 Sobre o projeto

O Synergia Anhembi é um sistema acadêmico desenvolvido em Java (Swing + JDBC) com MySQL para apoiar a gestão de equipes, projetos, tarefas e usuários.
O sistema foi construído seguindo boas práticas de POO e organizado em camadas (Model, DAO e View).

⸻

🚀 Funcionalidades
•	👥 Usuários: CRUD completo com autenticação e perfis (ADMIN, GERENTE, COLABORADOR).
•	👨‍👩‍👧 Equipes: Cadastro, edição e exclusão de equipes.
•	📂 Projetos: Gerenciamento de projetos com datas, status e responsável.
•	✅ Tarefas: Vinculação de tarefas a projetos e responsáveis.
•	📊 Relatórios: Resumo de projetos, desempenho de colaboradores e alerta de atrasos.
•	🔑 Login e Dashboard: Autenticação com redirecionamento para o menu principal.

⸻

🛠️ Tecnologias utilizadas
•	Java 17
•	Swing (GUI desktop)
•	MySQL 8.0
•	Maven (gerenciamento de dependências)
•	Git + GitHub (controle de versão)

📂 Estrutura do projeto

src/main/java/br/com/synergia/
├── dao/        → Classes de acesso ao banco (UsuarioDAO, ProjetoDAO, etc.)
├── model/      → Modelos (Usuario, Projeto, Equipe, Tarefa)
├── view/       → Telas Swing (LoginView, DashboardView, CRUDs, Relatórios)
└── Main.java   → Classe principal

⚙️ Requisitos
•	Java 17 ou superior
•	MySQL 8.0
•	Maven 3.9+

⸻

💾 Instalação e execução
1.	Clone o repositório:

git clone https://github.com/SEU_USUARIO/Synergia_anhembi.git
cd Synergia_anhembi

2. Configure o banco de dados MySQL:

CREATE DATABASE synergia;
USE synergia;
-- Execute os scripts SQL das tabelas (usuários, equipes, projetos, tarefas)

3. Ajuste o arquivo conexao.java com suas credenciais do banco.
4. Compile e rode:

mvn clean install
./run.sh

👨‍💻 Autor
•	Roosevelt Andrade (Desenvolvedor Full Stack)

🏷️ Versões
•	v1.0.0 → Primeira release estável com CRUDs completos e relatórios básicos.

