# PruebaBackend

## Descripción del proyecto

PruebaBackend es una API REST desarrollada con Spring Boot para gestionar pacientes, médicos y citas médicas. El sistema permite registrar pacientes y médicos, reservar citas, consultar franjas disponibles, cancelar citas y listar citas con filtros. La implementación sigue una estructura basada en capas de dominio, aplicación, infraestructura y API.

## Arquitectura utilizada

El proyecto está organizado con una arquitectura hexagonal/limpia, separando responsabilidades en capas:

- `domain`: modelos de negocio y contratos de repositorio.
- `application`: servicios de caso de uso (crear pacientes, crear citas, cancelar citas, listar citas, etc.).
- `infraestructure`: entidades JPA, repositorios, adaptadores y mapeo a base de datos.
- `api`: controladores REST y DTOs de entrada/salida.

Tecnologías principales:
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- H2/PostgreSQL compatible (según configuración)

## Requisitos previos

Asegúrate de tener instalado:
- Java 17 o superior
- Maven 3.8+ (o usar el wrapper `./mvnw`)

## Cómo compilar el proyecto

Desde la raíz del proyecto:

```bash
chmod +x mvnw
./mvnw compile
```

Para compilar limpiando artefactos previos:

```bash
./mvnw clean compile
```

## Cómo ejecutar la aplicación

Inicia la aplicación con:

```bash
./mvnw spring-boot:run
```

La API quedará disponible en:

```text
http://localhost:8080
```

## Cómo ejecutar las pruebas unitarias

Ejecuta todas las pruebas con:

```bash
./mvnw test
```

Si solo deseas ejecutar una clase de prueba específica:

```bash
./mvnw -Dtest=CrearPacienteServiceTest test
```

## Endpoints principales

### Pacientes
- `POST /api/pacientes` - Crear paciente
- `GET /api/pacientes` - Listar pacientes
- `GET /api/pacientes/{id}` - Buscar paciente por ID
- `PUT /api/pacientes/{id}` - Actualizar paciente
- `DELETE /api/pacientes/{id}` - Eliminar paciente

### Médicos
- `POST /api/medicos` - Crear médico
- `GET /api/medicos` - Listar médicos
- `GET /api/medicos/{id}` - Buscar médico por ID
- `PUT /api/medicos/{id}` - Actualizar médico
- `DELETE /api/medicos/{id}` - Eliminar médico

### Citas
- `POST /api/citas` - Crear cita
- `GET /api/citas` - Listar citas con filtros
- `GET /api/citas/available` - Consultar disponibilidad de citas
- `POST /api/citas/{id}/cancel` - Cancelar cita

## Ejemplos de JSON para probar la API

### Crear paciente

Request:
```json
{
  "fullName": "Ana Gómez",
  "documento": "12345678",
  "phone": "3001234567",
  "email": "ana@example.com"
}
```

### Crear médico

Request:
```json
{
  "fullName": "Dr. Carlos Ruiz",
  "specialty": "Cardiología",
  "phone": "3109876543",
  "email": "carlos@example.com"
}
```

### Crear cita

Request:
```json
{
  "pacienteId": "e7a8c3e2-8b9b-4d12-9f85-3c44d7f36a91",
  "medicoId": "f5b0c7d1-1a2f-4b33-8d66-2d1123c8b7e4",
  "fechaHora": "2026-07-10T10:30:00"
}
```

### Consultar disponibilidad

GET:
```text
/api/citas/available?medicoId=f5b0c7d1-1a2f-4b33-8d66-2d1123c8b7e4&fechaInicio=2026-07-10T08:00:00&fechaFin=2026-07-10T13:00:00
```

### Listar citas con filtros

GET:
```text
/api/citas?medicoId=f5b0c7d1-1a2f-4b33-8d66-2d1123c8b7e4&estado=PROGRAMADA
```

## Mini tutorial de Postman

1. Abre Postman y crea una nueva colección llamada `PruebaBackend`.
2. Define una variable de entorno llamada `baseUrl` con el valor:
   ```text
   http://localhost:8080
   ```
3. Crea las siguientes peticiones:

### 1. Crear paciente
- Método: `POST`
- URL: `{{baseUrl}}/api/pacientes`
- Body → `raw` → `JSON`
- Usa el JSON del ejemplo anterior.

### 2. Crear médico
- Método: `POST`
- URL: `{{baseUrl}}/api/medicos`
- Body → `raw` → `JSON`
- Usa el JSON del ejemplo anterior.

### 3. Crear cita
- Método: `POST`
- URL: `{{baseUrl}}/api/citas`
- Body → `raw` → `JSON`
- Usa el JSON del ejemplo anterior.
- Asegúrate de usar IDs válidos que existan en tu base de datos.

### 4. Consultar disponibilidad
- Método: `GET`
- URL: `{{baseUrl}}/api/citas/available?medicoId=<id-medico>&fechaInicio=2026-07-10T08:00:00&fechaFin=2026-07-10T13:00:00`

### 5. Cancelar cita
- Método: `POST`
- URL: `{{baseUrl}}/api/citas/<id-cita>/cancel`

### 6. Listar citas
- Método: `GET`
- URL: `{{baseUrl}}/api/citas`

## Notas importantes

- Las citas solo se pueden reservar en franjas de 30 minutos.
- Los horarios permitidos son:
  - Lunes a viernes: `08:00` a `18:00`
  - Sábados: `08:00` a `13:00`
  - Domingos y festivos: no hay atención
- Una cita cancelada con menos de 2 horas de anticipación genera una penalización para el paciente.
- Un paciente con 3 o más penalizaciones en los últimos 30 días no podrá agendar nuevas citas.

## Generar el paquete ejecutable

```bash
./mvnw clean package
```

El archivo `.jar` generado quedará en la carpeta `target/`.

