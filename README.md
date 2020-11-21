# avaliacao_java
Projeto Avaliação Java, crud de perguntas e Respostas.

Tecnologias:

- Java 11
- Spring
- Spring Boot
- MySQL
- Docker

## Subindo a API

#### Com Maven

Faça o clone do projeto e rode os comandos:

```
mvn clean package
```

Assim o Maven irá criar o `.jar` dentro de `target`.

Feito isso, configure o banco de dados, pode ser via Docker para mais facilidade.

```
docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=business -e MYSQL_DATABASE=comexport mysql:8.0
```

E por fim, rode o `.jar`:

```
java -jar target/*.jar
```

#### Com Docker

Para rodar o projeto com docker, basta utilizar o `docker-compose` que tudo será feito automáticamente:

```
docker-compose up
```

## Request http para manipulação da tabelas

- Inserindo uma role

POST /comexport/v1/role HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "description": "moderador",
  "enabled": true
}

- Inserindo um usuário

POST /comexport/v1/user HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
   "name":"Ronaldo Sousa de Paulo",
   "email":"ronaldonet@hotmail.com.br",
   "birthdate":"17/02/1989",
   "id_role": 1
}

-Inserindo flags

POST /comexport/v1/flags HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
  "description": "Java",
  "enabled": true
}

-Consulta Role por id

GET /comexport/v1/role/1 HTTP/1.1
Host: localhost:8080

-Consulta usuário por id

GET /comexport/v1/user/1 HTTP/1.1
Host: localhost:8080



