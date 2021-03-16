# Employer REST API
*Backend Spring REST API coding exercise*

## Goal
To create a simple Spring Boot application with REST API services provider.

Java 8 or newer and Spring Boot must be used.

## Requirements
- [x] 1. Scaffold the project with data source connection (any database but in memory one is preferred).
- [x] 2. reate API for authentication, check User existing and return generated token (Use this token to secure APIs on point 3-7).
- [x] 3. Create API for retrieving all employee.
- [x] 4. Create API for retrieving one employee by ID.
- [x] 5. Create API for saving one employee.
- [x] 6. Create API for modifying one employee.
- [x] 7. Create API for deleting one employee by ID.
- [x] 8. Unit testing.
- [ ] 9. Integration testing.
- [x] 10. Swagger is a plus.
- [x] 11. Liquibase is a plus.
- [x] 12. MapStruct is a plus.
- [x] 13. Docker is a plus.



## Docker
https://hub.docker.com/r/norrapat/employer-exercise

To pull image from Docker Hub. Run `docker pull norrapat/employer-exercise:1.0.1`


To start as container.

```
docker run -p 8080:8080 --name employer-app norrapat/employer-exercise:1.0.1
```

## REST API endpoint
http://localhost:8080/employer/swagger-ui/

## How to get authenticated with OAuth2
This application provided two ways to get authenticated.

- Swagger UI --- [authorize] button
- Directly call REST API to `POST /auth/token` with query
  params `username` and `password`

---

### Demo Users ###

- **Administrator**
  Username: `admin`
  Password: `password`
- **Norrapat Nimmanee**
  Username: `norrapatni`
  Password: `password`
- **Krittapas Bavontaweepanya**
  Username: `krittapasba`
  Password: `password`
- **Chatmongkol Wong-ake**
  Username: `chatmongkolwo`
  Password: `password`

---
