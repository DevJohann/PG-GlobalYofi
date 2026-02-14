# 💅 Global Yofi - Backend

**Global Yofi** es una plataforma de administración y venta de productos de belleza.  
Este repositorio contiene el backend desarrollado en **Spring Boot 3**, utilizando **Java 17**, **Spring Security con JWT**, **JPA/Hibernate**, y **MySQL** como base de datos relacional.

---

## 🚀 Objetivo del Proyecto

El propósito de este sistema es centralizar y optimizar la gestión de productos, inventarios, proveedores, reportes y usuarios dentro de una tienda de belleza.  
Incluye autenticación segura mediante **JSON Web Tokens (JWT)** y soporte para **roles (ADMIN y CLIENTE)**.

---

## 🧩 Características Principales

- ✅ CRUD completo de **Productos**, **Proveedores**, y **Categorías**.
- ✅ Sistema de **autenticación y registro con JWT** (sin Basic Auth).
- ✅ Gestión de **usuarios y roles** (`ADMIN`, `CLIENTE`).
- ✅ Generación de **reportes de productos e inventarios**.
- ✅ **Borrado lógico** de productos (`estado` en vez de eliminación física).
- ✅ Endpoints públicos y protegidos por rol.
- ✅ Configuración de seguridad moderna con **Spring Security 6**.
- ✅ Desarrollado bajo principios **RESTful** y **Clean Architecture**.

---

## 🏗️ Tecnologías Utilizadas

| Tipo | Tecnología / Herramienta |
|------|---------------------------|
| Lenguaje | Java 17 |
| Framework principal | Spring Boot 3.5.6 |
| Seguridad | Spring Security + JWT |
| Persistencia | Spring Data JPA / Hibernate |
| Base de datos | MySQL |
| Dependencias clave | Lombok, Validation API, DevTools |
| API Docs (opcional futuro) | Swagger / OpenAPI |
| Build Tool | Maven |

---

## ⚙️ Requisitos Previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- ☕ **Java 17 o superior**
- 🧰 **Maven 3.8+**
- 🐬 **MySQL Server** (o una base de datos compatible)
- 🧠 Un IDE como **IntelliJ IDEA**, **VS Code**, o **Eclipse**

---

## 🧱 Configuración del Proyecto

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/global-yofi-backend.git
cd global-yofi-backend
```
### 2. Configurar la base de datos
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/global_yofi_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
Los scripts de la base de datos los encontrara en /.Scripts
Ejecutalos en tu motor de BD.

El backend estará disponible en:
🟢 http://localhost:8080

### 📁 Estructura del Proyecto
```bash
src/
├── main/
│   ├── java/com/globalyofi/backend/
│   │   ├── config/               # Configuración de seguridad y beans
│   │   ├── controller/           # Controladores REST
│   │   ├── dto/                  # Objetos de transferencia (Request/Response)
│   │   ├── entity/               # Entidades JPA
│   │   ├── repository/           # Repositorios JPA
│   │   ├── security/             # Filtros JWT, utilidades y servicio de autenticación
│   │   └── service/              # Lógica de negocio
│   └── resources/
│       ├── application.properties
│       └── schema.sql (opcional)
└── test/
    └── ... (tests unitarios y de integración esto a futuro)
```

### 🧑‍💻 Autores
Edison Mauricio Beltran
📧 [embeltrang@unbosque.edu.co]
🌐 [https://www.linkedin.com/in/mauricio-beltr%C3%A1n-345bb92b4/]

Johann Toncon
📧 [jtoncon@unbosque.edu.co]
🌐 [www.linkedin.com/in/johann-felipe]
