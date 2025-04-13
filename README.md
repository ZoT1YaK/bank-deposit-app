## Prerequisites

To set up and run this project locally or in containers, you need:

- [Node.js (v18+)](https://nodejs.org/)
- [Angular CLI](https://angular.io/cli)
- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

##  Tech Stack

- **Frontend**: Angular (standalone components, SCSS)
- **Backend**: Quarkus (RESTEasy, Hibernate, JPA)
- **Database**: PostgreSQL
- **Deployment**: Docker, Docker Compose

---

## Quick Start with Docker

### 1. Clone the repository

```bash
git clone https://github.com/ZoT1YaK/bank-deposit-app.git
cd bank-deposit-app
```


### 2. Set up your environment variables

Copy the template:
```bash
cp .env.template .env
```

Then edit the .env file with your local credentials. For example:
```env
POSTGRES_DB=postgres
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_password

QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://db:5432/postgres
QUARKUS_DATASOURCE_USERNAME=postgres
QUARKUS_DATASOURCE_PASSWORD=your_password
```

### 3. Build and run all services

```bash
docker-compose up --build
```

---

## Access Points
- **Frontend**: http://localhost:4200
- **Backend**: 
  - Via Docker: http://localhost:4200/api/deposits
  - Via Local Dev: http://localhost:8081/deposits
- **PostgreSQL**: running on port 5432

---

## Development Setup (without Docker)

### Backend

Create a src/main/resources/application-local.properties with:

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/postgres
quarkus.datasource.username=postgres
quarkus.datasource.password=your_password

quarkus.http.port=8081

# CORS (required for Angular frontend)
quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:4200
quarkus.http.cors.methods=GET,POST,PUT,DELETE,OPTIONS
quarkus.http.cors.headers=accept,authorization,content-type,x-requested-with
```

```bash
cd backend
./mvnw quarkus:dev -Dquarkus.profile=local
```
Runs at: http://localhost:8081

### Frontend

```bash
cd frontend
npm install
npm start
```
Uses `proxy.conf.json` to redirect `/api` calls to http://localhost:8081

Runs at: http://localhost:4200