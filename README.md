cat <<EOF > README.md
# 🧶 Brechó API - Backend

Backend para o sistema de vendas de brechó, com autenticação JWT, CRUDs protegidos e integração com banco de dados MySQL.

## 🚀 Funcionalidades

- ✅ Cadastro e login com autenticação JWT  
- 🔐 Endpoints protegidos com token  
- 🛍️ CRUDs de produtos, categorias, vendas e usuários  
- 📦 Módulo completo para vincular ações ao usuário autenticado  
- 🧪 Testes via Postman com perfil `application-test.properties`

## 🛠️ Tecnologias

- Java + Spring Boot  
- MySQL  
- Maven  
- JWT  
- MapStruct  
- Lombok  

## 📂 Estrutura

\`\`\`
src/
└── main/
    └── java/
        └── com/brecho/SistemasVendas/
            ├── controllers/
            ├── dtos/
            ├── entities/
            ├── mappers/
            ├── repositories/
            └── services/
\`\`\`

## ⚙️ Como rodar o projeto localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/chriissf/brecho.git
   cd brecho/Backend

   Configure o application-dev.properties

Rode o projeto:

bash
./mvnw spring-boot:run
📜 Histórico de versões
Veja o CHANGELOG.md ou acesse as tags do projeto.

