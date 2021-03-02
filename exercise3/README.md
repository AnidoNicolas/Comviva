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
Desde la carpeta raiz del proyecto, copiar el archivo .war generado en la carpeta target en la carpeta bin/webapps del Servidor tomcat. 

###Paso 4
Deployar la aplicación. Ubicados en la carpeta bin del tomcat ejecutar el siguiente comando: 
"startup.bat" desde windows o "startup.sh" desde linux. La aplicación se ejecutará en la siguiente url localhost:8080/comviva-project.

