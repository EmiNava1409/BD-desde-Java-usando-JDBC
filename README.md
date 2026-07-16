
# Conexión a Bases de Datos desde Java usando JDBC

## Introducción
La conexión entre una aplicación y una base de datos es uno de los aspectos fundamentales en el desarrollo de software. En Java, esta comunicación se realiza mediante **JDBC (Java Database Connectivity)**, una API estándar que permite interactuar con diferentes sistemas gestores de bases de datos utilizando sentencias SQL.

En este proyecto se desarrolla un ejemplo práctico utilizando **PostgreSQL** como gestor de base de datos. La aplicación implementa la conexión mediante las clases **DriverManager**, **Connection**, **Statement** y **ResultSet**, demostrando el flujo completo desde el establecimiento de la conexión hasta la recuperación de la información almacenada.

Para representar un escenario cercano a una aplicación real, se implementó un pequeño sistema hospitalario encargado de consultar la información de pacientes registrados en la base de datos.



## Objetivo del proyecto

Implementar una aplicación Java que establezca una conexión con PostgreSQL utilizando la API JDBC, demostrando el funcionamiento de las clases principales que intervienen en la comunicación con la base de datos y aplicando buenas prácticas como el manejo de excepciones y el cierre automático de recursos mediante **try-with-resources**.



## Fundamentos teóricos

### ¿Qué es JDBC?

JDBC (Java Database Connectivity) es una API estándar de Java que permite conectar aplicaciones con bases de datos relacionales.

Gracias a JDBC es posible:

* Establecer conexiones.
* Ejecutar consultas SQL.
* Recuperar resultados.
* Modificar información.
* Gestionar transacciones.

Una de sus principales ventajas es que funciona con diferentes motores de bases de datos, como PostgreSQL, MySQL, Oracle y SQL Server, cambiando únicamente el controlador JDBC correspondiente.


## Componentes de JDBC

### - DriverManager

Es la clase encargada de localizar el controlador JDBC adecuado y establecer la conexión inicial entre la aplicación y la base de datos mediante la URL de conexión, el usuario y la contraseña.

En este proyecto se utiliza para conectarse a PostgreSQL.


### - Connection

Representa la sesión activa entre la aplicación Java y la base de datos.

A partir de este objeto pueden ejecutarse consultas SQL, controlar transacciones y cerrar la conexión cuando el trabajo ha finalizado.


### - Statement

Permite enviar instrucciones SQL simples al servidor de base de datos.

En este proyecto se utiliza para ejecutar la consulta:

```sql
SELECT * FROM pacientes;
```

### - ResultSet

Es el objeto que almacena el resultado de una consulta SQL.

Cada registro puede recorrerse mediante el método:

```java
resultado.next();
```

permitiendo acceder a cada columna de la tabla.


## Flujo de funcionamiento

El funcionamiento de JDBC sigue el siguiente proceso:

```text
DriverManager
        │
        ▼
Connection
        │
        ▼
Statement
        │
        ▼
ResultSet
        │
        ▼
Información mostrada en consola
```

Cada componente cumple una función específica dentro del proceso de comunicación entre Java y PostgreSQL.


## Escenario implementado

Para demostrar el funcionamiento de JDBC se desarrolló un sistema hospitalario sencillo.

La base de datos contiene una tabla denominada **pacientes**, donde se almacena información como:

* Identificador.
* Nombre.
* Edad.
* Diagnóstico.
* Habitación.
* Estado.

La aplicación establece la conexión con PostgreSQL y consulta toda la información almacenada para posteriormente mostrarla organizada en la consola y generar un resumen con el número total de pacientes registrados.



## Tecnologías utilizadas

* Java 17
* PostgreSQL
* JDBC
* Driver PostgreSQL 42.7.13
* SQL
* Visual Studio Code



## Estructura del proyecto

```text
ENTREGA_INDIVIDUAL_NAVARRETE/

├── Codigo/
│   └── ProyectoJDBC_Individual/
│       ├── lib/
│       ├── src/
│       └── scripts/
│
├── Documentacion/
│
├── Ejercicios/
│
│
└── README.md
```


## Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/USUARIO/ProyectoJDBC_Individual.git
```


### 2. Crear la base de datos

Ejecutar el archivo:

```text
scripts/database_script.sql
```

Este archivo crea:

* Base de datos `actividad_jdbc`
* Tabla `pacientes`
* Registros de prueba


### 3. Configurar la conexión

Verificar los datos de conexión:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/actividad_jdbc";
private static final String USUARIO = "emiliaNava";
private static final String CONTRASENA = "root";
```


### 4. Compilar y ejecutar

Para garantizar que el proyecto se encuentra correctamente configurado, compílalo desde la terminal antes de ejecutarlo:

```text
mvn clean compile
```

Si el resultado es **BUILD SUCCESS**: El proyecto está listo para ejecutarse.

