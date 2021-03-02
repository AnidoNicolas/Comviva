# Instalación y ejecución de la aplicación

### Paso 1
Configurar las credenciales de la DB desde el application.properties ubicado en el directorio /src/main/resources. Deberá existir un esquema "comviva" a partir de la cual generar la tabla de la aplicación.  Ejecutar la instancia local de Mysql.

Ejemplo: 
spring.datasource.url=jdbc:mysql://localhost:3306/comviva?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

### Paso 2
Generar el archivo jar con Maven. Para este proposito, desde el directorio raiz del proyecto ejecutaremos el siguiente comando: 
"mvn -DskipTests clean install"

### Paso 3
Ejecutar el jar generado. Ubicados en la carpeta raiz del proyecto, ejecutar el siguiente comando:
"java -jar target/exercise3-0.0.1-SNAPSHOT.jar"

###Paso 4
Desde navegador, ejecutar la aplicación desde localhost:8080

