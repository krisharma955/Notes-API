<div align="center">

# 📝 Notes API

**A secure, fast, and feature-rich REST API for managing personal notes.**

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![JWT](https://img.shields.io/badge/Auth-JWT-black?logo=jsonwebtokens)](https://jwt.io/)
[![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)
[![Deployed on Render](https://img.shields.io/badge/Deployed%20on-Render-46E3B7?logo=render&logoColor=white)](https://notes-api-1-gng7.onrender.com)

🔗 **Live API** → [notes-api-1-gng7.onrender.com](https://notes-api-1-gng7.onrender.com)
📘 **Swagger Docs** → [/swagger-ui/index.html](https://notes-api-1-gng7.onrender.com/swagger-ui/index.html)

</div>

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot (Web, Data JPA, Security, Validation) |
| Database | PostgreSQL |
| Auth | JWT (JJWT) |
| Mapping | MapStruct + Lombok |
| Docs | Springdoc OpenAPI (Swagger UI) |
| Deployment | Docker, Render |

---

## ✨ Features

### 🔐 Authentication
- Signup and login with JWT-based stateless authentication.
- Passwords hashed with BCrypt.

### 🗒️ Notes Management
- Create, read, update, and soft-delete notes.
- 📌 Pin / unpin notes.
- 🗃️ Archive / restore notes.

### 🔍 Search, ↕️ Sort & 📄 Pagination
> The core of how notes are queried and browsed.

| Capability | How it works |
|---|---|
| **Search** | Filter notes by keyword — matches note title, case-insensitive |
| **Sort** | Sort by any field (`createdAt`, `title`, etc.), ascending or descending |
| **Pagination** | Control page number and page size for note listings |

```http
GET /api/notes?keyword=meeting&page=0&size=10&sortBy=createdAt&direction=desc
```

### 👤 User Profile
- View and update profile (name, password).
- Soft-delete account.

---

## 📡 API Endpoints

### 🔐 Auth
| Method | Endpoint | Description |
|:---:|---|---|
| `POST` | `/api/auth/signup` | Register a new user |
| `POST` | `/api/auth/login` | Login and receive JWT |

### 🗒️ Notes
| Method | Endpoint | Description |
|:---:|---|---|
| `POST` | `/api/notes` | Create a new note |
| `GET` | `/api/notes/{id}` | Get a note by ID |
| `GET` | `/api/notes` | Get all notes 🔍 search · ↕️ sort · 📄 paginate |
| `PATCH` | `/api/notes/{id}` | Update a note |
| `DELETE` | `/api/notes/{id}` | Soft-delete a note |
| `PATCH` | `/api/notes/{id}/pin` | 📌 Pin or unpin a note |
| `PATCH` | `/api/notes/{id}/archive` | 🗃️ Archive or restore a note |

### 👤 Users
| Method | Endpoint | Description |
|:---:|---|---|
| `GET` | `/api/users/me` | Get current user profile |
| `PATCH` | `/api/users` | Update current user profile |
| `DELETE` | `/api/users` | Soft-delete current user |

📘 Full request/response schemas are available in the [Swagger UI](https://notes-api-1-gng7.onrender.com/swagger-ui/index.html).

---

## 🔑 Authentication

All endpoints except `/api/auth/**` require a valid JWT, sent via the `Authorization` header:

```http
Authorization: Bearer <token>
```

---

## 🚀 Running Locally

### Prerequisites
- ☕ Java 21
- 🐘 PostgreSQL
- 📦 Maven (or use the included `mvnw` wrapper)

### Environment Variables
| Variable | Description |
|---|---|
| `DB_URL` | PostgreSQL JDBC URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `JWT_SECRET` | Secret key for signing JWTs |
| `JWT_EXPIRATION` | Token expiration time (ms) |

### Build & Run
```bash
./mvnw clean package -DskipTests
java -jar target/Notes-API-0.0.1-SNAPSHOT.jar
```

### 🐳 Run with Docker
```bash
docker build -t notes-api .
docker run -p 8080:8080 --env-file .env notes-api
```

---

## ☁️ Deployment

<div align="center">

The API is deployed on **Render** and live at:

### 🔗 [notes-api-1-gng7.onrender.com](https://notes-api-1-gng7.onrender.com)

Interactive API documentation via Swagger UI:

### 📘 [/swagger-ui/index.html](https://notes-api-1-gng7.onrender.com/swagger-ui/index.html)

</div>
