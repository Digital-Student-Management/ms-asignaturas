# ms-asignaturas

Microservicio independiente del **Sistema Escolar del Colegio Bernardo O'Higgins de Coquimbo**, encargado de la gestión de asignaturas y sus bitácoras de clases asociadas.

---

## 📋 Descripción

`ms-asignaturas` es un microservicio REST autónomo que forma parte del ecosistema del sistema escolar digital. Su responsabilidad es administrar el catálogo de **asignaturas** (materias del establecimiento) y mantener un registro histórico de **bitácoras de clases**, donde cada entrada documenta las actividades realizadas, los objetivos de aprendizaje y las observaciones del docente para una sesión específica.

El microservicio expone una API RESTful con operaciones CRUD completas para ambos recursos, siguiendo principios de arquitectura limpia mediante la separación en capas: controlador, servicio, repositorio y modelo.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología               | Versión     | Rol                                           |
|--------------------------|-------------|-----------------------------------------------|
| Java                     | 21          | Lenguaje de programación                      |
| Spring Boot              | 3.4.5       | Framework principal del microservicio         |
| Maven                    | 3.x         | Gestión de dependencias y ciclo de vida       |
| MySQL                    | 8.x         | Motor de base de datos relacional             |
| Spring Data JPA          | (Boot 3)    | Abstracción del acceso a datos con Hibernate  |
| Lombok                   | (Boot 3)    | Reducción de código boilerplate               |
| Jakarta Validation       | (Boot 3)    | Validación de datos en DTOs                   |
| SpringDoc OpenAPI (Swagger) | 2.8.6    | Documentación interactiva de la API           |

---

## 📁 Estructura del Proyecto

```
ms-asignaturas/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/ms_sistemaEscolar/ms_asignaturas/
│       │       ├── MsAsignaturasApplication.java      # Clase principal de arranque
│       │       ├── config/
│       │       │   └── CorsConfig.java                # Configuración global de CORS
│       │       ├── controllers/
│       │       │   ├── AsignaturaController.java       # Endpoints REST de Asignaturas
│       │       │   └── BitacoraAsignaturaController.java # Endpoints REST de Bitácoras
│       │       ├── models/
│       │       │   ├── dto/
│       │       │   │   ├── AsignaturaDTO.java          # DTO para Asignatura
│       │       │   │   └── BitacoraAsignaturaDTO.java  # DTO para Bitácora
│       │       │   └── entity/
│       │       │       ├── Asignatura.java             # Entidad JPA de Asignatura
│       │       │       └── BitacoraAsignatura.java     # Entidad JPA de Bitácora
│       │       ├── repositories/
│       │       │   ├── AsignaturaRepository.java       # Repositorio Spring Data JPA
│       │       │   └── BitacoraAsignaturaRepository.java
│       │       └── services/
│       │           ├── AsignaturaService.java          # Lógica de negocio de Asignaturas
│       │           └── BitacoraAsignaturaService.java  # Lógica de negocio de Bitácoras
│       └── resources/
│           └── application.properties                  # Configuración de la aplicación
├── peticiones.rest                                     # Pruebas de endpoints (REST Client)
└── pom.xml                                             # Descriptor de dependencias Maven
```

---

## 🗄️ Modelo de Datos

El microservicio gestiona dos tablas relacionadas en la base de datos:

### `asignatura`
Almacena el catálogo de materias del establecimiento.

| Columna             | Tipo    | Descripción                          |
|---------------------|---------|--------------------------------------|
| `id_asignatura`     | INT (PK)| Identificador único autoincremental  |
| `nombre_asignatura` | VARCHAR | Nombre de la asignatura (requerido)  |
| `horas_semanales`   | INT     | Carga horaria semanal                |

### `bitacora_asignatura`
Registra el historial de clases para cada asignatura.

| Columna                   | Tipo    | Descripción                                       |
|---------------------------|---------|---------------------------------------------------|
| `id_bitacora_asignatura`  | INT (PK)| Identificador único autoincremental               |
| `fecha_clase`             | DATE    | Fecha en que se realizó la clase                  |
| `actividades_realizadas`  | TEXT    | Descripción de las actividades ejecutadas         |
| `observaciones_generales` | TEXT    | Observaciones del docente sobre la sesión         |
| `objetivo_aprendizaje`    | TEXT    | Objetivo pedagógico de la clase                   |
| `id_asignatura`           | INT (FK)| Referencia a la asignatura a la que pertenece     |

### Relación entre entidades

