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

## Swagger

```
http://localhost:8080/swagger-ui.html#/
```

## Banco de dados

```
db/init.sql
``` 

## Collection do postman para manipulação das tabelas

```
Avaliacao-java.postman_collection.json
``` 
