
# Guía Práctica
## Conexión a Bases de Datos desde Java usando JDBC

### Ejercicio práctico: Inventario de Librería "Book - Literario"

## Objetivo

Implementar una aplicación Java que establezca una conexión con una base de datos PostgreSQL utilizando la API JDBC. Durante la práctica se utilizarán las clases **DriverManager**, **Connection**, **Statement** y **ResultSet** para consultar la información almacenada en una tabla de libros y comprender el funcionamiento básico de la conexión entre Java y una base de datos relacional.

---

## Materiales necesarios

Antes de comenzar la práctica, asegúrese de contar con lo siguiente:

- Java JDK 17 o superior.
- PostgreSQL instalado y en ejecución.
- pgAdmin.
- Visual Studio Code con la extensión de Java.
- Driver JDBC de PostgreSQL (`postgresql-42.7.13.jar`).

---

## Parte 1. Creación de la base de datos

1. Abra pgAdmin.
2. Cree una base de datos llamada **inventario_libreria**.
3. Abra el **Query Tool** y ejecute el siguiente script:

```sql
CREATE TABLE libros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    precio DECIMAL(10,2)
);

INSERT INTO libros (titulo, autor, precio)
VALUES
('Cien años de soledad','Gabriel García Márquez',25.50),
('Don Quijote','Miguel de Cervantes',15.00),
('La Odisea','Homero',20.00);
```

---

## Parte 2. Configuración del proyecto

Abra el archivo `Solucion_Libreria.java` y configure los datos de conexión:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/inventario_libreria";
private static final String USUARIO = "TU_USUARIO";
private static final String CONTRASENA = "TU_CONTRASENA";
```

Verifique que el controlador JDBC de PostgreSQL se encuentre agregado al proyecto.

---

## Parte 3. Ejecución del programa

1. Abra el proyecto en Visual Studio Code.
2. Ejecute la clase `Solucion_Libreria.java`.
3. Espere a que la aplicación establezca la conexión con PostgreSQL.

---

## Resultado esperado

Al finalizar la ejecución, la aplicación deberá:

- Establecer correctamente la conexión con PostgreSQL.
- Mostrar el listado de libros registrados.
- Calcular y presentar el precio promedio de los libros almacenados en la base de datos.

---

## Componentes JDBC utilizados

Durante la práctica identifique en el código los siguientes componentes:

| Componente | Función |
|------------|---------|
| DriverManager | Establece la conexión con la base de datos. |
| Connection | Representa la sesión activa con PostgreSQL. |
| Statement | Ejecuta las instrucciones SQL. |
| ResultSet | Recupera los resultados de la consulta. |

---

## Preguntas de reflexión

1. ¿Qué función cumple DriverManager dentro de JDBC?

**Respuesta:** DriverManager localiza el controlador JDBC adecuado y establece la conexión entre la aplicación Java y la base de datos.

2. ¿Qué representa un objeto Connection?

**Respuesta:** Representa la sesión activa entre la aplicación y la base de datos, permitiendo ejecutar operaciones SQL.

3. ¿Qué diferencia existe entre Statement y ResultSet?

**Respuesta:** Statement ejecuta las consultas SQL, mientras que ResultSet almacena y permite recorrer los resultados obtenidos.

4. ¿Qué ocurriría si no se utilizara try-with-resources?

**Respuesta:** Los recursos deberían cerrarse manualmente; de lo contrario, podrían producirse fugas de memoria y conexiones abiertas innecesariamente.

5. ¿Por qué JDBC necesita un driver específico para PostgreSQL?

**Respuesta:** Porque el driver traduce las instrucciones JDBC al protocolo de comunicación que entiende PostgreSQL.

---

## Actividad adicional

Modifique el programa para que:

- Muestre únicamente los libros cuyo precio sea mayor a **20 dólares**.
- Ordene los resultados alfabéticamente por el título del libro.
````

### Un detalle importante

En tu versión original dices:

> "Compila con `javac Solucion_Libreria.java` y ejecuta con `java Solucion_Libreria`."

Pero tu clase pertenece al paquete:

```java
package Ejercicios.Guia_Practica_JDBC;
```

Por lo tanto, esos comandos no funcionarían directamente si se respeta la estructura del proyecto. Como la práctica está pensada para usarse en **Visual Studio Code**, es mejor indicar simplemente que se abra el proyecto y se ejecute la clase `Solucion_Libreria.java`. Eso evita confusiones y hace que la guía sea correcta.
