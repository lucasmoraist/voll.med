# Voll Med
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## Descrição
O projeto Vollmed é uma API Rest destinada ao gerenciamento de consultas em clínicas, permitindo a organização eficiente de agendamentos, pacientes e profissionais de saúde. A aplicação é ideal para otimizar o fluxo de trabalho nas clínicas, proporcionando uma experiência prática tanto para os usuários quanto para os administradores.

## Funcionalidades
1. Gerenciamento de Pacientes
   - Adicione, atualize e remova registros de pacientes.
2. Agendamento de Consultas
   - Permita o agendamento de consultas com validações para evitar conflitos de horário.
3. Cancelamento de Consultas
   - Realize o cancelamento de consultas, mantendo um histórico de alterações.
4. Visualização de Consultas
   - Consulte informações sobre todas as consultas agendadas.
5. Autenticação e Autorização
   - Implemente autenticação JWT (JSON Web Token) para proteger a API.
   - Utilize Spring Security para gerenciar usuários e permissões.
6. Documentação
   - Documentação da API utilizando Swagger.
   - Acesse a documentação na máquina através do link: http://localhost:8080/

## Instruções de Instalação
### Pré-requisitos
- Java 17 ou superior
- IDE (Eclipse, IntelliJ, VSCode)
- Maven 3.2.5 ou superior
- Docker
### Etapas
1. Clone o repositório na sua máquina
````shell
git clone https://github.com/lucasmoraist/voll.med.git 
````
2. Acesse o diretório do projeto
````shell
cd voll.med
````
3. Execute o Docker Compose para criar o container do banco de dados
````shell
docker-compose up -d
````
4. Faça o build da aplicação

**Windows**
````shell
mvn clean package ^
  -Djwt.secret=123456
````
5. Execute o arquivo jar gerado pelo build

**Windows**
````shell
java -jar target/api-0.0.1-SNAPSHOT.jar ^
  --jwt.secret=123456 ^
  --spring.profiles.active=dev
````

## Instruções de Uso
- Com o projeto em execução, abra sua ferramenta para testes de requisições (Insomnia ou Postman).
- Importe o arquivo json que está em `./collection` e já será possível realizar os testes.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests com melhorias, correções de bugs ou novos recursos.

## Contatos
<a href = "mailto:seu-email@gmail.com">
  <img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank" alt="">
</a>
<a href="https://www.linkedin.com/in/seu-linkedin/" target="_blank">
  <img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank" alt="">
</a>
