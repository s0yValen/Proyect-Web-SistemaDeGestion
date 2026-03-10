#Sistema de Gestión Web
La aplicación es una plataforma web robusta diseñada para gestionar empresas, empleados y transacciones financieras (ingresos y egresos).

🚀 Tecnologías Utilizadas
Backend: Java con Spring Boot (vía Maven).

Frontend: HTML5, CSS3 y Thymeleaf como motor de plantillas.

Persistencia: Spring Data JPA con soporte para bases de datos relacionales (PostgreSQL/MySQL).

Gestión de Dependencias: Maven.

Seguridad: Implementación básica de autenticación y control de acceso.

📋 Características del Proyecto
El sistema permite realizar las siguientes operaciones (CRUD completo):

Gestión de Empresas: Registro, edición y visualización de empresas asociadas al sistema.

Gestión de Empleados: Administración del personal, vinculación a empresas y asignación de roles (Administrador / Operativo).

Gestión de Movimientos Dinero: Registro detallado de ingresos y egresos, vinculados al empleado que realizó la transacción.

Interfaz Responsiva: Diseño adaptable para visualización en diferentes dispositivos.

🛠️ Instalación y Configuración
Para ejecutar este proyecto de forma local, sigue estos pasos:

Requisitos previos
Java JDK 17 o superior.

Maven instalado.

Una base de datos (PostgreSQL recomendada).

Pasos a seguir
Clonar el repositorio:

Bash
git clone https://github.com/s0yValen/Proyect-Web-SistemaDeGestion.git
Configurar la base de datos:
Edita el archivo src/main/resources/application.properties con tus credenciales de base de datos:

Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_tu_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
Ejecutar la aplicación:

Bash
./mvnw spring-boot:run
Acceder al sistema:
Abre tu navegador en http://localhost:8080

📂 Estructura del Código
src/main/java/.../entities: Modelos de datos (Empresa, Empleado, MovimientoDinero).

src/main/java/.../repositories: Interfaces para la comunicación con la base de datos.

src/main/java/.../services: Lógica de negocio de la aplicación.

src/main/java/.../controllers: Manejo de las rutas y peticiones web.

src/main/resources/templates: Vistas HTML dinámicas con Thymeleaf.

✒️ Autores
Paula Valentina Vargas Homez - Desarrollo Completo - s0yValen
