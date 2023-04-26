# TestNGDemo_Java
Este proyecto consiste en la automatización del login de Secure Area ejecutado en Chrome y Firefox: https://the-internet.herokuapp.com/.

Se usó el framework de Selenium y TestNg, se utilizó el patrón Page Object Model, lenguaje de programación Java y el gestor de dependencias Maven.

Se ejecutó el proyecto con la versión Java: 11.0.16.1 y Maven con la versión: 3.8.6.

## Ejecución del proyecto
Para correr el proyecto de forma exitosa se debe descargar el Java y Maven en la máquina local y configurar las variables de entorno del sistema.

Para ejecutar las pruebas en el ambiente local, se debe ingresar al sitio: https://www.selenium.dev/downloads/ y descargar la última versión estable del archivo jar selenium-server.

Abrir un cmd en la ruta del archivo descargado y copiar el comando: ``` java -jar selenium-server-x.x.x.jar standalone ``` de acuerdo a la versión descargada.

Para verificar que el servidor está arriba, probar la dirección: http://localhost:4444/ y se debe abrir la página del selenium-server.

Seguidamente se debe descargar el proyecto del repositorio y ejecutar en la terminal del IDE (recomendado IntelliJ IDEA) el comando:

```mvn clean test```.

Apenas termine la ejecución se puede observar el reporte en la carpeta generada: ```target/reports/evidence/AutomationReport.html``` (abrir con navegador de preferencia).