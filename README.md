# Proyecto Final_Multi: Sistema de Gestión de Alquiler de Vehículos (Java NetBeans MVC)

Este documento describe el proyecto `Final_Multi`, una aplicación de escritorio desarrollada en Java utilizando el entorno NetBeans y siguiendo el patrón de arquitectura Modelo-Vista-Controlador (MVC). El sistema está diseñado para gestionar el alquiler de vehículos, incluyendo la administración de clientes, empleados, vehículos, mantenimientos y seguros.

## 1. Conexión a la Base de Datos

La aplicación se conecta a una base de datos MySQL llamada `alquiler_vehiculo`.

**Detalles de Conexión:**
*   **Host:** `localhost`
*   **Puerto:** `3306`
*   **Base de Datos:** `alquiler_vehiculo`
*   **Usuario:** `root`
*   **Contraseña:** `1234`

La configuración de la conexión se encuentra en el archivo `src/Modelo/ConectarBD.java`.

## 2. Arquitectura MVC

El proyecto sigue el patrón Modelo-Vista-Controlador para una separación clara de responsabilidades:

*   **Modelo (`src/Modelo/`)**: Contiene las clases de datos (POJOs) que representan las entidades de la base de datos (ej. `Cliente`, `Vehiculo`, `Usuario`) y las clases de Acceso a Datos (DAOs) que interactúan directamente con la base de datos (ej. `ClienteDAO`, `UsuarioDAO`, `VehiculoDAO`). También incluye clases auxiliares como `ComboBoxLoader` para cargar datos en los JComboBox.
*   **Vista (`src/Vista/`)**: Comprende todas las interfaces de usuario (formularios JFrame) que el usuario final ve e interactúa. Ejemplos incluyen `LoginView`, `FormularioCliente`, `FormularioCliente_1`, `FormularioLicencia`, `FormularioCodigoPostal`, `FormularioEmpleado`, `FormularioMantenimiento`, `FormularioSeguroV`.
*   **Controlador (`src/Controlador/MainController.java`)**: Es el cerebro de la aplicación. Maneja la lógica de negocio, procesa las entradas del usuario desde las Vistas, actualiza el Modelo y decide qué Vista mostrar. En este proyecto, se utiliza un único controlador central (`MainController`) para gestionar todas las interacciones.

## 3. Funcionalidades Implementadas

Hasta la fecha, las siguientes funcionalidades principales han sido implementadas:

*   **Inicio de Sesión:** Permite a los usuarios (Clientes, Empleados, Administradores) autenticarse en el sistema.
*   **Registro de Usuarios:**
    *   **Registro de Clientes:** Permite a nuevos clientes registrarse, incluyendo la validación y, si es necesario, el registro previo de licencias de conducción y códigos postales.
    *   **Registro de Empleados:** Permite registrar nuevos empleados en el sistema.
*   **Búsqueda de Vehículos:** Permite a los clientes buscar vehículos disponibles utilizando varios filtros (marca, tipo, transmisión, cilindraje, blindaje, sucursal, color).
*   **Registro de Mantenimiento:** Permite registrar mantenimientos para vehículos, asociándolos a un tipo de mantenimiento y un taller.
*   **Registro de Seguro de Vehículo:** Permite registrar información de seguros para vehículos.

## 4. Explicación de Consultas SQL "Raras" o Interesantes

El archivo `SQL.sql` define la estructura de la base de datos. Algunas consultas o sentencias SQL que podrían considerarse "raras" o que merecen una explicación son:

### a) Creación de la tabla `Usuario` con `id_referencia` flexible

```sql
CREATE TABLE Usuario (
  id_usuario        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  correo            VARCHAR(100) NOT NULL UNIQUE,
  contrasena        VARCHAR(100) NOT NULL,
  rol               ENUM('Cliente', 'Empleado', 'Administrador'),
  id_referencia     INT UNSIGNED, -- Cliente o Empleado
  FOREIGN KEY (id_referencia) REFERENCES Cliente(id_cliente) -- o Empleado según el rol
);
```
**Explicación:**
Esta tabla `Usuario` es interesante porque utiliza un campo `id_referencia` que puede apuntar a diferentes tablas (`Cliente` o `Empleado`) dependiendo del `rol` del usuario. En SQL puro, una `FOREIGN KEY` solo puede referenciar una tabla específica. La línea `-- o Empleado según el rol` es un comentario que indica una lógica de aplicación, no una restricción de base de datos. En la aplicación Java, esto se maneja a nivel de código: cuando se inserta un `Usuario`, el `id_referencia` se establece al `id_cliente` o `id_empleado` recién creado, y el `rol` se define explícitamente. Esto es un patrón común para manejar herencia de roles en bases de datos relacionales sin usar características más avanzadas como tablas de herencia.

