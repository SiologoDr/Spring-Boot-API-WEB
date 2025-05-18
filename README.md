
# 📘 Digital Buho API - Sistema de Soporte y Gestión Web

![Spring Boot Logo](https://spring.io/img/spring.svg)

Bienvenido al repositorio unificado de los proyectos **Digital Buho API - SOPORTE** y **Spring Boot API WEB**. Este sistema combina una API RESTful robusta y un conjunto de controladores web, desarrollado con **Spring Boot**, para la gestión integral de datos de soporte técnico y entidades empresariales.

---

## 🚀 Descripción General

El sistema está diseñado para resolver la problemática de accesibilidad, gestión eficiente y estandarizada de datos mediante una arquitectura RESTful, facilitando la interacción entre clientes, desarrolladores, técnicos de soporte y administradores.

Se implementa con Spring Boot y se documenta con Swagger para un consumo intuitivo. Está desplegado en la nube y listo para producción.

---

## 🎯 Objetivos del Proyecto

### Objetivo General
Desarrollar una API RESTful robusta y segura para gestionar información empresarial, accesible mediante controladores REST y web.

### Objetivos Específicos
- Implementar operaciones CRUD completas para todas las entidades.
- Documentar la API utilizando Swagger.
- Crear una estructura modular y escalable.
- Desarrollar controladores web para vistas específicas.
- Desplegar la aplicación en la nube con disponibilidad continua.

---

## 🧩 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL (Supabase)**
- **Swagger / OpenAPI 3.0**
- **Maven**
- **Postman**
- **Render (Cloud Hosting)**

---

## 📂 Estructura del Proyecto

### Backend Principal (`/src/main/java`)

```bash
├── controllers/       # Controladores REST
├── models/            # Entidades de la BD
├── repositories/       # Interfaces de acceso a datos
├── services/          # Lógica de negocio
├── web/    # Controladores WEB
└── ApirestApplication  # Arranque del proyecto
```

### Plantillas Web (`/src/main/resources/templates`)

```bash
├── web-asignacion/  # Vistas del CRUD de ASIGNACION
├── web-cliente/  # Vistas del CRUD de CLIENTE
├── web-desarrollador/  # Vistas del CRUD de DESARROLLADOR
├── web-estado/  # Vistas del CRUD de ESTADO
├── web-notificacion-cliente/  # Vistas del CRUD de NOTIFICACION C
├── web-notificacion-desarrollador/  # Vistas del CRUD de NOTIFICACION D
├── web-persona/  # Vistas del CRUD de PERSONA
├── web-revision/  # Vistas del CRUD de REVISION
├── web-rol/  # Vistas del CRUD de ROL
├── web-solicitud/  # Vistas del CRUD de SOLICITUD
├── web-tecnico/  # Vistas del CRUD de TECNICO
├── web-usuario/  # Vistas del CRUD de USUARIO
├── index.html/  # Vista Principal de la WEB
└── application.properties  # Configuración del proyecto
```

---

## 🌐 Endpoints RESTful

| Método | Ruta                          | Acción        |
|--------|-------------------------------|---------------|
| GET    | `/api/{tabla}/listar`         | Listar todos  |
| POST   | `/api/{tabla}/crear`          | Crear nuevo   |
| GET    | `/api/{tabla}/listar/{id}`    | Buscar por ID |
| PUT    | `/api/{tabla}/actualizar/{id}`| Actualizar    |
| DELETE | `/api/{tabla}/eliminar/{id}`  | Eliminar      |

---

## 🧪 Pruebas y Documentación

- **Postman**: Incluye pruebas para cada operación CRUD.
- **Swagger**: Documentación interactiva disponible en:

```
https://spring-boot-api-web.onrender.com/swagger-ui/index.html
```

---

## 🛠 Instalación y Ejecución Local

### Requisitos Previos

- Java JDK 17+
- Maven 3.6+

### Pasos

```bash
git clone https://github.com/SiologoDr/Spring-Boot-API-WEB.git
cd Spring-Boot-API-WEB
mvn clean install
mvn spring-boot:run
```

Aplicación disponible en: `http://localhost:8080`

---

## ☁️ Despliegue en Producción

- 🌐 [API Web Desplegada](https://spring-boot-api-web.onrender.com/)

---

## 👤 Autor

**Digital Buho SAC**  
Proyecto desarrollado como parte de una solución empresarial y académica usando tecnologías modernas Java con Spring Boot.

---

## 📝 Licencia

Licenciado bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.
