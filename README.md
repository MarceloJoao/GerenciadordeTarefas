# 📌 Gerenciador de Tarefas

Aplicação web desenvolvida em Java utilizando **JavaServer Faces (JSF)**, com persistência em **PostgreSQL** via **JPA/Hibernate**.

O sistema permite o gerenciamento de tarefas, incluindo cadastro, edição, exclusão, conclusão e filtros, além do relacionamento com responsáveis.

---

## 🚀 Tecnologias utilizadas

- Java
- JSF (JavaServer Faces)
- JPA (Hibernate)
- PostgreSQL
- Maven
- Apache Tomcat
- Docker

---

## 📋 Funcionalidades

### ✔ Tarefas
- Criar tarefa
- Editar tarefa
- Excluir tarefa
- Marcar tarefa como concluída
- Listar tarefas
- Filtrar tarefas por:
  - Número (ID)
  - Texto (título/descrição)
  - Responsável
  - Status (Em andamento / Concluída)

### ✔ Responsáveis
- Cadastrar responsável
- Listar responsáveis
- Associar responsável à tarefa

---

## 🧠 Regras de negócio

- Toda tarefa é criada com status **EM_ANDAMENTO**
- Ao concluir, a tarefa passa para **CONCLUIDA**
- Tarefas concluídas não aparecem na lista de andamento
- Cada tarefa possui:
  - Título
  - Descrição
  - Responsável
  - Prioridade (Alta, Média, Baixa)
  - Deadline

---

## 🏗️ Arquitetura

O projeto foi estruturado em camadas:

- `controller` → controle da aplicação (JSF ManagedBean)
- `service` → regras de negócio
- `repository` → acesso a dados (JPA)
- `model` → entidades
- `util` → configuração do JPA

---

## ⚙️ Como executar o projeto
#### Pré-requisitos

- Java 17+ (ou versão utilizada)
- Apache Tomcat 9+
- Docker (para o banco)

### 1. Subir o banco de dados

```bash
docker compose up -d
```
### Banco configurado
  - DataBase: tarefas_db
  - Usuario: admin
  - Senha: 123456

### PgAdmin configurado
  - Usuario: admin@admin.com.br
  - Senha: admin

### 2. Configuração

Verifique o arquivo `persistence.xml`:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5433/tarefas_db" />
<property name="javax.persistence.jdbc.user" value="admin" />
<property name="javax.persistence.jdbc.password" value="123456" />

````
### 3. Executar aplicação
Fazer deploy no Tomcat (via IntelliJ ou manual)
Acessar no navegador:
http://localhost:8080/GerenciadordeTarefas_war_exploded/CadastroTarefas.xhtml