### b) Actualización de estados de mantenimiento de vehículos (Inferido de `triple_db_manager.py`)

Aunque no está directamente en `SQL.sql`, la lógica de tu proyecto Python (`triple_db_manager.py`) incluye una consulta compleja para actualizar el estado de los vehículos después de un mantenimiento:

```sql
UPDATE Vehiculo SET id_estado_vehiculo = 1
WHERE id_estado_vehiculo = 3 AND placa IN
(SELECT placa FROM Mantenimiento WHERE fecha_fin <= %s);
```
**Explicación:**
Esta consulta `UPDATE` es "rara" por el uso de una **subconsulta (`SELECT placa FROM Mantenimiento WHERE fecha_fin <= %s`)** dentro de la cláusula `WHERE` con el operador `IN`.

*   `id_estado_vehiculo = 1`: Asume que `1` representa el estado "Disponible" para un vehículo.
*   `id_estado_vehiculo = 3`: Asume que `3` representa el estado "En Mantenimiento".
*   La subconsulta `SELECT placa FROM Mantenimiento WHERE fecha_fin <= %s` selecciona todas las placas de vehículos que han terminado su mantenimiento (donde `fecha_fin` es menor o igual a la fecha actual).
*   La cláusula `placa IN (...)` filtra los vehículos que están actualmente "En Mantenimiento" (`id_estado_vehiculo = 3`) y cuya placa aparece en la lista de vehículos que han terminado su mantenimiento.

En resumen, esta consulta identifica los vehículos que estaban en mantenimiento y cuya fecha de fin de mantenimiento ya pasó, y los actualiza a un estado "Disponible".

### c) Consultas de Inserción con `RETURN_GENERATED_KEYS` (Ejemplo de `ClienteDAO.java`)

```java
// En ClienteDAO.java (y otros DAOs de inserción)
String sql = "INSERT INTO Cliente (...) VALUES (...)";
PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
// ... ejecutar update ...
ResultSet rs = ps.getGeneratedKeys();
if (rs.next()) {
    cliente.setIdCliente(rs.getInt(1)); // Asignar el ID generado
}
```
**Explicación:**
Aunque no es una consulta SQL en sí misma, el uso de `Statement.RETURN_GENERATED_KEYS` al preparar la sentencia `INSERT` es una característica importante de JDBC.

*   Normalmente, una sentencia `INSERT` no devuelve ningún resultado. Sin embargo, cuando se especifica `Statement.RETURN_GENERATED_KEYS`, la base de datos (si lo soporta, como MySQL con `AUTO_INCREMENT`) devuelve las claves generadas automáticamente (como el `id_cliente` autoincremental).
*   `ps.getGeneratedKeys()` recupera estas claves generadas como un `ResultSet`.
*   `rs.next()` y `rs.getInt(1)` se utilizan para obtener el primer (y generalmente único) ID generado y asignarlo al objeto `Cliente` en Java. Esto es crucial para que la aplicación conozca el ID del nuevo registro en la base de datos, especialmente si se necesita para operaciones posteriores (como vincular un `Usuario` a un `Cliente` recién creado).

## 5. Cómo Ejecutar el Proyecto

1.  **Configurar MySQL:** Asegúrate de tener un servidor MySQL corriendo y haber creado la base de datos `alquiler_vehiculo` utilizando el script `SQL.sql` proporcionado.
2.  **Abrir en NetBeans:** Abre el proyecto `Final_BDD_Java_NetBeans` en NetBeans.
3.  **Añadir Driver JDBC:** Asegúrate de que el driver JDBC de MySQL esté añadido a las librerías del proyecto en NetBeans. Si no lo tienes, puedes descargarlo (por ejemplo, `mysql-connector-java-8.0.xx.jar`) y añadirlo a las librerías del proyecto.
4.  **Compilar y Ejecutar:** Ejecuta la clase `MainApp.java` (clic derecho -> Run File).

La aplicación de inicio de sesión aparecerá. Puedes probar el registro de clientes y empleados, y la búsqueda de vehículos.
