# GARSS-Geotagged Age-related Services System



### Start system using docker compose

<pre>docker-compose up -d</pre>

### Stop system
<pre>docker-compose down</pre>

### Look at logs
<pre>docker logs garss-postgis-1
docker logs garss-spring-1</pre>

### Postgis database

Database created using /postgis_garss/efda\_init.sql

Stored on volume garss_postgis_garss
Delete volume to start with fresh database

<pre>docker volume rm garss_postgis_garss</pre>

### Build app-server

<pre>cd app-server/chatbot
./mvnw clean package</pre>



## Getting project to compile and run on new system

1. Install Eclipse, docker and github desktop
2. (Windows) Need wsl2 installed
2. (Windows) May need to all docker access through firewall
3. (Windows) May need to change port to 8080
4. Need to install lombak on eclipse
5. Should install maven
6. Will need access to command line


