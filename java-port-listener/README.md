# Java based Multi Port Listener (Dummy Listener)

## BUILD
```
javac MultiPortServer.java
jar cfm MultiPortServer.jar manifest.txt MultiPortServer.class MultiPortServer\$HelloHandler.class
```

## Execute Application
```
java -jar MultiPortServer.jar port_1 port_2 .... port_n
```
Here give the port number in the place of port_*
i.e.
```
java -jar MultiPortServer.jar 8080 8443 9090 3306
```