```
Asignatura  ──────────────────────────────  BitacoraAsignatura
    (1)         OneToMany / ManyToOne              (N)

Una Asignatura puede tener múltiples BitacoraAsignatura,
pero cada BitacoraAsignatura pertenece a una única Asignatura.
```

---

## ⚙️ Configuración y Ejecución

### Requisitos previos
- **Java 21** instalado y configurado en el `PATH`
- **MySQL 8.x** en ejecución local
- **Maven 3.x** disponible (o usar el wrapper `mvnw` incluido)

### Propiedades de la aplicación (`application.properties`)

```properties
# Servidor
server.port=8081

# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/db_asignaturas?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=<tu_contraseña>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
```

> **Nota:** La base de datos `db_asignaturas` se crea automáticamente si no existe gracias al parámetro `createDatabaseIfNotExist=true`. El esquema de tablas es generado por Hibernate en el primer arranque.

### Levantar el servicio

```bash
# Con Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Con Maven instalado localmente
mvn spring-boot:run
```

La aplicación quedará disponible en: **`http://localhost:8081`**

---

## 🌐 Endpoints Disponibles

### Asignaturas — `/api/asignaturas`

| Método | Endpoint                | Descripción                         |
|--------|-------------------------|-------------------------------------|
| `GET`  | `/api/asignaturas`      | Lista todas las asignaturas         |
| `GET`  | `/api/asignaturas/{id}` | Obtiene una asignatura por su ID    |
| `POST` | `/api/asignaturas`      | Crea una nueva asignatura           |
| `PUT`  | `/api/asignaturas/{id}` | Actualiza una asignatura existente  |
| `DELETE` | `/api/asignaturas/{id}` | Elimina una asignatura           |

### Bitácoras — `/api/bitacoras`

| Método   | Endpoint                             | Descripción                               |
|----------|--------------------------------------|-------------------------------------------|
| `GET`    | `/api/bitacoras`                     | Lista todas las bitácoras                 |
| `GET`    | `/api/bitacoras/{id}`                | Obtiene una bitácora por su ID            |
| `GET`    | `/api/bitacoras/asignatura/{id}`     | Lista bitácoras por asignatura            |
| `POST`   | `/api/bitacoras`                     | Crea una nueva bitácora de clase          |
| `PUT`    | `/api/bitacoras/{id}`                | Actualiza una bitácora existente          |
| `DELETE` | `/api/bitacoras/{id}`                | Elimina una bitácora                      |

---

## 📄 Documentación Interactiva (Swagger UI)

Una vez iniciada la aplicación, la documentación OpenAPI está disponible en:

```
http://localhost:8081/swagger-ui.html
```

---

## 🧪 Pruebas de Endpoints

El proyecto incluye el archivo **`peticiones.rest`** en la raíz del repositorio, que contiene una colección de 11 peticiones HTTP listas para ejecutar con la extensión [REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) de Visual Studio Code.

### Cómo usarlo

1. Instalar la extensión **REST Client** en VS Code (`humao.rest-client`).
2. Abrir el archivo `peticiones.rest` en el editor.
3. Hacer clic en el botón **"Send Request"** que aparece sobre cada bloque de petición.

### Peticiones incluidas

| #  | Método   | Recurso      | Descripción                            |
|----|----------|--------------|----------------------------------------|
| 1  | `POST`   | Asignatura   | Crear nueva asignatura                 |
| 2  | `GET`    | Asignatura   | Listar todas las asignaturas           |
| 3  | `GET`    | Asignatura   | Buscar asignatura por ID               |
| 4  | `PUT`    | Asignatura   | Actualizar asignatura existente        |
| 5  | `DELETE` | Asignatura   | Eliminar una asignatura                |
| 6  | `POST`   | Bitácora     | Crear nueva bitácora de clase          |
| 7  | `GET`    | Bitácora     | Listar todas las bitácoras             |
| 8  | `GET`    | Bitácora     | Buscar bitácora por ID                 |
| 9  | `GET`    | Bitácora     | Listar bitácoras por asignatura        |
| 10 | `PUT`    | Bitácora     | Actualizar bitácora existente          |
| 11 | `DELETE` | Bitácora     | Eliminar una bitácora                  |

---

## 🏫 Contexto del Sistema

Este microservicio forma parte del **Sistema de Gestión Escolar Digital** del **Colegio Bernardo O'Higgins de Coquimbo**. Cada módulo del sistema opera de forma autónoma y se comunica mediante APIs REST, permitiendo una arquitectura escalable y de fácil mantenimiento.

---

*Desarrollado con Spring Boot 3 · Java 21 · Colegio Bernardo O'Higgins — Coquimbo*
