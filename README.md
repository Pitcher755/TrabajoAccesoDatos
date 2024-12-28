# Gestión de Contratos - Procesamiento y Exportación de Datos

Este proyecto es una aplicación en Java diseñada para gestionar datos de contratos adjudicados a través de la lectura, validación y almacenamiento de información desde un archivo XML. Además, genera un archivo XML de salida adaptado a las especificaciones requeridas, excluyendo información sensible o innecesaria.

## Funcionalidades principales

- **Conexión a la base de datos**: Uso de JDBC para conectarse a un SGBD MySQL y realizar operaciones CRUD.
- **Procesamiento de datos**: Lectura de archivos XML, normalización de formatos de fechas y precios, y validación de datos antes de almacenarlos.
- **Generación de XML**: Creación de un archivo XML de salida basado en los datos procesados y almacenados, excluyendo campos no requeridos.
- **Manejo de errores**: Gestión robusta de excepciones para garantizar la estabilidad del programa ante datos incorrectos o fallos en la base de datos.
- **Pruebas unitarias**: Implementación de pruebas automatizadas con JUnit para garantizar la calidad del código.

## Tecnologías utilizadas

- **Lenguaje de programación**: Java 17.
- **Base de datos**: MySQL.
- **Frameworks/Librerías**: JUnit 5, DOM para manipulación de XML.
- **Herramientas adicionales**: Git, Maven.

## Estructura del proyecto

- **`AccesoDatos`**: Clases para la conexión a la base de datos y la gestión de operaciones CRUD.
- **`Servicios`**: Lógica para procesar archivos XML, manejar datos y coordinar el flujo completo del programa.
- **`ModeloContrato`**: Clases que definen la estructura de los contratos.
- **`Main`**: Punto de entrada del programa.
- **`Pruebas`**: Conjunto de pruebas unitarias para cada funcionalidad principal.
