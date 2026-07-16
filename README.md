
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

*(Captura de pgAdmin mostrando la base de datos `actividad_jdbc`.)*

### Imagen 2. Tabla pacientes

*(Captura de la tabla `pacientes` con los registros insertados.)*

### Imagen 3. Configuración del proyecto

*(Captura de Visual Studio Code mostrando el proyecto y el driver JDBC.)*

### Imagen 4. Conexión exitosa

*(Captura de la consola indicando que la conexión se estableció correctamente.)*

### Imagen 5. Consulta realizada

*(Captura del listado de pacientes obtenido mediante `ResultSet`.)*

### Imagen 6. Resumen hospitalario

*(Captura mostrando el total de pacientes consultados.)*

### Imagen 7. Validación de compilación:



## Conclusión

Este proyecto demuestra el funcionamiento de JDBC como mecanismo estándar para conectar aplicaciones Java con bases de datos relacionales. Mediante el uso de las clases `DriverManager`, `Connection`, `Statement` y `ResultSet`, fue posible establecer una conexión con PostgreSQL, ejecutar consultas SQL y recuperar información de manera estructurada.

Además, se aplicaron buenas prácticas como el uso de `try-with-resources` y el manejo de excepciones, garantizando una administración adecuada de los recursos y un código más seguro y mantenible. Este ejemplo constituye una base sólida para comprender cómo interactúan las aplicaciones Java con una base de datos antes de incorporar tecnologías de mayor nivel como los frameworks ORM.


## Autora

**María Emilia Navarrete Ávila**

Estudiante de Ingeniería en Software  
Pontificia Universidad Católica del Ecuador – Sede Manabí

Proyecto académico desarrollado para la asignatura **Prácticas de Gestión de la Información**.


