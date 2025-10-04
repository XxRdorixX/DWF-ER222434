# Spring API Rest - Complementarios (Post & PostComment)

Proyecto listo para ejecutar:
- Java 17
- Spring Boot 3.3.9 (Web, Data JPA, H2)
- Lombok
- MapStruct 1.6.2
- Tests: JPA (@DataJpaTest), Mockito (service), @WebMvcTest (controller), y pruebas de Mapper.

## Ejecutar
```bash
./mvnw spring-boot:run
# o con Maven instalado:
mvn clean package
java -jar target/spring-api-rest-0.0.1-SNAPSHOT.jar
```
## Endpoints
- `GET /posts`, `GET /posts/{id}`, `POST /posts`, `PUT /posts/{id}`, `DELETE /posts/{id}`
- `GET /comments?postId=...`, `GET /comments/{id}`, `POST /comments`, `PUT /comments/{id}`, `DELETE /comments/{id}`