Ejecución: Abre la clase principal en Visual Studio Code: 
```text
src/main/java/com/demo/jdbc/conexionPostgreSQLDemo.java
```
y presiona el botón Run (Ejecutar) que aparece sobre el método main.


## Explicación del código

### Establecimiento de la conexión

La conexión se realiza mediante:

```java
DriverManager.getConnection(...)
```


### Creación del Statement

Se genera un objeto `Statement` encargado de enviar instrucciones SQL.



### Ejecución de la consulta

```sql
SELECT * FROM pacientes;
```


### Obtención del ResultSet

La consulta devuelve un objeto `ResultSet`, que contiene todos los registros recuperados.


### Recorrido de resultados

Los datos se recorren mediante:

```java
while(resultado.next())
```

y posteriormente se imprimen en consola.


### Cierre de recursos

El proyecto utiliza **try-with-resources**, garantizando el cierre automático de:

* Connection
* Statement
* ResultSet

incluso cuando ocurre una excepción.


## Buenas prácticas implementadas

Durante el desarrollo del proyecto se aplicaron las siguientes buenas prácticas:

* Uso de `try-with-resources`.
* Manejo de excepciones mediante `try-catch`.
* Comentarios en español.
* Código organizado y legible.
* Uso exclusivo de JDBC sin frameworks ORM.
* Separación de las credenciales mediante constantes.


## Posibles errores

* Error de autenticación.
* PostgreSQL detenido.
* Driver JDBC no encontrado.
* Tabla inexistente.
* Puerto incorrecto.


## Adaptabilidad a otros gestores de bases de datos (MySQL)

Gracias a que JDBC es una API estándar de Java, este proyecto puede migrarse fácilmente a otros motores como MySQL. Para realizar este cambio, solo se requieren dos ajustes:

* ### 1. Dependencia (pom.xml).
Reemplazar el driver de PostgreSQL por el conector oficial de MySQL (mysql-connector-j).

* ### 2. URL de conexión: ### 
Actualizar la constante URL en el código fuente:

```text
MySQL: jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
```

Toda la lógica de manejo de conexiones (DriverManager, Connection, Statement, ResultSet) y las buenas prácticas (try-with-resources) permanecen exactamente iguales, demostrando la independencia del motor de base de datos que ofrece JDBC.


## Evidencias

### Imagen 1. Base de datos creada

<img width="273" height="247" alt="image" src="https://github.com/user-attachments/assets/3088f916-e1e0-4215-b11b-da5ffd439aad" />

### Imagen 2. Tabla pacientes

<img width="737" height="362" alt="image" src="https://github.com/user-attachments/assets/8550b5ef-e59e-4dad-be9e-586a65e46ba3" />

### Imagen 3. Resultado Tabla pacientes

<img width="967" height="265" alt="image" src="https://github.com/user-attachments/assets/d8811441-1722-4ab3-b866-fc43695ec393" />


### Imagen 4. Configuración del proyecto

<img width="430" height="665" alt="image" src="https://github.com/user-attachments/assets/17ce1626-b3be-48e1-b127-e938cc1fabed" />


### Imagen 5. Análisis del Código Fuente

<img width="1351" height="935" alt="image" src="https://github.com/user-attachments/assets/17b830b8-b1fe-48b2-9e49-7767c5448ef9" />
Implementación de conexión JDBC con try-with-resources para el acceso seguro a la tabla de pacientes.

### Imagen 6. Resumen hospitalario

<img width="906" height="427" alt="image" src="https://github.com/user-attachments/assets/ba92d470-f925-4090-bd58-3cb62795834a" />

### Imagen 7. Validación de compilación:

<img width="895" height="836" alt="image" src="https://github.com/user-attachments/assets/a22420e6-4a86-4989-8093-72cb7c4205b9" />

Se observa la compilación exitosa del proyecto mediante Maven, confirmando que las dependencias JDBC están correctamente configuradas y el código fuente no presenta errores de sintaxis


## Conclusión

Este proyecto demuestra el funcionamiento de JDBC como mecanismo estándar para conectar aplicaciones Java con bases de datos relacionales. Mediante el uso de las clases `DriverManager`, `Connection`, `Statement` y `ResultSet`, fue posible establecer una conexión con PostgreSQL, ejecutar consultas SQL y recuperar información de manera estructurada.

Además, se aplicaron buenas prácticas como el uso de `try-with-resources` y el manejo de excepciones, garantizando una administración adecuada de los recursos y un código más seguro y mantenible. Este ejemplo constituye una base sólida para comprender cómo interactúan las aplicaciones Java con una base de datos antes de incorporar tecnologías de mayor nivel como los frameworks ORM.


## Autora

**María Emilia Navarrete Ávila**

Estudiante de Ingeniería en Software  
Pontificia Universidad Católica del Ecuador – Sede Manabí

Proyecto académico desarrollado para la asignatura **Prácticas de Gestión de la Información**.


