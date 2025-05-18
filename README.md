
# 📘 Digital Buho API - SOPORTE

Bienvenido al repositorio oficial de **Digital Buho API - SOPORTE**, un backend desarrollado con **Spring Boot** que ofrece servicios RESTful para la gestión de soporte técnico en una plataforma de cursos alternativos y accesibles.

## 🚀 Descripción General

Esta API forma parte de una solución empresarial desarrollada para **Digital Buho SAC**, cuya misión es brindar soporte técnico eficiente y accesible para plataformas educativas. La arquitectura se basa en microservicios utilizando Spring Boot, documentada con Swagger, desplegada en la nube y lista para integrarse con diversos clientes frontend.

## 🧩 Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot**
- **Supabase (PostgreSQL)**
- **Swagger (Documentación)**
- **Postman (Pruebas)**
- **Render (Despliegue en la nube)**

## 📂 Estructura del Proyecto

```bash
├── controller/       # Controladores REST
├── model/            # Entidades del dominio
├── repository/       # Interfaces de acceso a datos
├── service/          # Lógica de negocio
├── configuration/    # Configuraciones generales
└── application.properties  # Configuración del proyecto
```

## 🌐 Endpoints RESTful

| Método | Ruta                          | Acción        |
|--------|-------------------------------|---------------|
| GET    | `/api/{tabla}/listar`         | Listar todos  |
| POST   | `/api/{tabla}/crear`          | Crear nuevo   |
| GET    | `/api/{tabla}/listar/{id}`    | Buscar por ID |
| PUT    | `/api/{tabla}/actualizar/{id}`| Actualizar    |
| DELETE | `/api/{tabla}/eliminar/{id}`  | Eliminar      |

## 🔎 Documentación

La API cuenta con documentación Swagger integrada para facilitar el desarrollo y pruebas. Una vez desplegada, accede a:

```
https://digital-buho-api-soporte.onrender.com/swagger-ui/index.html
```

## 📦 Pruebas con Postman

Todas las rutas han sido probadas con Postman, incluyendo los métodos:

- `GET`: Listado general e individual.
- `POST`: Creación de registros.
- `PUT`: Actualización de datos.
- `DELETE`: Eliminación de entradas.

## ☁️ Despliegue

El proyecto está **en producción** gracias a Render y accesible en el siguiente enlace:

🔗 [API en producción](https://digital-buho-api-soporte.onrender.com/)

## 📎 Enlaces del Proyecto

- 🔗 [Repositorio en GitHub](https://github.com/SiologoDr/Digital-Buho-API-SOPORTE)
- 🔗 [API en la nube](https://digital-buho-api-soporte.onrender.com/)

## 👤 Autor

**Digital Buho SAC**  
Proyecto desarrollado en el marco de un curso de negocios modernos con tecnología Java Spring Boot.
